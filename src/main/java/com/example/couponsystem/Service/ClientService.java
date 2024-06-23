package com.example.couponsystem.Service;

import com.example.couponsystem.Reposetories.CompanyReposetory;
import com.example.couponsystem.Reposetories.CouponReposetory;
import com.example.couponsystem.Reposetories.CustomerReposetory;
import com.example.couponsystem.model.Credetional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public abstract class ClientService {

    @Autowired
    protected CompanyReposetory companyReposetory;

    @Autowired
    protected CustomerReposetory customerReposetory;

    @Autowired
    public CouponReposetory couponReposetory;

    public abstract boolean login(Credetional credetional) throws Exception;


}
