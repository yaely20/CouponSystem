package com.example.couponsystem.Service;

import com.example.couponsystem.model.Coupon;
import com.example.couponsystem.Reposetories.CouponReposetory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {
    @Autowired
    private CouponReposetory couponReposetory;

    public List<Coupon> getAllCoupons(){
        return couponReposetory.findAll();
    }

    public Coupon getCouponById(Integer id){
        return couponReposetory.findById(id).orElse(null);
    }
    public Coupon addCoupon(Coupon coupon){
        return couponReposetory.save(coupon);
    }

    public Coupon updateCoupon(Integer id, Coupon coupon) {
        coupon.setId(id);
        return couponReposetory.save(coupon);
    }

    public void deleteCoupon(Integer id){
        couponReposetory.deleteById(id);
    }
}
