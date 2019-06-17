package wsw.github.io.less.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import wsw.github.io.less.common.exception.LessException;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.service.SysRoleService;
import wsw.github.io.less.service.SysUserService;

@Service("adminUserDetailService")
public class AdminUserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public UserDetails loadUserByUsername(String s) {

        //通过username获取用户信息
        SysUser user = sysUserService.findByUsername(s);
        if (user == null) {
            throw new LessException("用户不存在", 1000);
        }

        //定义权限列表.
        user.setRoles(sysRoleService.listRolesByUserId(user.getUserId()));

        return user;
    }
}
