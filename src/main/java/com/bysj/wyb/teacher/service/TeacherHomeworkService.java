package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Homework;
import com.bysj.wyb.teacher.result.Result;
import com.bysj.wyb.teacher.vo.HomeworkStatusVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author wangyb
 */
public interface TeacherHomeworkService {


    Result addHomework(Homework homework);

    Result findHomeworkStatus(String teacherUid);

    void downloadHomeworkList(List<HomeworkStatusVo> homeworkStatusVoList, HttpServletResponse response) throws Exception;

    Result giveScore(HomeworkStatusVo homeworkStatusVo);

    Result findHomeworkListByKey(String uid,String key);

    Result findHomeworkList(String uid);

    Result delHomework(String homeworkId);
}
