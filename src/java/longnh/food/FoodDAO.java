/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.food;

import java.io.Serializable;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import longnh.util.DBHelper;

/**
 *
 * @author LongNH
 */
public class FoodDAO implements Serializable {

    //create List of Food
    private List<FoodDTO> listFoods;

    public List<FoodDTO> getListFoods() {
        return listFoods;
    }

    public void loadListFoodsAll()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM Food";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive()) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public void loadListFoods(int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM "
                        + "( SELECT ROW_NUMBER() over (ORDER BY foodCreateDate DESC) as r, * "
                        + "FROM Food WHERE alive = ?) as x "
                        + "WHERE r BETWEEN ? * 8 - 7 and ? * 8";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, true);
                stm.setInt(2, index);
                stm.setInt(3, index);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive() && foodDTO.getFoodQuantity() > 0) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public void loadListFoodsAdmin(int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM "
                        + "( SELECT ROW_NUMBER() over (ORDER BY foodCreateDate DESC) as r, * "
                        + "FROM Food WHERE alive = ?) as x "
                        + "WHERE r BETWEEN ? * 8 - 7 and ? * 8";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);
                stm.setBoolean(1, true);
                stm.setInt(2, index);
                stm.setInt(3, index);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive()) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public void loadListFoodsByCategory(String foodCategoryKey, int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM "
                        + "( SELECT ROW_NUMBER() over (ORDER BY foodCreateDate DESC) as r, * "
                        + "FROM Food "
                        + "WHERE foodCategory = ? and alive = ?) as x "
                        + "WHERE r BETWEEN ? * 8 - 7 and ? * 8";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setString(1, foodCategoryKey);
                stm.setBoolean(2, true);
                stm.setInt(3, index);
                stm.setInt(4, index);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive() && foodDTO.getFoodQuantity() > 0) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public void loadListFoodsByCategoryAdmin(String foodCategoryKey, int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM "
                        + "( SELECT ROW_NUMBER() over (ORDER BY foodCreateDate DESC) as r, * "
                        + "FROM Food "
                        + "WHERE foodCategory = ? and alive = ?) as x "
                        + "WHERE r BETWEEN ? * 8 - 7 and ? * 8";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setString(1, foodCategoryKey);
                stm.setBoolean(2, true);
                stm.setInt(3, index);
                stm.setInt(4, index);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive()) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public void loadListFoodsBySearch(String searchKey, int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM "
                        + "( SELECT ROW_NUMBER() over (ORDER BY foodCreateDate DESC) as r, * "
                        + "FROM Food "
                        + "WHERE foodName like ?  and alive = ?) as x "
                        + "WHERE r BETWEEN ? * 8 - 7 and ? * 8";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setString(1, "%" + searchKey + "%");
                stm.setBoolean(2, true);
                stm.setInt(3, index);
                stm.setInt(4, index);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive() && foodDTO.getFoodQuantity() > 0) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public void loadListFoodsBySearchAdmin(String searchKey, int index)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM "
                        + "( SELECT ROW_NUMBER() over (ORDER BY foodCreateDate DESC) as r, * "
                        + "FROM Food "
                        + "WHERE foodName like ?  and alive = ?) as x "
                        + "WHERE r BETWEEN ? * 8 - 7 and ? * 8";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setString(1, "%" + searchKey + "%");
                stm.setBoolean(2, true);
                stm.setInt(3, index);
                stm.setInt(4, index);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    if (this.listFoods == null) {
                        this.listFoods = new ArrayList<>();
                    }//end if listFoods is null

                    if (foodDTO.isAlive()) {
                        this.listFoods.add(foodDTO);
                    }//end if foodDTO is valid
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

    public FoodDTO loadFoodsByID(String foodIDKey)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT "
                        + "foodID, foodName, "
                        + "foodImage, foodDescription, "
                        + "foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, "
                        + "alive "
                        + "FROM Food "
                        + "WHERE foodID = ?";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setString(1, foodIDKey);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    String foodID = rs.getString("foodID");
                    String foodName = rs.getString("foodName");
                    String foodImage = rs.getString("foodImage");
                    String foodDescription = rs.getString("foodDescription");
                    double foodPrice = rs.getDouble("foodPrice");

                    //wrong timezone
                    Date date = rs.getDate("foodCreateDate");
                    Calendar c = Calendar.getInstance();
                    c.setTime(date);
                    c.add(Calendar.DATE, 2);
                    date = (Date) c.getTime();
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String foodCreateDate = formatter.format(date);
                    //end format date
                    //plus 2 date for wrong timezone

                    String foodCategory = rs.getString("foodCategory");
                    int foodQuantity = rs.getInt("foodQuantity");
                    boolean alive = rs.getBoolean("alive");

                    FoodDTO foodDTO = new FoodDTO(foodID, foodName,
                            foodImage, foodDescription,
                            foodPrice, foodCreateDate,
                            foodCategory, foodQuantity, alive);

                    return foodDTO;
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

    public int countFood()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Food WHERE alive = ? AND foodQuantity > 0";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setBoolean(1, true);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public int countFoodAdmin()
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Food WHERE alive = ?";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setBoolean(1, true);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public int countFoodName(String searchKey)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Food WHERE foodName like ? and alive = ? and foodQuantity > 0";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setBoolean(2, true);
                stm.setString(1, "%" + searchKey + "%");
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public int countFoodNameAdmin(String searchKey)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Food WHERE foodName like ? and alive = ?";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setBoolean(2, true);
                stm.setString(1, "%" + searchKey + "%");
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public int countFoodCategory(String key)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Food WHERE foodCategory = ? and alive = ? and foodQuantity > 0";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setBoolean(2, true);
                stm.setString(1, key);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public int countFoodCategoryAdmin(String key)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            //1. Make Connection
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. Create SQL Statement
                String sql = "SELECT COUNT(*) FROM Food WHERE foodCategory = ? and alive = ?";

                //3. Create Statemanet
                stm = con.prepareStatement(sql);

                stm.setBoolean(2, true);
                stm.setString(1, key);
                //4. Query data
                rs = stm.executeQuery();
                while (rs.next()) {
                    return rs.getInt(1);
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
        return 0;
    }

    public boolean updateFood(String foodID, String foodName, String foodImage,
            String foodDescription, float foodPrice, String foodDate, String foodCategory,
            int foodQuantity)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Food "
                        + "SET foodName = ?, foodImage = ?, "
                        + "foodDescription = ?, foodPrice = ?, foodCreateDate = ?, "
                        + "foodCategory = ?, foodQuantity = ? "
                        + "WHERE foodID = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, foodName);
                stm.setString(2, foodImage);
                stm.setString(3, foodDescription);
                stm.setFloat(4, foodPrice);
                stm.setString(5, foodDate);
                stm.setString(6, foodCategory);
                stm.setInt(7, foodQuantity);
                stm.setString(8, foodID);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }//end if con is not null
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

    public boolean deleteFood(String foodID)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;

        try {
            con = DBHelper.makeConnection();

            if (con != null) {
                String sql = "UPDATE Food "
                        + "SET alive = 0 "
                        + "WHERE foodID = ?";

                stm = con.prepareStatement(sql);

                stm.setString(1, foodID);

                int row = stm.executeUpdate();

                if (row > 0) {
                    return true;
                }
            }//end if con is not null
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

    public boolean createFood(String foodID, String foodName,
            String foodImage, String foodDescription,
            float foodPrice, String foodCreateDate, String foodCategory,
            int foodQuantity) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO Food (foodID, foodName, foodImage, "
                        + "foodDescription, foodPrice, foodCreateDate, "
                        + "foodCategory, foodQuantity, alive) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

                System.out.println(foodPrice);
                stm = con.prepareStatement(sql);
                stm.setString(1, foodID);
                stm.setString(2, foodName);
                stm.setString(3, foodImage);
                stm.setString(4, foodDescription);
                stm.setFloat(5, foodPrice);
                stm.setString(6, foodCreateDate);
                stm.setString(7, foodCategory);
                stm.setInt(8, foodQuantity);
                stm.setBoolean(9, true);

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

    public boolean updateFoodQuantity(String foodID, int quantity)
            throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "UPDATE Food "
                        + "SET foodQuantity = ? "
                        + "WHERE foodID = ?";

                stm = con.prepareStatement(sql);
                stm.setString(2, foodID);
                stm.setInt(1, quantity);

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

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        FoodDAO dao = new FoodDAO();
        dao.loadListFoodsByCategory("Coffee", 1);
        List<FoodDTO> listFoods1 = dao.getListFoods();
        for (FoodDTO foodDTO : listFoods1) {
            System.out.println(foodDTO.toString());
        }
        System.out.println(dao.countFood());
    }
}
