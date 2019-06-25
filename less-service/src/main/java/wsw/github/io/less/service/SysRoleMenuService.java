package wsw.github.io.less.service;

import wsw.github.io.less.dao.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色与菜单对应关系 服务类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {
    List<SysRoleMenu> findByMenuId(long id);
}
