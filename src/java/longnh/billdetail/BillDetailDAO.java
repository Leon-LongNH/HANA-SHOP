/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.billdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import longnh.util.DBHelper;

/**
 *
 * @author LongNH
 */
public class BillDetailDAO implements Serializable {

    private List<BillDetailDTO> listBillDetails;

    public List<BillDetailDTO> getListBillDetails() {
        return listBillDetails;
    }

    public boolean createBillDetail(String billID, String foodID,
            int Quantity, float Price) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO BillDetail (billID, foodID, Quantity, Price, alive) "
                        + "VALUES (?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, billID);
                stm.setString(2, foodID);
                stm.setInt(3, Quantity);
                stm.setDouble(4, Price);
                stm.setBoolean(5, true);

                int executeUpdate = stm.executeUpdate();
                if (executeUpdate > 0) {
                    return true;
                }
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public void loadBillDetails(String billID) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT billID, foodID, Quantity, Price, alive "
                        + "FROM BillDetail "
                        + "WHERE billID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(1, billID);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String billIDSQL = rs.getString("billID");
                    String foodID = rs.getString("foodID");
                    int quantity = rs.getInt("Quantity");
                    float price = rs.getFloat("Price");
                    boolean alive = rs.getBoolean("alive");

                    BillDetailDTO detailDTO = new BillDetailDTO(billID, foodID, quantity, price);

                    if (this.listBillDetails == null) {
                        this.listBillDetails = new ArrayList<>();
                    }
                    
                    this.listBillDetails.add(detailDTO);
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
