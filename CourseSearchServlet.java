package servlets;

import dao.CourseDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import models.Course;


@WebServlet(name = "CourseSearchServlet", urlPatterns = {"/CourseSearchServlet"})
public class CourseSearchServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get search parameters from the request
        String courseId = request.getParameter("courseId");
        String courseName = request.getParameter("courseName");
        String institutionId = request.getParameter("institutionId");

        try {
            // Call searchCourses with the search parameters
            List<Course> courses = courseDAO.searchCourses(courseId, courseName, institutionId);

            // Set the result list as an attribute
            request.setAttribute("courses", courses);

            // Forward the request to the dashboard page to display the results
            request.getRequestDispatcher("professionalDashboard.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error searching courses.");
        }
    }
}