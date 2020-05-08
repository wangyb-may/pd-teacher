package com.bysj.wyb.teacher.controller;

import com.bysj.wyb.teacher.entity.Attachment;
import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.result.Result;
import com.bysj.wyb.teacher.service.TeacherCourseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author wangyb
 */
@RestController
@RequestMapping(value = "/teacherCourse")
@CrossOrigin
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

    @RequestMapping(value = "/delCourse")
    public Result delCourse(@RequestBody Course course){
        return teacherCourseService.delCourse(course);
    }

    @RequestMapping(value = "/upAttachment",produces = "application/json;charset=utf-8")
    public Result upAttachment(MultipartFile file, Attachment attachment){
        return teacherCourseService.upAttachment(file,attachment);
    }

    @RequestMapping(value = "/findAttachmentList")
    public Result findAttachmentList(@RequestBody Map<String,String> resBody){
        return teacherCourseService.findAttachmentList(resBody.get("uid"));
    }

    @RequestMapping(value = "/delAttachment")
    public Result delAttachment(@RequestBody Attachment attachment){
        return teacherCourseService.delAtachment(attachment);
    }
}
