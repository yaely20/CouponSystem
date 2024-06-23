package com.example.couponsystem.Controllers;

import com.example.couponsystem.login.LoginManager;
import com.example.couponsystem.model.Credetional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

public abstract class ClientController {
    @Autowired
    protected LoginManager loginManager;

    public abstract ResponseEntity<?> login(Credetional authenticationRequest) throws Exception;
}
