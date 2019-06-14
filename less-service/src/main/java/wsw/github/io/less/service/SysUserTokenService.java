package wsw.github.io.less.service;

import wsw.github.io.less.common.util.R;
import wsw.github.io.less.dao.entity.SysUserToken;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 系统用户Token 服务类
 * </p>
 *
 * @author wsw
 * @since 2019-06-14
 */
public interface SysUserTokenService extends IService<SysUserToken> {

    R createToken(String username);

    SysUserToken getByUsername(String username);

    String getUsernameByToken(String token);

    /**
     * 退出，修改token值
     * @param username  用户名
     */
    void logout(String username);
}
