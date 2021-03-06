package wsw.github.io.less.dao.mapper;

import wsw.github.io.less.dao.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 * 系统用户 Mapper 接口
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
public interface SysUserMapper extends BaseMapper<SysUser> {

    SysUser findByUsername(String username);
}
