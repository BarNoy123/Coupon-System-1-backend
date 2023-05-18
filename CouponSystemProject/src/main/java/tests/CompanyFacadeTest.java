package tests;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import beans.CATEGORY;
import beans.Company;
import beans.Coupon;
import clients.ClientType;
import clients.LoginManager;
import exception.CouponSystemExeption;
import facade.AdminFacade;
import facade.CompanyFacade;

public class CompanyFacadeTest {

	private static CompanyFacade companyFacade = null;

	public static void runAllCompanyFacadeTests() {
		loginTest();
		System.out.println();
		addCouponTest();
		System.out.println();
		updateCoupontest();
		System.out.println();
		deleteCouponTest();
		System.out.println();
		getAllCompanyCouponTest();
		System.out.println();
		getAllCompanyCouponByCategoryTest();
		System.out.println();
		getAllCompanyCouponBypriceTest();
		System.out.println();
		getCompanyDetalisTest();
		System.out.println();
	}

	public static void loginTest() {

		try {
			System.out.println("\u001b[32mLOGIN COMPANY SUCCEEDED\u001b[0m");
			companyFacade = (CompanyFacade) LoginManager.getInstance().login("amazon2@gmail.com", "12345",
					ClientType.COMPANY);
		} catch (CouponSystemExeption e) {
			System.out.println(e);

		} catch (SQLException e) {

			System.out.println(e);
		}

	}

	public static void addCouponTest() {
		Coupon coupon1 = new Coupon(1, CATEGORY.values()[(int) Math.random() * CATEGORY.values().length], "title" + 11,
				"description" + 11, Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(2)),
				(int) (Math.random() * 10 + 1), (Math.random() * 100 + 1), "image" + 11);
		Coupon coupon2 = new Coupon(1, CATEGORY.FOOD, "title" + 22, "description" + 22, Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now().plusMonths(2)), 10, 10, "image" + 22);
		try {
			System.out.println("Add coupon succeeded");
			companyFacade.addCoupon(coupon1);
			companyFacade.addCoupon(coupon2);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void updateCoupontest() {
		Coupon coupon = new Coupon(1, 1, CATEGORY.FOOD, "title1", "SALE", Date.valueOf(LocalDate.now()),
				Date.valueOf(LocalDate.now().plusMonths(2)), 10, 34.99, "image2");
		try {
			companyFacade.updateCoupon(1, coupon);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		}

	}

	public static void deleteCouponTest() {
		System.out.println("Delete coupon succeeded");
		try {
			companyFacade.deleteCoupon(6);
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		}

	}

	public static void getAllCompanyCouponTest() {
		System.out.println("Get All Company Coupon succeeded");
		try {
			for (Coupon coupon : companyFacade.getAllCompanyCoupon()) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExeption e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getAllCompanyCouponByCategoryTest() {
		System.out.println("Get All Company Coupon By Category succeeded");
		try {
			for (Coupon coupon : companyFacade.getAllCompanyCouponByCategory(CATEGORY.FOOD)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println();
		}
	}

	public static void getAllCompanyCouponBypriceTest() {
		System.out.println("Get All Company coupon by price succeeded");
		try {
			for (Coupon coupon : companyFacade.getAllCompanyCouponByprice(25)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExeption e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println();
		}
	}

	public static void getCompanyDetalisTest() {
		System.out.println("Get company detalis");
		try {
			System.out.println(companyFacade.getCompanyDetalis());
		} catch (CouponSystemExeption e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}
}