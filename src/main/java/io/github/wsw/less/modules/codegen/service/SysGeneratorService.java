package io.github.wsw.less.modules.codegen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.github.wsw.less.modules.codegen.entity.GenConfig;

import java.util.List;
import java.util.Map;

public interface SysGeneratorService {
    /**
     * 生成代码
     *
     * @param tableNames 表名称
     * @return
     */
    byte[] generatorCode(GenConfig tableNames);


    IPage<List<Map<String, Object>>> queryPage(Page page, String tableName);
}
