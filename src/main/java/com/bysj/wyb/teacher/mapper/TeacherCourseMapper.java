package com.bysj.wyb.teacher.mapper;

import com.bysj.wyb.teacher.entity.Attachment;
import com.bysj.wyb.teacher.entity.Course;
import com.bysj.wyb.teacher.entity.Homework;
import com.bysj.wyb.teacher.vo.HomeworkStatusVo;
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

    int delCourse(String courseId);

    List<Homework> findHomeworkByCourse(String courseId);

    int upAttachment(Attachment attachment);

    List<Attachment> findAttachmentList(String uid);

    int delAttachment(String attachmentId);

    List<String> findAttachmentIdListByCourse(String courseId);

    /**
     * 查询已加入课程的同学
     * @param courseId
     * @return
     */
    List<HomeworkStatusVo> findStudentByCourse(String courseId);
}
