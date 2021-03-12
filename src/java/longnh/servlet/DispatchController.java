/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnh.account.AccountDTO;

/**
 *
 * @author LongNH
 */
public class DispatchController extends HttpServlet {

    private final String WELCOME_PAGE = "LoadWelcomePageAllServlet";
    private final String WELCOME_PAGE_BY_CATEGORY = "LoadWelcomePageByCategoryServlet";
    private final String WELCOME_PAGE_BY_SEARCH = "LoadWelcomePageBySearchServlet";
    private final String ADMIN_PAGE = "LoadAdminPageAllServlet";
    private final String ADMIN_PAGE_BY_CATEGORY = "LoadAdminPageByCategoryServlet";
    private final String ADMIN_PAGE_BY_SEARCH = "LoadAdminPageBySearchServlet";
    private final String LOGIN_SERVLET = "LoginActionServlet";
    private final String ADMIN_UPDATE_FOOD = "LoadUpdatePageServlet";
    private final String UPDATE_SERVLET = "UpdateActionServlet";
    private final String DELETE_SERVLET = "DeleteActionServlet";
    private final String ADMIN_CREATE_FOOD = "LoadCreatePageServlet";
    private final String CREATE_SERVLET = "CreateActionServlet";
    private final String LOGOUT_SERVLET = "LogoutServlet";
    private final String ADD_TO_CART_SERVLET = "AddToCartServlet";
    private final String SHOW_CART_SERVLET = "shoping-cart.jsp";
    private final String UPDATE_CART_ITEM_SERVLET = "UpdateCartItemsServlet";
    private final String CHECKOUT_PAGE = "checkout-page.jsp";
    private final String PLACEORDER_SERVLET = "PlaceOrderServlet";
    private final String SHOW_ORDER_HISTORY = "ShowOrderHistoryServlet";
    private final String SHOW_ORDER_HISTORY_DETAIL = "ShowOrderHistoryDetailServlet";

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

        String action = request.getParameter("btAction");

        String url = WELCOME_PAGE;

        try {
            if (action == null) {

            } else {
                HttpSession session = request.getSession(false);
                AccountDTO accountDTO = (AccountDTO) session.getAttribute("ACCOUNT");
                if (action.equals("welcome-page")) {
                    url = WELCOME_PAGE;
                } else if (action.equals("ChooseCategory")) {
                    url = WELCOME_PAGE_BY_CATEGORY;
                } else if (action.equals("SearchFood")) {
                    url = WELCOME_PAGE_BY_SEARCH;
                } else if (action.equals("Log In")) {
                    url = LOGIN_SERVLET;
                } else if (action.equals("LoadAdminPage")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = ADMIN_PAGE;
                    }
                } else if (action.equals("SearchFoodAdmin")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = ADMIN_PAGE_BY_SEARCH;
                    }
                } else if (action.equals("ChooseCategoryAdmin")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = ADMIN_PAGE_BY_CATEGORY;
                    }
                } else if (action.equals("UpdateFood")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = ADMIN_UPDATE_FOOD;
                    }
                } else if (action.equals("UpdateAction")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = UPDATE_SERVLET;
                    }
                } else if (action.equals("DeleteFood")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = DELETE_SERVLET;
                    }
                } else if (action.equals("CreateNewFood")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = ADMIN_CREATE_FOOD;
                    }
                } else if (action.equals("CreateAction")) {
                    if (accountDTO == null || !accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = CREATE_SERVLET;
                    }
                } else if (action.equals("LogOut")) {
                    url = LOGOUT_SERVLET;
                } else if (action.equals("AddToCart")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = ADD_TO_CART_SERVLET;
                    }
                } else if (action.equals("ShowCart")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = SHOW_CART_SERVLET;
                    }
                } else if (action.equals("UpdateCartItems")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = UPDATE_CART_ITEM_SERVLET;
                    }
                } else if (action.equals("CheckOutPage")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = CHECKOUT_PAGE;
                    }
                } else if (action.equals("PlaceOrder")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = PLACEORDER_SERVLET;
                    }
                } else if (action.equals("ShowOrderHistory")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = SHOW_ORDER_HISTORY;
                    }
                } else if (action.equals("ShowBillDetail")) {
                    if (accountDTO == null || accountDTO.isAdmin()) {
                        url = "login.html";
                    } else {
                        url = SHOW_ORDER_HISTORY_DETAIL;
                    }
                }
            }
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
