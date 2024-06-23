package com.example.couponsystem.Controllers;

import com.example.couponsystem.Service.AdminService;
import com.example.couponsystem.jwt.AuthenticationResponse;
import com.example.couponsystem.jwt.JwtUtils;
import com.example.couponsystem.model.*;
import com.example.couponsystem.model.Coupon;
import com.example.couponsystem.model.Credetional;
import com.example.couponsystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/admin")
public class AdminController extends  ClientController{
    @Autowired
    private AdminService adminService;
    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credetional authenticationRequest) throws Exception {
        adminService = (AdminService)loginManager.login(authenticationRequest.getEmail(), authenticationRequest.getPassword(), ClientType.Admin);
        if(adminService != null) {
            String token = jwtUtils.generateToken(authenticationRequest, ClientType.Admin);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return new ResponseEntity<String>("Invalid Email or Password...", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/addCompany")
    public void addCompany(@RequestBody Company company) throws Exception {
        adminService.addCompany(company);
    }

    @PutMapping("/updateCompany")
    public void updateCompany(@RequestBody Company company) throws Exception{
        adminService.updateCompany(company);
    }

    @DeleteMapping("/deleteCompany/{id}")
    public void deleteCompany(@PathVariable("id") int companyID) throws Exception{
        adminService.deleteCompany(companyID);
    }

    @GetMapping("/getAllCompanies")
    public ArrayList<Company> getAllCompanies() throws Exception {
        return adminService.getAllCompanies();
    }

    @GetMapping("/getOneCompany/{id}")
    public Company getOneCompany(@PathVariable("id") int companyID) throws Exception {
        return adminService.getOneCompany(companyID);
    }

    @PostMapping("/addCustomer")
    public void addCustomer(@RequestBody Customer customer) throws Exception {
        adminService.addCustomer(customer);
    }

    @PutMapping("/updateCustomer")
    public void updateCustomer(@RequestBody Customer customer) throws Exception{
        adminService.updateCustomer(customer);
    }

    @DeleteMapping("/deleteCustomer/{id}")
    public void deleteCustomer(@PathVariable("id") int customerID) throws Exception{
        adminService.deleteCustomer(customerID);
    }

    @GetMapping("/getAllCustomers")
    public ArrayList<Customer> getAllCustomers() throws Exception {
        return adminService.getAllCustomers();
    }

    @GetMapping("/getOneCustomer/{id}")
    public Customer getOneCustomer(@PathVariable("id") int customerID) throws Exception {
        return adminService.getOneCustomer(customerID);
    }


}
