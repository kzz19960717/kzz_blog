package com.kzz.blog.service;

import com.kzz.blog.entity.Article;

import java.util.List;

public interface ArticleService {

    void insertArticle(Article article);
    Article findArticle(Integer uid);
    void  addDraft(Article article);
    List<Article> showDraft(String username);
    int countDrafts(String username);
    void deleteDraft(String username,Integer draftId);
    List<Article> findArticle();
    Integer findAllArticleCount();
    List<Article> getTitlesAndDate();
    List<List<Article>> getArticles();
}
