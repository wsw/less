package wsw.github.io.less.service.impl;

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
        return baseMapper.listMenusByRoles(roles);
    }
}
