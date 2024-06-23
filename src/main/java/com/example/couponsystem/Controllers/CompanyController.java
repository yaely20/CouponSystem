package com.example.couponsystem.Controllers;
import com.example.couponsystem.Service.AdminService;
import com.example.couponsystem.jwt.AuthenticationResponse;
import com.example.couponsystem.jwt.JwtUtils;
import com.example.couponsystem.model.*;
import com.example.couponsystem.Service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/company")
public class CompanyController extends ClientController{
    @Autowired
    private CompanyService companyService;
    @Autowired
    private JwtUtils jwtUtils;

    public CompanyController(){

    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Credetional authenticationRequest) throws Exception {
        companyService = (CompanyService) loginManager.login(authenticationRequest.getEmail(), authenticationRequest.getPassword(), ClientType.Company);
        if(companyService != null) {
            String token = jwtUtils.generateToken(authenticationRequest, ClientType.Company);
            return ResponseEntity.ok(new AuthenticationResponse(token));
        } else {
            return new ResponseEntity<String>("Invalid Email or Password...", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/addCoupon/{companyID}")
    public void addCoupon(@PathVariable("companyID") int companyID,@RequestBody Coupon coupon) throws Exception {
         companyService.addCoupon(coupon,companyID);
    }

    @PutMapping("/updateCoupon/{companyID}")
    public void updateCoupon(@PathVariable("companyID") int companyID,@RequestBody Coupon coupon) throws Exception{
         companyService.updateCoupon(coupon,companyID);
    }

    @DeleteMapping("/deleteCoupon/{id}")
    public void deleteCoupon(@PathVariable("id") int couponID) throws Exception{
        companyService.deleteCoupon(couponID);
    }

    @GetMapping("/getCompanyCoupons/{companyID}")
    public ArrayList<Coupon> getCompanyCoupons(@PathVariable("companyID") int companyID) throws Exception {
        return companyService.getCompanyCoupons(companyID);
    }

    @GetMapping("/getCompanyCouponsByCategory/{companyID}")
    public ArrayList<Coupon> getCompanyCoupons(@PathVariable("companyID") int companyID,@RequestParam("category") Category category) throws Exception {
        return companyService.getCompanyCoupons(category,companyID);
    }

    @GetMapping("/getCompanyCouponsByPrice/{companyID}")
    public ArrayList<Coupon> getCompanyCoupons(@PathVariable("companyID") int companyID,@RequestParam("maxPrice") double maxPrice) throws Exception {
        return companyService.getCompanyCoupons(maxPrice,companyID);
    }

    @GetMapping("/getCompanyDetails/{companyID}")
    public Company getCompanyDetails(@PathVariable("companyID") int companyID) throws Exception {
        return companyService.getCompanyDetails(companyID);
    }





}

