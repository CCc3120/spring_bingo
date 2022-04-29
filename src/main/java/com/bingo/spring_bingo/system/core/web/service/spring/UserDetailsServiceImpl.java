package com.bingo.spring_bingo.system.core.web.service.spring;

import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgMenuService;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author bingo
 * @date 2022-04-29 14:27
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private ISysOrgUserService sysOrgUserService;

    @Autowired
    private ISysOrgMenuService sysOrgMenuService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        SysOrgUser user = sysOrgUserService.findByLoginName(username);

        if (user == null) {
            System.out.println("账户不存在");
            throw new RuntimeException("账户不存在");
        } else if (SysModelEnum.BOOLEAN_NO.getCode().equals(user.getFdIsAvailable())) {
            System.out.println("账户不可用");
            throw new RuntimeException("账户不可用");
        } else if (SysModelEnum.BOOLEAN_YES.getCode().equals(user.getFdIsLock())) {
            System.out.println("账户已锁定");
            throw new RuntimeException("账户已锁定");
        }

        // 用户所有菜单权限
        sysOrgMenuService.findMenuAuthMarkByUser(user);

        return null;
    }
}
