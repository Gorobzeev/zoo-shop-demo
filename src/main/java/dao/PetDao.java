package dao;

import entity.Pet;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PetDao implements AbstractDao<Pet> {

    private static final String TABLE_NAME = "pets";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_PRICE = "price";
    private static final String COLUMN_CATEGORY = "category";
    private Statement statement;
    private Connection connection;


    public PetDao(Statement statement, Connection connection) {
        this.statement = statement;
        this.connection = connection;

    }

    @Override
    public List<Pet> loadAll() {
        List<Pet> pets = new ArrayList<>();

        try {
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM " + TABLE_NAME + ";");
            while (resultSet.next()) {
                int id = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                int price = resultSet.getInt(COLUMN_PRICE);
                String category = resultSet.getString(COLUMN_CATEGORY);
                pets.add(new Pet(id, name, price, category));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pets;
    }

    @Override
    public Pet findById(int id) {
        Pet pet = null;
        try {
            ResultSet resultSet = statement
                    .executeQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + id
                            + ";");
            while (resultSet.next()) {
                int idFind = resultSet.getInt(COLUMN_ID);
                String name = resultSet.getString(COLUMN_NAME);
                int price = resultSet.getInt(COLUMN_PRICE);
                String category = resultSet.getString(COLUMN_CATEGORY);
                pet = new Pet(idFind, name, price, category);
                break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    @Override
    public boolean delete(Pet delete) {
        try {
            statement.executeUpdate("DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + "=" + delete.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public boolean update(Pet changed) {
        try {
            statement.executeUpdate("UPDATE " + TABLE_NAME + " SET "
                    + COLUMN_NAME + "='" + changed.getName() + "',"
                    + COLUMN_PRICE + "='" + changed.getPrice() + "',"
                    + COLUMN_CATEGORY + "='" + changed.getCategory()
                    + "' WHERE " + COLUMN_ID + "=" + changed.getId() + ";");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Pet add(Pet item) {
        try {
            statement.executeUpdate("INSERT INTO " + TABLE_NAME + " " + " ("
                    + COLUMN_NAME + ", "
                    + COLUMN_PRICE + ", "
                    + COLUMN_CATEGORY +")"
                    + " VALUES ('"
                    + item.getName() + "','"
                    + item.getPrice() + "','"
                    + item.getCategory()
                    + "')");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
