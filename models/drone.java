package models;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class drone {

    private String serialNo;
    private droneMODELS model;
    private float weight_limit;
    private int battery_capacity;
    private int battery_level;
    private droneSTATUS state;
    private helpers OpsHelper;
    private String message;    


    /**
     * Gets the message from the database operation
     * @return a string value
     */
    public String getMessage() {
        return message;
    }

    /**
     * Set the message from the database operation
     * @param message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Constructor for the drone object.
     * @param serialNo
     * @param model
     * @param weight_limit
     * @param battery_capacity
     * @param battery_level
     * @param state
     */
    public drone(String serialNo, droneMODELS model, float weight_limit, int battery_capacity, int battery_level, droneSTATUS state) {

        this.serialNo = serialNo;
        this.model = model;
        this.weight_limit = weight_limit;
        this.battery_capacity = battery_capacity;
        this.battery_level = battery_level;
        this.state = state;
        this.message = "";
        this.OpsHelper = new helpers();
        this.createDronesTable();
    }

    /**
     * Overloads the constructor for the drone object.
     */
    public drone() {

        this.serialNo = "NEW";
        this.model = droneMODELS.Lightweight;
        this.weight_limit = droneMODELS.Lightweight.getValue();
        this.battery_capacity = 100;
        this.battery_level = 0;
        this.state = droneSTATUS.IDLE;
        this.message = "";
        this.OpsHelper = new helpers();
        this.createDronesTable();
    }

    /**
     * Gets the serial number of the drone object
     * @return a string value
     */
    public String getSerialNo() {
        return serialNo;
    }

    /**
     * Sets the value of the drone serial number
     * @param serialNo
     */
    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    /**
     * Gets the model of the drone object
     * @return an enum value of type droneMODELS
     */
    public droneMODELS getModel() {
        return model;
    }

    /**
     * Sets the value of the drone model
     * @param model
     */
    public void setModel(droneMODELS model) {
        this.model = model;
    }

    /**
     * Gets the weight limit of the drone object
     * @return a decimal value
     */
    public float getWeightLimit() {
        return weight_limit;
    }

    /**
     * Sets the value of the drone weight limit
     * @param weight_limit
     */
    public void setWeightLimit(float weight_limit) {
        this.weight_limit = weight_limit;
    }

    /**
     * Gets the battery capacity of the drone object
     * @return an integer value
     */
    public int getBatteryCapacity() {
        return battery_capacity;
    }

    /**
     * Sets the value of the drone battery capacity
     * @param battery_capacity
     */
    public void setBatteryCapacity(int battery_capacity) {
        this.battery_capacity = battery_capacity;
    }

    /**
     * Gets the battery level of the drone object
     * @return an integer value
     */
    public int getBatteryLevel() {
        return battery_level;
    }

    /**
     * Sets the value of the drone battery level
     * @param battery_level
     */
    public void setBatteryLevel(int battery_level) {
        this.battery_level = battery_level;
    }

    /**
     * Gets the status of the drone object
     * @return an enum value of type droneSTATUS
     */
    public droneSTATUS getState() {
        return state;
    }

    /**
     * Sets the value of the drone status
     * @param state
     */
    public void setState(droneSTATUS state) {
        this.state = state;
    }


    /**
     * Creates the db table for drones. If the table already exists, abort
     */
    private void createDronesTable() {

        // SQL statement for creating a new table
        String qry = "CREATE TABLE IF NOT EXISTS drones (\n"
                + " serialNo text PRIMARY KEY,\n"
                + " model text NOT NULL,\n"
                + " weight_limit real\n"
                + " battery_capacity int,\n"
                + " battery_level int,\n"
                + " state text\n"
                + ");";
        try {
            Statement s = OpsHelper.getConn().createStatement();
            if (s.execute(qry)) {
                setMessage("Drones table created successfully!");
            } else {
                setMessage("Drones table creation failed!");
            }
        } catch (SQLException e) {
            setMessage(e.getMessage());
        }
    }


    /**
     * Add a new drone record in the db. Return true if operation was successful, otherwise retrun false
     * @param d
     * @return a boolean value true or false
     */
    public boolean insertDroneRecord(drone d) {        
        
        String qry = "INSERT INTO drones(serialNo, model, weight_limit, battery_capacity, battery_level, state) VALUES(?,?,?,?,?,?)"; 
        if(OpsHelper.getConn() != null){

            try{  
            
                PreparedStatement s = OpsHelper.getConn().prepareStatement(qry);
                s.setString(1, d.getSerialNo());  
                s.setString(2, d.getModel().toString()); 
                s.setDouble(3, d.getWeightLimit());
                s.setInt(4, d.getBatteryCapacity());  
                s.setInt(5, d.getBatteryLevel()); 
                s.setString(6, d.getState().toString());
                if (s.executeUpdate() > -1){
                    this.setMessage("Drone record created successfully!");
                    return true;
                }else{
                    this.setMessage("Error creating drone record!");
                }                

            } catch (SQLException e) {  
                this.setMessage(e.getMessage());  
            } 
        }        
        return false;

    }

    public HashMap<String, drone> loadAllDrones(){  
        
        HashMap<String, drone> t = new HashMap<String, drone>();

        String sql = "SELECT * FROM drones";           
        try {  
            
            Statement s  = OpsHelper.getConn().createStatement();  
            ResultSet rs    = s.executeQuery(sql);
              
            // loop through the result set  
            while (rs.next()) {

                drone d = new drone();
                d.serialNo = rs.getString("serialNo");
                d.model = droneMODELS.valueOf(rs.getString("model"));
                d.weight_limit = rs.getFloat("weight_limit");
                d.battery_capacity = rs.getInt("battery_capacity");
                d.battery_level = rs.getInt("battery_level");
                d.state = droneSTATUS.valueOf(rs.getString("state"));                
                t.put(d.serialNo, d);

            }  
        } catch (SQLException e) {  
            this.message = e.getMessage();
        } 

        return t;
    }  

}
