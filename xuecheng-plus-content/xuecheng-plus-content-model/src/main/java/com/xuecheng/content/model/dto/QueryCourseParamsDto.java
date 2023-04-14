package com.xuecheng.content.model.dto;

import lombok.Data;
import lombok.ToString;

/**
 * @author 马里亚纳大海沟
 * @version 1.0
 * @description TODO
 * @date 2023/4/13 15:45
 */
@Data
@ToString
public class QueryCourseParamsDto {
    //审核状态
    private String auditStatus;
    //课程名称
    private String courseName;
    //发布状态
    private String publishStatus;
}
