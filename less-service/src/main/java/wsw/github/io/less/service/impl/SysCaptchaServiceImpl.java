package wsw.github.io.less.service.impl;

import cn.hutool.core.date.DateTime;
import wsw.github.io.less.dao.entity.SysCaptcha;
import wsw.github.io.less.dao.mapper.SysCaptchaMapper;
import wsw.github.io.less.service.SysCaptchaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统验证码 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-17
 */
@Service
public class SysCaptchaServiceImpl extends ServiceImpl<SysCaptchaMapper, SysCaptcha> implements SysCaptchaService {

    @Override
    public void createCaptcha(String uuid, String code) {
        SysCaptcha captcha = new SysCaptcha();
        captcha.setCode(code);
        captcha.setUuid(uuid);
        captcha.setExpireTime(new DateTime(DateTime.now().getTime() + (60 * 1000)));
        this.save(captcha);
    }

    @Override
    public SysCaptcha validate(String uuid, String code) {
        SysCaptcha captcha = new SysCaptcha();
        captcha.setCode(code);
        captcha.setUuid(uuid);
        return baseMapper.findByUuidAndCode(captcha);
    }
}
