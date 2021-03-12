/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.google;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import longnh.account.AccountDAO;
import longnh.account.AccountDTO;

/**
 *
 * @author LongNH
 */
@WebServlet(name = "GoogleLoginServlet", urlPatterns = {"/GoogleLoginServlet"})
public class GoogleLoginServlet extends HttpServlet {
    
    private final String ERRORS_PAGE = "errors.html";
    private final String LOGIN_FAIL_PAGE = "login-fail.html";
    private final String ADMIN_PAGE = "LoadAdminPageAllServlet";
    private final String WELCOME_PAGE = "LoadWelcomePageAllServlet";

    public GoogleLoginServlet() {
        super();
    }

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
        String code = request.getParameter("code");

        String url = WELCOME_PAGE;
        try {
            HttpSession session = request.getSession();
            
            if (code == null || code.isEmpty()) {
                RequestDispatcher dis = request.getRequestDispatcher("login.jsp");
                dis.forward(request, response);
            } else {
                String accessToken = GoogleUtils.getToken(code);
                GooglePojo googlePojo = GoogleUtils.getUserInfo(accessToken);

                AccountDAO accountDAO = new AccountDAO();
                AccountDTO checkAccountGG = accountDAO.checkAccountGG(googlePojo.getEmail());
                
                if (checkAccountGG == null) {
                    accountDAO.createAccountGoogle(googlePojo.getId(), googlePojo.getEmail());
                }
                
                AccountDTO checkAccountGG1 = accountDAO.checkAccountGG(googlePojo.getEmail());
                session.setAttribute("ACCOUNT", checkAccountGG1);
            RequestDispatcher dis = request.getRequestDispatcher(url);
            dis.forward(request, response);
            }
        } catch (SQLException ex) {
            log("LoginActionServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } catch (ClassNotFoundException ex) {
            log("LoginActionServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
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
