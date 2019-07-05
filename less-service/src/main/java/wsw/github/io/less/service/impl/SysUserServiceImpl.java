package wsw.github.io.less.service.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import wsw.github.io.less.dao.entity.SysUser;
import wsw.github.io.less.dao.entity.SysUserRole;
import wsw.github.io.less.dao.mapper.SysRoleMapper;
import wsw.github.io.less.dao.mapper.SysUserMapper;
import wsw.github.io.less.dao.util.PageUtils;
import wsw.github.io.less.dao.util.Query;
import wsw.github.io.less.service.SysRoleService;
import wsw.github.io.less.service.SysUserRoleService;
import wsw.github.io.less.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 系统用户 服务实现类
 * </p>
 *
 * @author wsw
 * @since 2019-06-13
 */
@Service
@Transactional
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Override
    public SysUser findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String username = (String) params.get("username");

        IPage<SysUser> query = new Query<SysUser>().getPage(params);
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>()
                .like(StrUtil.isNotEmpty(username),"username", username)
                .orderByDesc("status");

        Object o = params.get("createUserId");
        if (o != null) {
            queryWrapper.eq("create_user_id", (Long) o );
        }

        IPage<SysUser> userIPage = this.page(query, queryWrapper);
        List<SysUser> list = userIPage.getRecords().stream().map(record ->
                record.setRoles(sysRoleService.listRolesByUserId(record.getUserId()))).collect(Collectors.toList());

        return new PageUtils(list, (int)userIPage.getTotal(), (int)userIPage.getPages(), (int)userIPage.getCurrent());
    }

    @Override
    public boolean addUser(SysUser user, Long[] roles) {
        // 角色添加
        this.save(user);
        List<SysUserRole> userRoles = new ArrayList<>();
        wrapperUserRole(user, roles, userRoles);
        return sysUserRoleService.saveBatch(userRoles);
    }

    @Override
    public boolean updateUser(SysUser user, Long[] roles) {
        this.updateById(user);
        QueryWrapper<SysUserRole> query = new QueryWrapper<SysUserRole>()
                .eq("user_id", user.getUserId());
        sysUserRoleService.remove(query);
        List<SysUserRole> userRoles = new ArrayList<>();
        wrapperUserRole(user, roles, userRoles);
        return sysUserRoleService.saveBatch(userRoles);
    }

    private void wrapperUserRole(SysUser user, Long[] roles, List<SysUserRole> userRoles) {
        for (Long role : roles) {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setRoleId(role);
            sysUserRole.setUserId(user.getUserId());
            userRoles.add(sysUserRole);
        }
    }
}
