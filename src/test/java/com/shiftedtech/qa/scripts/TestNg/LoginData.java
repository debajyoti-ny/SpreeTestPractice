package com.shiftedtech.qa.scripts.TestNg;

/**
 * POJO (Plane Old Java Object Class)
 */

public class LoginData {
    private String id;
    private String description;
    private String email;
    private String password;
    private boolean skipped;

    public LoginData(){

    }

    public LoginData(String id, String description, String email, String password) {
        this.id = id;
        this.description = description;
        this.email = email;
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isSkipped() {
        return skipped;
    }

    public void setSkipped(boolean skipped) {
        this.skipped = skipped;
    }

    @Override
    public String toString() {
        return "LoginData{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", skipped=" + skipped +
                '}';
    }
}
