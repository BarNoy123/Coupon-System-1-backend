package dao;

import java.sql.SQLException;
import java.util.List;

import beans.Customer;

public interface CustomerDAO {

	void addCustomer(Customer customer) throws SQLException;

	void updateCustomer(int customerId, Customer customer) throws SQLException;

	void deleteCustomer(int customerId) throws SQLException;

	Customer getCustomer(int customerId) throws SQLException;
	
	int getCustomerIdByEmail(String email) throws SQLException;
	
	List<Customer>getAllCustomers()throws SQLException;

	boolean isExistByEmail(String email) throws SQLException;

	boolean isExistByEmailAndPassword(String email, String password) throws SQLException;

	boolean isOtherExistByEmail(int idCompany, String email) throws SQLException;

	boolean isExistById(int idCustomer) throws SQLException;
}
