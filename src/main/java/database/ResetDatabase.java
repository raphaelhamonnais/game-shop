package database;


import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ResetDatabase {


    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/sr03_web_project?serverTimezone=UTC";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";

    private static final String DROP_TABLES_SCRIPT = "sql-scripts/current/drop_tables.sql";
    private static final String CREATE_TABLES_SCRIPT = "sql-scripts/current/create_tables.sql";
    private static final String DELETE_FROM_TABLES_SCRIPT = "sql-scripts/current/truncate_tables.sql";
    private static final String INSERT_INTO_TABLES_SCRIPT = "sql-scripts/current/insert_tables.sql";

    private static void executeScript(String scriptToExecute) throws ClassNotFoundException, SQLException {

        Class.forName(DRIVER);
        // Create MySql Connection as try resource
        try (Connection con = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD)) {
            // Initialize object for ScripRunner
            ScriptRunner sr = new ScriptRunner(con);

            // Give the input file to Reader
            String sqlScript = ResetDatabase.class.getClassLoader().getResource(scriptToExecute).getFile();
            Reader reader = new BufferedReader(new FileReader(sqlScript));

            // Exctute script
            sr.runScript(reader);
        } catch (Exception e) {
            System.err.println("Failed to Execute" + scriptToExecute
                    + " The error is " + e.getMessage());
        }
    }


    public static void dropTables() throws SQLException, ClassNotFoundException {
        executeScript(DROP_TABLES_SCRIPT);
    }
    public static void createTables() throws SQLException, ClassNotFoundException {
        executeScript(CREATE_TABLES_SCRIPT);
    }
    public static void deleteFromTables() throws SQLException, ClassNotFoundException {
        executeScript(DELETE_FROM_TABLES_SCRIPT);
    }
    public static void insertIntoTables() throws SQLException, ClassNotFoundException {
        executeScript(INSERT_INTO_TABLES_SCRIPT);
    }

}
