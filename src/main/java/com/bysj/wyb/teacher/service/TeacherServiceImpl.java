package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Teacher;
import com.bysj.wyb.teacher.feign.CommonFeign;
import com.bysj.wyb.teacher.mapper.TeacherMapper;
import com.bysj.wyb.teacher.result.HandleResult;
import com.bysj.wyb.teacher.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author wangyb
 */
@Service
@Slf4j
public class TeacherServiceImpl implements TeacherService {

    @Resource
    TeacherMapper teacherMapper;

    @Resource
    CommonFeign commonFeign;

    @Override
    public Result login(String uid, String password) {
        HandleResult hr=new HandleResult();
        try{
            Teacher t=teacherMapper.login(uid);
            if(null!=t){
                if(password.equals(t.getPassword())){
                    commonFeign.logCounter(t.getUid());
                    return hr.outResultWithData("0","登陆成功",t);
                }else{
                    return hr.outResultWithoutData("1","登录失败，密码错误");
                }
            }else{
                return hr.outResultWithoutData("1","无此账号，请联系管理员注册");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常");
        }


    }
}
