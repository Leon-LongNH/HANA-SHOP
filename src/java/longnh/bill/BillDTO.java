/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.bill;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author LongNH
 */
public class BillDTO implements Serializable {

    private String billID;
    private String billOf;
    private String billDate;
    private String billPaymentM;
    private boolean alive;

    public BillDTO() {
    }

    public BillDTO(String billID, String billOf, String billDate, String billPaymentM, boolean alive) {
        this.billID = billID;
        this.billOf = billOf;
        this.billDate = billDate;
        this.billPaymentM = billPaymentM;
        this.alive = alive;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillOf() {
        return billOf;
    }

    public void setBillOf(String billOf) {
        this.billOf = billOf;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillPaymentM() {
        return billPaymentM;
    }

    public void setBillPaymentM(String billPaymentM) {
        this.billPaymentM = billPaymentM;
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
        hash = 53 * hash + Objects.hashCode(this.billID);
        hash = 53 * hash + Objects.hashCode(this.billOf);
        hash = 53 * hash + Objects.hashCode(this.billDate);
        hash = 53 * hash + Objects.hashCode(this.billPaymentM);
        hash = 53 * hash + (this.alive ? 1 : 0);
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
        final BillDTO other = (BillDTO) obj;
        if (this.alive != other.alive) {
            return false;
        }
        if (!Objects.equals(this.billID, other.billID)) {
            return false;
        }
        if (!Objects.equals(this.billOf, other.billOf)) {
            return false;
        }
        if (!Objects.equals(this.billDate, other.billDate)) {
            return false;
        }
        if (!Objects.equals(this.billPaymentM, other.billPaymentM)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BillDTO{" + "billID=" + billID + ", billOf=" + billOf + ", billDate=" + billDate + ", billPaymentM=" + billPaymentM + ", alive=" + alive + '}';
    }

}
