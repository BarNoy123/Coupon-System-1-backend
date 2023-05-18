package facade;

import java.sql.SQLException;
import java.util.List;

import beans.CATEGORY;
import beans.Company;
import beans.Coupon;
import exception.CouponSystemExeption;

public interface CompanyFacade {

	void addCoupon(Coupon coupon) throws CouponSystemExeption, SQLException;
	
	void updateCoupon(int couponId,Coupon coupon) throws CouponSystemExeption, SQLException;
	
	void  deleteCoupon(int couponId) throws CouponSystemExeption, SQLException;
	
	List<Coupon> getAllCompanyCoupon() throws CouponSystemExeption, SQLException;

	List<Coupon> getAllCompanyCouponByCategory(CATEGORY category) throws CouponSystemExeption, SQLException;

	List<Coupon> getAllCompanyCouponByprice(double price) throws CouponSystemExeption, SQLException;

	Company getCompanyDetalis()throws CouponSystemExeption, SQLException;
}
