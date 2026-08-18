package back.repository;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import back.interfacedb.InterfaceSQL;
import back.model.Medoc;

public class MedocRepository {

	private static ArrayList<Medoc> _listMedoc = new ArrayList<>();

	// database tools
	private static InterfaceSQL _interfaceSQL;
	private static Connection _connection;

	//class test
	private static boolean isConnectionOk = false;

	public static ArrayList<Medoc> getListsMedoc() {
		refreshList();
		return _listMedoc;
	}

	///////////////////////////////////////////////
	//	INTERN OPERATIOm
	///////////////////////////////////////////////

	private static void refreshList() {
		if(!isConnectionOk) {
			etablishConnection();
		}

		String query = "SELECT * FROM medoc";
		try (
			PreparedStatement stmt = _connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
		) {
			while(result.next()) {
				Medoc medoc = new Medoc(
					result.getString("medoc_number"),
					result.getString("medoc_designation"),
					result.getFloat("medoc_unit_price"),
					result.getInt("medoc_stock"),
					result.getBoolean("medoc_is_active"),
					result.getString("medoc_type")
				);

				_listMedoc.add(medoc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private static void etablishConnection() {
		_interfaceSQL = new InterfaceSQL(
			"XXXXXXXX",
			"XXXXXXXXX",
			000000,
			"XXXXXXXX"
		);

		_interfaceSQL.setUser("root");
		_interfaceSQL.setPassword("Hasambarana36");

		try {
			_interfaceSQL.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		_connection = _interfaceSQL.getConnection();
	
		isConnectionOk = true;
	}
}