package com.shao.excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.metadata.WriteSheet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class ExcelWriteTest {
    public static void main(String[] args) {
        String fileName = "write.xlsx";
        // 写入方法一
        /*EasyExcel.write(fileName, ExcelData.class).sheet(1,"学生信息").doWrite(getList());*/
        // 写入方法二
        ExcelWriter excelWriter = EasyExcel.write(fileName, ExcelData.class).build();
        WriteSheet writeSheet = EasyExcel.writerSheet().build();
        excelWriter.write(getList(), writeSheet);
        excelWriter.finish();
    }
    private static List<ExcelData> getList() {
        ArrayList<ExcelData> list = new ArrayList<>();
        IntStream.range(0, 10).forEach(i -> {
            ExcelData excelData = new ExcelData();
            excelData.setSno(i);
            excelData.setSname("Jack"+i);
            list.add(excelData);
        });
        return list;
    }
}
