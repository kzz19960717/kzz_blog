package com.kzz.blog.mapper;

import com.kzz.blog.entity.Class1;
import com.kzz.blog.entity.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ClassAndTagMapper {
    List<Class1> findAllClass();
    List<Tag> findAllTag();
    Integer findClassCount();
    Integer findTagCount();
}
