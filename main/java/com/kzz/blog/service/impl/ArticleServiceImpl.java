package com.kzz.blog.service.impl;

import com.kzz.blog.entity.Article;
import com.kzz.blog.entity.Class1;
import com.kzz.blog.entity.Tag;
import com.kzz.blog.entity.User;
import com.kzz.blog.mapper.ArticleMapper;
import com.kzz.blog.mapper.UserMapper;
import com.kzz.blog.service.ArticleService;
import com.kzz.blog.service.IUserService;
import com.kzz.blog.service.ex.ArticleInsertException;
import com.kzz.blog.service.ex.UpdateException;
import com.kzz.blog.service.ex.UserNotFoundException;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    ArticleMapper articleMapper;
    private List<Article> articles;
    @Override
    public void insertArticle(Article article) {
        /**
         * 判断用户是否存在
         */
        User row =userMapper.findByUsername(article.getUsername());
        if (row==null){
            throw new UserNotFoundException("用户不存在请重试");
        }
        /**
         *
         */
        article.setIsDelete(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setCreatedDate(sdf.format(new Date()));
        System.out.println(article.getCreatedDate());
        article.setDraft(0);
        Integer result=articleMapper.addArticle(article);
        if(result==null){
            throw new ArticleInsertException("文章加入失败");
        }
        addTag(article);
        addClass(article);
        }


    @Override
    public Article findArticle(Integer uid) {
        String row=userMapper.selectByUid(uid);
        if (row==null){
            throw new UserNotFoundException("用户不存在请重试");
        }
        Article article=articleMapper.findArticle(uid);
        if(article==null){
            throw new ArticleInsertException("查询失败请重试");
        }
        return article;
    }
    /**
     * 添加草稿业务层
     */
    public void addDraft(Article article){
        User row =userMapper.findByUsername(article.getUsername());
        if (row==null){
            throw new UserNotFoundException("用户不存在请重试");
        }
        article.setIsDelete(0);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setCreatedDate(sdf.format(new Date()));
        article.setDraft(1);
        Integer result=articleMapper.addArticle(article);
        if(result==null){
            throw new ArticleInsertException("文章加入失败");
        }
    }
    /**
     * 增加tag
     */
    public void addTag(Article article){
        Integer check=null;
        String tagname=article.getTag();
        Tag tag=new Tag();
        tag.setName(tagname);
        Tag resultTag=articleMapper.findTag(tagname);
        if (resultTag==null){
            //增加tag
            tag.setCount(1);
            check= articleMapper.addTag(tag);
            if (check==null){
                throw new ArticleInsertException("标签加入异常");
            }
        }else {
            //tag.count+1
            check = articleMapper.updateTag(tag.getName());
            if (check == null) {
                throw new ArticleInsertException("标签更新异常,请重试");
            }
        }
    }
    /**
     * 增加class
     */
    public void addClass(Article article){
        Integer check=null;
        String classname=article.getClassname();
        Class1 c=new Class1();
        c.setName(classname);
        Class1 resultClass=articleMapper.findClass(classname);
        if (resultClass==null){
            //增加class
            c.setCount(1);
            check= articleMapper.addClass(c);
            if (check==null){
                throw new ArticleInsertException("类加入异常");
            }
        }else {
            //tag.count+1
            check = articleMapper.updateClass(classname);
            if (check == null) {
                throw new ArticleInsertException("类更新异常,请重试");
            }
        }
    }

    @Override
    public List<Article> showDraft(String username) {
       List<Article> articleList= articleMapper.finddraft(username);
       return articleList;
    }

    @Override
    public int countDrafts(String username) {
        Integer count=articleMapper.countDraft(username);
        if (count==null){
            return 0;
        }
        return count;
    }

    @Override
    public void deleteDraft(String username,Integer draftId) {
        if (username==null){
            throw new UserNotFoundException("用户状态异常请重新登录");
        }
        Article article=articleMapper.findArticle(draftId);
        String classname=article.getClassname();
        String tag=article.getTag();
        Integer rows=articleMapper.deleteDraft(draftId);
        if(rows==null){
            throw new UpdateException("删除异常请重试");
        }
        articleMapper.deleteClass(classname);
        articleMapper.deleteTag(tag);
        articleMapper.deleteClass1();
        articleMapper.deleteTag1();
    }
    @Override
    public List<Article> findArticle(){
        List<Article> articles=articleMapper.findAllArticle();
        System.out.println(articles);
        return articles;
    }

    @Override
    public Integer findAllArticleCount() {
      Integer count=  articleMapper.findAllCount();
        if(count==null) {
            return 0;
        }
        return count;
    }

    @Override
    public List<Article> getTitlesAndDate() {
       List<Article> article1= articleMapper.findAllArticle();
       List<Article> articles=new ArrayList<>();
       for(Article article:article1){
          String title= article.getTitle();
          String date= article.getCreatedDate();
          Article a=new Article();
          article.setTitle(title);
          article.setCreatedDate(date);
          articles.add(a);
       }
        return articles;
    }
    public List<List<Article>> getArticles() {
        articles = findArticle();
        List<Article> fistPage = new ArrayList<>();
        List<Article> secondPage = new ArrayList<>();
        List<Article> thirdPage = new ArrayList<>();
        List<Article> fourthPage = new ArrayList<>();
        List<Article> fivethPage = new ArrayList<>();
        List<Article> sixthtPage = new ArrayList<>();
        List<Article> sevenPage = new ArrayList<>();
        List<Article> eightPage = new ArrayList<>();
        for (int i = 0; i < articles.size(); i++) {
            if (i < 5) {
                fistPage.add(articles.get(i));
            }
            if (i / 5 == 1) {
                secondPage.add(articles.get(i));
            }
            if (i / 5 == 2) {
                thirdPage.add(articles.get(i));
            }
            if (i / 5 == 3) {
                fourthPage.add(articles.get(i));
            }
            if (i / 5 == 4) {
                fivethPage.add(articles.get(i));
            }
            if (i / 5 == 5) {
                sixthtPage.add(articles.get(i));
            }
            if (i / 5 == 6) {
                sevenPage.add(articles.get(i));
            }
            if (i / 5 == 7) {
                eightPage.add(articles.get(i));
            }
        }
        List<List<Article>> list = new ArrayList<>();
        list.add(fistPage);
        list.add(secondPage);
        list.add(thirdPage);
        list.add(fourthPage);
        list.add(fivethPage);
        list.add(sixthtPage);
        list.add(sevenPage);
        list.add(eightPage);
        return list;
    }
}
