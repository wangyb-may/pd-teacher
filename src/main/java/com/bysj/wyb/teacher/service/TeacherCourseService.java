package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Attachment;
import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.result.Result;
import org.springframework.web.multipart.MultipartFile;

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

    Result delCourse(Course course);

    /**
     * 上传附件
     * @param file
     * @param attachment
     * @return
     */
    Result upAttachment(MultipartFile file, Attachment attachment);

    Result findAttachmentList(String uid);

    Result delAtachment(Attachment attachment);

    Result findStudentByCourse(String courseId);
}
