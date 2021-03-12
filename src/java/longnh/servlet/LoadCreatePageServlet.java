/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longnh.food.FoodDAO;
import longnh.food.FoodDTO;
import longnh.foodcategory.FoodCategoryDAO;
import longnh.foodcategory.FoodCategoryDTO;

/**
 *
 * @author LongNH
 */
@WebServlet(name = "LoadCreatePageServlet", urlPatterns = {"/LoadCreatePageServlet"})
public class LoadCreatePageServlet extends HttpServlet {

    private final String CREATE_PAGE = "create-food-page.jsp";
    private final String ERRORS_PAGE = "errors.html";
    

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String url = CREATE_PAGE;
        try {
            FoodCategoryDAO categoryDAO = new FoodCategoryDAO();

            //load all category
            categoryDAO.loadListFoodCategory();
            List<FoodCategoryDTO> listFoodCategory = categoryDAO.getListFoodCategory();

            request.setAttribute("ALL_CATEGORY", listFoodCategory);

            FoodDAO foodDAO = new FoodDAO();
            //load all food
            foodDAO.loadListFoodsAll();
            List<FoodDTO> listFoods = foodDAO.getListFoods();

            Map<String, String> map = new HashMap<>();

            //kiem tra food co san de auto dat ten
            for (FoodCategoryDTO foodCategoryDTO : listFoodCategory) {
                map.put(foodCategoryDTO.getCategoryID(), "0");
            }
            for (FoodDTO listFood : listFoods) {
                int quantity = 0;
                String formatted = "0";
                if (map.containsKey(listFood.getFoodCategory())) {
                    quantity = Integer.parseInt(map.get(listFood.getFoodCategory())) + 1;
                }

                formatted = quantity + "";
                map.put(listFood.getFoodCategory(), formatted);
            }

            Set<String> set = map.keySet();
            for (String key : set) {
                int quantity = Integer.parseInt(map.get(key)) + 1;
                String formatted = "01";

                formatted = String.format("%02d", quantity);

                map.put(key, formatted);
            }

            request.setAttribute("FOOD_ID", map);

            //set today
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String time = dtf.format(now);

            request.setAttribute("TODAY", time);
        } catch (SQLException ex) {
            log("LoadCreatePageServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } catch (ClassNotFoundException ex) {
            log("LoadCreatePageServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
