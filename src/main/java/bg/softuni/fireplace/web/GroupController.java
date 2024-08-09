package bg.softuni.fireplace.web;

import bg.softuni.fireplace.model.dto.AddArticleDTO;
import bg.softuni.fireplace.model.dto.AddCommentDTO;
import bg.softuni.fireplace.model.dto.AddGroupDTO;
import bg.softuni.fireplace.model.dto.GroupDetailsDTO;
import bg.softuni.fireplace.service.GroupService;
import bg.softuni.fireplace.service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/groups")
public class GroupController {
    private final ModelMapper modelMapper;
    private final GroupService groupService;
    private final UserService userService;

    @Autowired
    public GroupController(ModelMapper modelMapper, GroupService groupService, UserService userService) {
        this.modelMapper = modelMapper;
        this.groupService = groupService;
        this.userService = userService;
    }

    @GetMapping("/add")
    public String newGroup(Model model) {
        if (!model.containsAttribute("addGroupDTO")) {
            model.addAttribute("addGroupDTO", AddGroupDTO.empty());
        }

        return "group/add-group";
    }

    @PostMapping("add")
    public String addGroup(@Valid AddGroupDTO addGroupDTO, BindingResult bindingResult, RedirectAttributes rAtt) {

        if (bindingResult.hasErrors()) {
            rAtt.addFlashAttribute("addGroupDTO", addGroupDTO);
            rAtt.addFlashAttribute("org.springframework.validation.BindingResult.addGroupDTO", bindingResult);
            return "redirect:/all-groups";
        }

        AddGroupDTO a = this.modelMapper.map(addGroupDTO, AddGroupDTO.class);

        this.groupService.addGroup(a);
        if (a == null) {
            throw new IllegalArgumentException("Group creation failed!");
        }

        return "redirect:/all-groups";
    }

    @GetMapping("/data/{id}")
    public String GroupData(@PathVariable("id") Long id, Model model) {

        model.addAttribute("articleDetails", groupService.getGroupDetails(id));
        return "group/group-details";
    }
/*
    @GetMapping("/join/{id}")
    public ModelAndView joinGroup(@PathVariable(name = "id") String id, ModelAndView modelAndView, Principal principal) {
        GroupDetailsViewModel groupDetailsViewModel = this.modelMapper.map(this.groupService.findGroupById(id), GroupDetailsViewModel.class);

        User user = this.modelMapper.map(this.userService.findUserByUserName(principal.getName()), User.class);
        user.setGroup(this.modelMapper.map(groupDetailsViewModel, Group.class));
        this.userService.editUser(user.getId(), this.modelMapper.map(user, UserServiceModel.class));
        modelAndView.addObject("group", groupDetailsViewModel);

        return super.view("group/join-group", modelAndView);
    }

 */
}
