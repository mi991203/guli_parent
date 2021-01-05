package com.shao.excel;

import com.alibaba.excel.EasyExcel;

public class ExcelReadTest {
    public static void main(String[] args) {
        String filename = "write.xlsx";
        EasyExcel.read(filename, ExcelData.class, new ExcelListener()).sheet("第二个表格").doRead();
    }

}
