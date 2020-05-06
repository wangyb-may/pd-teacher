package com.bysj.wyb.teacher.vo;

import lombok.Data;

/**
 * @author wangyb
 */
@Data
public class HomeworkStatusVo {
    String homeworkId;

    String homeworkName;

    String context;

    String stuId;

    String stuName;

    String classNumber;

    String createTime;

    String upStatu;

    String url;

    int score;

}
