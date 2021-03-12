/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
@WebServlet(name = "LoadWelcomePageBySearchServlet", urlPatterns = {"/LoadWelcomePageBySearchServlet"})
public class LoadWelcomePageBySearchServlet extends HttpServlet {

    private final String WELCOME_PAGE = "welcome-page.jsp";
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

        String url = WELCOME_PAGE;
        String key = request.getParameter("SearchKey");
        String indexparam = request.getParameter("index");
        if (indexparam == null) {
            indexparam = "1";
        }
        int index = Integer.parseInt(indexparam);

        try {
            FoodCategoryDAO dao = new FoodCategoryDAO();
            dao.loadListFoodCategory();

            List<FoodCategoryDTO> listFoodCategory = dao.getListFoodCategory();

            request.setAttribute("ALL_CATEGORY", listFoodCategory);

            FoodDAO foodDAO = new FoodDAO();

            int count = foodDAO.countFoodName(key);
            int sizePage = 8;
            int endPage = count / sizePage;
            if (count % sizePage != 0) {
                endPage++;
            }
            request.setAttribute("END_PAGE", endPage);

            foodDAO.loadListFoodsBySearch(key, index);

            List<FoodDTO> listFoods = foodDAO.getListFoods();

            request.setAttribute("ALL_FOOD", listFoods);
            request.setAttribute("SEARCH_KEY", key);
        } catch (SQLException ex) {
            log("LoadWelcomePageBySearchServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } catch (ClassNotFoundException ex) {
            log("LoadWelcomePageBySearchServlet _ " + ex.getMessage());
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
