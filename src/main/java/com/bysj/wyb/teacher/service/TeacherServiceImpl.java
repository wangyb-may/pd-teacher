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
                if(t.getDel()==1){
                    return hr.outResultWithoutData("1","该账号已经停用，请联系管理员!");
                }
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

    @Override
    public Result updatePersonalImformation(Teacher teacher) {
        HandleResult hr=new HandleResult();
        try{
            if(1==teacherMapper.updatePersonalImformation(teacher)){
                return hr.outResultWithData("0","修改成功！",teacher);
            }else{
                return hr.outResultWithoutData("1","修改失败，请检查是否修改参数错误");
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常，请联系管理员");
        }
    }


}
