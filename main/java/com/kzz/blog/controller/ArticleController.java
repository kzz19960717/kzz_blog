package com.kzz.blog.controller;

import com.kzz.blog.entity.Article;
import com.kzz.blog.entity.Class1;
import com.kzz.blog.service.ArticleService;
import com.kzz.blog.service.ClassAndTagService;
import com.kzz.blog.service.ex.UserNotFoundException;
import com.kzz.blog.util.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("article")
public class ArticleController extends BaseController {
    //todo  测试
    @Autowired
    ArticleService articleService;
    @Autowired
    ClassAndTagService classAndTagService;
    @PostMapping("release.do")
    @ResponseBody
    public JsonResult<Void> release(Article article, HttpSession session, ModelMap modelMap){
        String username=getUsernameFromSession(session);
        article.setUsername(username);
        articleService.insertArticle(article);
        return new JsonResult<>(OK);
    }
    @PostMapping("uptoDraft.do")
    @ResponseBody
    public JsonResult<Void> draft(HttpSession session,Article article){
        String username=getUsernameFromSession(session);
        article.setUsername(username);
        articleService.addDraft(article);
        return new JsonResult<>(OK);
    }
    @PostMapping("deleteDraft.do")
    @ResponseBody
    public JsonResult<Void> delete(String uid,HttpSession session){
        Integer uid1=Integer.valueOf(uid);
        articleService.deleteDraft(getUsernameFromSession(session),uid1);
        return new JsonResult<>(OK);
    }


}
