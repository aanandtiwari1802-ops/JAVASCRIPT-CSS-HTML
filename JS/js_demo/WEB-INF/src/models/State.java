package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class State {
    private Integer stateId;
    private String state;   

    public State(Integer stateId, String state) {
        this.stateId = stateId;
        this.state = state;
    }

    public static ArrayList<State> collectAllStates() {
        ArrayList<State> states = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/brsdb?user=root&password=1234");

            String query = "select * from states order by state_id";

            PreparedStatement ps = con.prepareStatement(query);

            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                states.add(new State(rs.getInt("state_id"), rs.getString("state")));
            }
        } catch(SQLException|ClassNotFoundException e) {
            e.printStackTrace();
        }

        return states;
    }

    public Integer getStateId() {
        return stateId;
    }
    public void setStateId(Integer stateId) {
        this.stateId = stateId;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    
    
}