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
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnh.account.AccountDTO;
import longnh.bill.BillDAO;
import longnh.billdetail.BillDetailDAO;
import longnh.food.FoodDAO;
import longnh.food.FoodDTO;

/**
 *
 * @author LongNH
 */
@WebServlet(name = "PlaceOrderServlet", urlPatterns = {"/PlaceOrderServlet"})
public class PlaceOrderServlet extends HttpServlet {

    private final String ERRORS_PAGE = "errors.html";
    private final String WELCOME_PAGE = "LoadWelcomePageAllServlet";

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

        String paymentMethods = request.getParameter("Paypal");
        String url = WELCOME_PAGE;

        try {
            HttpSession session = request.getSession(false);
            FoodDAO foodDAO = new FoodDAO();
            BillDetailDAO billDetailDAO = new BillDetailDAO();

            if (session != null) {
                Map<FoodDTO, Integer> cart = (Map<FoodDTO, Integer>) session.getAttribute("CART");

                if (cart != null) {
                    //create bill
                    BillDAO billDAO = new BillDAO();
                    //create billID
                    int countBill = billDAO.countBill() + 1;
                    String billID = String.format("%05d", countBill);

                    //get billOf
                    AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
                    String username = accountDTO.getUsername();

                    //create billDate
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    LocalDateTime now = LocalDateTime.now();
                    String time = dtf.format(now);

                    //check paymentMethods
                    if (paymentMethods != null) {
                        paymentMethods = "Paypal";
                    } else {
                        paymentMethods = "Cash";
                    }
                    //add bill to db

                    billDAO.createBill(billID, username, time, paymentMethods);

                    //create billdetail
                    Set<FoodDTO> set = cart.keySet();
                    for (FoodDTO key : set) {
                        float foodPrice = (float) key.getFoodPrice();
                        float amount = cart.get(key);
                        float Price = foodPrice * amount;

                        billDetailDAO.createBillDetail(billID, key.getFoodID(), cart.get(key), Price);
                        foodDAO.updateFoodQuantity(key.getFoodID(), key.getFoodQuantity() - cart.get(key));
                    }

                    session.removeAttribute("CART");
                }
            }
        } catch (SQLException ex) {
            log("PlaceOrderServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } catch (ClassNotFoundException ex) {
            log("PlaceOrderServlet _ " + ex.getMessage());
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
