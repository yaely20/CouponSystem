package com.example.couponsystem.Service;

import com.example.couponsystem.Reposetories.CompanyReposetory;
import com.example.couponsystem.Reposetories.CustomerReposetory;
import com.example.couponsystem.model.Company;
import com.example.couponsystem.model.Credetional;
import com.example.couponsystem.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class AdminService extends ClientService{
    @Override
    public boolean login(Credetional credetional) throws Exception {
        return credetional.getEmail().equals("admin@admin.com") && credetional.getPassword().equals("admin");
    }

    public AdminService(){

    }

    //actions of company
    public void addCompany(Company company) throws Exception {
        if(company==null)
            throw new Exception("can't add a null company");
        if(companyReposetory.existsById(company.getId()))
            throw new Exception("company is already exists");
        if(companyReposetory.existsByNameOrEmail(company.getName(),company.getCredetional().getEmail()))
            throw new Exception("company's name or email is already exists");
        companyReposetory.save(company);
    }

    public void updateCompany(Company company) throws Exception {
        if(company==null)
            throw new Exception("can't add a null company");
        if(!companyReposetory.existsById(company.getId()))
            throw new Exception("company isn't exists");
//        if(companyReposetory.findCompanyByEmailAndId(company.getCredetional().getEmail(),company.getId())!-null)
//            throw new Exception("company's email is already exists");
        companyReposetory.save(company);
    }

    public void deleteCompany(int companyID) throws Exception {
        if(companyID<=0)
            throw new Exception("invalid id");
        if(!companyReposetory.existsById(companyID))
            throw new Exception("company isn't exists");
        Company company=companyReposetory.findById(companyID);
        companyReposetory.delete(company);
    }

    public ArrayList<Company> getAllCompanies() throws Exception {
        if(companyReposetory.findAll().isEmpty())
            throw new Exception("there aren't companies");
        return (ArrayList<Company>) companyReposetory.findAll();
    }

    public Company getOneCompany(int companyID) throws Exception {
        if(companyID<=0)
            throw new Exception("invalid id");
        if(!companyReposetory.existsById(companyID))
            throw new Exception("company isn't exists");
        Company company=companyReposetory.findById(companyID);
        return company;
    }

    //actions of customer
    public void addCustomer(Customer customer) throws Exception {
        if(customer==null)
            throw new Exception("can't add a null customer");
        if(customerReposetory.existsById(customer.getIdCustomer()))
            throw new Exception("customer is already exists");
        if(customerReposetory.existsByEmail(customer.getCredetional().getEmail()))
            throw new Exception("customer's email is already exists");
        customerReposetory.save(customer);
    }

    public void updateCustomer(Customer customer) throws Exception {
        if(customer==null)
            throw new Exception("can't add a null customer");
        if(!customerReposetory.existsById(customer.getIdCustomer()))
            throw new Exception("customer isn't exists");
//        if(customerReposetory.findCustomerByEmailAndId(customer.getCredetional().getEmail(),customer.getIdCustomer())!=null)
//            throw new Exception("customer's email is already exists");
        customerReposetory.save(customer);
    }

    public void deleteCustomer(int customerID) throws Exception {
        if(customerID<=0)
            throw new Exception("invalid id");
        if(!customerReposetory.existsById(customerID))
            throw new Exception("customer isn't exists");
        Customer customer=customerReposetory.findById(customerID);
        customerReposetory.delete(customer);
    }

    public ArrayList<Customer> getAllCustomers() throws Exception {
        if(customerReposetory.findAll().isEmpty())
            throw new Exception("there aren't customers");
        return (ArrayList<Customer>) customerReposetory.findAll();
    }

    public Customer getOneCustomer(int customerID) throws Exception {
        if(customerID<=0)
            throw new Exception("invalid id");
        if(!customerReposetory.existsById(customerID))
            throw new Exception("customer isn't exists");
        Customer customer=customerReposetory.findById(customerID);
        return customer;
    }

}
