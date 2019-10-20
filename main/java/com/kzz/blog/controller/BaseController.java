package com.kzz.blog.controller;

import com.kzz.blog.controller.ex.*;
import com.kzz.blog.entity.User;
import com.kzz.blog.service.ex.*;
import com.kzz.blog.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BaseController {
    /**
     * 响应状态：成功
     */
    protected static final int OK = 2000;
    /**
     * 从Session中获取uid
     * @param session HttpSession对象
     * @return 当前登录的用户的id
     */
    protected Integer getUidFromSession(HttpSession session) {
        return Integer.valueOf(session.getAttribute("uid").toString());
    }
    protected String getUsernameFromSession(HttpSession session) {

        return session.getAttribute("username").toString();
    }


    @ExceptionHandler({ServiceException.class, FileUploadException.class})
    @ResponseBody
    public JsonResult<Void> handleException(Throwable e) {

        JsonResult<Void> jr = new JsonResult<>(e);
        JsonResult<User> jru = new JsonResult<>(e);

        if (e instanceof UsernameDuplicateException) {
            jr.setState(4000);
        } else if (e instanceof UserNotFoundException) {
            jr.setState(4001);
        } else if (e instanceof InsertException) {
            jr.setState(5000);
        } else if (e instanceof FileEmptyException) {
            jr.setState(6000);
        } else if (e instanceof FileSizeException) {
            jr.setState(6001);
        } else if (e instanceof FileTypeException) {
            jr.setState(6002);
        } else if (e instanceof FileStateException) {
            jr.setState(6003);
        } else if (e instanceof FileIOException) {
            jr.setState(6004);
        }else if (e instanceof ArticleInsertException){
            jr.setState(7001);
        }
        return jr;
    }
    /**
     * 将一个类查询方式加入map（属性值为int型时，0时不加入，
     * 属性值为String型或Long时为null和“”不加入）
     *
     */
    public static Map<String, Object> setConditionMap(Object obj){
        Map<String, Object> map = new HashMap<String, Object>();
        if(obj==null){
            return null;
        }
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field : fields){
            String fieldName =  field.getName();
            if(getValueByFieldName(fieldName,obj)!=null)
                map.put(fieldName,  getValueByFieldName(fieldName,obj));
        }

        return map;

    }
    /**
     * 根据属性名获取该类此属性的值
     * @param fieldName
     * @param object
     * @return
     */
    private static Object getValueByFieldName(String fieldName,Object object){
        String firstLetter=fieldName.substring(0,1).toUpperCase();
        String getter = "get"+firstLetter+fieldName.substring(1);
        try {
            Method method = object.getClass().getMethod(getter);
            Object value = method.invoke(object);
            return value;
        } catch (Exception e) {
            return null;
        }

    }


}
