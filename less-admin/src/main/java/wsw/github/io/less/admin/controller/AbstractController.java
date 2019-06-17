package wsw.github.io.less.admin.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import wsw.github.io.less.dao.entity.SysUser;

public abstract class AbstractController {
    public Authentication getCurrentUserAuthentication () {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    public SysUser getCurrentUser() {
        return (SysUser) getCurrentUserAuthentication().getPrincipal();
    }
}
