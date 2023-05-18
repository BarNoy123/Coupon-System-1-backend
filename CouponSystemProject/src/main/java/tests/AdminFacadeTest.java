package tests;

import java.sql.SQLException;

import javax.management.loading.PrivateClassLoader;

import beans.Company;
import beans.Customer;
import clients.ClientType;
import clients.LoginManager;
import exception.CouponSystemExeption;
import facade.AdminFacade;
import facade.CustomerFacade;

public class AdminFacadeTest {

	private static AdminFacade adminFacade = null;

	public static void runAllAdminFacadeTests() {
		loginTest();
		System.out.println();
		addCompanyTest();
		System.out.println();
		updateCompanyTest();
		System.out.println();
		deleteCompanyTest();
		System.out.println();
		getAllCompanies();
		System.out.println();
		getCompany();
		System.out.println();
		addCustomerTest();
		System.out.println();
		updateCustomerTest();
		System.out.println();
		deleteCustomerTest();
		System.out.println();
		getAllCustomersTest();
		System.out.println();
		getOneCustomerTest();
		System.out.println();

	}

	public static void loginTest() {

		try {
			System.out.println("\u001b[32mLOGIN SUCCEEDED\u001b[0m");
			adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin",
					ClientType.ADMINISTRATOR);
		} catch (CouponSystemExeption e) {
			System.out.println(e);

		} catch (SQLException e) {

			System.out.println(e);
		}

		try {
			System.out.println("\u001b[32mLOGIN FAILED\u001b[0m");
			adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com", "admin1",
					ClientType.ADMINISTRATOR);
		} catch (CouponSystemExeption e) {
			System.out.println(e);

		} catch (SQLException e) {

			System.out.println(e);
		}
	}

	public static void addCompanyTest() {
		Company company = new Company("Renuar", "renuar@gmail.com", "1234");
		System.out.println("Add company succeeded");
		try {
			adminFacade.addCompany(company);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		Company company1 = new Company("Walla", "renuar@gmail.com", "1234");
		System.out.println("Add company failed (email already exist)");
		try {
			adminFacade.addCompany(company1);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
		Company company2 = new Company("Renuar", "walla@gmail.com", "1234");
		System.out.println("Add company failed (name already exist)");
		try {
			adminFacade.addCompany(company2);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void updateCompanyTest() {
		Company company1 = new Company(1, "Amazon", "amazon2@gmail.com", "12345");
		System.out.println("Update company succeeded");
		try {
			adminFacade.updateCompany(1, company1);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void deleteCompanyTest() {
		System.out.println("Delete company succeeded");
		try {
			adminFacade.deleteCompany(3);
			System.out.println();
		} catch (CouponSystemExeption e) {

			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void getAllCompanies() {
		System.out.println("Get all companies succeeded");
		try {
			for (Company company : adminFacade.getAllCompanies()) {
				System.out.println(company);
			}
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getCompany() {
		System.out.println("Get company succeeded");
		try {
			System.out.println(adminFacade.getCompany(1));
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void addCustomerTest() {
		Customer customer = new Customer("Eti", "Noy", "eti@gmail.com", "12345");
		System.out.println("Add customer succeeded");
		try {
			adminFacade.addCustomer(customer);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void updateCustomerTest() {
		Customer customer = new Customer(1, "Moshe", "Levi", "moshe123@gmail.com", "123456");
		System.out.println("Update customer succeeded");
		try {
			adminFacade.updateCustomer(1, customer);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void deleteCustomerTest() {
		System.out.println("Delete customer succeeded");
		try {
			adminFacade.deleteCustomer(5);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getAllCustomersTest() {
		System.out.println("get all customers test succeeded");
		try {
			for (Customer customer : adminFacade.getAllCustomers()) {
				System.out.println(customer);
			}
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getOneCustomerTest() {
		System.out.println("get one customer test succeeded");
		try {
			System.out.println(adminFacade.getOneCustomer(2));

		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

}
