package facade;

import java.sql.SQLException;
import java.util.List;

import beans.Company;
import beans.Customer;
import exception.CouponSystemExeption;

public interface AdminFacade {

	void addCompany(Company company) throws CouponSystemExeption, SQLException;

	void updateCompany(int companyId, Company company) throws CouponSystemExeption, SQLException;

	void deleteCompany(int companyId) throws CouponSystemExeption, SQLException;

	void addCustomer(Customer customer) throws CouponSystemExeption, SQLException;

	void updateCustomer(int customerId,Customer customer) throws CouponSystemExeption, SQLException;

	void deleteCustomer(int customerId) throws CouponSystemExeption, SQLException;

	List<Company> getAllCompanies() throws CouponSystemExeption, SQLException;

	Company getCompany(int companyId) throws CouponSystemExeption, SQLException;

	List<Customer> getAllCustomers() throws CouponSystemExeption, SQLException;

	Customer getOneCustomer(int customerId) throws CouponSystemExeption, SQLException;
}
