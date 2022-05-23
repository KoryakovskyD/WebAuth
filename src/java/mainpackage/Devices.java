/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mainpackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mainpackage.beans.NewSessionBean;
import mainpackage.beans.NewSessionBeanLocal;

/**
 *
 * @author Дмитрий
 */
@WebServlet(name = "Devices", urlPatterns = {"/devices"})
public class Devices extends HttpServlet {
    
     @EJB
            NewSessionBeanLocal filterBean;
    
    List<DeviceDto> deviceList;
    HttpServletRequest request;
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
        this.request = request;
        deviceList = DeviceDto.deviceList;
        response.setContentType("text/html;charset=UTF-8");
        List<DeviceDto> deviceList = filterBean.filter(request);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Devices</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<div>");
            out.println("<div>");
            out.println("<form action=\"devices\" method=\"GET\">");
            out.println("<p>Filter</p>");
            out.println("<input type=\"text\" name=\"vendor\" value=\"" + Objects.toString(request.getParameter("vendor"), "") + "\" /><br><br>");
            out.println("<input type=\"text\" name=\"model\" value=\"" + Objects.toString(request.getParameter("model"), "") + "\" /><br><br>");
            out.println("<input type=\"date\" name=\"date1\" value=\"" + Objects.toString(request.getParameter("date1")) + "\" />");
            out.println("<input type=\"date\" name=\"date2\" value=\"" + Objects.toString(request.getParameter("date2")) + "\" /><br><br>");
            out.println("<input type=\"submit\" value=\"Apply\"/><br><br>");
            out.println("</form>");
            out.println("</div>");
            out.println("<h1>All devices</h1>");
            out.println("<table border=\"1\">");
            out.println("<tr>");
            out.println("<th>Identity</th>");
            out.println("<th>Vendor</th>");
            out.println("<td>Model</td>");
            out.println("<td>Date</td>");
            out.println("</tr>");
            for(DeviceDto d: deviceList) {
               out.println("<tr>");
               out.println("<td>" + d.getId() + "</td>");
               out.println("<td>" + d.getVendor() + "</td>");
               out.println("<td>" + d.getModel() + "</td>");
               out.println("<td>" + d.getDate() + "</td>");
               out.println("</tr>");
           }
           out.println("</table><br><br>");
           out.println("<a href=\"http://localhost:8080/auth/device\">Add new device</a>");
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
        if (!Person.checkSessionId(request.getSession().getId())) {
            response.sendRedirect("http://localhost:8080/auth/");
        } else {
        processRequest(request, response);
        }
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
