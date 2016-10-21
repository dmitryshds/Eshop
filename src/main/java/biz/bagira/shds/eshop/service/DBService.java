package biz.bagira.shds.eshop.service;

import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

/**
 * Created by Dmitriy on 06.10.2016.
 */
 public class DBService {

    final static Logger logger = Logger.getLogger(DBService.class);

    Properties queriProperties;


    public DBService() {
        logger.debug("Constructor DBService() -- loadProperties()");
        loadProperties();
    }

    //TODO for test only
//    public Connection getConnection() {
//
//        Connection connection = null;
//        try {
//            Class.forName("com.mysql.jdbc.Driver").newInstance();
//
//
//            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/shop?" +
//                                   "user=root&password=root");
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }


 public Connection getConnection() {
        Connection connection = null;
        try {
            Context context = new InitialContext();
            Context initContext = (Context) context.lookup("java:/comp/env");
            DataSource ds = (DataSource) initContext.lookup("jdbc/shop");
            connection = ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }






    public String getQuery(String name) {
        logger.debug("Get query  " + name);
        if (queriProperties == null)
            loadProperties();
        String query = queriProperties.getProperty(name);
        logger.debug("query = " + query);
        return query;


    }


    private void loadProperties() {

        queriProperties = new Properties();
        try {
            queriProperties.load(getClass().getResourceAsStream("/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
