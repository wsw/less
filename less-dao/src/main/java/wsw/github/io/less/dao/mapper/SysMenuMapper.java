package wsw.github.io.less.dao.mapper;

import wsw.github.io.less.dao.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wsw.github.io.less.dao.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 菜单管理 Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    List<SysMenu> listMenusByRoles(List<SysRole> list);
}
