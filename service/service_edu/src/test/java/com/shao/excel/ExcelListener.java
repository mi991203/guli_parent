package com.shao.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.metadata.CellData;

import java.util.Map;

public class ExcelListener extends AnalysisEventListener<ExcelData> {
    @Override
    public void invoke(ExcelData excelData, AnalysisContext analysisContext) {
        System.out.println("***"+excelData);
    }

    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
