package com.bysj.wyb.teacher.vo;

import lombok.Data;

import java.util.List;

/**
 * @author wangyb
 */
@Data
public class HomeworkInformationVo {

    String teacherId;

    List<String> classNumberList;

    List<String> homeworkList;
}
