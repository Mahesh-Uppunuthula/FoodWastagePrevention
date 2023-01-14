package com.example.foodwastageprevention;

public class UserHelper {
    String userId;
    String ownership;
    String fName;
    String lName;

    public UserHelper(String userId, String ownership, String fName, String lName) {
        this.userId = userId;
        this.ownership = ownership;
        this.fName = fName;
        this.lName = lName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOwnership() {
        return ownership;
    }

    public void setOwnership(String ownership) {
        this.ownership = ownership;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }
}
