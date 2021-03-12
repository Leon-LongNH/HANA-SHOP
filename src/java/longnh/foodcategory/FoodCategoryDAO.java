/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.foodcategory;

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
public class FoodCategoryDAO implements Serializable {

    //create listFoodCategory
    List<FoodCategoryDTO> listFoodCategory;

    public List<FoodCategoryDTO> getListFoodCategory() {
        return listFoodCategory;
    }

    public void loadListFoodCategory()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT categoryID, categoryName, alive "
                        + "FROM FoodCategory";
                
                //3. Create Statement
                stm = con.prepareStatement(sql);
                
                //4. Execute Query
                rs = stm.executeQuery();
                
                while (rs.next()) {
                    String categoryID = rs.getString("categoryID");
                    String categoryName = rs.getString("categoryName");
                    boolean alive = rs.getBoolean("alive");
                    
                    FoodCategoryDTO foodCategoryDTO = 
                            new FoodCategoryDTO(categoryID, categoryName, alive);
                    
                    if (this.listFoodCategory == null) {
                        this.listFoodCategory = new ArrayList<>();
                    }//end listFoodCategory is null
                    
                    if (foodCategoryDTO.isAlive()) {
                        this.listFoodCategory.add(foodCategoryDTO);
                    }//end if foodCategoryDTO valid 
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
    }
    
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FoodCategoryDAO dAO = new FoodCategoryDAO();
        dAO.loadListFoodCategory();
        List<FoodCategoryDTO> listFoodCategory1 = dAO.getListFoodCategory();
        for (FoodCategoryDTO foodCategoryDTO : listFoodCategory1) {
            System.out.println(foodCategoryDTO.toString());
        }
    }
}
