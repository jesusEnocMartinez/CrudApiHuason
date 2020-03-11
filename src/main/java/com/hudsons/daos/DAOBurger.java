
package com.hudsons.daos;

import com.hudsons.classes.Burger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DAOBurger extends IDAO<Burger, Integer> {

    private static DAOBurger instance = null;

    public static DAOBurger getInstance() {
        if (instance == null) {
            instance = new DAOBurger();
        }
        return instance;
    }

    @Override
    public void create(Burger p) throws SQLException {
        try (
                PreparedStatement pr = this.connection.prepareStatement("INSERT INTO burger (name, price) VALUES (?,?)")) {
            pr.setString(1, p.getName());
            pr.setFloat(2, p.getPrice());
            pr.execute();
        }
    }

    @Override
    public Burger findById(Integer id) throws SQLException {
        try (
                PreparedStatement pr = this.connection.prepareStatement("SELECT * FROM  burger WHERE id = ?")) {
            pr.setInt(1, id);
            ArrayList<Burger> arr = mappingResultSetToObject(pr);
            if (arr.size() > 0) {
                return arr.get(0);
            }
            return null;
        }
    }

    private ArrayList<Burger> mappingResultSetToObject(PreparedStatement ps) throws SQLException {
        ArrayList<Burger> array = new ArrayList<>();
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                array.add(new Burger(rs.getInt("id"), rs.getFloat("price"), rs.getString("name")));
            }
        }
        return array;
    }

    @Override
    public void update(Burger p) throws SQLException {
        try (
                PreparedStatement pr = this.connection.prepareStatement("UPDATE burger SET name = ?, price=? WHERE id = ?")) {
            pr.setString(1, p.getName());
            pr.setFloat(2, p.getPrice());
            pr.setInt(3, p.getId());
            pr.execute();
        }
    }

    @Override
    public ArrayList<Burger> getAll() throws SQLException {
        try (
                PreparedStatement pr = this.connection.prepareStatement("SELECT * FROM  burger")) {
            return mappingResultSetToObject(pr);
        }
    }

    @Override
    public void delete(Integer p) throws SQLException {
        try (
                PreparedStatement pr = this.connection.prepareStatement("DELETE FROM  burger WHERE id = ?")) {
            pr.setInt(1, p);
            pr.execute();
        }
    }

}
