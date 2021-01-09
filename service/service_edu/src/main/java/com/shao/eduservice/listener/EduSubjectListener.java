package com.shao.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.eduservice.entity.EduSubject;
import com.shao.eduservice.entity.excel.SubjectData;
import com.shao.eduservice.service.EduSubjectService;
import com.shao.servicebase.exceptionhandler.GuliException;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class EduSubjectListener extends AnalysisEventListener<SubjectData> {
    private EduSubjectService eduSubjectService;

    public EduSubjectListener() {}

    public EduSubjectListener(EduSubjectService eduSubjectService) {
        this.eduSubjectService = eduSubjectService;
    }

    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (ObjectUtils.isEmpty(data)) {
            throw new GuliException(20001, "上传的excel文件中没有相应数据");
        }
        if (!StringUtils.isEmpty(data.getOneSubjectName())) {
            EduSubject eduOneSubject = existOneSubject(data.getOneSubjectName());
            if (ObjectUtils.isEmpty(eduOneSubject)) {
                eduOneSubject = new EduSubject().setTitle(data.getOneSubjectName()).setParentId("0");
                eduSubjectService.save(eduOneSubject);
            }
            if (!StringUtils.isEmpty(data.getTwoSubjectName())) {
                EduSubject eduTwoSubject = existTwoSubject(data.getTwoSubjectName(), eduOneSubject.getId());
                if (ObjectUtils.isEmpty(eduTwoSubject)) {
                    eduTwoSubject = new EduSubject().setTitle(data.getTwoSubjectName()).setParentId(eduOneSubject.getId());
                    eduSubjectService.save(eduTwoSubject);
                }
            }
        }else {
            throw new GuliException(20002, "上传的excel表格中存在空一级分类的值");
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    /**
     * 根据name属性查询数据库中是否存在这条记录
     * @param name 课程名
     * @return 数据库这天条记录对应的实体类
     */
    private EduSubject existOneSubject(String name) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", 0);
        return eduSubjectService.getOne(wrapper);
    }

    /**
     * 更具name和parent_id这两列查询数据库中是否存在对应的记录
     * @param name 课程名
     * @param pId 父目录ID
     * @return 二级课程对应实体类
     */
    private EduSubject existTwoSubject(String name, String pId) {
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title", name);
        wrapper.eq("parent_id", pId);
        return eduSubjectService.getOne(wrapper);
    }
}
