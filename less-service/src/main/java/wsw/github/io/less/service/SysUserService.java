package wsw.github.io.less.service;

import wsw.github.io.less.dao.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import wsw.github.io.less.dao.util.PageUtils;

import java.util.Map;

/**
 * <p>
 * 系统用户 服务类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysUserService extends IService<SysUser> {
    public SysUser findByUsername(String username);
    public PageUtils queryPage(Map<String, Object> params);
    public boolean addUser(SysUser sysUser, Long[] roles);
    public boolean updateUser(SysUser sysUser, Long[] roles);
}
