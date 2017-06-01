package poker.util.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class QueryUtil {

	public static List query(String str, IParseResultSet iprs) throws SQLException {

		Connection connect = DBManager.getConnection();
		Statement statement = connect.createStatement();
		ResultSet resultSet = statement.executeQuery(str);
		return iprs.action(resultSet);

	}

}
