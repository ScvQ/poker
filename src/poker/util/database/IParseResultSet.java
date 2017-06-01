package poker.util.database;

import java.sql.ResultSet;
import java.util.List;

public interface IParseResultSet {
	
	List action(ResultSet resultSet);
	List action(int resultSet);

}
