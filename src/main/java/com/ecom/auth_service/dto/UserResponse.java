package com.ecom.auth_service.dto;

import java.time.LocalDate;

public class UserResponse {

    private Long id;
    private String name;
    private String email;
    private String role;

    private String mobileNumber;
    private String gender;
    private LocalDate birthday;
    private String alternateMobileNumber;
    private String hintName;


    public UserResponse() {
    }


    public UserResponse(
            Long id,
            String name,
            String email,
            String role,
            String mobileNumber,
            String gender,
            LocalDate birthday,
            String alternateMobileNumber,
            String hintName) {

        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.mobileNumber = mobileNumber;
        this.gender = gender;
        this.birthday = birthday;
        this.alternateMobileNumber = alternateMobileNumber;
        this.hintName = hintName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }


    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }


    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }


    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }


    public String getAlternateMobileNumber() {
        return alternateMobileNumber;
    }

    public void setAlternateMobileNumber(String alternateMobileNumber) {
        this.alternateMobileNumber = alternateMobileNumber;
    }


    public String getHintName() {
        return hintName;
    }

    public void setHintName(String hintName) {
        this.hintName = hintName;
    }
}