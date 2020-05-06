package com.bysj.wyb.teacher.controller;

import com.bysj.wyb.teacher.entity.Homework;
import com.bysj.wyb.teacher.result.Result;
import com.bysj.wyb.teacher.service.TeacherHomeworkService;
import com.bysj.wyb.teacher.vo.HomeworkStatusVo;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author wangyb
 */
@RestController
@RequestMapping(value = "/teacherHomework")
public class TeacherHomeworkController {

    @Resource
    TeacherHomeworkService teacherHomeworkService;


    @RequestMapping(value = "/addHomework")
    public Result addHomework(@RequestBody Homework homework){
        return teacherHomeworkService.addHomework(homework);
    }

    @RequestMapping(value = "/findStatus")
    public Result findHomeworkStatus(@RequestParam String uid){
        return teacherHomeworkService.findHomeworkStatus(uid);
    }

    @RequestMapping(value = "/downloadList")
    public void downloadHomeworkList(@RequestBody List<HomeworkStatusVo> homeworkStatusVoList, HttpServletResponse response) throws Exception {
        teacherHomeworkService.downloadHomeworkList(homeworkStatusVoList, response);
    }

    @RequestMapping(value = "/giveScore")
    public Result giveScore(@RequestBody HomeworkStatusVo homeworkStatusVo){
        return teacherHomeworkService.giveScore(homeworkStatusVo);
    }

    @RequestMapping(value = "/findHomeworkStatuBykey")
    public Result findHomeworkStatuBykey(@RequestBody Map<String,String> resBody){
        return teacherHomeworkService.findHomeworkListByKey(resBody.get("uid"),resBody.get("key"));
    }

    @RequestMapping(value = "/findHomeworkList")
    public Result findHomeworkList(@RequestBody Map<String,String> resBody){
        return teacherHomeworkService.findHomeworkList(resBody.get("uid"));
    }

    @RequestMapping(value = "/delHomework")
    public Result delHomework(@RequestBody Map<String,String> resBody){
        return teacherHomeworkService.delHomework(resBody.get("homeworkId"));
    }
}
