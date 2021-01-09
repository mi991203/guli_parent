package com.shao.eduservice.service;

import com.shao.eduservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.shao.eduservice.entity.subject.OneSubject;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author ShaoHong
 * @since 2021-01-05
 */
public interface EduSubjectService extends IService<EduSubject> {

    /**
     * 添加课程信息
     * @param multipartFile 上传的文件
     * @param eduSubjectService 课程科目服务类
     */
    void saveSubject(MultipartFile multipartFile, EduSubjectService eduSubjectService);

    /**
     * 获取课程分类列表（树形）
     * @return 课程分类列表集合
     */
    List<OneSubject> getAllOneTwoSubject();

}
