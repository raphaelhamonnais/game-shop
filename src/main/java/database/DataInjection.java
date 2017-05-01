package database;

import java.io.IOException;
import java.sql.SQLException;

public class DataInjection {

    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {
        ResetDatabase.dropTables();
        ResetDatabase.createTables();

        GamesInjection.injectFullData();
//        GamesInjection.injectTestData();
    }

}
