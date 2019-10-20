package com.kzz.blog.controller;

import com.kzz.blog.entity.Article;
import com.kzz.blog.entity.Doc;
import com.kzz.blog.service.ArticleService;
import com.kzz.blog.service.ClassAndTagService;
import com.kzz.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("home")
public class HomeController extends BaseController {

    @Autowired
    IUserService userService;
    @Autowired
    ClassAndTagService classAndTagService;
    @Autowired
    ArticleService articleService;


    @RequestMapping("category.do")
    public String category(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        Map<String, Integer> tags = classAndTagService.findTags();
        Map<String, Integer> classes = classAndTagService.findClasses();
        modelMap.addAttribute("classes", classes);
        modelMap.addAttribute("tags", tags);
        return "category";
    }

    @RequestMapping("index.do")
    public String index(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        return "index";
    }

    @RequestMapping("home.do")
    public String home(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        if(username.equals("虾sir")){
            modelMap.addAttribute("root","欢迎您尊贵的VIP用户");
        }
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("one", articleService.getArticles().get(0));
        } catch (NullPointerException e) {
        }
        return "home";
    }

    @RequestMapping("signin.do")
    public String signin(ModelMap modelMap, HttpSession session) {
        return "signin";
    }

    @RequestMapping("archive.do")
    public String archive(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(0);
        modelMap.addAttribute("one",list);
        return "archive";
    }

    @RequestMapping("aboutme.do")
    public String aboutme(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        return "aboutme";
    }

    @RequestMapping("setting.do")
    public String setting(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        Doc doc = userService.selectDocs(getUidFromSession(session));
        modelMap.addAttribute("name", doc.getName());
        modelMap.addAttribute("email", doc.getEmail());
        modelMap.addAttribute("openEmail", doc.getOpenEmail());
        modelMap.addAttribute("github", doc.getGithub());
        modelMap.addAttribute("weibo", doc.getWeibo());
        modelMap.addAttribute("userHomePage", doc.getUserHomePage());
        modelMap.addAttribute("instructor", doc.getInstructor());
        return "setting";
    }

    @PostMapping("logout.do")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:../home/signin.do";
    }

    @RequestMapping("edit_post.do")
    public String edit(ModelMap modelMap, HttpSession session) {
        Map<String, Integer> classmaps = classAndTagService.findClasses();
        modelMap.addAttribute("classmaps", classmaps);
        Integer uid = getUidFromSession(session);
        String allUsername = userService.selectByUid(uid);
        modelMap.addAttribute("inUsername", allUsername);
        return "edit_post";
    }

    @RequestMapping("drafts.do")
    public String drafts(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        List<Article> articleList = articleService.showDraft(username);
        modelMap.addAttribute("articles", articleList);
        modelMap.addAttribute("count", articleService.countDrafts(username));
        return "drafts";
    }

    @RequestMapping("post.do")
    public String post(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        return "post";
    }
    @RequestMapping("home2.do")
    public String home2(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("two", articleService.getArticles().get(1));
        } catch (NullPointerException e) {
        }
        return "home2";
    }
    @RequestMapping("home3.do")
    public String home3(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("three", articleService.getArticles().get(2));
        } catch (NullPointerException e) {
        }
        return "home3";
    }
    @RequestMapping("home4.do")
    public String home4(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("four", articleService.getArticles().get(3));
        } catch (NullPointerException e) {
        }
        return "home4";
    }
    @RequestMapping("home5.do")
    public String home5(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("five", articleService.getArticles().get(4));
        } catch (NullPointerException e) {
        }
        return "home5";
    }
    @RequestMapping("home6.do")
    public String home6(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("six", articleService.getArticles().get(5));
        } catch (NullPointerException e) {
        }
        return "home6";
    }
    @RequestMapping("home7.do")
    public String home7(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("seven", articleService.getArticles().get(6));
        } catch (NullPointerException e) {
        }
        return "home7";
    }
    @RequestMapping("home8.do")
    public String home8(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        try {
            modelMap.addAttribute("eight", articleService.getArticles().get(7));
        } catch (NullPointerException e) {
        }
        return "home8";
    }
    @RequestMapping("archive2.do")
    public String archive2(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(1);
        modelMap.addAttribute("one",list);
        return "archive2";
    }
    @RequestMapping("archive3.do")
    public String archive3(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(2);
        modelMap.addAttribute("one",list);
        return "archive3";
    }
    @RequestMapping("archive4.do")
    public String archive4(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(3);
        modelMap.addAttribute("one",list);
        return "archive4";
    }
    @RequestMapping("archive5.do")
    public String archive5(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(4);
        modelMap.addAttribute("one",list);
        return "archive5";
    }
    @RequestMapping("archive6.do")
    public String archive6(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(5);
        modelMap.addAttribute("one",list);
        return "archive6";
    }
    @RequestMapping("archive7.do")
    public String archive7(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(6);
        modelMap.addAttribute("one",list);
        return "archive7";
    }
    @RequestMapping("archive8.do")
    public String archive8(ModelMap modelMap, HttpSession session) {
        String username = getUsernameFromSession(session);
        modelMap.addAttribute("inUsername", username);
        int count=articleService.findAllArticleCount();
        modelMap.addAttribute("count",count);
        List<Article> list=articleService.getArticles().get(7);
        modelMap.addAttribute("one",list);
        return "archive8";
    }
}
