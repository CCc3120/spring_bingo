package com.bingo.spring_bingo.system.core.security;

import com.bingo.spring_bingo.common.constant.SysModelEnum;
import com.bingo.spring_bingo.system.core.exception.AuthenticationLoginException;
import com.bingo.spring_bingo.system.core.web.model.SysLoginUser;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgMenuService;
import com.bingo.spring_bingo.system.service.ISysOrgUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author bingo
 * @date 2022-04-29 14:27
 */
@Slf4j
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
            throw new AuthenticationLoginException(AuthenticationLoginException.NO_ACCOUNT);
        } else if (SysModelEnum.BOOLEAN_NO.getCode().equals(user.getFdIsAvailable())) {
            throw new AuthenticationLoginException(AuthenticationLoginException.NO_AVAILABLE);
        } else if (SysModelEnum.BOOLEAN_YES.getCode().equals(user.getFdIsLock())) {
            throw new AuthenticationLoginException(AuthenticationLoginException.LOCK);
        }

        return createLoginUser(user);
    }

    private UserDetails createLoginUser(SysOrgUser user) {
        SysLoginUser loginUser = new SysLoginUser();
        // 用户所有菜单权限
        Set<String> authMark = sysOrgMenuService.findMenuAuthMarkByUser(user);
        loginUser.setAuthmarks(authMark);

        loginUser.setFdUserId(user.getFdId());
        loginUser.setFdUsername(user.getFdLoginName());
        loginUser.setFdPassword(user.getFdPassword());
        return loginUser;
    }

}
