/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.food;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author LongNH
 */
public class FoodDTO implements Serializable {

    //fields
    private String foodID;
    private String foodName;
    private String foodImage;
    private String foodDescription;
    private double foodPrice;
    private String foodCreateDate;
    private String foodCategory;
    private int foodQuantity;
    private boolean alive;

    public FoodDTO() {
    }

    public FoodDTO(String foodID, String foodName, String foodImage, String foodDescription, double foodPrice, String foodCreateDate, String foodCategory, int foodQuantity, boolean alive) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodImage = foodImage;
        this.foodDescription = foodDescription;
        this.foodPrice = foodPrice;
        this.foodCreateDate = foodCreateDate;
        this.foodCategory = foodCategory;
        this.foodQuantity = foodQuantity;
        this.alive = alive;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodImage() {
        return foodImage;
    }

    public void setFoodImage(String foodImage) {
        this.foodImage = foodImage;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodCreateDate() {
        return foodCreateDate;
    }

    public void setFoodCreateDate(String foodCreateDate) {
        this.foodCreateDate = foodCreateDate;
    }

    public String getFoodCategory() {
        return foodCategory;
    }

    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }

    public int getFoodQuantity() {
        return foodQuantity;
    }

    public void setFoodQuantity(int foodQuantity) {
        this.foodQuantity = foodQuantity;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.foodID);
        hash = 67 * hash + Objects.hashCode(this.foodName);
        hash = 67 * hash + Objects.hashCode(this.foodImage);
        hash = 67 * hash + Objects.hashCode(this.foodDescription);
        hash = 67 * hash + (int) (Double.doubleToLongBits(this.foodPrice) ^ (Double.doubleToLongBits(this.foodPrice) >>> 32));
        hash = 67 * hash + Objects.hashCode(this.foodCreateDate);
        hash = 67 * hash + Objects.hashCode(this.foodCategory);
        hash = 67 * hash + this.foodQuantity;
        hash = 67 * hash + (this.alive ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final FoodDTO other = (FoodDTO) obj;
        if (Double.doubleToLongBits(this.foodPrice) != Double.doubleToLongBits(other.foodPrice)) {
            return false;
        }
        if (this.foodQuantity != other.foodQuantity) {
            return false;
        }
        if (this.alive != other.alive) {
            return false;
        }
        if (!Objects.equals(this.foodID, other.foodID)) {
            return false;
        }
        if (!Objects.equals(this.foodName, other.foodName)) {
            return false;
        }
        if (!Objects.equals(this.foodImage, other.foodImage)) {
            return false;
        }
        if (!Objects.equals(this.foodDescription, other.foodDescription)) {
            return false;
        }
        if (!Objects.equals(this.foodCreateDate, other.foodCreateDate)) {
            return false;
        }
        if (!Objects.equals(this.foodCategory, other.foodCategory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodDTO{" + "foodID=" + foodID + ", foodName=" + foodName + ", foodImage=" + foodImage + ", foodDescription=" + foodDescription + ", foodPrice=" + foodPrice + ", foodCreateDate=" + foodCreateDate + ", foodCategory=" + foodCategory + ", foodQuantity=" + foodQuantity + ", alive=" + alive + '}';
    }

    public Set<FoodDTO> keySet() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
