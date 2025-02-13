
package dao;

import models.Course;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {
    
    /**
     * This class will save/update course details in the courses table in the "aep" database when the academic
     * institution will add/update the following parameters using dashboard.jsp
     * 
     * @param institutionId          ID of the institution offering the course
     * @param title                  Title of the course
     * @param code                   Code of the course
     * @param term                   Term during which the course is offered
     * @param schedule               Schedule details of the course
     * @param deliveryMethod         Delivery method of the course (hybridm, online, in-person)
     * @param qualificationsRequired Required qualifications for the course
     * @param compensation           Compensation offered for the course
     * @throws SQLException if a database error occurs
     */
    public void saveOrUpdateCourse(int institutionId, String title, String code, String term, 
                                    String schedule, String deliveryMethod, String qualificationsRequired, double compensation) throws SQLException {

        String query = "INSERT INTO Courses (InstitutionID, Title, Code, Term, Schedule, DeliveryMethod, QualificationsRequired, Compensation) " +
                       "VALUES (?, ?, ?, ?, ?, ?, ?, ?) " +
                       "ON DUPLICATE KEY UPDATE Title = ?, Term = ?, Schedule = ?, DeliveryMethod = ?, QualificationsRequired = ?, Compensation = ?";

        try (Connection connection = DatabaseConnection.getConnection();        //trying to connect to database
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, institutionId);
            ps.setString(2, title);
            ps.setString(3, code);
            ps.setString(4, term);
            ps.setString(5, schedule);
            ps.setString(6, deliveryMethod);
            ps.setString(7, qualificationsRequired);
            ps.setDouble(8, compensation);

            // Update the existing record if the course code already exists
            ps.setString(9, title);
            ps.setString(10, term);
            ps.setString(11, schedule);
            ps.setString(12, deliveryMethod);
            ps.setString(13, qualificationsRequired);
            ps.setDouble(14, compensation);

            ps.executeUpdate();
        }
    }
    
     /**
     * Retrieves a list of courses from courses table for a specific institution based on the InstitutionID.
     * The result will contain couseID, InstitutionID, Title, Code,Schedule,DeliveryMethod,QualificationRequired,
     * and compensation (i.e. courses object)
     * 
     * @param institutionId ID of the institution
     * @return List of courses offered by the institution
     * @throws SQLException if a database error occurs
     */
    public List<Course> getCoursesByInstitution(int institutionId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        String query = "SELECT * FROM Courses WHERE InstitutionID = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, institutionId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setInstitutionId(rs.getInt("InstitutionID"));
                course.setTitle(rs.getString("Title"));
                course.setCode(rs.getString("Code"));
                course.setTerm(rs.getString("Term"));
                course.setSchedule(rs.getString("Schedule"));
                course.setDeliveryMethod(rs.getString("DeliveryMethod"));
                course.setQualificationsRequired(rs.getString("QualificationsRequired"));
                course.setCompensation(rs.getDouble("Compensation"));
                courses.add(course);
            }
        }
        return courses;
    }
    
     /**
     * Searches for courses based on course ID, course name, or institution ID. The user can use any
     * combination of parameters to search for the course. The method is used by the CourseSearchServlet.java
     * class to search for the courses based on above parameters entered by the user in the professionalDashoboard.jsp
     * 
     * @param courseId     Course ID to search for (optional)
     * @param courseName   Course name to search for (optional, supports partial matches)
     * @param institutionId Institution ID to search for (optional)
     * @return List of courses matching the search criteria
     * @throws SQLException if a database error occurs
     */
    public List<Course> searchCourses(String courseId, String courseName, String institutionId) throws SQLException {
        List<Course> courses = new ArrayList<>();
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM Courses WHERE 1=1");

        // Dynamically add conditions based on provided search parameters
        if (courseId != null && !courseId.isEmpty()) {
            queryBuilder.append(" AND CourseID = ?");
        }
        if (courseName != null && !courseName.isEmpty()) {
            queryBuilder.append(" AND Title LIKE ?");
        }
        if (institutionId != null && !institutionId.isEmpty()) {
            queryBuilder.append(" AND InstitutionID = ?");
        }

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(queryBuilder.toString())) {

            int parameterIndex = 1;
            
            if (courseId != null && !courseId.isEmpty()) {
                ps.setInt(parameterIndex++, Integer.parseInt(courseId));  
            }
            if (courseName != null && !courseName.isEmpty()) {
                ps.setString(parameterIndex++, "%" + courseName + "%");  // Use LIKE for partial matching
            }
            if (institutionId != null && !institutionId.isEmpty()) {
                ps.setInt(parameterIndex++, Integer.parseInt(institutionId));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Course course = new Course();
                course.setCourseId(rs.getInt("CourseID"));
                course.setInstitutionId(rs.getInt("InstitutionID"));
                course.setTitle(rs.getString("Title"));
                course.setCode(rs.getString("Code"));
                course.setTerm(rs.getString("Term"));
                course.setSchedule(rs.getString("Schedule"));
                course.setDeliveryMethod(rs.getString("DeliveryMethod"));
                course.setQualificationsRequired(rs.getString("QualificationsRequired"));
                course.setCompensation(rs.getDouble("Compensation"));
                courses.add(course);
            }
        }
        return courses;
    }
    
    
}
