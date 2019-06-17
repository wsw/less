package wsw.github.io.less.service.impl;

import cn.hutool.core.date.DateTime;
import wsw.github.io.less.common.util.R;
import wsw.github.io.less.common.util.TokenGenerator;
import wsw.github.io.less.dao.entity.SysUserToken;
import wsw.github.io.less.dao.mapper.SysUserTokenMapper;
import wsw.github.io.less.service.SysUserTokenService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统用户Token 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-14
 */
@Service
public class SysUserTokenServiceImpl extends ServiceImpl<SysUserTokenMapper, SysUserToken> implements SysUserTokenService {

    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public R createToken(String username) {

        String token = TokenGenerator.generateValue();
        DateTime now = new DateTime();
        DateTime expired = new DateTime(now.getTime() + EXPIRE * 1000);

        SysUserToken userToken = this.getByUsername(username);
        if (userToken == null) {
            SysUserToken newToken = new SysUserToken();
            newToken.setToken(token);
            newToken.setUsername(username);
            newToken.setExpireTime(expired);
            newToken.setUpdateTime(now);

            this.save(newToken);
        } else {
            userToken.setUpdateTime(now);
            userToken.setExpireTime(expired);
            userToken.setToken(token);

            this.saveOrUpdate(userToken);
        }

        return R.ok().put("token", token).put("expire", EXPIRE);
    }

    @Override
    public SysUserToken getByUsername(String username) {
        return baseMapper.getByUsername(username);
    }

    @Override
    public String getUsernameByToken(String token) {
        SysUserToken userToken = baseMapper.queryByToken(token);

        return userToken == null ? null : userToken.getUsername();
    }

    @Override
    public void logout(String username) {

        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        SysUserToken tokenEntity = new SysUserToken();
        tokenEntity.setUsername(username);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
