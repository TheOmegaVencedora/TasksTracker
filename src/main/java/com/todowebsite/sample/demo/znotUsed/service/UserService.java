package com.todowebsite.sample.demo.znotUsed.service;

import com.todowebsite.sample.demo.user.CrmUser;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    void  save(CrmUser crmUser);
}
