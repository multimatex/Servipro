package Servlets;

import Controles.AccesoDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Utils.Encriptar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class IniciarSesion extends HttpServlet {

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
            
            if (request.getParameter("Login") != null) {
                //Listas Desplegables
                BL.Usuarios.clsPerfil bl_clsPerfil = new BL.Usuarios.clsPerfil();

                request.setAttribute("lstclsPerfil", bl_clsPerfil.getPerfil());
                
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
            String Id_usuario = null, Contraseña = null;
            int GrupoEmpl = 0;
            Id_usuario = request.getParameter("id_usuario");
            Contraseña = request.getParameter("contraseña");

            AccesoDatos a = new AccesoDatos();
            int ca = a.buscar(Id_usuario, Contraseña);
            if (ca > 0) {
                HttpSession objsesion = request.getSession(true);
                objsesion.setAttribute("id_usuario", Id_usuario);
                response.sendRedirect("index.htm");
            } else {
                response.sendRedirect("logear?Login=true");
            }
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
