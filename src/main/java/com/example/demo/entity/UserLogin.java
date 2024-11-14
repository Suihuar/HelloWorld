package com.example.demo.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
import java.sql.Date;

@Entity
public class UserLogin {
    @Id // primary key
    @GeneratedValue(strategy= GenerationType.IDENTITY) // match with auto increment
    private Integer id;

    @Size(min = 8, max = 15)
    private String password;

    @NotBlank
    @Email
    private String email;

    // getters and setters...
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}