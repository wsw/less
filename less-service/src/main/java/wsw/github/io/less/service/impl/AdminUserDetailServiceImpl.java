package wsw.github.io.less.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import wsw.github.io.less.common.exception.LessException;
import wsw.github.io.less.common.util.Constant;
import wsw.github.io.less.dao.entity.SysRole;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.service.SysRoleService;
import wsw.github.io.less.service.SysUserService;

import java.util.ArrayList;
import java.util.List;

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

        //定义权限列表. // 给超级用户直接设置一个SUPER
        if (user.getUserId().equals(Constant.SUPER_USER_ID)) {
            SysRole sysRole = new SysRole();
            sysRole.setRoleId(0l);
            sysRole.setRoleName("SUPER");
            List<SysRole> roles = new ArrayList<>();
            roles.add(sysRole);
            user.setRoles(roles);
        } else {
            user.setRoles(sysRoleService.listRolesByUserId(user.getUserId()));
        }

        return user;
    }
}
