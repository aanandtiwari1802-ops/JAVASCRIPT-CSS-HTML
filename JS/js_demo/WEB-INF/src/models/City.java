package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class City {
    private Integer cityId;
    private String name;
    private State state;

    public City(Integer cityId, String name) {
        this.cityId = cityId;
        this.name = name;
    }

    public static ArrayList<City> collectAllCities(int stateId) {
        ArrayList<City> cities = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brsdb?user=root&password=1234");

            String query = "select * from cities where state_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, stateId);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                cities.add(new City(rs.getInt("city_id"), rs.getString("name")));
            }
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cities;
    }

    public Integer getCityId() {
        return cityId;
    }
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public State getState() {
        return state;
    }
    public void setState(State state) {
        this.state = state;
    }

    
}
