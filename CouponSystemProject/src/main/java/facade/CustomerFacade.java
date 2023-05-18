package facade;

import java.sql.SQLException;
import java.util.List;

import beans.CATEGORY;
import beans.Coupon;
import beans.Customer;
import exception.CouponSystemExeption;

public interface CustomerFacade {

	void pruchaseCoupon(Coupon coupon) throws CouponSystemExeption, SQLException;

	List<Coupon> getCustomerCoupons() throws CouponSystemExeption, SQLException;

	List<Coupon> getCustomerCoupons(CATEGORY category) throws CouponSystemExeption, SQLException;

	List<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemExeption, SQLException;

	Customer getCustomerDetails() throws CouponSystemExeption, SQLException;
}
