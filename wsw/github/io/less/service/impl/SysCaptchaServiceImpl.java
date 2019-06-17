package wsw.github.io.less.service.impl;

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

}
