package com.bingo.spring_bingo.system.service.spring;

import com.bingo.spring_bingo.system.dao.SysOrgMenuMapper;
import com.bingo.spring_bingo.system.model.SysOrgUser;
import com.bingo.spring_bingo.system.service.ISysOrgMenuService;
import com.bingo.spring_bingo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 菜单 权限
 *
 * @author bingo
 * @date 2022-04-29 14:43
 */
@Service
public class SysOrgMenuServiceImpl implements ISysOrgMenuService {

    @Autowired
    private SysOrgMenuMapper sysOrgMenuMapper;

    @Override
    public Set<String> findMenuAuthMarkByUser(SysOrgUser sysOrgUser) {
        List<String> authMarkList = sysOrgMenuMapper.findMenuAuthMarkByUserId(sysOrgUser.getFdId());
        Set<String> authMarkSet = new HashSet<>();
        if ("admin".equals(sysOrgUser.getFdLoginName())) {
            authMarkSet.add("*:*:*");
        } else {
            authMarkList.forEach(s -> {
                if (StringUtil.isNotNull(s)) {
                    authMarkSet.addAll(Arrays.asList(s.trim().split(";")));
                }
            });
        }
        return authMarkSet;
    }
}
