package com.kzz.blog.service.impl;

import com.kzz.blog.entity.Class1;
import com.kzz.blog.entity.Tag;
import com.kzz.blog.mapper.ClassAndTagMapper;
import com.kzz.blog.service.ClassAndTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassAndTagImpl implements ClassAndTagService {
    @Autowired
    ClassAndTagMapper classAndTagMapper;



    @Override
    public Map<String,Integer> findTags() {
        List<Tag> tagNames=classAndTagMapper.findAllTag();
        Map<String,Integer> tags=new HashMap<>();
        for (Tag tag:tagNames){
            tags.put(tag.getName(),tag.getCount());
        }
        return tags;
    }

    @Override
    public Map<String,Integer> findClasses() {
        List<Class1> tagNames=classAndTagMapper.findAllClass();
        Map<String,Integer> classes=new HashMap<>();
        for (Class1 class1:tagNames){
            classes.put(class1.getName(),class1.getCount());
        }
        return classes;
    }

    @Override
    public int findTagsCount() {
        int tagsCount=classAndTagMapper.findTagCount();
        return tagsCount;
    }

    @Override
    public int findClassesCount() {
        int classesCount=classAndTagMapper.findClassCount();
        return classesCount;
    }
}
