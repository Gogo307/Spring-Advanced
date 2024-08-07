package bg.softuni.fireplace.model.dto;


import jakarta.validation.constraints.*;

public class UserRegistrationDTO {

    @NotEmpty
    @Size(min = 5, max = 20)
    private String username;

    @NotEmpty
    private String password;

    @NotEmpty
    @Email
    private String email;

    @Min(1)
    @Max(119)
    private Integer age;

    @Override
    public String toString() {
        return "UserRegistrationDTO{" +
                "username='" + username + '\'' +
                ", password='" + (password == null ? "N/A" : "[PROVIDED]") + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
