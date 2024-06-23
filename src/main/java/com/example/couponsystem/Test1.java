package com.example.couponsystem;


import com.example.couponsystem.Service.*;
import com.example.couponsystem.login.LoginManager;
import com.example.couponsystem.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class Test1 implements CommandLineRunner {

    @Autowired
    LoginManager loginManager;
    void addAdmin() {
        try {
            AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.Admin);
            //adminService.addCompany(new Company(1, "laline", new Credetional("elishstar1@gmail.com", "123")));
            //adminService.addCustomer(new Customer(1,"michal","mazuz",new Credetional("michal7969@gmail.com","1234")));
           // adminService.addCompany(new Company(2, "greg", new Credetional("greg100@gmail.com", "g123")));
           // adminService.addCustomer(new Customer(2,"elish","katz",new Credetional("elish454@gmail.com","e1234")));
           // adminService.deleteCustomer(2);
          //  adminService.deleteCompany(2);
            System.out.println(adminService.getAllCompanies());
            System.out.println(adminService.getAllCustomers());
            CompanyService companyService=(CompanyService) loginManager.login("elishstar1@gmail.com", "123",ClientType.Company);
           // Company c=new Company(1, "laline", new Credetional("elishstar1@gmail.com", "123"));
           //companyService.addCoupon(new Coupon(companyService.getCompanyDetails(1),Category.Cultivation,"50% for you","for your birthday",LocalDate.of(2024,8,6),LocalDate.of(2024,9,6),1,50,""),1);
        //  companyService.addCoupon(new Coupon(c,Category.Cultivation,"60% for you"," sale",LocalDate.of(2024,8,6),LocalDate.of(2024,9,6),1,50,""),1);
         //   companyService.addCoupon(new Coupon(c,Category.Cultivation,"Holiday"," sale",LocalDate.of(2024,8,6),LocalDate.of(2024,9,6),1,50,""),1);
            System.out.println(companyService.getCompanyDetails(1));
            CouponService couponService;
            CustomerService customerService=(CustomerService)loginManager.login("michal7969@gmail.com","1234",ClientType.Customer);
            customerService.purchaseCoupon(customerService.couponReposetory.getById(1),1);

        } catch (Exception e) {
            e.getMessage();
        }
    }
//    @PersistenceContext
//    private EntityManager entityManager;

//    Company company1=new Company(1,"laline",new Credetional("elishstar1@gmail.com","123"));
//    Coupon coupon1=new Coupon(1,company1, Category.Cultivation,"birthday","50$ for your birthday",LocalDate.of(2024,8,6),LocalDate.of(204,9,6),1,50,"");
//    Coupon coupon2=new Coupon(2,company1, Category.Cultivation,"sale","50$ for sale",LocalDate.of(2024,8,6),LocalDate.of(204,10,6),1,20,"");
//    Customer customer1=new Customer(1,"michal","mazuz",new Credetional("michal7969@gmail.com","1234"));
//    Customer admin=new Customer(1,"elisheva","katzinelbogen",new Credetional("admin@admin.com","admin"));
//
//    ArrayList<Coupon> couponList=new ArrayList<>();
//    ArrayList<Customer> customerList=new ArrayList<>();



    @Override
    public void run(String... args) throws Exception {

        addAdmin();
    }
}
