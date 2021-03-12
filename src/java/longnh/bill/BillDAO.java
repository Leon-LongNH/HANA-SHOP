/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.bill;

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
public class BillDAO implements Serializable {

    private List<BillDTO> listBills;

    public List<BillDTO> getListBills() {
        return listBills;
    }

    public boolean createBill(String billID, String billOf,
            String billDate, String billPaymentM)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Bill (billID, billOf, billDate, billPaymentM, alive) "
                        + "VALUES (?, ?, ?, ?, ?)";

                stm = con.prepareStatement(sql);

                stm.setString(1, billID);
                stm.setString(2, billOf);
                stm.setString(3, billDate);
                stm.setString(4, billPaymentM);
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

    public int countBill() throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT COUNT (*) FROM Bill";

                stm = con.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    int num = rs.getInt(1);
                    return num;
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
        return 0;
    }

    public void loadListBill(String billOf) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "SELECT billID, billOf, "
                        + "billDate, billPaymentM, "
                        + "alive "
                        + "FROM Bill "
                        + "WHERE billOf = ?";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, billOf);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String billID = rs.getString("billID");
                    String billof = rs.getString("billOf");
                    String billDate = rs.getString("billDate");
                    String billPaymentM = rs.getString("billPaymentM");
                    boolean alive = rs.getBoolean("alive");
                    
                    BillDTO billDTO = new BillDTO(billID, billof, billDate, billPaymentM, alive);
                    
                    if (this.listBills == null) {
                        this.listBills = new ArrayList<>();
                    }
                    
                    this.listBills.add(billDTO);
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
