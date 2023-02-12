package services;

import dataDto.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientService {

    public long create(String name) {
        long generatedId = 0;
        try (PreparedStatement insertIntoClientSt =
                     Database.getInstance().getConnection()
                             .prepareStatement("INSERT INTO client (name) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            insertIntoClientSt.setString(1, name);
            insertIntoClientSt.executeUpdate();
            try (ResultSet generatedKeys = insertIntoClientSt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getLong(1);
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return generatedId;
    }

    public String getById(long id) {
        String name = "";
        try (PreparedStatement selectFromClientSt =
                     Database.getInstance().getConnection()
                             .prepareStatement("SELECT * FROM client WHERE id = ?")) {
            selectFromClientSt.setLong(1, id);

            try (ResultSet resultSet = selectFromClientSt.executeQuery()) {
                resultSet.next();
                return resultSet.getString("name");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return name;
    }

    public void setName(long id, String name) {
        try (PreparedStatement setClientSt =
                     Database.getInstance().getConnection()
                             .prepareStatement("UPDATE client SET name = ? WHERE id = ?")) {
            setClientSt.setString(1, name);
            setClientSt.setLong(2, id);
            setClientSt.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    public void deleteById(long id) {

        try (PreparedStatement deleteClientSt =
                     Database.getInstance().getConnection()
                             .prepareStatement("DELETE FROM client WHERE id = ?")) {
            deleteClientSt.setLong(1, id);
            deleteClientSt.execute();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public List<Client> listAll() {
        List<Client> clientList = new ArrayList<>();

        try (PreparedStatement preparedStatement = Database.getInstance()
                .getConnection()
                .prepareStatement("SELECT *  FROM client");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                clientList.add(new Client(resultSet.getLong("id"), resultSet.getString("name")));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return clientList;
    }

}
