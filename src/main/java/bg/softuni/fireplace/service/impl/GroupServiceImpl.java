package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.AddGroupDTO;
import bg.softuni.fireplace.model.dto.GroupDetailsDTO;
import bg.softuni.fireplace.model.entity.GroupEntity;
import bg.softuni.fireplace.repository.GroupRepository;
import bg.softuni.fireplace.service.GroupService;
import bg.softuni.fireplace.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(ModelMapper modelMapper, GroupRepository groupRepository) {
        this.modelMapper = modelMapper;
        this.groupRepository = groupRepository;
    }

    @Override
    public AddGroupDTO addGroup(AddGroupDTO addGroupDTO) {

        GroupEntity group = this.modelMapper.map(addGroupDTO, GroupEntity.class);
        this.groupRepository.saveAndFlush(group);
        return this.modelMapper.map(group, AddGroupDTO.class);

    }

    @Override
    public GroupDetailsDTO findGroupById(Long id) {
        return this.groupRepository.findById(id)
                .map(g -> {
                    GroupDetailsDTO groupDetailsDTO = this.modelMapper.map(g, GroupDetailsDTO.class);
                    this.groupRepository.findById(groupDetailsDTO.getId());

                    return groupDetailsDTO;
                })
                .orElseThrow(() -> new ObjectNotFoundException("Group with the given id was not found!",404));
    }

    @Override
    public List<GroupDetailsDTO> findAllGroups() {
        return this.groupRepository.findAll()
                .stream()
                .map(group -> this.modelMapper.map(group, GroupDetailsDTO.class))
                .collect(Collectors.toList());
    }
}
