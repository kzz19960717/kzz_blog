package com.kzz.blog.service.impl;

import com.kzz.blog.entity.Doc;
import com.kzz.blog.entity.User;
import com.kzz.blog.mapper.DocMapper;
import com.kzz.blog.mapper.UserMapper;
import com.kzz.blog.service.IUserService;
import com.kzz.blog.service.ex.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DocMapper docMapper;
    /**
     * 注册
     * @param user
     * @throws InsertException
     * @throws UsernameDuplicateException
     */
    @Override
    public void reg(User user) throws InsertException,UsernameDuplicateException{
        String username=user.getUsername();
        if (userMapper.findByUsername(username)!=null){
            throw new UsernameDuplicateException("尝试注册的用户名(" + username + ")已经被占用");
        }
        Integer rows=userMapper.insertUser(user);
        if(rows!=1){
            throw new InsertException("插入用户数据时出现未知错误！请联系系统管理员");
        }
        User u=userMapper.findByUsername(username);
        Integer uid=u.getUid();
        String email=u.getEmail();
        if(uid==null||email==null){
            throw new InsertException("插入用户数据时出现未知错误！请联系系统管理员");
        }
        Integer rows2=userMapper.insertUserDoc(uid,email);
        if(rows2!=1){
            throw new InsertException("插入用户数据时出现未知错误！请联系系统管理员");
        }
    }

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public User login(String username, String password) throws UsernameDuplicateException,PasswordDuplicateException{
        User result = userMapper.findByUsername(username);
        if (result == null) {
            throw new UserNotFoundException("用户名不存在");
        }
        if (!result.getPassword() .equals(password)) {
            throw new PasswordDuplicateException("密码错误请重新输入");
        }
        User user = new User();
        user.setUid(result.getUid());
        user.setUsername(username);
        user.setAvatar(result.getAvatar());
        return user;
    }

    @Override
    public void changePassword(String username,String newPassword,String oldPassword) {
        User rows=userMapper.findByUsername(username);
        if(rows==null){
            throw new UserNotFoundException("出错了,该用户不存在");
        }
        if(!rows.getPassword().equals(oldPassword)){
            throw new PasswordDuplicateException("原密码输入错误");
        }
        Integer result=userMapper.changePassword(newPassword,rows.getUid());
        if(result==0){
            throw new InsertException("数据修改失败");
        }

    }

    @Override
    public void updateDoc(Doc doc) {
        Integer row=docMapper.updateDoc(doc);
        if(row==0){
            throw new InsertException("数据修改失败");
        }
    }

    @Override
    public Doc selectDocs(Integer uid) {
        Doc doc=docMapper.selectDoc(uid);
        if (doc==null){
            throw new UserNotFoundException("数据查询失败");
        }
        return doc;
    }

    @Override
    public String selectByUid(Integer uid) {
       String username = userMapper.selectByUid(uid);
        return username;
    }

    @Override
    public void changeAvatar(Integer uid, String username, String avatar) {
        // 根据参数uid查询用户数据
        String result = userMapper.selectByUid(uid);
        // 判断查询结果是否为null
        if (result == null) {
            // 是：没有与username匹配的数据，用户名不存在，抛出UserNotFoundException，并描述错误
            throw new UserNotFoundException("用户名不存在");
        }

        // 执行更新头像，获取返回值
        Integer rows = userMapper.uploadAvatar(uid, avatar);
        // 判断返回的受影响行数是否不为1
        if (!rows.equals(1)) {
            // 是：UpdateException
            throw new UpdateException("更新用户头像时出现未知错误！请联系系统管理员");
        }
    }
    @Override
    public void forgotPassword(String username,String password,String email,String phone){
        Integer uid=userMapper.forgotPassword(username,email,phone);
        if(uid==null){
            throw new UserNotFoundException("用户名不存在或手机密码错误");
        }
        Integer row=userMapper.changePassword(password,uid);
        if(row==null){
            throw new UpdateException("更新密码时出现未知错误！请联系系统管理员");
        }
    }

}
