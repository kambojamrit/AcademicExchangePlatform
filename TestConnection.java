
package servlets;

import utils.DatabaseConnection;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

@WebServlet( urlPatterns = {"/TestConnection"})
public class TestConnection extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.setContentType("text/html");
       
        try (PrintWriter out = response.getWriter()) {
            // Attempt to connect to the database
            try (Connection connection = DatabaseConnection.getConnection()) {
                if (connection != null && !connection.isClosed()) {
                    out.write("<h3>Database connection successful!</h3>");
                } else {
                    out.write("<h3>Failed to establish database connection.</h3>");
                }
            } catch (SQLException e) {
                // Handle SQL exceptions
                out.write("<h3>Error connecting to the database: " + e.getMessage() + "</h3>");
                e.printStackTrace(out);
            }
        }
    }
            
    }


    