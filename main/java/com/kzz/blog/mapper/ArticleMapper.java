package com.kzz.blog.mapper;

import com.kzz.blog.entity.Article;
import com.kzz.blog.entity.Class1;
import com.kzz.blog.entity.Tag;

import java.util.List;

public interface ArticleMapper {
    Integer addArticle(Article article);
    Article findArticle(Integer uid);
    Tag findTag(String name);
    Integer addTag(Tag tag);
    Class1 findClass(String name);
    Integer addClass(Class1 class1);
    Integer updateTag(String name);
    Integer updateClass(String name);
    List<Article> finddraft(String username);
    Integer countDraft(String username);
    Integer deleteDraft(Integer uid);
    Integer deleteClass(String name);
    Integer deleteTag(String name);
    Integer deleteTag1();
    Integer deleteClass1();
    List<Article> findAllArticle();
    Integer findAllCount();

}
