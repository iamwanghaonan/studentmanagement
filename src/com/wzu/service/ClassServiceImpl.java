package com.wzu.service;

import com.wzu.dao.ClassDao;
import com.wzu.dao.ClassDaoImpl;
import com.wzu.pojo.Apply;
import com.wzu.pojo.ClassInfo;

import java.util.List;

public class ClassServiceImpl implements ClassService{
    ClassDao classDao = new ClassDaoImpl();

    @Override
    public int createClass(ClassInfo classInfo) { return classDao.createClass(classInfo); }

    @Override
    public List<ClassInfo> getClassInfo() { return classDao.getClassInfo(); }

    @Override
    public boolean joinClass(String userId, String userName, String classId, String className) {
        return classDao.joinClass(userId, userName, classId, className);
    }

    @Override
    public List<Apply> getApplyDetail(String classid) { return classDao.getApplyDetail(classid); }

    @Override
    public boolean delApply(String id) { return classDao.delApply(id); }

    @Override
    public String getClassName(String classid) {
        return classDao.getClassName(classid);
    }

    @Override
    public String getClassId(String classname) {
        return classDao.getClassId(classname);
    }

    @Override
    public boolean delClass(String classid) { return classDao.delClass(classid); }

    @Override
    public boolean userAddClass(String id, String classid) { return classDao.userAddClass(id, classid); }

    @Override
    public boolean removeClassUser(String id, String classid) {
        return classDao.removeClassUser(id, classid);
    }
}
