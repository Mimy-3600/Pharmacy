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

	public static boolean registerNewMedoc(Medoc medoc) {
		if(!isConnectionOk) {
			etablishConnection();
		}

		String insertMedocSQL = """
			INSERT INTO medoc (
				medoc_number, 
				medoc_designation, 
				medoc_unit_price
			) VALUES (?, ?, ?);
		""";

		try(PreparedStatement stmt = _connection.prepareStatement(insertMedocSQL)) {
			
			stmt.setString(1, medoc.getMedocNumber());
			stmt.setString(2, medoc.getMedocDesignation());
			stmt.setBigDecimal(3, medoc.getMedocUnitPrice());

			int ok = stmt.executeUpdate();
			if(ok == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while inserting");
		}
	}

	public static boolean deleteMedoc(Medoc medoc) {
		if(!isConnectionOk) {
			etablishConnection();
		}

		String insertMedocSQL = """
			DELETE FROM medoc WHERE medoc_number = ?;
		""";

		try(PreparedStatement stmt = _connection.prepareStatement(insertMedocSQL)) {
			
			stmt.setString(1, medoc.getMedocNumber());

			int ok = stmt.executeUpdate();
			if(ok == 1) {
				return true;
			} else {
				return false;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error while deleting");
		}
	}

	///////////////////////////////////////////////
	//	INTERN OPERATIOm
	///////////////////////////////////////////////

	private static void refreshList() {
		if(!isConnectionOk) {
			etablishConnection();
		}

		String query = "SELECT * FROM medoc WHERE medoc_is_active = TRUE ORDER BY medoc_add_date DESC;";
		try (
			PreparedStatement stmt = _connection.prepareStatement(query);
			ResultSet result = stmt.executeQuery();
		) {
			while(result.next()) {
				Medoc medoc = new Medoc(
					result.getString("medoc_number"),
					result.getString("medoc_designation"),
					result.getBigDecimal("medoc_unit_price"),
					result.getInt("medoc_stock"),
					result.getBoolean("medoc_is_active")
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
			"XXXXXXXX",
			XXXXXXXX,
			"XXXXXXXX"
		);

		_interfaceSQL.setUser("XXXXXXXXXX");
		_interfaceSQL.setPassword("XXXXXXXXXXXXXXXX");

		try {
			_interfaceSQL.connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		_connection = _interfaceSQL.getConnection();
	
		isConnectionOk = true;
	}
}