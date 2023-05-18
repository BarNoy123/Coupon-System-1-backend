package dao;

import java.sql.SQLException;
import java.util.List;

import beans.CATEGORY;
import beans.Coupon;

public interface CouponDAO {

	void addCoupon(Coupon coupon) throws SQLException;

	void updateCoupon(int couponId, Coupon coupon) throws SQLException;

	void deleteCoupon(int couponId) throws SQLException;

	Coupon getCoupon(int couponId) throws SQLException;

	List<Coupon> getAllCoupons() throws SQLException;

	boolean isExistByCompanyIdAndTitle(int companyId, String title) throws SQLException;

	boolean isExistByIdAndCompanyId(int id, int companyId) throws SQLException;

	void deleteAllCouponsPurchasedByCustomerId(int customerId) throws SQLException;

	void deleteAllCouponsPurchasedByCouponId(int couponId) throws SQLException;

	void deleteAllCouponsByCompanyId(int companyId) throws SQLException;

	void purchaseCoupon(int customersId, int couponId) throws SQLException;

	boolean isPurchaseExistByCstomerIdAndCouponId(int customersId, int couponId) throws SQLException;

	List<Coupon> getAllCouponsByCustomerId(int customerId) throws SQLException;

	List<Coupon> getAllCompanyCouponsByCompanyId(int companyId) throws SQLException;

	List<Coupon> getAllCompanyCouponsByCompanyIdAndCategory(int companyId, CATEGORY category) throws SQLException;

	List<Coupon> getAllCompanyCouponsByCompanyIdAndPrice(int companyId, double price) throws SQLException;

	void deleteAllExpiredCoupons() throws SQLException;

	List<Coupon> getAllCustomerCouponByCustomerIdAndCategory(int customerId, CATEGORY category) throws SQLException;

	List<Coupon> getAllCustomerCouponByCustomerIdAndMaxPrice(int customerId, double price) throws SQLException;
}
