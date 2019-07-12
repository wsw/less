package wsw.github.io.less.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import wsw.github.io.less.dao.entity.SysRole;
import wsw.github.io.less.dao.entity.SysRoleMenu;
import wsw.github.io.less.dao.mapper.SysRoleMapper;
import wsw.github.io.less.service.SysRoleMenuService;
import wsw.github.io.less.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMenuService sysRoleMenuService;

    @Override
    public List<SysRole> listRolesByUserId(long id) {
        return baseMapper.listRolesByUserId(id);
    }

    @Override
    public List<SysRole> listByMenuId(long id) {
        return baseMapper.listByMenuId(id);
    }

    @Override
    public boolean addRole(SysRole role) {

        this.save(role);

        List<SysRoleMenu> roleMenus = getRoleMenu(role);

        return sysRoleMenuService.saveBatch(roleMenus);
    }

    @Override
    public boolean updateRole(SysRole role) {
        this.saveOrUpdate(role);

        List<SysRoleMenu> roleMenus = getRoleMenu(role);

        this.removeRoleMenu(role.getRoleId());

        return sysRoleMenuService.saveBatch(roleMenus);
    }

    @Override
    public boolean delRole(long id) {
        this.removeRoleMenu(id);
        return this.removeById(id);
    }

    private List<SysRoleMenu> getRoleMenu(SysRole role) {
        return role.getMenuIds().stream().map(menuId -> {
            SysRoleMenu sysRoleMenu = new SysRoleMenu();
            sysRoleMenu.setMenuId(menuId);
            sysRoleMenu.setRoleId(role.getRoleId());
            return sysRoleMenu;
        }).collect(Collectors.toList());
    }

    private void removeRoleMenu(long id) {
        QueryWrapper<SysRoleMenu> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("role_id", id);
        sysRoleMenuService.remove(queryWrapper);
    }
}
