package wsw.github.io.less.admin.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.CircleCaptcha;
import cn.hutool.core.date.DateTime;
import lombok.AllArgsConstructor;
import lombok.Cleanup;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import wsw.github.io.less.admin.form.SysLoginForm;
import wsw.github.io.less.common.exception.LessException;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.common.util.ResultEnum;
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
            throw new LessException(ResultEnum.CAPTCHA_WRONG);
        }
        if (captcha.getExpireTime().getTime() < DateTime.now().getTime()) {
            throw new LessException(ResultEnum.CAPTCHA_EXPIRE);
        }

        SysUser sysUser = sysUserService.findByUsername(form.getUsername());
        if (sysUser == null || !passwordEncoder.matches(form.getPassword(), sysUser.getPassword())) {
            // 密码比较用matches
            throw new LessException(ResultEnum.LOGIN_WRONG);
        }

        //账号锁定  其他账号相关的
        if(sysUser.getStatus() == 0){
            throw new LessException(ResultEnum.USER_INVALID);
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
