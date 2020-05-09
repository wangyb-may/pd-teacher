package com.bysj.wyb.teacher.mapper;

import com.bysj.wyb.teacher.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author wangyb
 */
@Mapper
public interface TeacherMapper {

    Teacher login(String uid);

    int updatePersonalImformation(Teacher teacher);

}
