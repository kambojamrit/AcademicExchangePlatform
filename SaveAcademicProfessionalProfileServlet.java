
package servlets;

import dao.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


@WebServlet(name = "SaveAcademicProfessionalProfileServlet", urlPatterns = {"/SaveAcademicProfessionalProfileServlet"})
public class SaveAcademicProfessionalProfileServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve userId from the session
        HttpSession session = request.getSession();
        int userId = (int) session.getAttribute("userId");

        // Get data from the form
        String institution = request.getParameter("institution");
        String academicPosition = request.getParameter("academicPosition");
        String educationBackground = request.getParameter("educationBackground");
        String areaOfExpertise = request.getParameter("areaOfExpertise");

        try {
            // Save data to the database
            userDAO.saveAcademicProfessionalProfile(userId, institution, academicPosition, educationBackground, areaOfExpertise);

            // Set session attributes for the newly saved profile data
            session.setAttribute("userInstitution", institution);
            session.setAttribute("userAcademicPosition", academicPosition);
            session.setAttribute("userEducationBackground", educationBackground);
            session.setAttribute("userAreaOfExpertise", areaOfExpertise);

            response.sendRedirect("professionalDashboard.jsp?success=1"); // Redirect to a dashboard or confirmation page
        } catch (SQLException ex) {
            Logger.getLogger(SaveAcademicProfessionalProfileServlet.class.getName()).log(Level.SEVERE, null, ex);
            response.sendRedirect("academicProfessionalProfile.jsp?error=1"); // Redirect back with error
        }
    }
}