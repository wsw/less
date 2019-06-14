package wsw.github.io.less.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.service.SysUserService;

@RestController
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/hello")
//    @Secured("ROLE_Root")
    public R hello() {
        R result = new R();
        return result.put("count", sysUserService.count());
    }
}