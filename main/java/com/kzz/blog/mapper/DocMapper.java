package com.kzz.blog.mapper;

import com.kzz.blog.entity.Doc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

public interface DocMapper {
    Integer updateDoc(Doc doc);
    Doc selectDoc(Integer uid);

}
