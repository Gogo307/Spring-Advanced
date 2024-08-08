package bg.softuni.fireplace.service;

import bg.softuni.fireplace.model.dto.AddGroupDTO;
import bg.softuni.fireplace.model.dto.GroupDetailsDTO;

import java.util.List;

public interface GroupService {

    AddGroupDTO addGroup(AddGroupDTO addGroupDTO);

    GroupDetailsDTO findGroupById(Long id);

    List<GroupDetailsDTO> findAllGroups();

}
