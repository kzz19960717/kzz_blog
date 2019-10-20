package com.kzz.blog.entity;

import java.util.Objects;

public class Class1 extends BaseEntity{
    private static final long serialVersionUID = -497206759190774579L;
    private Integer id;
    private String name;
    private Integer count;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Class1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Class1)) return false;
        Class1 class1 = (Class1) o;
        return Objects.equals(id, class1.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
