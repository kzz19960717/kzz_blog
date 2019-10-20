package com.kzz.blog.mapper;

import com.kzz.blog.entity.Doc;
import com.kzz.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface UserMapper {
        /**
         * 插入用户数据
         */
        Integer insertUser(User user);
        Integer insertUserDoc(@Param("uid") Integer uid,@Param("email")String email);
        /**
         * 根据用户名查询用户数据
         */
        User findByUsername(String username);

        /**
         * 更改密码
         */
        Integer changePassword(@Param("password") String password,@Param("uid") Integer uid);
        /**
         * 个人资料增加与更新
         */
        String selectByUid(Integer uid);
        /**
         * 头像上传
         */
        Integer uploadAvatar(@Param("uid") Integer uid,
                       @Param("avatar") String avatar);
        Integer forgotPassword(@Param("username")String username,@Param("email")String email,@Param("phone")String phone);

}
