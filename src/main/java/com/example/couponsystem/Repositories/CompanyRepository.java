package com.example.couponsystem.Repositories;

import com.example.couponsystem.Model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface CompanyRepository extends JpaRepository<Company,Integer> {
    @Query("select id from Company where credetional.email=?1 and credetional.password=?2")
    public Integer findByEmailAndPassword(String email,String password);

   @Query("select case when count(c)>0 then 'true' else 'false' end from  Company c where  c.credetional.email=?1 and c.credetional.password=?2")
    public boolean existsByNameOrEmail(String name,String password);

   @Query("select c from Company  c where c.credetional.email=?1 and c.id=?2")
   public Company findCompanyByEmailAndId(String email,int id);

   @Query("select  c from Coupon c where c.company.id=?1")
   public Set<Company> findCompanyCoupons(int companyId);
    @Query("select  c from Company c where c.id=?1")
   public Company findById(int companyID);
}
