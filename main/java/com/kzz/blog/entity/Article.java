package com.kzz.blog.entity;

import java.util.Date;
import java.util.Objects;

public class Article extends BaseEntity{
    private static final long serialVersionUID = 4592934164773782806L;
    private String title;
    private String tag;
    private Integer uid;
    private String mainBody;
    private String createdDate;
    private String classname;
    private Integer isDelete;
    private Integer draft;
    private String username;

    @Override
    public String toString() {
        return "Article{" +
                "title='" + title + '\'' +
                ", tag='" + tag + '\'' +
                ", uid=" + uid +
                ", mainBody='" + mainBody + '\'' +
                ", createdDate=" + createdDate +
                ", classname='" + classname + '\'' +
                ", isDelete=" + isDelete +
                ", draft=" + draft +
                ", username='" + username + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getDraft() {
        return draft;
    }

    public void setDraft(Integer draft) {
        this.draft = draft;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getMainBody() {
        return mainBody;
    }

    public void setMainBody(String mainBody) {
        this.mainBody = mainBody;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article)) return false;
        Article article = (Article) o;
        return Objects.equals(uid, article.uid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uid);
    }

}
