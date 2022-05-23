/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Objects;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainpackage.beans.DeviceStoreLocal;

/**
 *
 * @author dkory
 */
@WebServlet(name = "Device", urlPatterns = {"/device"})
public class Device extends HttpServlet {
    
    @EJB
        DeviceStoreLocal deviceStore;

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Servlet Device</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Add new device</h1>");
            out.println("<div>");
            out.println("<form action=\"device\" method=\"POST\">");
            out.println("<input type=\"text\" name=\"vendor\"/><br><br>");
            out.println("<input type=\"text\" name=\"model\"/><br><br>");
            out.println("<input type=\"date\" name=\"date\"/><br><br>");
            out.println("<input type=\"submit\" value=\"Add\"/>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
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
        String vendor = Objects.toString(request.getParameter("vendor"), "");
        String model = Objects.toString(request.getParameter("model"), "");
        String dateStr = Objects.toString(request.getParameter("date"), "");
        
        Date date = null;
        if(dateStr.length()>4) date = Date.valueOf(dateStr);
        
        deviceStore.createDevice(vendor, model, date);
        response.sendRedirect("http://localhost:8080/auth/devices");
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
