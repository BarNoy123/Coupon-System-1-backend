package tests;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import beans.CATEGORY;
import beans.Coupon;
import clients.ClientType;
import clients.LoginManager;
import exception.CouponSystemExeption;
import facade.CustomerFacade;

public class CustomerFacadeTest {

	private static CustomerFacade customerFacade = null;

	public static void runAllCompanyFacadeTests() {
		loginTest();
		System.out.println();
		pruchaseCouponTest();
		System.out.println();
		getCustomerCouponsTest();
		System.out.println();
		getCustomerCouponsByCategoryTest();
		System.out.println();
		getCustomerCouponsByPriceTest();
		System.out.println();
		getCustomerDetailsTest();
		System.out.println();
	}

	public static void loginTest() {

		try {
			System.out.println("\u001b[32mLOGIN CUSTOMER SUCCEEDED\u001b[0m");
			customerFacade = (CustomerFacade) LoginManager.getInstance().login("bar@gmail.com", "1234",
					ClientType.CUSTOMER);

		} catch (CouponSystemExeption e) {
			System.out.println(e);

		} catch (SQLException e) {

			System.out.println(e);
		}

	}

	public static void pruchaseCouponTest() {
		Coupon couponToBuy = new Coupon(7, 1, CATEGORY.FOOD, "title" + 22, "description" + 22,
				Date.valueOf(LocalDate.now()), Date.valueOf(LocalDate.now().plusMonths(2)), 10, 10, "image" + 22);
		try {
			customerFacade.pruchaseCoupon(couponToBuy);

		} catch (CouponSystemExeption e) {
			System.out.println(e);

		} catch (SQLException e) {

			System.out.println(e);
		}

	}

	public static void getCustomerCouponsTest() {
		System.out.println("Getcustomer Coupon succeeded");
		try {
			for (Coupon coupon : customerFacade.getCustomerCoupons()) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExeption e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getCustomerCouponsByCategoryTest() {
		System.out.println("Get customer Coupon succeeded by category");
		try {
			for (Coupon coupon : customerFacade.getCustomerCoupons(CATEGORY.FOOD)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExeption e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getCustomerCouponsByPriceTest() {
		System.out.println("Get customer Coupon succeeded by price");
		try {
			for (Coupon coupon : customerFacade.getCustomerCoupons(35)) {
				System.out.println(coupon);
			}
		} catch (CouponSystemExeption e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

	public static void getCustomerDetailsTest() {
		System.out.println("Get customer Detail succeeded");
		try {

			System.out.println(customerFacade.getCustomerDetails());
		} catch (CouponSystemExeption e) {
			System.out.println();
		} catch (SQLException e) {
			System.out.println(e);
		}
	}

}
