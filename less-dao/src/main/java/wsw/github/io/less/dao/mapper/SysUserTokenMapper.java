package wsw.github.io.less.dao.mapper;

import wsw.github.io.less.dao.entity.SysUserToken;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户Token Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2019-06-14
 */
public interface SysUserTokenMapper extends BaseMapper<SysUserToken> {

    SysUserToken getByUsername(String username);

    SysUserToken queryByToken(String token);

}
