package com.example.demo.Service;


import com.example.demo.pojo.PutStorageInfo;
import javafx.scene.control.Cell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName TestService
 * @Description TODO
 * @Author Dave
 * @Date 2021/12/8 9:06
 * @Version 1.0
 **/
@Service
public class TestService {
    public  List<PutStorageInfo> readExcelByPOI(String filePath){
        List<PutStorageInfo> infoList =new ArrayList<PutStorageInfo>();
        Map<String, List<String>> map =new HashMap<String,List<String>>();
        infoList.clear();
        try{
            InputStream is =new FileInputStream(filePath);

            int index = filePath.lastIndexOf(".");
            String postfix = filePath.substring(index+1);

            Workbook workbook =null;
            if("xls".equals(postfix)){
                workbook =new HSSFWorkbook(is);
            }else if("xlsx".equals(postfix)){
                workbook =new XSSFWorkbook(is);
            }
            //获取第1张表
            Sheet sheet = workbook.getSheetAt(0);
            //总的行数
            int rows = sheet.getLastRowNum();
            //总的列数--->最后一列为null则有问题，读取不完整，将表头的数目作为总的列数，没有的则补为null
            int columns = sheet.getRow(0).getLastCellNum();
            //先列后行
            for(int i =1; i <= rows; i++){
          Row row = sheet.getRow(i);
                if(null!= row && row.getFirstCellNum()==-1){//这一行是空行，不读取
                    continue;
                }
                //这一行的总列数
                // columns = row.getLastCellNum();
                List<String> contentList =new ArrayList<String>();
                contentList.clear();
                for(int j =0; j < columns; j++){
                    if(row.getCell(j)!=null){
//                        row.getCell(j).setCellType(Cell.CELL_TYPE_STRING);
                        contentList.add(row.getCell(j).getStringCellValue());
                    }else{
                        contentList.add("");
                    }
                }
                map.put("StorageInfo"+i, contentList);
            }

            //遍历map集合，封装成bean
            for(Map.Entry<String,List<String>> entry : map.entrySet()){
                List<String> list = entry.getValue();
                PutStorageInfo storageInfo =new PutStorageInfo();
                storageInfo.setProductcode(list.get(0));
                storageInfo.setProductsort(list.get(1));
                storageInfo.setProductbrand(list.get(2));
//                storageInfo.setProductname(list.get(3));
//                storageInfo.setProductquantity(list.get(4));
//                storageInfo.setProductcontent(list.get(5));
//                storageInfo.setProductnetweight(list.get(6));
//                storageInfo.setProductcountry(list.get(7));
//                storageInfo.setProductpdate(list.get(8));
//                storageInfo.setProductprice(list.get(9));
//                storageInfo.setProductmark(list.get(10));

                infoList.add(storageInfo);
            }
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return infoList;
    }

}
