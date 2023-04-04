package controllers;

import models.*;

public class dispatchController {
    
    public String registerDrone(){
        String serialNO = "";

        //todo: Add a new drone to db
        //drone d = new drone();
        


        return serialNO;
    }

    /**
     * Loads a specified drone with medication
     * 
     * @param d the drone to be loaded
     * @param m a list of medication to be loaded on the drone
     * @return true if the drone is loaded correctly, otherwise return false
     */
    public boolean loadDrone(drone d, medication[] m){
        boolean loaded = false;
        //todo: load medication to a drone and change the drone state
        return loaded;
    }
    
    //
    /**
     * Get available drones for loading of medication;
     * @return a string array of the serial numbers of the available drones
     */
    public String[] getAvailableDrones(){
        String[] drones = {};

        return drones;
    }


    public boolean getLoadedMedication(){
        return false;
    }

    /**
     * Get the battery level of a specified drone
     * 
     * @param d the drone whose battery level is to be checked
     * @return the current battery level of the drone
     */
    public int getBatteryLevel(drone d){
        int l = 0;

        return l;
    }
    
}