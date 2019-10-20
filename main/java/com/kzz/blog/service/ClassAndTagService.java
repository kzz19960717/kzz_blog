package com.kzz.blog.service;

import com.kzz.blog.entity.Class1;
import com.kzz.blog.entity.Tag;

import java.util.List;
import java.util.Map;

public interface ClassAndTagService {
    Map<String,Integer> findTags();
    Map<String,Integer> findClasses();
    int findTagsCount();
    int findClassesCount();
}
