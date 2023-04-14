package com.xuecheng.content;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xuecheng.base.model.PageParams;
import com.xuecheng.base.model.PageResult;
import com.xuecheng.content.mapper.CourseBaseMapper;
import com.xuecheng.content.model.dto.CourseCategoryTreeDto;
import com.xuecheng.content.model.dto.QueryCourseParamsDto;
import com.xuecheng.content.model.po.CourseBase;
import com.xuecheng.content.service.CourseBaseInfoService;
import com.xuecheng.content.service.CourseCategoryService;
import org.apache.commons.lang.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @author 马里亚纳大海沟
 * @version 1.0
 * @description 课程测试
 * @date 2023/4/13 16:10
 */
@SpringBootTest
public class CourseBaseMapperTests {
    @Autowired
    private CourseBaseMapper courseBaseMapper;

    @Autowired
    private CourseBaseInfoService courseBaseInfoService;

    @Test
    void testCourseBaseMapper() {
        CourseBase courseBase = courseBaseMapper.selectById(74L);
//        Assertions.assertNotNull(courseBase);
        System.out.println(courseBase);

        QueryCourseParamsDto courseParamsDto =new QueryCourseParamsDto();
        courseParamsDto.setCourseName("java");
        courseParamsDto.setAuditStatus("");

        int pageNo =1 ;
        int pageSize =5 ;
        IPage page = new Page(pageNo,pageSize);
        LambdaQueryWrapper<CourseBase> wrapper = new LambdaQueryWrapper();
        wrapper.like(StringUtils.isNotEmpty(courseParamsDto.getCourseName()),CourseBase::getName,courseParamsDto.getCourseName());

        wrapper.eq(StringUtils.isNotEmpty(courseParamsDto.getAuditStatus()),CourseBase::getAuditStatus,courseParamsDto.getAuditStatus());
        IPage iPage = courseBaseMapper.selectPage(page, wrapper);
        List<CourseBase> records = iPage.getRecords();
        for (CourseBase cb:records
        ) {
            System.out.println(cb.toString());
        }

        PageResult<CourseBase> pageResult = new PageResult<>(records,iPage.getTotal(),pageNo,pageSize);
        System.out.println(pageResult);
    }

    @Test
    void testCourseBaseService() {
        PageParams pageParams = new PageParams(1, 5);
        QueryCourseParamsDto queryCourseParamsDto = new QueryCourseParamsDto();
        queryCourseParamsDto.setCourseName("java");
        PageResult<CourseBase> pageResult = courseBaseInfoService.queryCourseBaseList(pageParams, queryCourseParamsDto);
        System.out.println(pageResult);
    }


    @Autowired
    CourseCategoryService courseCategoryService;


    @Test
    void testqueryTreeNodes() {
        List<CourseCategoryTreeDto> categoryTreeDtos = courseCategoryService.queryTreeNodes();
        System.out.println(categoryTreeDtos);
    }
}
