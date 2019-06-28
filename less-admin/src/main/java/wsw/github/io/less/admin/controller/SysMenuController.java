package wsw.github.io.less.admin.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.dao.entity.SysMenu;
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

    @PostMapping("menu")
    public R addMenu(@RequestBody SysMenu sysMenu) {
        return R.ok().put("status", sysMenuService.save(sysMenu));
    }

    @PutMapping("menu")
    public R updateMenu(@RequestBody SysMenu sysMenu){
        return R.ok().put("status", sysMenuService.updateById(sysMenu));
    }

    @DeleteMapping("menu")
    public R delMenu(@RequestParam("id") long id) {
        return R.ok().put("status", sysMenuService.removeById(id));
    }
}
