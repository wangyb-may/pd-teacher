package com.bysj.wyb.teacher.controller;

import com.bysj.wyb.teacher.entity.Teacher;
import com.bysj.wyb.teacher.result.HandleResult;
import com.bysj.wyb.teacher.result.Result;
import com.bysj.wyb.teacher.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping(value = "/teacher")
public class TeacherController {

    @Resource
    TeacherService teacherService;

    @RequestMapping(value = "/login")
    public Result teacherLogin(@RequestBody Map<String,String> resBody){
        return teacherService.login(resBody.get("nickName"),resBody.get("password"));
    }

    @RequestMapping(value = "/update")
    public Result updatePersonalImformation(@RequestBody Teacher teacher){
        return teacherService.updatePersonalImformation(teacher);
    }


}
