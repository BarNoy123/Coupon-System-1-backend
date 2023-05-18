package dao;

import java.sql.SQLException;
import java.util.List;

import beans.Company;
import exception.CouponSystemExeption;

public interface CompanyDAO {

	void addCompany(Company company) throws SQLException;

	void updateCompany(int companyId, Company company) throws SQLException;

	void deleteCompany(int companyId) throws SQLException;

	Company getCompany(int companyId) throws SQLException;

	int getCompanyIdByEmail(String email) throws CouponSystemExeption;

	List<Company> getAllCompany() throws SQLException;

	boolean isOtherExistByEmail(int idCompany, String email) throws SQLException;

	boolean isExistByEmailOrName(String email, String name) throws SQLException;

	boolean isExistByEmailAndPassword(String email, String password) throws  CouponSystemExeption;

	boolean isExistByName(String name) throws SQLException;

	boolean isExistByEmail(String email) throws SQLException;

	boolean isExistById(int idCompany) throws SQLException;
}
