package io.github.wsw.less.modules.codegen.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface SysGeneratorMapper {

    Map<String, String> queryTable(String tableName);

    List<Map<String, String>> queryColumns(String tableName);

    IPage<List<Map<String, Object>>> queryList(Page page, @Param("tableName") String tableName);
}
