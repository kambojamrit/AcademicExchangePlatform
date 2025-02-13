
package servlets;

import dao.CourseDAO;
import models.Course;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;


@WebServlet(name = "SaveCourseDetailsServlet", urlPatterns = {"/SaveCourseDetailsServlet"})
public class SaveCourseDetailsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int institutionId = (int) session.getAttribute("userId");

        String title = request.getParameter("courseTitle");
        String code = request.getParameter("courseCode");
        String term = request.getParameter("courseTerm");
        String schedule = request.getParameter("courseSchedule");
        String deliveryMethod = request.getParameter("courseDeliveryMethod");
        String qualificationsRequired = request.getParameter("qualificationsRequired");
        double compensation = Double.parseDouble(request.getParameter("compensation"));

        CourseDAO courseDAO = new CourseDAO();
        try {
            // Save or update the course details
            courseDAO.saveOrUpdateCourse(institutionId, title, code, term, schedule, deliveryMethod, qualificationsRequired, compensation);

            // Redirect back to dashboard with updated courses
            response.sendRedirect("dashboard.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("dashboard.jsp?error=1");
        }
    }
    
}