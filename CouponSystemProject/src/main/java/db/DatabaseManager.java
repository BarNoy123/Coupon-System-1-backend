package db;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import beans.CATEGORY;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import dao.CategoryDAO;
import dao.CategoryDAOImpl;
import dao.CompanyDAO;
import dao.CompanyDAOImpl;
import dao.CouponDAO;
import dao.CouponDAOImpl;
import dao.CustomerDAO;
import dao.CustomerDAOImpl;

public class DatabaseManager {

	public static CategoryDAO categoryDAO = new CategoryDAOImpl();

	protected static final String url = "jdbc:mysql://localhost:3306?createDatabaseIfNotExist=TRUE&useTimezone=TRUE&serverTimezone=UTC";

	protected static final String username = "root";

	protected static final String password = "1234";

	public static final String CREATE_SCHEMA = "create schema `bar_coupon_system`";

	public static final String DROP_SCHEMA = "drop schema `bar_coupon_system`";

	private static final String CREATE_TABLE_COMPANIES = "CREATE TABLE `bar_coupon_system`.`companies` (\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\n" + "  `name` VARCHAR(45) NOT NULL,\n"
			+ "  `email` VARCHAR(45) NOT NULL,\n" + "  `password` VARCHAR(45) NOT NULL,\n" + "  PRIMARY KEY (`id`));\n";

	private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE `bar_coupon_system`.`customers` (\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\n" + "  `first_name` VARCHAR(45) NOT NULL,\n"
			+ "  `last_name` VARCHAR(45) NOT NULL,\n" + "  `email` VARCHAR(45) NOT NULL,\n"
			+ "  `password` VARCHAR(45) NOT NULL,\n" + "  PRIMARY KEY (`id`));\n";

	private static final String CREATE_TABLE_CATEGORIES = "CREATE TABLE `bar_coupon_system`.`categories` (\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\n" + "  `name` VARCHAR(45) NOT NULL,\n" + "  PRIMARY KEY (`id`));";

	private static final String CREATE_TABLE_COUPONS = "CREATE TABLE `bar_coupon_system`.`coupons` (\n"
			+ "  `id` INT NOT NULL AUTO_INCREMENT,\n" + "  `company_id` INT NOT NULL,\n"
			+ "  `category_id` INT NOT NULL,\n" + "  `title` VARCHAR(256) NOT NULL,\n"
			+ "  `description` VARCHAR(256) NOT NULL,\n" + "  `start_date` DATE NOT NULL,\n"
			+ "  `end_date` DATE NOT NULL,\n" + "  `amount` INT NOT NULL,\n" + "  `price` DOUBLE NOT NULL,\n"
			+ "  `image` VARCHAR(1000) NOT NULL,\n" + "  PRIMARY KEY (`id`),\n"
			+ "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\n"
			+ "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\n" + "  CONSTRAINT `company_id`\n"
			+ "    FOREIGN KEY (`company_id`)\n" + "    REFERENCES `bar_coupon_system`.`companies` (`id`)\n"
			+ "    ON DELETE NO ACTION\n" + "    ON UPDATE NO ACTION,\n" + "  CONSTRAINT `category_id`\n"
			+ "    FOREIGN KEY (`category_id`)\n" + "    REFERENCES `bar_coupon_system`.`categories` (`id`)\n"
			+ "    ON DELETE NO ACTION\n" + "    ON UPDATE NO ACTION);\n";

	private static final String CREATE_TABLE_CUSTOMERS_AND_COUPONS = "CREATE TABLE `bar_coupon_system`.`customers_coupons` (\n"
			+ "  `customers_id` INT NOT NULL,\n" + "  `coupon_id` INT NOT NULL,\n"
			+ "  PRIMARY KEY (`customers_id`, `coupon_id`),\n" + "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\n"
			+ "  CONSTRAINT `customer_id`\n" + "    FOREIGN KEY (`customers_id`)\n"
			+ "    REFERENCES `bar_coupon_system`.`customers` (`id`)\n" + "    ON DELETE NO ACTION\n"
			+ "    ON UPDATE NO ACTION,\n" + "  CONSTRAINT `coupon_id`\n" + "    FOREIGN KEY (`coupon_id`)\n"
			+ "    REFERENCES `bar_coupon_system`.`coupons` (`id`)\n" + "    ON DELETE NO ACTION\n"
			+ "    ON UPDATE NO ACTION);";

	public static void dropAndCreateStrategy() throws SQLException {
		JDBCUtils.runQuery(DROP_SCHEMA);
		JDBCUtils.runQuery(CREATE_SCHEMA);
		JDBCUtils.runQuery(CREATE_TABLE_CATEGORIES);
		JDBCUtils.runQuery(CREATE_TABLE_COMPANIES);
		JDBCUtils.runQuery(CREATE_TABLE_COUPONS);
		JDBCUtils.runQuery(CREATE_TABLE_CUSTOMERS);
		JDBCUtils.runQuery(CREATE_TABLE_CUSTOMERS_AND_COUPONS);

		for (CATEGORY category : CATEGORY.values()) {

			String name = category.name();
			categoryDAO.addCategory(name);
		}
		initData();
	}

	public static void initData() throws SQLException {

		CouponDAO couponDAO = new CouponDAOImpl();
		CompanyDAO companyDAO = new CompanyDAOImpl();
		CustomerDAO customerDAO = new CustomerDAOImpl();

		Company company1 = new Company("Amazon", "amazon@gmail.com", "1234");
		Company company2 = new Company("Facebook", "facebook@gmail.com", "1234");
		Company company3 = new Company("Apple", "amazon@.gmail.com", "1234");
		Company company4 = new Company("Zara", "zara@gmail.com", "1234");
		Company company5 = new Company("Ebay", "ebay@gmail.com", "1234");

		companyDAO.addCompany(company1);
		companyDAO.addCompany(company2);
		companyDAO.addCompany(company3);
		companyDAO.addCompany(company4);
		companyDAO.addCompany(company5);

		for (int i = 1; i <= 5; i++) {
			Coupon coupon = new Coupon(i, CATEGORY.values()[(int) Math.random() * CATEGORY.values().length],
					"title" + i, "description" + i, Date.valueOf(LocalDate.now()),
					Date.valueOf(LocalDate.now().plusMonths(2)), (int) (Math.random() * 10 + 1),
					(Math.random() * 100 + 1), "image" + i);

			couponDAO.addCoupon(coupon);

		}
		Customer customer1 = new Customer("Moshe", "Levi", "moshe@gmail.com", "1234");
		Customer customer2 = new Customer("Bar", "Noy", "bar@gmail.com", "1234");
		Customer customer3 = new Customer("Boaz", "Noy", "boaz@gmail.com", "1234");
		Customer customer4 = new Customer("Smadar", "Noy", "smadar@gmail.com", "1234");
		Customer customer5 = new Customer("Yam", "Noy", "yam@gmail.com", "1234");

		customerDAO.addCustomer(customer1);
		customerDAO.addCustomer(customer2);
		customerDAO.addCustomer(customer3);
		customerDAO.addCustomer(customer4);
		customerDAO.addCustomer(customer5);

	}

}
