package ro.academyplus.model.dto;

import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Required;
import ro.academyplus.model.dto.validators.FieldMatchAnnotation;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@FieldMatchAnnotation.List({
        @FieldMatchAnnotation(first = "password", second = "confirmPassword", message = "The password fields must match"),
        @FieldMatchAnnotation(first = "email", second = "confirmEmail", message = "The email fields must match")
})

public class UserDTO {
    @Size(min = 1, max = 30, message = "Name field must have at least one character")
    private String name;
    @Size(min = 4, max = 24,message = "Password must be between 4 and 24 characters")
    private String password;
    private String confirmPassword;
    @Size(min = 3, max = 30, message = "Email field must be at least 3 characters long")
    @Email
    private String email;
    private String confirmEmail;

    public UserDTO() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getConfirmEmail() {
        return confirmEmail;
    }

    public void setConfirmEmail(String confirmEmail) {
        this.confirmEmail = confirmEmail;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", email='" + email + '\'' +
                ", confirmEmail='" + confirmEmail + '\'' +
                '}';
    }
}