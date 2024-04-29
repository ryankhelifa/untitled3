package com.exemple.model;

public class User {

    private Long userId;
    private String name;
    private String familyName;
    private String email;
    private String password;
    private boolean isAdmin = false;

    public User() {

    }
    public User(Long userId, String name, String familyName, String email, String password, boolean isAdmin) {
        this.userId = userId;
        this.name = name;
        this.familyName = familyName;
        this.email = email;
        this.password = password;
        this.isAdmin = isAdmin;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFamilyName() {
        return familyName;
    }
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public boolean isAdmin() {
        return isAdmin;
    }
    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", familyName='" + familyName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isAdmin=" + isAdmin +
                '}';
    }



}