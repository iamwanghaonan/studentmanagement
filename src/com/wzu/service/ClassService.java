package com.wzu.service;

import com.wzu.pojo.Apply;
import com.wzu.pojo.ClassInfo;

import java.util.List;

public interface ClassService {
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
