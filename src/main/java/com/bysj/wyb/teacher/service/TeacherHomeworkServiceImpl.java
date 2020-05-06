package com.bysj.wyb.teacher.service;

import com.bysj.wyb.teacher.entity.Homework;
import com.bysj.wyb.teacher.mapper.TeacherHomeworkMapper;
import com.bysj.wyb.teacher.result.HandleResult;
import com.bysj.wyb.teacher.result.IdWorker;
import com.bysj.wyb.teacher.result.Result;
import com.bysj.wyb.teacher.result.ZipUtil;
import com.bysj.wyb.teacher.vo.HomeworkStatusVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author wangyb
 */
@Service
@Slf4j
public class TeacherHomeworkServiceImpl implements TeacherHomeworkService {

    @Resource
    TeacherHomeworkMapper teacherHomeworkMapper;



    @Override
    public Result addHomework(Homework homework) {
        HandleResult hr=new HandleResult();

        IdWorker snow=new IdWorker(1,1,1);
        homework.setId(String.valueOf(snow.nextId()));
        String createTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        homework.setCreatetime(createTime);
        if(1==teacherHomeworkMapper.addHomework(homework)){
            List<String> uids=teacherHomeworkMapper.backUid(homework.getCourseid());
            for(String x : uids){
                teacherHomeworkMapper.reStatus(homework.getId(),x);
            }
            return hr.outResultWithoutData("0","添加成功");
        }
        else
        {
            return hr.outResultWithoutData("1","添加失败");
        }
    }

    @Override
    public Result findHomeworkStatus(String teacherUid) {
        HandleResult hr=new HandleResult();
        List<HomeworkStatusVo> hs=teacherHomeworkMapper.findHomeworkStatu(teacherUid);
        for(HomeworkStatusVo hv : hs){
            if("0".equals(hv.getUpStatu())){
                hv.setUpStatu("未提交");
            }else{
                hv.setUpStatu("已提交");
            }
        }
        return hr.outResultWithData("0","查询成功",hs);
    }

    @Override
    public void downloadHomeworkList(List<HomeworkStatusVo> homeworkStatusVoList, HttpServletResponse response) throws Exception {
        ZipUtil outstream=new ZipUtil();
        outstream.download2(homeworkStatusVoList,response);
    }

    @Override
    public Result giveScore(HomeworkStatusVo homeworkStatusVo) {
        HandleResult hr=new HandleResult();
        if(1==teacherHomeworkMapper.giveScore(homeworkStatusVo)){
            return hr.outResultWithoutData("0","评分成功！");
        }else{
            return hr.outResultWithoutData("1","服务异常");
        }
    }

    @Override
    public Result findHomeworkListByKey(String uid, String key) {
        HandleResult hr=new HandleResult();
        try {
            return hr.outResultWithData("0","查询成功",teacherHomeworkMapper.findHomeworkStatuByKey(uid,key));
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常");
        }
    }

    @Override
    public Result findHomeworkList(String uid) {
        HandleResult hr=new HandleResult();
        try {
            return hr.outResultWithData("0","查询成功",teacherHomeworkMapper.findHomworkList(uid));
        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","服务异常");
        }
    }

    @Override
    public Result delHomework(String homeworkId) {
        HandleResult hr=new HandleResult();
        try {
            if(1==teacherHomeworkMapper.delHomework(homeworkId)){
                return hr.outResultWithoutData("0","删除成功");
            }

        }catch (Exception e){
            log.error(e.getMessage());
            return hr.outResultWithoutData("1","删除失败，服务异常");
        }
        return null;
    }


}
