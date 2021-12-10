package com.example.demo;

import com.example.demo.Service.TestService;
import com.example.demo.Service.TspService;
import com.example.demo.pojo.PutStorageInfo;
import com.example.demo.pojo.Tsp;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    TspService tspService;
    @Autowired
    TestService testService;
    @Test
    void contextLoads() {
        List<Tsp> findlist = tspService.findlist();
        for (Tsp tsp : findlist) {
            String xgjb = tspService.xgjb(Integer.valueOf(tsp.getXgjb1()));//相关疾病
            int id = tsp.getId();
            System.out.println(id);
            String jyxx=null;
            if (!StringUtils.isBlank(tsp.getJyjcxx1())){
                 jyxx = tspService.jyxx(Integer.valueOf(tsp.getJyjcxx1()));//基因基础信息
            }
            String jbzd = tsp.getJbzd();//鉴别诊断
            String bxzl = tsp.getBxzl();//靶向治疗
            String yhpg = tsp.getYhpg();//预后评估
            String mrd = tsp.getMrd();//MRD
            String qthpx = tsp.getMrd();//其他（含胚系）
            String xhtl = tsp.getXhtl();//信号通路
            String lcyy = "";//临床意义
            String zhyy = ""; //整合意义
            if (!StringUtils.isBlank(jbzd)) {
                lcyy = lcyy + jbzd + "\n";
            }
            if (!StringUtils.isBlank(bxzl)) {
                lcyy = lcyy + bxzl + "\n";
            }
            if (!StringUtils.isBlank(yhpg)) {
                lcyy = lcyy + yhpg + "\n";
            }
            if (!StringUtils.isBlank(mrd)) {
                lcyy = lcyy + mrd + "\n";
            }
            if (!StringUtils.isBlank(qthpx)) {
                lcyy = lcyy + qthpx;
            }
            if (StringUtils.isBlank(lcyy)){
                if (StringUtils.isBlank(xhtl)){
                    if (StringUtils.isBlank(jyxx)){
                        zhyy="未见明确报道";
                    }else {
                        zhyy=jyxx;
                    }
                }
                lcyy="在"+xgjb+"疾病中未见明确报道";
            }
            if (!StringUtils.isBlank(lcyy)) {
                if (!StringUtils.isBlank(jyxx)) {
                    zhyy = zhyy + jyxx + "隶属于\n";
                }
                if (!StringUtils.isBlank(xhtl)) {
                    zhyy = zhyy + xhtl + "\n";
                }
                zhyy = zhyy + lcyy;
            }
            boolean up = tspService.up(zhyy, id);
        }
    }
    @Test
    void test(){
        List<PutStorageInfo> putStorageInfos = testService.readExcelByPOI("E:\\报批\\experiment_template2.xls");
        for (PutStorageInfo putStorageInfo : putStorageInfos) {
            System.out.println(putStorageInfo);
        }
    }

}
