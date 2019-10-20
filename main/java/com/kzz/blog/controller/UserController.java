package com.kzz.blog.controller;

import com.kzz.blog.controller.ex.*;
import com.kzz.blog.entity.Doc;
import com.kzz.blog.entity.User;
import com.kzz.blog.service.IUserService;
import com.kzz.blog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("user")
public class UserController extends BaseController{

    @Autowired
    private IUserService UserService;

    @RequestMapping("reg.do")
    @ResponseBody
    public JsonResult<Void> Reg(User user){
        UserService.reg(user);
        return new JsonResult<>(OK);
    }
    @RequestMapping("login.do")
    @ResponseBody
    public JsonResult<Void> b(User user, HttpSession session){
        String username=user.getUsername();
        String password=user.getPassword();
        User u=UserService.login(username,password);
        session.setAttribute("uid",u.getUid());
        session.setAttribute("avatar",u.getAvatar());
        session.setAttribute("username",u.getUsername());
        return new JsonResult<>(OK);
    }
    @PostMapping("changePassword.do")
    @ResponseBody
    public JsonResult<Void> changePassword(String oldPassword,String newPassword,HttpSession session){
        String username=getUsernameFromSession(session);
        UserService.changePassword(username,newPassword,oldPassword);
        return new JsonResult<>(OK);
    }
    @PostMapping("handle_doc.do")
    @ResponseBody
    public JsonResult<Void> handleDoc(Doc doc,HttpSession session){
        Integer uid=getUidFromSession(session);
        doc.setUid(uid);
        UserService.updateDoc(doc);
        return new JsonResult<>(OK);
    }

    /**
     * 上传头像
     * @param avatar
     * @param session
     * @return
     */
    public static final long AVATAR_MAX_SIZE = 850 * 1024;

    public static final List<String> AVATAR_TYPES = new ArrayList<>();

    static {
        AVATAR_TYPES.add("images/jpg");
        AVATAR_TYPES.add("images/png");
        AVATAR_TYPES.add("images/gif");
        AVATAR_TYPES.add("images/bmp");
    }

    @PostMapping("change_avatar.do")
    @ResponseBody
    public JsonResult<String> changeAvatar(
            @RequestParam("avatar") MultipartFile avatar,
            HttpSession session) {
        // 检查上传的文件是否为空
        if (avatar.isEmpty()) {
            throw new FileEmptyException("描述");
        }

        // 检查上传的文件的大小是否超出了限制
        if (avatar.getSize() > AVATAR_MAX_SIZE) {
            throw new FileSizeException("描述(AVATAR_MAX_SIZE)");
        }

        // 检查上传的文件的类型是否超出了限制(image/jpeg;image/png;image/gif;image/bmp)
        if (!AVATAR_TYPES.contains(avatar.getContentType())) {
            throw new FileTypeException("描述(AVATAR_TYPES)");
        }
        // 获取原始文件名
        String originalFilename = avatar.getOriginalFilename();

        // 文件夹
        String parentPath = session.getServletContext().getRealPath("../upload");
        File parent = new File(parentPath);
        if (!parent.exists()) {
            parent.mkdirs();
        }

        // 文件名
        String suffix = "";
        int beginIndex = originalFilename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = originalFilename.substring(beginIndex);
        }
        String filename = suffix;

        // 用于保存上传的文件的对象
        File dest = new File(parent, filename);
        // 保存客户端上传的文件
        try {
            avatar.transferTo(dest);
        } catch (IllegalStateException e) {
            throw new FileStateException("文件状态异常，请重新选择文件并再次上传");
        } catch (IOException e) {
            throw new FileIOException("上传时出现读写错误，请重新上传");
        }

        // 将文件的路径存储到数据表中
        String avatarPath = "/upload/" + filename;
        Integer uid = getUidFromSession(session);
        String username = getUsernameFromSession(session);
        UserService.changeAvatar(uid, username, avatarPath);

        // 响应：OK, 头像路径
        return new JsonResult<>(OK, avatarPath);
    }
    @RequestMapping("forget.do")
    public String forget(){
        return "forget";
    }
    @PostMapping("handle_forget.do")
    @ResponseBody
    public JsonResult<Void> doforgot(User user){
        UserService.forgotPassword(user.getUsername(),user.getPassword(),user.getEmail(),user.getPhone());
        return new JsonResult<>(OK);
    }

}
