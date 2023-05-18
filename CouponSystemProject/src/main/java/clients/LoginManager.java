package clients;

import java.sql.SQLException;

import beans.Company;
import dao.CustomerDAOImpl;
import exception.CouponSystemExeption;
import facade.AdminFacade;
import facade.AdminFacadeImpl;
import facade.ClientFacade;
import facade.CompanyFacadeImpl;
import facade.CustomerFacadeImpl;

public class LoginManager {

	private static final LoginManager instance = new LoginManager();

	private LoginManager() {

	}

	public static LoginManager getInstance() {
		return instance;

	}

	public ClientFacade login(String email, String password, ClientType clientType)
			throws CouponSystemExeption, SQLException {
		switch (clientType) {
		case ADMINISTRATOR:
			AdminFacadeImpl adminFacade = new AdminFacadeImpl();
			if (adminFacade.login(email, password)) {
				return adminFacade;
			}

			break;
		case COMPANY:
			CompanyFacadeImpl companyFacade = new CompanyFacadeImpl();
			if (companyFacade.login(email, password)) {
				return companyFacade;
			}

			break;
		case CUSTOMER:
			CustomerFacadeImpl customerFacadeImpl = new CustomerFacadeImpl();
			if (customerFacadeImpl.login(email, password)) {
				return customerFacadeImpl;
			}
			break;

		default:
			throw new CouponSystemExeption("invalid client type");
		}
		throw new CouponSystemExeption("login client type");
	}
}
