package com.enterprise.demo.core.service.provider;

import com.alibaba.dubbo.config.annotation.Service;
import com.enterprise.demo.api.service.AccountService;

@Service(version = "1.0.0")
public class AccountServiceImpl implements AccountService {
    @Override
    public String selectAccountById(String accountId) {
        return null;
    }
}
