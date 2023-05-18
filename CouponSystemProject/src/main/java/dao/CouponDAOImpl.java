package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import beans.CATEGORY;
import beans.Company;
import beans.Coupon;
import db.ConvertUtils;
import db.JDBCUtils;

public class CouponDAOImpl implements CouponDAO {

	private static final String INSERT_COUPON = "INSERT INTO `bar_coupon_system`.`coupons` (`company_id`,`category_id`,`title`,`description`,`start_date`,`end_date`,`amount`,`price`,`image`) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE_COUPON = "UPDATE `bar_coupon_system`.`coupons` SET `company_id` = ?, `category_id` = ?, `title` = ?, `description` = ?, `start_date` = ?, `end_date` = ?, `amount` = ?, `price` = ?, `image` = ? WHERE (`id` = ?)";
	private static final String DELETE_COUPON = "DELETE FROM `bar_coupon_system`.`coupons` WHERE (`id` = ?)";
	private static final String GET_COUPON = "SELECT * FROM `bar_coupon_system`.`coupons` WHERE (`id` = ?);";
	private static final String GET_ALL_COUPON = "SELECT * FROM `bar_coupon_system`.`coupons`";
	private static final String EXISTS_BY_COMPANYID_AND_TITLE = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`coupons` WHERE `company_id` = ? and `title` = ?)AS res";
	private static final String EXISTS_BY_ID_AND_COMPANYID = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`coupons` WHERE `id` = ? and `company_id` = ?)AS res";
	private static final String DELETE_ALL_COUPONS_PURCHASED_BY_CUSTOMERID = "DELETE FROM `bar_coupon_system`.`customers_coupons`WHERE `customers_id` = ?";
	private static final String DELETE_ALL_COUPONS_PURCHASED_BY_COUPONID = "DELETE FROM `bar_coupon_system`.`customers_coupons`WHERE `coupon_id` = ?";
	private static final String DELETE_ALL_COUPONS_BY_COMPANYID = "DELETE FROM `bar_coupon_system`.`coupons` WHERE (`company_id` = ?)";
	private static final String PURCHASE_COUPON = "INSERT INTO `bar_coupon_system`.`customers_coupons`(`customers_id`,`coupon_id`) VALUES(?,?)";
	private static final String IS_PURCHASE_EXIST_BY_CSTOMERID_AND_COUPONID = "SELECT EXISTS (SELECT * FROM `bar_coupon_system`.`customers_coupons` WHERE `customers_id` = ? and `coupon_id`= ?)AS res";
	private static final String GET_ALL_COMPANY_COUPONS_BY_COMPANYID = "SELECT * FROM `bar_coupon_system`.`coupons` WHERE (`company_id` = ?)";
	private static final String GET_ALL_COMPANY_COUPONS_BY_COMPANYID_AND_CATEGORY = "SELECT * FROM `bar_coupon_system`.`coupons` WHERE `company_id` = ? and `category_id` = ?";
	private static final String GET_ALL_COMPANY_COUPONS_BY_COMPANYID_AND_PRICE = "SELECT * FROM `bar_coupon_system`.`coupons` WHERE `company_id` = ? and `price` <= ?";
	private static final String DELETE_ALL_EXPIRED_PURCHASE_COUPONS = "DELETE FROM `bar_coupon_system`.`customers_coupons` WHERE `coupon_id` IN (SELECT `id` FROM `bar_coupon_system`.`coupons` WHERE `end_date`< current_date())";
	private static final String DELETE_EXPIRED_COUPONS = "DELETE FROM `bar_coupon_system`.`coupons` WHERE `end_date`< current_date()";
	private static final String GET_ALL_COUPONS_BY_CUSTOMERS_ID_AND_CATEGORY = "SELECT * FROM `bar_coupon_system`.`coupons`  JOIN `bar_coupon_system`.`customers_coupons` ON `bar_coupon_system`.`coupons`.id = `bar_coupon_system`.`customers_coupons`.coupon_id WHERE (`bar_coupon_system`.`customers_coupons`.`customers_id` = ?) AND (`bar_coupon_system`.`coupons`.`category_id` = ?)";
	private static final String GET_ALL_COUPONS_BY_CUSTOMERS_ID_AND_MAX_PRICE = "SELECT * FROM `bar_coupon_system`.`coupons`  JOIN `bar_coupon_system`.`customers_coupons` ON `bar_coupon_system`.`coupons`.id = `bar_coupon_system`.`customers_coupons`.coupon_id WHERE (`bar_coupon_system`.`customers_coupons`.`customers_id` = ?) AND (`bar_coupon_system`.`coupons`.`price` <= ?)";
	private static final String GET_ALL_COUPONS_BY_CUSTOMERS_ID = "SELECT * FROM `bar_coupon_system`.`coupons` AS c JOIN `bar_coupon_system`.`customers_coupons` AS cvc ON c.id = cvc.coupon_id WHERE (`customers_id` = ?)";

	@Override
	public void addCoupon(Coupon coupon) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, coupon.getCompanyId());
		params.put(2, coupon.getCategory().ordinal() + 1);
		params.put(3, coupon.getTitle());
		params.put(4, coupon.getDescription());
		params.put(5, coupon.getStartDate());
		params.put(6, coupon.getEndDate());
		params.put(7, coupon.getAmount());
		params.put(8, coupon.getPrice());
		params.put(9, coupon.getImage());

		JDBCUtils.runQuery(INSERT_COUPON, params);

	}

	@Override
	public void updateCoupon(int couponId, Coupon coupon) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, coupon.getCompanyId());
		params.put(2, coupon.getCategory().ordinal() + 1);
		params.put(3, coupon.getTitle());
		params.put(4, coupon.getDescription());
		params.put(5, coupon.getStartDate());
		params.put(6, coupon.getEndDate());
		params.put(7, coupon.getAmount());
		params.put(8, coupon.getPrice());
		params.put(9, coupon.getImage());
		params.put(10, couponId);
		System.out.println(params);
		JDBCUtils.runQuery(UPDATE_COUPON, params);

	}

	@Override
	public void deleteCoupon(int couponId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, couponId);

		JDBCUtils.runQuery(DELETE_COUPON, params);
	}

	@Override
	public Coupon getCoupon(int couponId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, couponId);
		List<?> list = JDBCUtils.runQueryWithResult(GET_COUPON);
		Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) list.get(0));
		return coupon;

	}

	@Override
	public List<Coupon> getAllCoupons() throws SQLException {
		List<Coupon> coupons = new ArrayList<>();
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COUPON);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;

	}

	@Override
	public boolean isExistByCompanyIdAndTitle(int companyId, String title) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);
		params.put(2, title);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_COMPANYID_AND_TITLE, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;
	}

	@Override
	public boolean isExistByIdAndCompanyId(int id, int companyId) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, id);
		params.put(2, companyId);
		List<?> list = JDBCUtils.runQueryWithResult(EXISTS_BY_ID_AND_COMPANYID, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;
	}

	@Override
	public void deleteAllCouponsPurchasedByCustomerId(int customerId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customerId);

		JDBCUtils.runQuery(DELETE_ALL_COUPONS_PURCHASED_BY_CUSTOMERID, params);

	}

	@Override
	public void deleteAllCouponsPurchasedByCouponId(int couponId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, couponId);

		JDBCUtils.runQuery(DELETE_ALL_COUPONS_PURCHASED_BY_COUPONID, params);

	}

	@Override
	public void deleteAllCouponsByCompanyId(int companyId) throws SQLException {
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);

		JDBCUtils.runQuery(DELETE_ALL_COUPONS_BY_COMPANYID, params);

	}

	@Override
	public void purchaseCoupon(int customersId, int couponId) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customersId);
		params.put(2, couponId);

		JDBCUtils.runQuery(PURCHASE_COUPON, params);

	}

	@Override
	public boolean isPurchaseExistByCstomerIdAndCouponId(int customersId, int couponId) throws SQLException {
		boolean result = false;
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customersId);
		params.put(2, couponId);
		List<?> list = JDBCUtils.runQueryWithResult(IS_PURCHASE_EXIST_BY_CSTOMERID_AND_COUPONID, params);
		result = ConvertUtils.objectToBool((Map<String, Object>) list.get(0));
		return result;
	}

	@Override
	public List<Coupon> getAllCompanyCouponsByCompanyId(int companyId) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);
		List<Coupon> coupons = new ArrayList<>();
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COMPANY_COUPONS_BY_COMPANYID, params);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCompanyCouponsByCompanyIdAndCategory(int companyId, CATEGORY category)
			throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);
		params.put(2, category.ordinal() + 1);

		List<Coupon> coupons = new ArrayList<>();
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COMPANY_COUPONS_BY_COMPANYID_AND_CATEGORY, params);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;
	}

	@Override
	public List<Coupon> getAllCompanyCouponsByCompanyIdAndPrice(int companyId, double price) throws SQLException {

		Map<Integer, Object> params = new HashMap<>();
		params.put(1, companyId);
		params.put(2, price);

		List<Coupon> coupons = new ArrayList<>();
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COMPANY_COUPONS_BY_COMPANYID_AND_PRICE, params);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;
	}

	@Override
	public void deleteAllExpiredCoupons() throws SQLException {
		JDBCUtils.runQuery(DELETE_ALL_EXPIRED_PURCHASE_COUPONS);
		JDBCUtils.runQuery(DELETE_EXPIRED_COUPONS);

	}

	@Override
	public List<Coupon> getAllCustomerCouponByCustomerIdAndCategory(int customerId, CATEGORY category)
			throws SQLException {
		List<Coupon> coupons = new ArrayList<>();
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customerId);
		params.put(2, category.ordinal() + 1);
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COUPONS_BY_CUSTOMERS_ID_AND_CATEGORY, params);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;
	}

	@Override
	public List<Coupon>getAllCustomerCouponByCustomerIdAndMaxPrice(int customerId, double price) throws SQLException {
		List<Coupon> coupons = new ArrayList<>();
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customerId);
		params.put(2, price);
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COUPONS_BY_CUSTOMERS_ID_AND_MAX_PRICE, params);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;
	}

	@Override
	public List<Coupon>getAllCouponsByCustomerId(int customerId) throws SQLException {
		List<Coupon> coupons = new ArrayList<>();
		Map<Integer, Object> params = new HashMap<>();
		params.put(1, customerId);
		List<?> list = JDBCUtils.runQueryWithResult(GET_ALL_COUPONS_BY_CUSTOMERS_ID, params);
		for (Object obj : list) {
			Coupon coupon = ConvertUtils.objectToCoupon((Map<String, Object>) obj);
			coupons.add(coupon);
		}
		return coupons;

	}
}
