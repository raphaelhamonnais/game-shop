package database;

import model.HibernateSessionFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public abstract class DataInjection {

    private static final Logger LOGGER = LogManager.getLogger(DataInjection.class);

    protected String dataFilePath;

    protected SessionFactory sessionFactory;
    protected Iterable<CSVRecord> records;


    public DataInjection() {
        sessionFactory = HibernateSessionFactory.getSessionFactory();
    }

    protected void injectData()  throws IOException {
        dataFilePath = getDataFilePaht();
        Reader in = new FileReader(dataFilePath);
        records = parseCsvFile(in);
        createDataFromCsv();
        saveToSession();
    }

    private static CSVParser parseCsvFile(Reader in) throws IOException {
        return CSVFormat
                .EXCEL
                .withHeader()
                .parse(in);
    }

    protected abstract String getDataFilePaht();

    protected abstract void createDataFromCsv();

    protected void saveToSession(){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            LOGGER.info("Saving data to session");
            saveCreatedDataToSession(session);
            LOGGER.info("Commiting transactions");
            transaction.commit();
        }
        catch (Exception e) {
            LOGGER.error("Error while savind data, rolling back");
            transaction.rollback();
            throw e;
        }
        finally {
            LOGGER.info("Closing session");
            session.close();
        }
    }

    protected abstract void saveCreatedDataToSession(Session session);
}
