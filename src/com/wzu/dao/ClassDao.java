package com.wzu.dao;

import com.wzu.pojo.Apply;
import com.wzu.pojo.ClassInfo;
import com.wzu.pojo.User;

import java.util.List;

public interface ClassDao {
    int createClass(ClassInfo classInfo);
    List<ClassInfo> getClassInfo();
    boolean joinClass(String userId, String userName, String classId, String className);
    List<Apply> getApplyDetail(String classid);
    boolean delApply(String id);
    String getClassName(String classid);
    String getClassId(String classname);
    boolean delClass(String classid);
    boolean userAddClass(String id, String classid);
    boolean removeClassUser(String id, String classid);
}
