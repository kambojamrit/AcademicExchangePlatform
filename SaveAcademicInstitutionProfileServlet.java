
package servlets;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet(name = "SaveAcademicInstitutionProfileServlet", urlPatterns = {"/SaveAcademicInstitutionProfileServlet"})
public class SaveAcademicInstitutionProfileServlet extends HttpServlet {
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
        String address = request.getParameter("address");
        String coursesOffered = request.getParameter("coursesOffered");

        try {
            // Save data to the database
            userDAO.saveAcademicInstitutionProfile(userId, address, coursesOffered);
            
              // Retrieve Name and Email from the database for the user
            String name = userDAO.getInstitutionName(userId);
            String email = userDAO.getInstitutionEmail(userId);
            
            // Set session attributes with the saved data
            session.setAttribute("userName", name);
            session.setAttribute("userEmail", email);
            session.setAttribute("institutionAddress", address);
            session.setAttribute("coursesOffered", coursesOffered);
            
            response.sendRedirect("dashboard.jsp?success=1"); // Redirect to a dashboard or confirmation page
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("academicInstitutionProfile.jsp?error=1"); // Redirect back with error
        }
    }
}
