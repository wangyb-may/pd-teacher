package com.bysj.wyb.teacher.mapper;

import com.bysj.wyb.teacher.entity.Homework;
import com.bysj.wyb.teacher.vo.HomeworkStatusVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangyb
 */
@Mapper
public interface TeacherHomeworkMapper {

    int addHomework(Homework homework);

    int reStatus(String homeworkId, String uid);

    List<String> backUid(String courseId);

    List<HomeworkStatusVo> findHomeworkStatu(String teacherUid);

    int giveScore(HomeworkStatusVo homeworkStatusVo);

    List<HomeworkStatusVo> findHomeworkStatuByKey(String uid,String key);

    List<Homework> findHomworkList(String uid);

    int delHomework(String homeworkId);
}
