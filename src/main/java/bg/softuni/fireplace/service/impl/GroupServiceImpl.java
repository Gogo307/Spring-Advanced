package bg.softuni.fireplace.service.impl;

import bg.softuni.fireplace.model.dto.AddGroupDTO;
import bg.softuni.fireplace.model.dto.ArticleDetailsDTO;
import bg.softuni.fireplace.model.dto.GroupDetailsDTO;
import bg.softuni.fireplace.model.entity.GroupEntity;
import bg.softuni.fireplace.repository.GroupRepository;
import bg.softuni.fireplace.service.GroupService;
import bg.softuni.fireplace.service.exception.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {

    private final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    private final RestClient groupRestClient;
    private final ModelMapper modelMapper;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(@Qualifier("groupRestClient")RestClient groupRestClient, ModelMapper modelMapper, GroupRepository groupRepository) {
        this.groupRestClient = groupRestClient;
        this.modelMapper = modelMapper;
        this.groupRepository = groupRepository;
    }

    @Override
    public AddGroupDTO addGroup(AddGroupDTO addGroupDTO) {

        GroupEntity group = this.modelMapper.map(addGroupDTO, GroupEntity.class);
        try{
            this.groupRepository.saveAndFlush(group);
            return this.modelMapper.map(group, AddGroupDTO.class);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

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
    public GroupDetailsDTO getGroupDetails(Long id) {
        return groupRestClient
                .get()
                .uri("/groups/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(GroupDetailsDTO.class);
    }

    @Override
    public List<GroupDetailsDTO> getAllGroupDetails() {
        LOGGER.info("Get all groups...");

        return groupRestClient
                .get()
                .uri("/groups")
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }
/*
    @Override
    public List<GroupDetailsDTO> findAllGroups() {
        return this.groupRepository.findAll()
                .stream()
                .map(group -> this.modelMapper.map(group, GroupDetailsDTO.class))
                .collect(Collectors.toList());
    }

 */
}
