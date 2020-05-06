package com.bysj.wyb.teacher.mapper;

import com.bysj.wyb.teacher.entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangyb
 */
@Mapper
public interface TeacherCourseMapper {

    int addCourse(Course course);

    List<Course> findCourseList(String uid);

    int editCourse(Course course);
}
