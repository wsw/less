package wsw.github.io.less.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.service.SysMenuService;

@RestController
@RequestMapping("/sys/")
@AllArgsConstructor
public class SysMenuController extends AbstractController {

    private SysMenuService sysMenuService;

    @GetMapping("menus")
    public R menus() {
        SysUser user = getCurrentUser();
        return R.ok().put("menus", sysMenuService.listMenusByRoles(user.getRoles()));
    }
}
