package {Entity=wsw.github.io.admin.entity, Mapper=wsw.github.io.admin.mapper, ModuleName=admin, Xml=wsw.github.io.admin.mapper.xml, ServiceImpl=wsw.github.io.admin.service.impl, Service=wsw.github.io.admin.service, Controller=wsw.github.io.admin.controller}.${moduleName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${mainPath}.common.util.PageUtils;
import {Entity=wsw.github.io.admin.entity, Mapper=wsw.github.io.admin.mapper, ModuleName=admin, Xml=wsw.github.io.admin.mapper.xml, ServiceImpl=wsw.github.io.admin.service.impl, Service=wsw.github.io.admin.service, Controller=wsw.github.io.admin.controller}.${moduleName}.entity.${className}Entity;

import java.util.Map;

/**
 * ${comments}
 *
 * @author wsw
 * @email ${email}
 * @date ${datetime}
 */
public interface ${className}Service extends IService<${className}Entity> {

    PageUtils queryPage(Map<String, Object> params);
}
