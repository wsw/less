package wsw.github.io.less.service;

import wsw.github.io.less.dao.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import wsw.github.io.less.dao.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 菜单管理 服务类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenu> listMenusByRoles(List<SysRole> roles, boolean isSuper);
}
