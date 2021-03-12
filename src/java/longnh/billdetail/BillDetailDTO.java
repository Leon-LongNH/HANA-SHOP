/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.billdetail;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author LongNH
 */
public class BillDetailDTO implements Serializable{
    private String billID;
    private String foodID;
    private int quantity;
    private float price;

    public BillDetailDTO() {
    }

    public BillDetailDTO(String billID, String foodID, int quantity, float price) {
        this.billID = billID;
        this.foodID = foodID;
        this.quantity = quantity;
        this.price = price;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getFoodID() {
        return foodID;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.billID);
        hash = 79 * hash + Objects.hashCode(this.foodID);
        hash = 79 * hash + this.quantity;
        hash = 79 * hash + Float.floatToIntBits(this.price);
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
        final BillDetailDTO other = (BillDetailDTO) obj;
        if (this.quantity != other.quantity) {
            return false;
        }
        if (Float.floatToIntBits(this.price) != Float.floatToIntBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.billID, other.billID)) {
            return false;
        }
        if (!Objects.equals(this.foodID, other.foodID)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BillDetailDTO{" + "billID=" + billID + ", foodID=" + foodID + ", quantity=" + quantity + ", price=" + price + '}';
    }
    
}
