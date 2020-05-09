package com.bysj.wyb.teacher.service;


import com.bysj.wyb.teacher.entity.Teacher;
import com.bysj.wyb.teacher.result.Result;

/**
 * @author wangyb
 */
public interface TeacherService {

    Result login(String uid,String password);

    Result updatePersonalImformation(Teacher teacher);


}
