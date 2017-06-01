package poker.util.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UpdateUtil {

	public static List update(String str, IParseResultSet iprs) throws SQLException {

		Connection connect = DBManager.getConnection();

		Statement statement = connect.createStatement();

		int resultSet = statement.executeUpdate(str);

		System.out.println("更新了" + resultSet + "行");

		return iprs.action(resultSet);
	}

}
