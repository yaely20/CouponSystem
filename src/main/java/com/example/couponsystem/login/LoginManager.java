package com.example.couponsystem.login;

import com.example.couponsystem.Service.AdminService;
import com.example.couponsystem.Service.ClientService;
import com.example.couponsystem.Service.CompanyService;
import com.example.couponsystem.Service.CustomerService;
import com.example.couponsystem.model.ClientType;
import com.example.couponsystem.model.Credetional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class LoginManager {
    @Autowired
    AdminService adminService;


    @Autowired
    CompanyService companyService;

    @Autowired
    CustomerService customerService;

    private LoginManager() {

    }

    public ClientService login(String email , String password, ClientType clientType) throws Exception{
        boolean boolLogin = false;
        ClientService clientService = null;
        switch (clientType){
            case Admin :
                clientService=adminService;
                boolLogin= clientService.login(new Credetional(email,password));
                break;
            case Company:
                clientService=companyService;
                boolLogin= clientService.login(new Credetional(email,password));
                break;
            case Customer:
                clientService=customerService;
                boolLogin= clientService.login(new Credetional(email,password));
                break;
        }
        if(!boolLogin)
            return null;
        else
            return clientService;
    }
}
