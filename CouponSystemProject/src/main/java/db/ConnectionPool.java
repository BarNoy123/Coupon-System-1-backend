package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {

	private static final int NUM_OF_CONS = 10;
	// step 1
	private static ConnectionPool instance = new ConnectionPool();

	private Stack<Connection> connections;

	// step 2
	private ConnectionPool() {

		this.connections = new Stack<>();
		for (int i = 0; i < NUM_OF_CONS; i++) {
			try {
				connections.push(DriverManager.getConnection(DatabaseManager.url,DatabaseManager.username,
						DatabaseManager.password));
			} catch (SQLException e) {
				e.printStackTrace();

			}
		}
	}

	// step 3
	public static ConnectionPool getInstance() {
		return instance;
	}

	public Connection getConnection() {
		synchronized (connections) {
			if (connections.size() == 0) {
				try {
					connections.wait();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
					;
				}
			}
		}
		return connections.pop();
	}

	public void restoreConnection(Connection conn) {
		synchronized (connections) {
			connections.push(conn);
			connections.notify();
		}
	}

	public void closeAll() throws SQLException {
		synchronized (connections) {
			if (connections.size() != NUM_OF_CONS) {
				try {
					wait();
				} catch (InterruptedException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		for (Connection conn : connections) {
			conn.close();
		}
	}

}
