package facade;

import java.sql.SQLException;
import java.util.List;

import beans.CATEGORY;
import beans.Company;
import beans.Coupon;
import exception.CouponSystemExeption;

public class CompanyFacadeImpl extends ClientFacade implements CompanyFacade {

	private int companyId;

	@Override
	public boolean login(String email, String password) throws CouponSystemExeption {
		if ((companyDAO.isExistByEmailAndPassword(email, password)) == false) {
			throw new CouponSystemExeption("Company email or password is incorrect");
		}
		companyId = companyDAO.getCompanyIdByEmail(email);
		return true;
	}

	@Override
	public void addCoupon(Coupon coupon) throws CouponSystemExeption, SQLException {
		if (couponDAO.isExistByCompanyIdAndTitle(companyId, coupon.getTitle())) {
			throw new CouponSystemExeption("Title already exist");
		}
		couponDAO.addCoupon(coupon);
	}

	@Override
	public void updateCoupon(int couponId, Coupon coupon) throws CouponSystemExeption, SQLException {
		if (couponId != coupon.getId()) {
			throw new CouponSystemExeption("Can't update coupon id");
		}

		if (coupon.getCompanyId() != companyId) {
			throw new CouponSystemExeption("Can't update coupon company id");
		}
		couponDAO.updateCoupon(couponId, coupon);
	}

	@Override
	public void deleteCoupon(int couponId) throws CouponSystemExeption, SQLException {
		if (!couponDAO.isExistByIdAndCompanyId(couponId, companyId)) {
			throw new CouponSystemExeption("Coupon is not found");
		}
	// חסר מחיקת רכישות לפני
		couponDAO.deleteCoupon(couponId);
	}

	@Override
	public List<Coupon> getAllCompanyCoupon() throws CouponSystemExeption, SQLException {

		return couponDAO.getAllCompanyCouponsByCompanyId(companyId);
	}

	@Override
	public List<Coupon> getAllCompanyCouponByCategory(CATEGORY category) throws CouponSystemExeption, SQLException {

		return couponDAO.getAllCompanyCouponsByCompanyIdAndCategory(companyId, category);
	}

	@Override
	public List<Coupon> getAllCompanyCouponByprice(double price) throws CouponSystemExeption, SQLException {

		return couponDAO.getAllCompanyCouponsByCompanyIdAndPrice(companyId, price);
	}

	@Override
	public Company getCompanyDetalis() throws CouponSystemExeption, SQLException {
		List<Coupon> coupons = getAllCompanyCoupon();
		Company company = companyDAO.getCompany(companyId);
		company.setCoupons(coupons);

		return company;
	}

}
