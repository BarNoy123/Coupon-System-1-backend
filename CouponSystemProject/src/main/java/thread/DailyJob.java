package thread;

import java.awt.desktop.QuitEvent;
import java.sql.SQLException;

import dao.CouponDAO;
import dao.CouponDAOImpl;

public class DailyJob implements Runnable {

	private CouponDAO couponDAO;
	private static boolean quite=false;
	private static final int timeToSleep = 1000 * 60 * 60 * 24;

	public DailyJob() {
		this.couponDAO = new CouponDAOImpl();


	}

	@Override
	public void run() {
		while (quite == false) {
			try {
				couponDAO.deleteAllExpiredCoupons();
			} catch (SQLException e) {
				System.out.println(e);

			}

			try {
				Thread.sleep(timeToSleep);
			} catch (InterruptedException e) {
				System.out.println(e);
			}
		}
	}

	public static void stop() {
		quite = true;
	}
}
