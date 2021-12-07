package com.example.demo.pojo;

import lombok.Data;

import javax.persistence.Column;

/**
 * @author Dave
 * @date 2021/10/26 16:53
 */
@Data
public class Tsp {
    private String zhyy = null;//整合意义
    @Column(name = "xgjb1")
    private String xgjb1;//相关疾病
    @Column(name = "jyjcxx1")
    private String jyjcxx1;//基因基础信息
    @Column(name = "xhtl")
    private String xhtl;//信号通路
    @Column(name = "jbzd")
    private String jbzd;//鉴别诊断
    @Column(name = "bxzl")
    private String bxzl;//靶向治疗
    @Column(name = "yhpg")
    private String yhpg;//预后评估
    @Column(name = "mrd")
    private String mrd;//MRD
    @Column(name = "qthpx")
    private String qthpx;//其他（含胚系）
    @Column(name = "lcyy")
    private String lcyy;//临床意义

    @Column(name = "id")
    private int id;
}
