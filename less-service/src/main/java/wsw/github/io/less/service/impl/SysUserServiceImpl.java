package wsw.github.io.less.service.impl;

import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.dao.mapper.SysUserMapper;
import wsw.github.io.less.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-12
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
