package dao;

import utils.DatabaseConnection;
import java.sql.*;

/**
 * This class provides updateAcademicProfessional and updateAcademicInstitution methods for updating data related to 
 * academic professionals and academic institutions in the database. It uses prepared statements to ensure secure 
 * database interactions.
 */
public class ProfileDAO {

    /**
     * Updates the details of an academic professional in the database when this method is called by the 
     * SaveAcademicProfessionalProfileServlet. The following parameters will used to populate the "academicprofessionals"
     * table in the database.
     * 
     * @param userId       the unique identifier of the professional
     * @param position     the academic position of the professional
     * @param institution  the institution associated with the professional
     * @param education    the educational background of the professional
     * @param expertise    the area of expertise of the professional
     * @throws SQLException if a database access error occurs or the SQL statement is invalid
     */
    public void updateAcademicProfessional(int userId, String position, String institution, String education, String expertise) throws SQLException {
        String query = "INSERT AcademicProfessionals SET AcademicPosition = ?, Institution = ?, EducationBackground = ?, AreaOfExpertise = ? WHERE ProfessionalID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, position);
            statement.setString(2, institution);
            statement.setString(3, education);
            statement.setString(4, expertise);
            statement.setInt(5, userId);
            statement.executeUpdate();
        }
    }
    
     /**
     * Updates the details of an academic institution in the database when called by the SaveAcademicInstitutionProfileServlet
     * 
     * 
     * @param userId   the unique identifier of the institution
     * @param address  the address of the institution
     * @param courses  the courses offered by the institution
     * @throws SQLException if a database access error occurs or the SQL statement is invalid
     */

    public void updateAcademicInstitution(int userId, String address, String courses) throws SQLException {
        String query = "INSERT AcademicInstitutions SET Address = ?, CoursesOffered = ? WHERE InstitutionID = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, address);
            statement.setString(2, courses);
            statement.setInt(3, userId);
            statement.executeUpdate();
        }
    }
}
