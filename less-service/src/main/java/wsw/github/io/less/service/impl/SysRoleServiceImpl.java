package wsw.github.io.less.service.impl;

import wsw.github.io.less.dao.entity.SysRole;
import wsw.github.io.less.dao.mapper.SysRoleMapper;
import wsw.github.io.less.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Override
    public List<SysRole> listRolesByUserId(long id) {
        return baseMapper.listRolesByUserId(id);
    }

    @Override
    public List<SysRole> listByMenuId(long id) {
        return baseMapper.listByMenuId(id);
    }
}
