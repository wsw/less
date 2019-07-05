package wsw.github.io.less.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wsw.github.io.less.dao.entity.SysMenu;
import wsw.github.io.less.dao.entity.SysRole;
import wsw.github.io.less.dao.mapper.SysMenuMapper;
import wsw.github.io.less.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Override
    public List<SysMenu> listMenusByRoles(List<SysRole> roles) {
        if (roles.stream().filter(role -> role.getRoleName().equals("SUPER")) != null){
            QueryWrapper query = new QueryWrapper();
            query.orderByAsc("order_num");
            return baseMapper.selectList(query);
        } else {
            return baseMapper.listMenusByRoles(roles);
        }
    }
}
