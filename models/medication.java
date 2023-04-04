package models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class medication {
    
    private String name;
    private float weight;
    private String code;
    private String image;
    private LocalDate expiry_date;
    
    
    /**
     * Initializes the medication object with populated values
     * @param name
     * @param weight
     * @param code
     * @param image
     * @param expiry_date
     */
    public medication(String name, float weight, String code, String image, LocalDate expiry_date) {
        this.name = name;
        this.weight = weight;
        this.code = code;
        this.image = image;
        this.expiry_date = expiry_date;
        
    }

    /**
     * Initializes the medication object with empty values.
     * Overloads the medication constructor.
     */
    public medication() {
        this.name = "";
        this.weight = 0;
        this.code = "";
        this.image = "";
        this.expiry_date = LocalDateTime.now().toLocalDate();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public float getWeight() {
        return weight;
    }
    public void setWeight(float weight) {
        this.weight = weight;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }
    public LocalDate getExpiryDate() {
        return expiry_date;
    }
    public void setExpiryDate(LocalDate expiry_date) {
        this.expiry_date = expiry_date;
    }
}
