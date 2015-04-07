package lv.velexauto.velex.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dinjvald on 23/03/15.
 */

public class DatabaseCleaner extends DBConnection {

    private List<String> getTableNames() {
        List<String> tableNames = new ArrayList<String>();
        tableNames.add("agreement");
        return tableNames;
    }

    public void cleanDatabase() throws DBException {
        for(String tableName : getTableNames()) {
            Connection connection = getConnection();
            try {
                connection = getConnection();
                PreparedStatement preparedStatement = connection
                        .prepareStatement("delete from " + tableName);
                preparedStatement.executeUpdate();
            } catch (Throwable e) {
                System.out.println("Exception while execute cleanDatabase() for table " + tableName);
                e.printStackTrace();
                throw new DBException(e);
            } finally {
                closeConnection(connection);
            }
        }
    }
}
