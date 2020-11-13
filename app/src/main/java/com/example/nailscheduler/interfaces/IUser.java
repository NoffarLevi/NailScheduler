package com.example.nailscheduler.interfaces;


import com.example.nailscheduler.enums.UserType;

public interface IUser {
    String getEmail();
    void setEmail(String email);
    String getFullName();
    void setFullName(String fullName);
    UserType getType();
    void setType(UserType type);
}
