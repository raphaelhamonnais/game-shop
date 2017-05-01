package database;

import model.*;
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
import java.math.BigDecimal;
import java.math.MathContext;
import java.time.LocalDate;
import java.util.*;

public class GamesInjection {

    private static final Logger LOGGER = LogManager.getLogger(GamesInjection.class);
    private static final String GAMES_FULL_DATA_SET = "data-sets/games_full_data_set.csv";
    private static final String GAMES_TEST_DATA_SET = "data-sets/games_test_data_set.csv";



    private static final String NAME_CSV_HEADER = "Name";
    private static final String CONSOLE_CSV_HEADER = "Platform";
    private static final String YEAR_CSV_HEADER = "Year";
    private static final String CATEGORY_CSV_HEADER = "Genre";
    private static final String PUBLISHER_CSV_HEADER = "Publisher";

    private static final BigDecimal START_GAME_PRICE = new BigDecimal(59.99);

    private static List<CSVRecord> dataSet = new ArrayList<>();
    private static Map<String, Console> consolesMap = new HashMap<>();
    private static Map<String, Category> categoriesMap = new HashMap<>();
    private static Map<String, Publisher> publishersMap = new HashMap<>();
    private static Map<String, Game> gamesMap = new HashMap<>();


    public static void injectTestData() throws IOException {
        String dataFilePath = GamesInjection.class.getClassLoader().getResource(GAMES_TEST_DATA_SET).getFile();
        injectData(dataFilePath);
    }

    public static void injectFullData() throws IOException {
        String dataFilePath = GamesInjection.class.getClassLoader().getResource(GAMES_FULL_DATA_SET).getFile();
        injectData(dataFilePath);
    }

    private static void injectData(String dataFilePath) throws IOException {
        Reader in = new FileReader(dataFilePath);
        Iterable<CSVRecord> records = parseCsvFile(in);
        createMapsForConsolesCategoriesAndPublishers(records);
        createGamesAndMapRelations();
        saveToSession();
    }

    private static CSVParser parseCsvFile(Reader in) throws IOException {
        return CSVFormat
                .EXCEL
                .withHeader()
                .parse(in);
    }

    private static void createMapsForConsolesCategoriesAndPublishers(Iterable<CSVRecord> records) {
        // creating maps of categories, consoles and publishers
        int lineNumber = 1;
        for (CSVRecord record : records) {

            if (yearIncorrect(record.get(YEAR_CSV_HEADER)))
                continue;

            if (lineNumber % 1000 == 0)
                LOGGER.info("Parsing CSV file (current lineNumber = " + lineNumber + ")");

            dataSet.add(record);
            String catName = record.get(CATEGORY_CSV_HEADER);
            String consoleName = record.get(CONSOLE_CSV_HEADER);
            String pubName = record.get(PUBLISHER_CSV_HEADER);
            String gameName = record.get(NAME_CSV_HEADER);

            Game game = new Game();
            game.setGameName(gameName);
            try {
                game.setGameReleaseYear(Integer.parseInt(record.get(YEAR_CSV_HEADER)));
            } catch (NumberFormatException ignored) {}


            if (! categoriesMap.containsKey(catName))
                categoriesMap.put(catName, new Category(catName));

            if (! consolesMap.containsKey(consoleName))
                consolesMap.put(consoleName, new Console(consoleName));

            if (! publishersMap.containsKey(pubName))
                publishersMap.put(pubName, new Publisher(pubName));

            if (! gamesMap.containsKey(gameName + pubName))
                gamesMap.put(gameName + pubName, game);

            lineNumber++;
        }
    }

    private static void createGamesAndMapRelations() {
        // Creating games, physical games and mapping relations
        int lineNumber = 1;
        for (CSVRecord record : dataSet) {
            if (lineNumber % 1000 == 0)
                LOGGER.info("Creating physical games and mapping relations (current lineNumber = " + lineNumber + " out of " + dataSet.size() + ")");

            Category category = categoriesMap.get(record.get(CATEGORY_CSV_HEADER));
            Console console = consolesMap.get(record.get(CONSOLE_CSV_HEADER));
            Publisher publisher = publishersMap.get(record.get(PUBLISHER_CSV_HEADER));
            Game game = gamesMap.get(record.get(NAME_CSV_HEADER) + record.get(PUBLISHER_CSV_HEADER));

            ModelRelationsHandler.mapRelations(game, publisher);
            ModelRelationsHandler.mapRelations(game, category);

            PhysicalGame physicalGame = new PhysicalGame(game, console, 100, computePrice(game.getGameReleaseYear()));
            ModelRelationsHandler.mapRelations(game, console, physicalGame);

            lineNumber++;
        }
    }

    private static void saveToSession() {
        SessionFactory sessionFactory = HibernateSessionFactory.getSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        LOGGER.info("Saving data to session");

        try {
            LOGGER.info("Saving Console data");
            for (Console console : consolesMap.values())
                session.saveOrUpdate(console);

            LOGGER.info("Saving Category data");
            for (Category category : categoriesMap.values())
                session.saveOrUpdate(category);

            LOGGER.info("Saving Publisher data");
            for (Publisher publisher : publishersMap.values())
                session.saveOrUpdate(publisher);

            LOGGER.info("Saving Games data");
            for (Game game : gamesMap.values())
                session.saveOrUpdate(game);

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
            LOGGER.info("Closing session factory");
            sessionFactory.close();
        }
    }

    // decrement price by 5% for each year between release date and now
    private static BigDecimal computePrice(int gameReleaseYear) {
        BigDecimal price = START_GAME_PRICE;
        int currentYear = LocalDate.now().getYear();
        int yearsDifference = currentYear - gameReleaseYear;
        LOGGER.debug("Current year = " + currentYear + " --- Game release year = " + gameReleaseYear);
        LOGGER.debug("Initial price : " + price.round(new MathContext(2)));
        if (yearsDifference > 0)
            price = price.multiply(new BigDecimal(0.95).pow(yearsDifference));
        LOGGER.debug("New price : " + price.round(new MathContext(2)));
        return price;
    }


    private static boolean yearIncorrect(String year) {
        if (year == null)
            return true;

        if (year.isEmpty() || year.equals(""))
            return true;

        try {
            Integer.parseInt(year);
        }
        catch (Exception e){
            return true;
        }

        return false;
    }

}
