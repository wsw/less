package {Entity=wsw.github.io.admin.entity, Mapper=wsw.github.io.admin.mapper, ModuleName=admin, Xml=wsw.github.io.admin.mapper.xml, ServiceImpl=wsw.github.io.admin.service.impl, Service=wsw.github.io.admin.service, Controller=wsw.github.io.admin.controller}.${moduleName}.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${mainPath}.common.utils.PageUtils;
import ${mainPath}.common.utils.Query;

import {Entity=wsw.github.io.admin.entity, Mapper=wsw.github.io.admin.mapper, ModuleName=admin, Xml=wsw.github.io.admin.mapper.xml, ServiceImpl=wsw.github.io.admin.service.impl, Service=wsw.github.io.admin.service, Controller=wsw.github.io.admin.controller}.${moduleName}.dao.${className}Dao;
import {Entity=wsw.github.io.admin.entity, Mapper=wsw.github.io.admin.mapper, ModuleName=admin, Xml=wsw.github.io.admin.mapper.xml, ServiceImpl=wsw.github.io.admin.service.impl, Service=wsw.github.io.admin.service, Controller=wsw.github.io.admin.controller}.${moduleName}.entity.${className}Entity;
import {Entity=wsw.github.io.admin.entity, Mapper=wsw.github.io.admin.mapper, ModuleName=admin, Xml=wsw.github.io.admin.mapper.xml, ServiceImpl=wsw.github.io.admin.service.impl, Service=wsw.github.io.admin.service, Controller=wsw.github.io.admin.controller}.${moduleName}.service.${className}Service;


@Service("${classname}Service")
public class ${className}ServiceImpl extends ServiceImpl<${className}Dao, ${className}Entity> implements ${className}Service {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<${className}Entity> page = this.page(
                new Query<${className}Entity>().getPage(params),
                new QueryWrapper<${className}Entity>()
        );

        return new PageUtils(page);
    }

}