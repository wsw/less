package wsw.github.io.less.admin.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import wsw.github.io.less.admin.form.SysLoginForm;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.dao.entity.SysCaptcha;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.service.SysCaptchaService;
import wsw.github.io.less.service.SysUserService;
import wsw.github.io.less.service.SysUserTokenService;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@AllArgsConstructor
public class AuthController extends AbstractController {

    SysCaptchaService sysCaptchaService;
    SysUserService sysUserService;
    PasswordEncoder passwordEncoder;
    SysUserTokenService sysUserTokenService;

    @PostMapping("/auth/login")
    public R loginForm(@RequestBody SysLoginForm form) {
        SysCaptcha captcha = sysCaptchaService.validate(form.getUuid(), form.getCode());
        if (captcha == null) {
            return R.error("验证码不正确");
        }
        if (captcha.getExpireTime().getTime() < DateTime.now().getTime()) {
            return R.error("验证码过期了");
        }

        SysUser sysUser = sysUserService.findByUsername(form.getUsername());
        if (sysUser == null || !sysUser.getPassword().equals(passwordEncoder.encode(form.getPassword()))) {
            return R.error("账号或密码不正确");
        }

        //账号锁定  其他账号相关的
        if(sysUser.getStatus() == 0){
            return R.error("账号已被锁定,请联系管理员");
        }

        return sysUserTokenService.createToken(sysUser.getUsername());
    }

    @GetMapping("/auth/captcha.jpg")
    public void captcha(HttpServletResponse response, @RequestParam("uuid") String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        CircleCaptcha captcha = CaptchaUtil.createCircleCaptcha(200, 100, 4, 20);

        sysCaptchaService.createCaptcha(uuid, captcha.getCode());

        @Cleanup ServletOutputStream out = response.getOutputStream();

        captcha.write(out);
    }

    @GetMapping("auth/logout")
    public R logout() {
        sysUserTokenService.logout(getCurrentUser().getUsername());
        return R.ok("登出成功!");
    }
}
