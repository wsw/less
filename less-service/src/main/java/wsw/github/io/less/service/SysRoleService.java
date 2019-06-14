package wsw.github.io.less.service;

import wsw.github.io.less.dao.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 角色 服务类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> listRolesByUserId(long id);
}
