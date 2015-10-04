package dao;


import entity.Pet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FacadeDao {

    private Connection connection;
    private PetDao petDao;

    private Statement statement;

    public FacadeDao() {
        try {
            String userName = "root";
            String password = "toor";
            String url = "jdbc:mysql://localhost/myzoo";
            try {
                Class.forName ("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(FacadeDao.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(url, userName, password);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (connection == null) {
            System.exit(1);
        }
        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        petDao = new PetDao(statement, connection);
    }

    public AbstractDao<Pet> getPetDao() {
        return petDao;
    }


    public void closeSqlConnection() {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
