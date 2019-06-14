package wsw.github.io.less.admin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import wsw.github.io.less.common.util.R;

@RestController
public class AuthController {

    @GetMapping("/auth/login")
    public R login() {
        return R.error("请登录");
    }

    @PostMapping("/auth/login")
    public R loginForm() {
        return R.ok("成功");
    }
}
