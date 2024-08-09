package bg.softuni.fireplace.web;

import bg.softuni.fireplace.service.ArticleService;
import bg.softuni.fireplace.service.GroupService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/groups")
public class GroupsController {

    private final GroupService groupService;

    public GroupsController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping("/all")
    public String getAllGroups(Model model) {

        model.addAttribute("allGroups", groupService.getAllGroupDetails());
        return "group/all-groups";
    }
}
