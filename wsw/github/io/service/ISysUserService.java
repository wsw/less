package {Entity=wsw.github.io.entity, Mapper=wsw.github.io.mapper, ModuleName=null, Xml=wsw.github.io.mapper.xml, ServiceImpl=wsw.github.io.service.impl, Service=wsw.github.io.service, Controller=wsw.github.io.controller}.${moduleName}.service;

import com.baomidou.mybatisplus.extension.service.IService;
import ${mainPath}.common.util.PageUtils;
import {Entity=wsw.github.io.entity, Mapper=wsw.github.io.mapper, ModuleName=null, Xml=wsw.github.io.mapper.xml, ServiceImpl=wsw.github.io.service.impl, Service=wsw.github.io.service, Controller=wsw.github.io.controller}.${moduleName}.entity.${className}Entity;

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
