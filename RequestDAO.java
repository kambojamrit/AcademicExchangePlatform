
package dao;

import models.CourseRequest;
import utils.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The class provides methods to manage course requests in the database and saving changes in 
 * the "requests" table.
 * It allows submission of new requests and retrieval of requests by academic professional ID.
 */

public class RequestsDAO {
    
     /**
     * When an academic professional submits a new course request using professionalDashboard.jsp, the "requests"
     * table in the database is updated using the couseid, AcademicProfessionalID, RequestDate, Status.
     *
     * @param request the object containing details of the request such as course ID,
     *                academic professional ID, request date, and status.
     * @throws SQLException if a database access error occurs.
     */
    public void submitRequest(CourseRequest request) throws SQLException {
        String query = "INSERT INTO Requests (CourseID, AcademicProfessionalID, RequestDate, Status) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, request.getCourseId());
            ps.setInt(2, request.getAcademicProfessionalId());
            ps.setDate(3, request.getRequestDate());
            ps.setString(4, request.getStatus());
            ps.executeUpdate();
        }
    }

     /**
     * Retrieves a list of course requests associated with a specific academic professional ID.
     *
     * @param professionalId the unique identifier of the academic professional.
     * @return a  List of CourseRequest objects containing the details of the requests.
     * @throws SQLException if a database access error occurs.
     */
    public List<CourseRequest> getRequestsByProfessionalId(int professionalId) throws SQLException {
        String query = "SELECT * FROM Requests WHERE AcademicProfessionalID = ?";
        List<CourseRequest> requests = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, professionalId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                CourseRequest request = new CourseRequest();
                request.setRequestId(rs.getInt("RequestID"));
                request.setCourseId(rs.getInt("CourseID"));
                request.setAcademicProfessionalId(rs.getInt("AcademicProfessionalID"));
                request.setRequestDate(rs.getDate("RequestDate"));
                request.setStatus(rs.getString("Status"));
                requests.add(request);
            }
        }
        return requests;
    }

    
}
