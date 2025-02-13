
package servlets;

import dao.UserDAO;
import models.User;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "AuthenticationServlet", urlPatterns = {"/AuthenticationServlet"})
public class AuthenticationServlet extends HttpServlet {
private UserDAO userDAO;

    @Override
    public void init() {
        userDAO = new UserDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User user = userDAO.authenticateUser(email, password);
            if (user != null) {
               HttpSession session = request.getSession();
                session.setAttribute("user", user);
                session.setAttribute("userType", user.getUserType()); // Save the user type
                session.setAttribute("userId", user.getUserId()); // Save the user ID

                String userType = user.getUserType();
                int userId = user.getUserId();
                
                // Check if the user has completed their profile
                boolean isProfileCompleted = userDAO.isProfileCompleted(userId, userType);

                // Get user data for profile pre-filling
                if ("AcademicInstitution".equals(userType)) {
                    String name = userDAO.getInstitutionName(userId);
                    String emailAddr = userDAO.getInstitutionEmail(userId);
                    String address = userDAO.getInstitutionAddress(userId);
                    String coursesOffered = userDAO.getInstitutionCoursesOffered(userId);
                    
                    // Set session attributes for profile data
                    session.setAttribute("userName", name);
                    session.setAttribute("userEmail", emailAddr);
                    session.setAttribute("institutionAddress", address);
                    session.setAttribute("coursesOffered", coursesOffered);
                }

                if ("AcademicProfessional".equals(userType)) {
                    // Retrieve academic professional's profile data
                    String name = userDAO.getAcademicProfessionalName(userId);
                    String emailAddr = userDAO.getAcademicProfessionalEmail(userId);
                    String institution = userDAO.getAcademicProfessionalInstitution(userId);
                    String academicPosition = userDAO.getAcademicProfessionalPosition(userId);
                    String educationBackground = userDAO.getAcademicProfessionalEducationBackground(userId);
                    String areaOfExpertise = userDAO.getAcademicProfessionalAreaOfExpertise(userId);

                    // Set session attributes for profile data
                    session.setAttribute("userName", name);
                    session.setAttribute("userEmail", emailAddr);
                    session.setAttribute("userInstitution", institution);
                    session.setAttribute("userAcademicPosition", academicPosition);
                    session.setAttribute("userEducationBackground", educationBackground);
                    session.setAttribute("userAreaOfExpertise", areaOfExpertise);
                }
                
                if (!isProfileCompleted) {
                    // Redirect to profile creation page based on user type
                    if ("AcademicProfessional".equals(userType)) {
                        response.sendRedirect("academicProfessionalProfile.jsp");
                    } else if ("AcademicInstitution".equals(userType)) {
                        response.sendRedirect("academicInstitutionProfile.jsp");
                    }
                } else {
                    // Redirect to dashboard based on user type
                    if ("AcademicProfessional".equals(userType)) {
                        response.sendRedirect("professionalDashboard.jsp");
                    } else if ("AcademicInstitution".equals(userType)) {
                        response.sendRedirect("dashboard.jsp");
                    }
                }
            } else {
                response.sendRedirect("login.jsp?error=1"); // Login failed, redirect back with error
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=1"); // Database error
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Invalidate session and redirect to login page
        HttpSession session = request.getSession();
        session.invalidate();
        response.sendRedirect("login.jsp");
    }
}