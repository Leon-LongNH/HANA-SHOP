/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longnh.servlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import longnh.food.FoodDAO;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author LongNH
 */
@WebServlet(name = "CreateActionServlet", urlPatterns = {"/CreateActionServlet"})
public class CreateActionServlet extends HttpServlet {

    private final String ERRORS_PAGE = "errors.html";
    private final String ADMIN_PAGE = "LoadAdminPageAllServlet";
    private final String CREATE_PAGE = "LoadCreatePageServlet";

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

        String url = ADMIN_PAGE;

        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(request);
            if (!isMultipart) {

            } else {
                FileItemFactory factory = new DiskFileItemFactory();
                ServletFileUpload upload = new ServletFileUpload(factory);
                List items = null;
                try {
                    items = (List) upload.parseRequest(request);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                }
                Iterator iter = items.iterator();
                Hashtable params = new Hashtable();
                String fileName = null;

                String img = null;
                while (iter.hasNext()) {
                    FileItem item = (FileItem) iter.next();
                    if (item.isFormField()) {
                        params.put(item.getFieldName(), item.getString());
                        img = (String) params.get("foodImage");
                    } else {
                        try {
                            String itemName = item.getName();
                            fileName = itemName.substring(
                                    itemName.lastIndexOf("\\") + 1);
                            System.out.println("path" + fileName);
                            String RealPath = getPath() + "/web/img/" + img;
                            System.out.println("Rpath" + RealPath);
                            File saveFile = new File(RealPath);
                            item.write(saveFile);
                        } catch (Exception e) {
                            log("CreateActionServlet _ " + e.getMessage());
                            request.setAttribute("ERRORS", "Img name already exist!!!");
                            url = CREATE_PAGE;
                        }
                    }
                }

                if (!url.equals(CREATE_PAGE)) {
                    String id = (String) params.get("foodCategory");
                    String name = (String) params.get("foodName");
                    String description = (String) params.get("foodDescription");
                    String price = (String) params.get("foodPrice");
                    String date = (String) params.get("foodCreateDate");
                    String quantity = (String) params.get("foodQuantity");

                    float parseFloat = Float.parseFloat(price);
                    FoodDAO foodDAO = new FoodDAO();
                    boolean createFood = foodDAO.createFood(id, name, img, description, parseFloat, date, id.replaceAll("[^A-Za-z()\\[\\]]", ""), Integer.parseInt(quantity));

                    if (!createFood) {
                        url = CREATE_PAGE;
                        request.setAttribute("ERRORS", "Something wrong!!!");
                    }
                }
            }
        } catch (SQLException ex) {
            log("CreateActionServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } catch (ClassNotFoundException ex) {
            log("CreateActionServlet _ " + ex.getMessage());
            url = ERRORS_PAGE;
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    public String getPath() throws UnsupportedEncodingException {

        String path = this.getClass().getClassLoader().getResource("").getPath();
        String fullPath = URLDecoder.decode(path, "UTF-8");
        String pathArr[] = fullPath.split("/build/web/WEB-INF/classes/");
        fullPath = pathArr[0];

        return fullPath;

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
