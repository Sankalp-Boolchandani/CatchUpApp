package com.sankalp.chatapp.Models;

public class Users {

    String userName, mail, password, userId, profilePic, lastMessage;

    public Users(){}

    public Users(String userName, String mail, String password, String userId, String profilePic, String lastMessage) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
        this.userId = userId;
        this.profilePic = profilePic;
        this.lastMessage = lastMessage;
    }

    //for firebase signup!!
    public Users(String userName, String mail, String password) {
        this.userName = userName;
        this.mail = mail;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }
}
