package tests;

import java.sql.SQLException;

import db.DatabaseManager;
import thread.DailyJob;

public class TestAll {

	public static void runAllTests() {
		try {
			DatabaseManager.dropAndCreateStrategy();
		} catch (SQLException e) {

			e.printStackTrace();
		}

		DailyJob dailyJob = new DailyJob();
		Thread thread = new Thread(dailyJob);
		thread.setDaemon(true);
		thread.start();
		System.out.println("\u001b[31m-----ADMIN_FACADE_TEST-----\u001b[0m");
		System.out.println("\u001b[31m==========================\u001b[0m");
		AdminFacadeTest.runAllAdminFacadeTests();
		System.out.println("\u001b[34m-----COMPANY_FACADE_TEST-----\u001b[0m");
		System.out.println("\u001b[34m=============================\u001b[0m");
		CompanyFacadeTest.runAllCompanyFacadeTests();
		System.out.println("\u001b[33m----CUSTOMER_FACADE_TEST-----\u001b[0m");
		System.out.println("\u001b[33m==============================\u001b[0m");
		CustomerFacadeTest.runAllCompanyFacadeTests();

		DailyJob.stop();
	}

}
