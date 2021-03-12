/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.foodcategory;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author LongNH
 */
public class FoodCategoryDTO implements Serializable {
    private String categoryID;
    private String categoryName;
    private boolean alive;

    public FoodCategoryDTO() {
    }

    public FoodCategoryDTO(String categoryID, String categoryName, boolean alive) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
        this.alive = alive;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.categoryID);
        hash = 97 * hash + Objects.hashCode(this.categoryName);
        hash = 97 * hash + (this.alive ? 1 : 0);
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
        final FoodCategoryDTO other = (FoodCategoryDTO) obj;
        if (this.alive != other.alive) {
            return false;
        }
        if (!Objects.equals(this.categoryID, other.categoryID)) {
            return false;
        }
        if (!Objects.equals(this.categoryName, other.categoryName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "FoodCategoryDTO{" + "categoryID=" + categoryID + ", categoryName=" + categoryName + ", alive=" + alive + '}';
    }
    
}
