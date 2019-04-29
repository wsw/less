package io.github.wsw.less.modules.codegen.entity;

import lombok.Data;

import java.util.List;

@Data
public class TableEntity {
    private String tableName;
    private String comments;
    private String caseClassName;
    private String lowerClassName;
    private List<ColumnEntity> columns;
    private ColumnEntity pk;
}
