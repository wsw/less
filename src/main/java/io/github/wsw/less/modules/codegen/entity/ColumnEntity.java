package io.github.wsw.less.modules.codegen.entity;

import lombok.Data;

@Data
public class ColumnEntity {
    private String columnName; // 列名
    private String dataType; // 数据类型
    private String comments;
    private String caseAttrName;
    private String lowerAttrName;
    private String attrType;
    private String extra;
}
