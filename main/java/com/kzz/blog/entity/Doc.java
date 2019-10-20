package com.kzz.blog.entity;

import java.io.Serializable;

public class Doc implements Serializable {
    private static final long serialVersionUID = -6649819647753773170L;
    private Integer uid;
    private String name;
    private String email;
    private Integer openEmail;
    private String github;
    private String weibo;
    private String userHomePage;
    private String instructor;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getOpenEmail() {
        return openEmail;
    }

    public void setOpenEmail(Integer openEmail) {
        this.openEmail = openEmail;
    }

    public String getGithub() {
        return github;
    }

    public void setGithub(String github) {
        this.github = github;
    }

    public String getWeibo() {
        return weibo;
    }

    public void setWeibo(String weibo) {
        this.weibo = weibo;
    }

    public String getUserHomePage() {
        return userHomePage;
    }

    public void setUserHomePage(String userHomePage) {
        this.userHomePage = userHomePage;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    @Override
    public String toString() {
        return "Doc{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", openEmail=" + openEmail +
                ", github='" + github + '\'' +
                ", weibo='" + weibo + '\'' +
                ", userHomePage='" + userHomePage + '\'' +
                ", instructor='" + instructor + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Doc doc = (Doc) o;

        return uid.equals(doc.uid);
    }

    @Override
    public int hashCode() {
        return uid.hashCode();
    }
}
