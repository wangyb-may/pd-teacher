package com.bysj.wyb.teacher.controller;

import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.result.Result;
import com.bysj.wyb.teacher.service.TeacherCourseService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wangyb
 */
@RestController
@RequestMapping(value = "/teacherCourse")
public class TeacherCourseController {

    @Resource
    TeacherCourseService teacherCourseService;

    @RequestMapping(value = "/addCourse")
    public Result addCorse(@RequestBody Course course){
        Result result = teacherCourseService.addCourse(course);
        return result;
    }

    @RequestMapping(value = "/findCourse")
    public Result findCourseList(@RequestBody Map<String,String> resBody){
        return teacherCourseService.findCourseList(resBody.get("uid"));
    }

    @RequestMapping(value = "/editCourse")
    public Result editCourse(@RequestBody Course course){
        return teacherCourseService.editCourse(course);
    }
}
