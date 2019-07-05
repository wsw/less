package wsw.github.io.less.admin.controller;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import wsw.github.io.less.common.exception.LessException;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.common.util.RequestPage;
import wsw.github.io.less.common.util.ResultEnum;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.dao.entity.SysUserRole;
import wsw.github.io.less.dao.util.PageUtils;
import wsw.github.io.less.service.SysUserRoleService;
import wsw.github.io.less.service.SysUserService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/sys/")
public class SysUserController extends AbstractController {

    private SysUserService sysUserService;
    private PasswordEncoder passwordEncoder;
    private SysUserRoleService sysUserRoleService;

    @PostMapping("user")
    public R addUser(@RequestBody Map<String, Object> obj) {
        SysUser user = new SysUser();
        user.setUsername((String)obj.get("username"));
        user.setPassword((String)obj.get("password"));
        if (user.getUsername() != null && user.getPassword() != null) {
            if (sysUserService.findByUsername(user.getUsername()) == null) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
                user.setMobile((String)obj.get("mobile"));
                user.setStatus((int)obj.get("status"));
                user.setEmail((String)obj.get("email"));
                user.setCreateUserId(getCurrentUser().getUserId());
                user.setCreateTime(DateTime.now());
                sysUserService.addUser(user, Convert.toLongArray(obj.get("roles")));
            } else {
                throw new LessException(ResultEnum.USER_DUPLICATE);
            }
        } else {
            throw new LessException(ResultEnum.LACK_OF_USERINFO);
        }
        return R.ok("新增用户成功！");
    }

    @PutMapping("user")
    public R updateUser(@RequestBody Map<String, Object> obj) {
        SysUser user = new SysUser();
        user.setUserId(Long.valueOf((Integer)obj.get("userId")));
        user.setUsername((String)obj.get("username"));
        if (obj.get("password") != null) {
            user.setPassword(passwordEncoder.encode((String)obj.get("password")));
        }
        user.setMobile((String)obj.get("mobile"));
        user.setStatus((Integer) obj.get("status"));
        user.setEmail((String)obj.get("email"));

        return R.ok().put("status", sysUserService.updateUser(user, Convert.toLongArray(obj.get("roles"))));
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

    @GetMapping("users")
    public R users(@RequestParam Map<String, Object> params) {

        if (!getCurrentUser().equals(1)) {
            params.put("createUserId", getCurrentUser().getUserId());
        }

        return R.ok().put("page", sysUserService.queryPage(params));
    }
}
