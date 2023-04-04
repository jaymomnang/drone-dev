package models;

import java.sql.Connection;
import java.sql.DriverManager;  
import java.sql.SQLException; 

public class helpers {

    private String connectionMessage;
    private Connection conn;

    public helpers() {
        this.connectionMessage = "Lost connection to database!";
        this.conn = connect();        
    }

    public String getConnectionMessage() {
        return connectionMessage;
    }    
    public Connection getConn() {
        return conn;
    }   
    
    /**
     * Establishes a connection to the SQLite database
     * @return the database connection
     */
    public Connection connect() {         
        
        try {  
            // connection string for db
            String url = "jdbc:sqlite:./sqlite/droneOps.db";
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }  
            // create a connection to the database  
            this.conn = DriverManager.getConnection(url);
            this.connectionMessage = "Connection to SQLite Successful."; 
              
        } catch (SQLException e) {  
            this.connectionMessage = e.getMessage();
        } finally {  
            try {  
                if (conn != null) {  
                    conn.close();  
                }  
            } catch (SQLException ex) {  
                this.connectionMessage = ex.getMessage();
            }  
        }        
        return conn;
    }    
    
}

//List of drone available models
enum droneMODELS{
    Lightweight (50), //50grams maximum
    Middleweight (100), 
    Cruiserweight (250), 
    Heavyweight (500);

    private final int value;
    droneMODELS(final int val) {
        value = val;
    }
    public int getValue() { return value; }
}

//List of drone available states
enum droneSTATUS{
    IDLE, 
    LOADING, 
    LOADED, 
    DELIVERING, 
    DELIVERED, 
    RETURNING
}