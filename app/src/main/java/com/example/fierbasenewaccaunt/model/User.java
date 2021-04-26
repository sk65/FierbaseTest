package com.example.fierbasenewaccaunt.model;

public class User {
    String id;
    String email;
    String name;
    String imgUri;

    public User(String email, String name,  String imgUri) {
        this.email = email;
        this.name = name;
        this.imgUri = imgUri;
    }
    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUri() {
        return imgUri;
    }

    public void setImgUri(String imgUri) {
        this.imgUri = imgUri;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", imgUri='" + imgUri + '\'' +
                '}';
    }
}
