package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.result.Result;

/**
 * @author wangyb
 */
public interface TeacherCourseService {

    /**
     * 添加课程
     * @param course
     * @return
     */
    Result addCourse(Course course);

    Result findCourseList(String uid);

    Result editCourse(Course course);
}
