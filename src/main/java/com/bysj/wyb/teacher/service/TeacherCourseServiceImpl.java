package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.mapper.TeacherCourseMapper;
import com.bysj.wyb.teacher.result.HandleResult;
import com.bysj.wyb.teacher.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author admin
 */
@Service
@Slf4j
public class TeacherCourseServiceImpl implements TeacherCourseService{
    @Resource
    TeacherCourseMapper teacherCourseMapper;

    HandleResult hr=new HandleResult();

    @Override
    public Result addCourse(Course course) {
        HandleResult hr=new HandleResult();
        String strNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replaceAll("-","").replaceAll(":","").replaceAll(" ","");
        course.setCourseId(strNow);
        if(teacherCourseMapper.addCourse(course)==1)
        {
            return hr.outResultWithoutData("0","添加成功");
        }
        else
        {
            return hr.outResultWithoutData("1","添加失败");
        }
    }

    @Override
    public Result findCourseList(String uid) {
        try {
            return hr.outResultWithData("0","查询成功",teacherCourseMapper.findCourseList(uid));
        }catch(Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常");
        }
    }

    @Override
    public Result editCourse(Course course) {
        HandleResult hr=new HandleResult();
        try {
            course.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(1==teacherCourseMapper.editCourse(course)){
                hr.outResultWithoutData("0","课程信息更新成功");
            }else{
                hr.outResultWithoutData("1","课程信息更新失败，请检查更新信息或联系管理员");
            }
        }catch(Exception e){
            log.error(e.getMessage());
            hr.outResultWithoutData("1","服务异常，请联系管理员");
        }
        return null;
    }
}
