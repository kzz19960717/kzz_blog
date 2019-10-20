package com.kzz.blog.service;

import com.kzz.blog.entity.Doc;
import com.kzz.blog.entity.User;

import javax.servlet.http.HttpSession;

public interface IUserService {
    void reg(User user);
    User login(String username,String password);
    void changePassword(String username,String newPassword,String oldPassword);
    void updateDoc(Doc doc);
    Doc selectDocs(Integer uid);
    String selectByUid(Integer uid);
    void changeAvatar(Integer uid, String username, String avatar);
    void forgotPassword(String username,String password,String email,String phone);
}
