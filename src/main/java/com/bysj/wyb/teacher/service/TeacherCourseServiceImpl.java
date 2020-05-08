package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Attachment;
import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.entity.Homework;
import com.bysj.wyb.teacher.feign.CommonFeign;
import com.bysj.wyb.teacher.mapper.TeacherCourseMapper;
import com.bysj.wyb.teacher.mapper.TeacherHomeworkMapper;
import com.bysj.wyb.teacher.result.HandleResult;
import com.bysj.wyb.teacher.result.IdWorker;
import com.bysj.wyb.teacher.result.Result;
import com.google.gson.internal.$Gson$Types;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


/**
 * @author admin
 */
@Service
@Slf4j
public class TeacherCourseServiceImpl implements TeacherCourseService{
    @Resource
    TeacherCourseMapper teacherCourseMapper;

    HandleResult hr=new HandleResult();

    @Resource
    TeacherHomeworkMapper teacherHomeworkMapper;

    @Resource
    CommonFeign commonFeign;

    @Override
    public Result addCourse(Course course) {
        String strNow=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).replaceAll("-","").replaceAll(":","").replaceAll(" ","");
        course.setCourseId(strNow);
        course.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
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
        try {
            course.setUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(1==teacherCourseMapper.editCourse(course)){
                return hr.outResultWithoutData("0","课程信息更新成功");
            }else{
                return hr.outResultWithoutData("1","课程信息更新失败，请检查更新信息或联系管理员");
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常，请联系管理员");
        }
    }

    @Override
    public Result delCourse(Course course) {
        try {
            List<Homework> homeworkList=teacherCourseMapper.findHomeworkByCourse(course.getCourseId());
            for(Homework h : homeworkList){
                teacherHomeworkMapper.delHomework(h.getId());
            }
            List<String> attachmentIdList=teacherCourseMapper.findAttachmentIdListByCourse(course.getCourseId());
            for(String x : attachmentIdList){
                teacherCourseMapper.delAttachment(x);
            }
            teacherCourseMapper.delCourse(course.getCourseId());
            return hr.outResultWithoutData("0","删除成功");
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常，请联系管理员");
        }
    }

    @Override
    public Result upAttachment(MultipartFile file, Attachment attachment) {
        try {
            Result res=commonFeign.upload(file, "attachment/"+attachment.getName());
            if(null!=res.getData()){
                attachment.setUpUrl(res.getData().toString());
                IdWorker snow=new IdWorker(1,1,1);
                attachment.setId(String.valueOf(snow.nextId()));
                attachment.setUpTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                teacherCourseMapper.upAttachment(attachment);
                List<Attachment> attachmentList=teacherCourseMapper.findAttachmentList(attachment.getUid());
                return hr.outResultWithData("0","插入成功!已更新数据！",attachmentList);
            }else{
                return hr.outResultWithoutData("1","插入失败，请联系管理员");
            }
        }catch(Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常，请联系管理员");
        }

    }

    @Override
    public Result findAttachmentList(String uid) {
        try{
            return hr.outResultWithData("0","查询成功",teacherCourseMapper.findAttachmentList(uid));
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常，请联系管理员");
        }
    }

    @Override
    public Result delAtachment(Attachment attachment) {
        try{
            teacherCourseMapper.delAttachment(attachment.getId());
            return hr.outResultWithoutData("0","删除成功！");
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常，请联系管理员!");
        }
    }
}
