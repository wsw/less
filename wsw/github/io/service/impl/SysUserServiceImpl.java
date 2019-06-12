package {Entity=wsw.github.io.entity, Mapper=wsw.github.io.mapper, ModuleName=null, Xml=wsw.github.io.mapper.xml, ServiceImpl=wsw.github.io.service.impl, Service=wsw.github.io.service, Controller=wsw.github.io.controller}.${moduleName}.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import ${mainPath}.common.utils.PageUtils;
import ${mainPath}.common.utils.Query;

import {Entity=wsw.github.io.entity, Mapper=wsw.github.io.mapper, ModuleName=null, Xml=wsw.github.io.mapper.xml, ServiceImpl=wsw.github.io.service.impl, Service=wsw.github.io.service, Controller=wsw.github.io.controller}.${moduleName}.dao.${className}Dao;
import {Entity=wsw.github.io.entity, Mapper=wsw.github.io.mapper, ModuleName=null, Xml=wsw.github.io.mapper.xml, ServiceImpl=wsw.github.io.service.impl, Service=wsw.github.io.service, Controller=wsw.github.io.controller}.${moduleName}.entity.${className}Entity;
import {Entity=wsw.github.io.entity, Mapper=wsw.github.io.mapper, ModuleName=null, Xml=wsw.github.io.mapper.xml, ServiceImpl=wsw.github.io.service.impl, Service=wsw.github.io.service, Controller=wsw.github.io.controller}.${moduleName}.service.${className}Service;


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