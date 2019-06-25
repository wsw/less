package wsw.github.io.less.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import wsw.github.io.less.dao.entity.SysRoleMenu;
import wsw.github.io.less.dao.mapper.SysRoleMenuMapper;
import wsw.github.io.less.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {

    @Override
    public List<SysRoleMenu> findByMenuId(long id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("menu_id", id);
        return baseMapper.selectList(queryWrapper);
    }
}
