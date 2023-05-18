package facade;

import java.sql.SQLException;
import java.util.List;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.CustomerDAO;
import exception.CouponSystemExeption;

public class AdminFacadeImpl extends ClientFacade implements AdminFacade {

	@Override
	public boolean login(String email, String password) throws CouponSystemExeption {
		if ((email.equalsIgnoreCase("admin@admin.com") && password.equals("admin")) == false) {
			throw new CouponSystemExeption("Email or password is worng");
		}
		return true;
	}

	@Override
	public void addCompany(Company company) throws SQLException, CouponSystemExeption {
		if (companyDAO.isExistByEmail(company.getEmail())) {
			throw new CouponSystemExeption("Email already exsit");
		}
		if (companyDAO.isExistByName(company.getName())) {
			throw new CouponSystemExeption("Name already exsit ");
		}
		companyDAO.addCompany(company);
	}

	@Override
	public void updateCompany(int companyId, Company company) throws CouponSystemExeption, SQLException {
		if (companyId != company.getId()) {
			throw new CouponSystemExeption(" Cant update copmany id");
		}

		if (companyDAO.getCompany(companyId).getName().equals(company.getName()) == false) {
			throw new CouponSystemExeption("Cant update company name");
		}
		companyDAO.updateCompany(companyId, company);
	}

	@Override
	public void deleteCompany(int companyId) throws CouponSystemExeption, SQLException {
		if (companyDAO.isExistById(companyId) == false) {
			throw new CouponSystemExeption("Company doesn't exist");
		}
		for (Coupon coupon : couponDAO.getAllCompanyCouponsByCompanyId(companyId)) {
			couponDAO.deleteAllCouponsPurchasedByCouponId(coupon.getId());
		}
		couponDAO.deleteAllCouponsByCompanyId(companyId);
		companyDAO.deleteCompany(companyId);

	}

	@Override
	public List<Company> getAllCompanies() throws CouponSystemExeption, SQLException {
		return companyDAO.getAllCompany();

	}

	@Override
	public Company getCompany(int companyId) throws CouponSystemExeption, SQLException {
		if (companyDAO.isExistById(companyId) == false) {
			throw new CouponSystemExeption("Company doesn't exist");
		}

		return companyDAO.getCompany(companyId);
	}

	@Override
	public void addCustomer(Customer customer) throws CouponSystemExeption, SQLException {
		if (customerDAO.isExistByEmail(customer.getEmail())) {
			throw new CouponSystemExeption("Email is already exist");
		}
		customerDAO.addCustomer(customer);
	}

	@Override
	public void updateCustomer(int customerId, Customer customer) throws CouponSystemExeption, SQLException {
		if (customerId != customer.getId()) {
			throw new CouponSystemExeption("Can't update customer id");
		}
		customerDAO.updateCustomer(customerId, customer);

	}

	@Override
	public void deleteCustomer(int customerId) throws CouponSystemExeption, SQLException {
		if (customerDAO.isExistById(customerId) == false) {
			throw new CouponSystemExeption("Customer doesn't exist");
		}
		couponDAO.deleteAllCouponsPurchasedByCustomerId(customerId);
		customerDAO.deleteCustomer(customerId);

	}

	@Override
	public List<Customer> getAllCustomers() throws CouponSystemExeption, SQLException {
		return customerDAO.getAllCustomers();
	}

	@Override
	public Customer getOneCustomer(int customerId) throws CouponSystemExeption, SQLException {
		if (customerDAO.isExistById(customerId) == false) {
			throw new CouponSystemExeption("Customer doesn't exist");
		}
		return customerDAO.getCustomer(customerId);
	}
}