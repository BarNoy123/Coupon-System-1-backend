package facade;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import beans.CATEGORY;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import exception.CouponSystemExeption;

public class CustomerFacadeImpl extends ClientFacade implements CustomerFacade {

	private int customerId;

	@Override
	public boolean login(String email, String password) throws CouponSystemExeption, SQLException {
		if (customerDAO.isExistByEmailAndPassword(email, password) == false) {
			throw new CouponSystemExeption("Customer email or password is worng");
		}
		customerId = customerDAO.getCustomerIdByEmail(email);
		return true;

	}

	@Override
	public void pruchaseCoupon(Coupon coupon) throws CouponSystemExeption, SQLException {
		if (couponDAO.isPurchaseExistByCstomerIdAndCouponId(customerId, coupon.getId())) {
			throw new CouponSystemExeption("Already purchase this coupon");

		}

		if (coupon.getAmount() <= 0) {
			throw new CouponSystemExeption("Coupon SOLD OUT");
		}

		if (coupon.getEndDate().compareTo(Date.valueOf(LocalDate.now())) < 0) {
			throw new CouponSystemExeption("Can't purchase expired coupon");
		}
		couponDAO.purchaseCoupon(customerId, coupon.getId());
		coupon.setAmount(coupon.getAmount() - 1);
		couponDAO.updateCoupon(coupon.getId(), coupon);
	}

	@Override
	public List<Coupon> getCustomerCoupons() throws CouponSystemExeption, SQLException {
		return couponDAO.getAllCouponsByCustomerId(customerId);
	}

	@Override
	public List<Coupon> getCustomerCoupons(CATEGORY category) throws CouponSystemExeption, SQLException {

		return couponDAO.getAllCustomerCouponByCustomerIdAndCategory(customerId, category);
	}

	@Override
	public List<Coupon> getCustomerCoupons(double maxPrice) throws CouponSystemExeption, SQLException {

		return couponDAO.getAllCustomerCouponByCustomerIdAndMaxPrice(customerId, maxPrice);
	}

	@Override
	public Customer getCustomerDetails() throws CouponSystemExeption, SQLException {
		List<Coupon> coupons = couponDAO.getAllCouponsByCustomerId(customerId);
		Customer customer = customerDAO.getCustomer(customerId);
		customer.setCoupons(coupons);

		return customer;

	}

}