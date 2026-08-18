package back.interfacedb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class InterfaceSQL {

	private String m_sgbdName;
	private String m_domainName;
	private int m_port;
	private String m_databaseName;
	private String m_password = "";
	private String m_user = "";

	private Connection connection;
	private boolean isConnected = false;

	public InterfaceSQL(String sgbdName, String domainName, int port, String databaseName) {
		m_sgbdName = sgbdName;
		m_domainName = domainName;
		m_port = port;
		m_databaseName = databaseName;
	}

	public void setPassword(String password) {
		m_password = password;
	}

	public void setUser(String user) {
		m_user = user;
	}

	public void connect() throws SQLException {
		if(!isConnected) {
			if(m_user == "") {
				throw new RuntimeException("You haven't specified user yet");
			}
			if(m_password == "") {
				throw new RuntimeException("You haven't specified password yet");
			}

			String url = "jdbc:" + m_sgbdName + "://" + m_domainName + ":" + m_port + "/" + m_databaseName;

			connection = DriverManager.getConnection(
				url, m_user, m_password
			);

			if(connection != null && !connection.isClosed()) {
				isConnected = true;
			} else {
				throw new RuntimeException("Error while connect to database");
			}
		}
	}

	public void close() throws SQLException {
		if(connection != null && !connection.isClosed()) {
			connection.close();
			isConnected = false;
		}
	}

	public Connection getConnection() {
		if(!isConnected) {
			throw new RuntimeException("Database not connected");
		}

		return connection;
	}

}