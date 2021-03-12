/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnh.food.FoodDAO;
import longnh.food.FoodDTO;

/**
 *
 * @author LongNH
 */
@WebServlet(name = "AddToCartServlet", urlPatterns = {"/AddToCartServlet"})
public class AddToCartServlet extends HttpServlet {

    private final String WELCOME_PAGE = "LoadWelcomePageAllServlet";
    private final String WELCOME_PAGE_CATEGORY = "LoadWelcomePageByCategoryServlet";
    private final String WELCOME_PAGE_SEARCH = "LoadWelcomePageBySearchServlet";
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

        String foodID = request.getParameter("foodID");
        String foodCategory = request.getParameter("KeyCategory");
        String foodSearchKey = request.getParameter("SearchKey");

        String url = WELCOME_PAGE;
        try {
            HttpSession session = request.getSession();

            FoodDAO foodDAO = new FoodDAO();
            FoodDTO loadFoodsByID = foodDAO.loadFoodsByID(foodID);
            Map<FoodDTO, Integer> cart = (Map<FoodDTO, Integer>) session.getAttribute("CART");
            
            if (cart == null) {
                cart = new HashMap<>();
            }
            
            int quantity = 1;
            if (cart.containsKey(loadFoodsByID)) {
                quantity = cart.get(loadFoodsByID);
                Set<FoodDTO> set = cart.keySet();
                for (FoodDTO foodDTO : set) {
                    if (foodDTO.getFoodQuantity() > quantity && foodDTO.equals(loadFoodsByID)) {
                        quantity = cart.get(loadFoodsByID) + 1;
                    }
                }
            }
            
            cart.put(loadFoodsByID, quantity);
            session.setAttribute("CART", cart);
            if (!foodCategory.equals("")) {
                url = WELCOME_PAGE_CATEGORY;
            } else if (!foodSearchKey.equals("")) {
                url = WELCOME_PAGE_SEARCH;
            } else {
                url = WELCOME_PAGE;
            }
        } catch (SQLException ex) {
            log("AddToCartServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } catch (ClassNotFoundException ex) {
            log("AddToCartServlet _ " + ex.getMessage());
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
