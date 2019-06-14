package wsw.github.io.less.dao.mapper;

import wsw.github.io.less.dao.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import wsw.github.io.less.dao.entity.SysUserRole;

import java.util.List;

/**
 * <p>
 * 角色 Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> listRolesByUserId(long id);
}
