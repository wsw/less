package wsw.github.io.less.admin.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import wsw.github.io.less.common.exception.LessException;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.common.util.ResultEnum;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.service.SysUserService;

@RestController
@AllArgsConstructor
@RequestMapping("/sys/")
public class SysUserController extends AbstractController {

    private SysUserService sysUserService;
    private PasswordEncoder passwordEncoder;

    @PostMapping("user")
    public R addUser(@RequestBody SysUser user) {
        if (user.getUsername() != null && user.getPassword() != null) {
            if (sysUserService.findByUsername(user.getUsername()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                sysUserService.save(user);
            } else {
                throw new LessException(ResultEnum.USER_DUPLICATE);
            }
        } else {
            throw new LessException(ResultEnum.LACK_OF_USERINFO);
        }
        return R.ok("新增用户成功！");
    }

    @GetMapping("hello")
    public R hello() {
        R result = new R();
        return result.ok().put("count", getCurrentUser());
    }

    @GetMapping("user")
    public R user() {
        return R.ok().put("user", getCurrentUser());
    }
}
