package com.example.couponsystem.Controllers;

import com.example.couponsystem.Service.CompanyService;
import com.example.couponsystem.jwt.AuthenticationResponse;
import com.example.couponsystem.jwt.JwtUtils;
import com.example.couponsystem.model.*;
import com.example.couponsystem.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends ClientController{
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtUtils jwtUtils;

    public  CustomerController(){

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credetional authenticationRequest) throws Exception {
        customerService = (CustomerService) loginManager.login(authenticationRequest.getEmail(), authenticationRequest.getPassword(), ClientType.Customer);
        if(customerService != null) {
            String token = jwtUtils.generateToken(authenticationRequest, ClientType.Customer);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return new ResponseEntity<String>("Invalid Email or Password...", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/purchaseCoupon/{customerID}")
    public void purchaseCoupon(@RequestBody Coupon coupon,@PathVariable("customerID") int customerID) throws Exception {
         customerService.purchaseCoupon(coupon,customerID);
    }

    @GetMapping("/getCustomerCoupons/{customerID}")
    public ArrayList<Coupon> getCustomerCoupons(@PathVariable("customerID") int customerID) throws Exception {
        return customerService.getCustomerCoupons(customerID);
    }

    @GetMapping("/getCustomerCouponsByCategory/{customerID}")
    public ArrayList<Coupon> getCustomerCoupons(@RequestParam("category") Category category,@PathVariable("customerID") int customerID) throws Exception {
        return customerService.getCustomerCoupons(category,customerID);
    }

    @GetMapping("/getCustomerCouponsByPrice/{customerID}")
    public ArrayList<Coupon> getCustomerCoupons(@RequestParam("maxPrice") double maxPrice,@PathVariable("customerID") int customerID) throws Exception {
        return customerService.getCustomerCoupons(maxPrice,customerID);
    }

    @GetMapping("/getCustomerDetails/{customerID}")
    public Customer getCustomerDetails(@PathVariable("customerID") int customerID) throws Exception {
        return customerService.getCustomerDetails(customerID);
    }

    @GetMapping("/getAllCoupons/{customerID}")
    public ArrayList<Coupon> getAllCoupons(@PathVariable("customerID") int customerID) throws Exception {
        return customerService.getAllCoupons(customerID);
    }




}
