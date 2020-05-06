package com.bysj.wyb.teacher.entity;

import lombok.Data;

import javax.persistence.Entity;

/**
 * @author wangyb
 */
@Entity
@Data
public class Homework {

    String id;

    String name;

    String createtime;

    String classnumber;

    String courseid;

    String teacherid;

    String context;


}
