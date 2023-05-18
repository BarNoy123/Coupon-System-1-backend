package facade;

import java.sql.SQLException;

import beans.Customer;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;
import exception.CouponSystemExeption;

public abstract class ClientFacade {

	protected CompanyDAO companyDAO = new CompanyDAOImpl();
	protected CustomerDAO customerDAO = new CustomerDAOImpl();
	protected CouponDAO couponDAO = new CouponDAOImpl();
	
	public ClientFacade() {
		
	}
	
	public abstract boolean login(String email, String password) throws CouponSystemExeption, SQLException;
		
	
	
	
}
