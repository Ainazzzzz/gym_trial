package peaksoft.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.*;
import peaksoft.dao.UserDao;
import peaksoft.entity.additional.Id;
import peaksoft.service.implService.UserServiceImpl;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Users")
@Data
@NamedQuery(
        name = "User.checkCredentials",
        query = "FROM User u WHERE u.username = :username AND u.password = :password"
)
public class User extends Id {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private boolean isActive;
    private UserServiceImpl userService;

    public void setFirstAndLastName(String first_name, String last_name) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.username = generateUsername();
        this.password = generatePassword();
    }

    private String generateUsername() {
        String baseUsername = firstName + "." + lastName;
        String username = baseUsername;
        int serialNumber = 1;

        while (userService.usernameExists(username)) {
            serialNumber++;
            username = baseUsername + "." + serialNumber;
        }

        return username;
    }

    private String generatePassword() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 10; i++) {
            password.append(characters.charAt(random.nextInt(characters.length())));
        }

        return password.toString();
    }


    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
