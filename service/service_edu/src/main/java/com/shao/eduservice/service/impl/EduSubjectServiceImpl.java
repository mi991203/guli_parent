package com.shao.eduservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.shao.eduservice.entity.excel.SubjectData;
import com.shao.eduservice.entity.subject.OneSubject;
import com.shao.eduservice.entity.subject.TwoSubject;
import com.shao.eduservice.entity.EduSubject;
import com.shao.eduservice.listener.EduSubjectListener;
import com.shao.eduservice.mapper.EduSubjectMapper;
import com.shao.eduservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-05
 */
@Service
@Slf4j
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {

    @Override
    public void saveSubject(MultipartFile multipartFile, EduSubjectService eduSubjectService) {
        try (InputStream is = multipartFile.getInputStream()) {
            EasyExcel.read(is, SubjectData.class, new EduSubjectListener(eduSubjectService)).sheet().doRead();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<OneSubject> getAllOneTwoSubject() {
        // 首先查询所以一级分类
        QueryWrapper<EduSubject> oneWrapper = new QueryWrapper<>();
        oneWrapper.eq("parent_id", "0");
        List<EduSubject> oneList = baseMapper.selectList(oneWrapper);
        // 然后查询所有的二级分类
        QueryWrapper<EduSubject> twoWrapper = new QueryWrapper<>();
        twoWrapper.ne("parent_id", "0");
        List<EduSubject> twoList = baseMapper.selectList(twoWrapper);
        // 根据oneList和twoList对象进行操作形成对应的结果
        List<OneSubject> resultList = new ArrayList<>();
        oneList.forEach(oneEduSubject -> {
            OneSubject oneSubject = new OneSubject();
            oneSubject.setId(oneEduSubject.getId());
            oneSubject.setLabel(oneEduSubject.getTitle());
            List<TwoSubject> twoSubjects = new ArrayList<>();
            twoList.forEach(twoEduSubject -> {
                if (oneEduSubject.getId().equals(twoEduSubject.getParentId())) {
                    TwoSubject twoSubject = new TwoSubject();
                    twoSubject.setId(twoEduSubject.getId());
                    twoSubject.setLabel(twoEduSubject.getTitle());
                    twoSubjects.add(twoSubject);
                }
            });
            oneSubject.setChildren(twoSubjects);
            resultList.add(oneSubject);
        });
        return resultList;
    }
}
