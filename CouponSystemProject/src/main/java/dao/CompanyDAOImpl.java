package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.CATEGORY;
import beans.Company;
import db.ConvertUtils;
import db.JDBCUtils;
import exception.CouponSystemExeption;

public class CompanyDAOImpl implements CompanyDAO {

	private static final String INSERT_COMPANY = "INSERT INTO `bar_coupon_system`.`companies` (`name`,`email`,`password`) VALUES (?,?,?)";
	private static final String UPDATE_COMPANY = "UPDATE `bar_coupon_system`.`companies` SET `name` = ?, `email` = ?, `password` = ? WHERE (`id` = ?)";
	private static final String DELETE_COMPANY = "DELETE FROM `bar_coupon_system`.`companies` WHERE (`id` = ?)";
	private static final String GET_COMPANY = "SELECT * FROM `bar_coupon_system`.`companies` WHERE (`id` = ?);";
	private static final String GET_ALL_COMPANY = "SELECT * FROM `bar_coupon_system`.`companies`";
	private static final String EXISTS_BY_EMAIL = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`companies` WHERE `email` = ?)AS res";
	private static final String EXISTS_BY_NAME = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`companies` WHERE `name` = ?)AS res";
	private static final String EXISTS_BY_EMAIL_AND_PASSWORD = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`companies` WHERE `email` = ? and `password` = ?)AS res";
	private static final String EXISTS_BY_EMAIL_OR_NAME = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`companies` WHERE `email` = ? or `name` = ?)AS res";
	private static final String EXISTS_BY_OTHER_EMAIL = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`companies` WHERE id != ? and `email` = ?)AS res";
	private static final String GET_COMPANY_ID_BY_EMAIL = "SELECT `id` FROM `bar_coupon_system`.`companies` WHERE `email` = ?";
	private static final String EXISTS_BY_ID = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`companies` WHERE `id` = ?)AS res";

	@Override
	public void addCompany(Company company) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, company.getName());
		params.put(2, company.getEmail());
		params.put(3, company.getPassword());

		JDBCUtils.runQuery(INSERT_COMPANY, params);

	}

	@Override
	public void updateCompany(int companyId, Company company) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, company.getName());
		params.put(2, company.getEmail());
		params.put(3, company.getPassword());
		params.put(4, companyId);

		JDBCUtils.runQuery(UPDATE_COMPANY, params);
	}

	@Override
	public void deleteCompany(int companyId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);

		JDBCUtils.runQuery(DELETE_COMPANY, params);

	}

	@Override
	public Company getCompany(int companyId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);
		List<?> list = JDBCUtils.runQueryWithResult(GET_COMPANY,params);
		Company company = ConvertUtils.objectToCompany((Map<String, Object>) list.get(0));
		return company;

	}

	@Override
	public List<Company> getAllCompany() throws SQLException {
		List<Company> companies = new ArrayList<>();
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COMPANY);
		for (Object obj : list) {
			Company company = ConvertUtils.objectToCompany((Map<String, Object>) obj);
			companies.add(company);
		}
		return companies;

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
	public boolean isExistByName(String name) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, name);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_NAME, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;

	}

	@Override
	public boolean isExistByEmailAndPassword(String email, String password) throws CouponSystemExeption {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, email);
		params.put(2, password);
		List<?> list;
		try {
			list = JDBCUtils.runQueryWithResult(EXISTS_BY_EMAIL_AND_PASSWORD, params);
			result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
			return result;
		} catch (SQLException e) {
			throw new  CouponSystemExeption("isExistByEmailAndPassword failed", e);
		}

	}

	@Override
	public boolean isExistByEmailOrName(String email, String name) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, email);
		params.put(2, name);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_EMAIL_OR_NAME, params);
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
	public int getCompanyIdByEmail(String email) throws CouponSystemExeption {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, email);
		List<?> list;
		try {
			list = JDBCUtils.runQueryWithResult(GET_COMPANY_ID_BY_EMAIL, params);
			return ConvertUtils.objectToInt((Map<String, Object>) list.get(0));
		} catch (SQLException e) {
			throw new CouponSystemExeption("getCompanyIdByEmail failed", e);
		}
	}

	@Override
	public boolean isExistById(int idCompany) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, idCompany);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_ID, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;

	}

}
