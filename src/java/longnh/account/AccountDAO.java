/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.account;


import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import longnh.util.DBHelper;

/**
 *
 * @author LongNH
 */
public class AccountDAO implements Serializable {
    public AccountDTO checkAccount(String usernameK, String passwordK)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create sql statement
                String sql = "SELECT username, pass, lastname, isAdmin, googleID, googleName "
                        + "FROM Account WHERE username = ? AND pass = ?";
                
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, usernameK);
                stm.setString(2, passwordK);
                //4. execute query
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("pass");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    String googleID = rs.getString("googleID");
                    String googleName = rs.getString("googleName");
                    
                    AccountDTO accountDTO = 
                            new AccountDTO(username, password, lastname, googleID, googleName, isAdmin);
                    
                    return accountDTO;
                }//end while rs is not null
            }//end if con is not null
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
        return null;
    }
    
    public boolean createAccountGoogle(String googleID, String googleName)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Account (username, pass, lastname, isAdmin, googleID, googleName) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                
                stm = con.prepareStatement(sql);
                stm.setString(1, googleName);
                stm.setString(2, "NULL");
                stm.setString(3, googleName);
                stm.setBoolean(4, false);
                stm.setString(5, googleID);
                stm.setString(6, googleName);
                
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
    
    public AccountDTO checkAccountGG(String usernameK)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            //1. Make connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create sql statement
                String sql = "SELECT username, pass, lastname, isAdmin, googleID, googleName "
                        + "FROM Account WHERE username = ?";
                
                //3.create statement
                stm = con.prepareStatement(sql);
                stm.setString(1, usernameK);
                //4. execute query
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("pass");
                    String lastname = rs.getString("lastname");
                    boolean isAdmin = rs.getBoolean("isAdmin");
                    String googleID = rs.getString("googleID");
                    String googleName = rs.getString("googleName");
                    
                    AccountDTO accountDTO = 
                            new AccountDTO(username, password, lastname, googleID, googleName, isAdmin);
                    
                    return accountDTO;
                }//end while rs is not null
            }//end if con is not null
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
        return null;
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        AccountDAO aO = new AccountDAO();
        System.out.println(aO.checkAccount("LongNH", "123456").toString());
    }
}
