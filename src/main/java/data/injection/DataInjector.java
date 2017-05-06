package data.injection;

import data.database.ResetDatabase;
import model.HibernateSessionFactoryHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.sql.SQLException;

public class DataInjector {

    private static final Logger LOGGER = LogManager.getLogger(DataInjector.class);


    public static void main(String[] args) throws SQLException, ClassNotFoundException, IOException {

        ResetDatabase.dropTables();
        ResetDatabase.createTables();

        new GamesInjection(false).injectData();
        new UsersAddressesAndShoppingInjection().injectData();

        LOGGER.info("Closing session factory");
        HibernateSessionFactoryHandler.getSessionFactory().close();
    }

}
