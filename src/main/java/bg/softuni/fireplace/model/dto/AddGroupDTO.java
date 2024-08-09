package bg.softuni.fireplace.model.dto;

import bg.softuni.fireplace.model.entity.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;

import java.util.Set;

public class AddGroupDTO {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "members")
    private Set<UserEntity> users;

    public AddGroupDTO() {
    }

    public static AddGroupDTO empty() {
        return new AddGroupDTO();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }
}
