package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.Company;
import beans.Coupon;
import beans.Customer;
import db.ConvertUtils;
import db.JDBCUtils;

public class CustomerDAOImpl implements CustomerDAO {

	private static final String INSERT_CUSTOMER = "INSERT INTO `bar_coupon_system`.`customers` (`first_name`,`last_name`,`email`,`password`) VALUES (?,?,?,?)";
	private static final String UPDATE_CUSTOMER = "UPDATE `bar_coupon_system`.`customers` SET `first_name` = ?, `last_name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?)";
	private static final String DELETE_CUSTOMER = "DELETE FROM `bar_coupon_system`.`customers` WHERE (`id` = ?)";
	private static final String GET_CUSTOMER = "SELECT * FROM `bar_coupon_system`.`customers` WHERE (`id` = ?);";
	private static final String GET_ALL_CUSTOMER = "SELECT * FROM `bar_coupon_system`.`customers`";
	private static final String EXISTS_BY_EMAIL = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`customers` WHERE `email` = ?)AS res";
	private static final String EXISTS_BY_EMAIL_AND_PASSWORD = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`customers` WHERE `email` = ? and `password` = ?)AS res";
	private static final String EXISTS_BY_OTHER_EMAIL = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`customers` WHERE `id` != ? and `email` = ?)AS res";
	private static final String GET_CUSTOMER_ID_BY_EMAIL = "SELECT `id` FROM `bar_coupon_system`.`customers` WHERE `email` = ?";
	private static final String EXISTS_BY_ID = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`customers` WHERE `id` = ?)AS res";

	@Override
	public void addCustomer(Customer customer) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customer.getFirstName());
		params.put(2, customer.getLastName());
		params.put(3, customer.getEmail());
		params.put(4, customer.getPassword());

		JDBCUtils.runQuery(INSERT_CUSTOMER, params);

	}

	@Override
	public void updateCustomer(int customerId, Customer customer) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customer.getFirstName());
		params.put(2, customer.getLastName());
		params.put(3, customer.getEmail());
		params.put(4, customer.getPassword());
		params.put(5, customerId);

		JDBCUtils.runQuery(UPDATE_CUSTOMER, params);

	}

	@Override
	public void deleteCustomer(int customerId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customerId);

		JDBCUtils.runQuery(DELETE_CUSTOMER, params);

	}

	@Override
	public Customer getCustomer(int customerId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customerId);
		List<?> list = JDBCUtils.runQueryWithResult(GET_CUSTOMER,params);
		Customer customer = ConvertUtils.objectToCustomer((Map<String, Object>) list.get(0));
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() throws SQLException {

		List<Customer> customers = new ArrayList<>();
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_CUSTOMER);
		for (Object obj : list) {
			Customer customer = ConvertUtils.objectToCustomer((Map<String, Object>) obj);
			customers.add(customer);
		}
		return customers;
	}

	@Override
	public boolean isExistByEmail(String email) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, email);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_EMAIL, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;

	}

	@Override
	public boolean isExistByEmailAndPassword(String email, String password) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, email);
		params.put(2, password);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_EMAIL_AND_PASSWORD, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;
	}

	@Override
	public boolean isOtherExistByEmail(int idCompany, String email) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, idCompany);
		params.put(2, email);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_OTHER_EMAIL, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;
	}

	@Override
	public int getCustomerIdByEmail(String email) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, email);
		List<?> list = JDBCUtils.runQueryWithResult(GET_CUSTOMER_ID_BY_EMAIL, params);
		return ConvertUtils.objectToInt((Map<String, Object>) list.get(0));
	}

	@Override
	public boolean isExistById(int idCustomer) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, idCustomer);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_ID, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;

	}
}