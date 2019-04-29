package io.github.wsw.less.modules.codegen.entity;

import lombok.Data;

@Data
public class GenConfig {
    private String packageName;
    private String author;
    private String moduleName;
    private String tablePrefix;
    private String tableName;
    private String comments;
}
