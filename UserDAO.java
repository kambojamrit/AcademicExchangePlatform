
package dao;

import utils.DatabaseConnection;
import models.User;
import java.sql.*;

/**
 * This class interacts with the user class to manipulate data and perform CRUD operations on "users"
 * "academicinstitutions" and "academicprofessionals" tables in the "aep" database.
 * 
 */
public class UserDAO {
    
     /**
     * Registers a new user in the database.
     *
     * @param user the {@link User} object containing user details such as name, email, password, and user type.
     * @throws SQLException if a database access error occurs.
     */
    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO Users (Name, Email, Password, UserType) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType());
            statement.executeUpdate();
        }
    }
    
    /**
     * Saves academic professional profile information for a user in the database.
     *
     * @param userId             the unique identifier of the user.
     * @param institution        the institution where the user works or is associated with.
     * @param academicPosition   the academic position held by the user.
     * @param educationBackground the user's education background.
     * @param areaOfExpertise    the user's area of expertise.
     * @throws SQLException if a database access error occurs.
     */
    public void saveAcademicProfessionalProfile(int userId, String institution, String academicPosition, String educationBackground, String areaOfExpertise) throws SQLException {
    String query = "INSERT INTO AcademicProfessionals (UserID, Institution, AcademicPosition, EducationBackground, AreaOfExpertise) VALUES (?, ?, ?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userId);
        statement.setString(2, institution);
        statement.setString(3, academicPosition);
        statement.setString(4, educationBackground);
        statement.setString(5, areaOfExpertise);
        statement.executeUpdate();
    }
}
    /**
     * Saves academic institution profile information for a user in the database.
     *
     * @param userId         the unique identifier of the user.
     * @param address        the address of the institution.
     * @param coursesOffered the courses offered by the institution.
     * @throws SQLException if a database access error occurs.
     */
    public void saveAcademicInstitutionProfile(int userId, String address, String coursesOffered) throws SQLException {
    String query = "INSERT INTO AcademicInstitutions (UserID, Address, CoursesOffered) VALUES (?, ?, ?)";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setInt(1, userId);
        statement.setString(2, address);
        statement.setString(3, coursesOffered);
        statement.executeUpdate();
    }
}

     /**
     * Retrieves the name of a user by their ID.
     *
     * @param userId the unique identifier of the user.
     * @return the name of the user, or {@code null} if no user is found.
     * @throws SQLException if a database access error occurs.
     */
    public String getInstitutionName(int userId) throws SQLException {
        String query = "SELECT name FROM users WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("name");
                }
            }
        }
        return null;
    }

     /**
     * Retrieves the email of a user by their ID.
     *
     * @param userId the unique identifier of the user.
     * @return the email of the user, or {@code null} if no user is found.
     * @throws SQLException if a database access error occurs.
     */
    public String getInstitutionEmail(int userId) throws SQLException {
        String query = "SELECT email FROM users WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("email");
                }
            }
        }
        return null;
    }
    
    /**
     * Retrieves the address of an academic institution associated with a user.
     *
     * @param userId the unique identifier of the user.
     * @return the address of the institution, or {@code null} if no institution is found.
     * @throws SQLException if a database access error occurs.
     */
    public String getInstitutionAddress(int userId) throws SQLException {
        String query = "SELECT address FROM academicinstitutions WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("address");
                }
            }
        }
        return null;
    }
    
     /**
     * Retrieves the courses offered by an academic institution associated with that institution.
     *
     * @param userId the unique identifier of the (institution) user.
     * @return the courses offered by the institution, or {@code null} if no institution is found.
     * @throws SQLException if a database access error occurs.
     */
    public String getInstitutionCoursesOffered(int userId) throws SQLException {
        String query = "SELECT coursesOffered FROM academicinstitutions WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("coursesOffered");
                }
            }
        }
        return null;
    }
    
    

     /**
     * Checks if a user's profile is complete based on their user type.
     *
     * @param userId   the unique identifier of the user.
     * @param userType the type of the user (e.g., "AcademicInstitution" or "AcademicProfessional").
     * @return {@code true} if the profile is complete; {@code false} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public boolean isProfileCompleted(int userId, String userType) throws SQLException {
       boolean isCompleted = false;
        
       if ("AcademicInstitution".equals(userType)) {
        String query = "SELECT address, coursesOffered FROM academicinstitutions WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String address = rs.getString("address");
                    String coursesOffered = rs.getString("coursesOffered");
                    // Check if required fields are filled
                    return address != null && !address.isEmpty() && coursesOffered != null && !coursesOffered.isEmpty();
                }
            }
        }
        
       } else if ("AcademicProfessional".equals(userType)) {
        // Check if the academic professional profile is completed
        String query = "SELECT institution, academicPosition, educationBackground, areaOfExpertise FROM academicprofessionals WHERE userId = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String institution = rs.getString("institution");
                    String academicPosition = rs.getString("academicPosition");
                    String educationBackground = rs.getString("educationBackground");
                    String areaOfExpertise = rs.getString("areaOfExpertise");
                    // Check if required fields are filled
                    isCompleted = (institution != null && !institution.isEmpty()) && 
                                  (academicPosition != null && !academicPosition.isEmpty()) && 
                                  (educationBackground != null && !educationBackground.isEmpty()) && 
                                  (areaOfExpertise != null && !areaOfExpertise.isEmpty());
                }
            }
        }
    }

    return isCompleted;
    }
    
    public String getAcademicProfessionalName(int userId) throws SQLException {
    String query = "SELECT Name FROM Users WHERE UserID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Name");
            }
        }
    }
    return null;
}

public String getAcademicProfessionalEmail(int userId) throws SQLException {
    String query = "SELECT Email FROM Users WHERE UserID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Email");
            }
        }
    }
    return null;
}

public String getAcademicProfessionalInstitution(int userId) throws SQLException {
    String query = "SELECT Institution FROM AcademicProfessionals WHERE UserID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("Institution");
            }
        }
    }
    return null;
}

public String getAcademicProfessionalPosition(int userId) throws SQLException {
    String query = "SELECT AcademicPosition FROM AcademicProfessionals WHERE UserID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("AcademicPosition");
            }
        }
    }
    return null;
}

public String getAcademicProfessionalEducationBackground(int userId) throws SQLException {
    String query = "SELECT EducationBackground FROM AcademicProfessionals WHERE UserID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("EducationBackground");
            }
        }
    }
    return null;
}

public String getAcademicProfessionalAreaOfExpertise(int userId) throws SQLException {
    String query = "SELECT AreaOfExpertise FROM AcademicProfessionals WHERE UserID = ?";
    try (Connection connection = DatabaseConnection.getConnection();
         PreparedStatement stmt = connection.prepareStatement(query)) {
        stmt.setInt(1, userId);
        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getString("AreaOfExpertise");
            }
        }
    }
    return null;
}

       /**
     * Authenticates a user by their email and password. (Work in progress, might delete this method
     * after completing AuthenticationServlet code.
     *
     * @param email    the email of the user.
     * @param password the password of the user.
     * @return a {@link User} object if authentication is successful; {@code null} otherwise.
     * @throws SQLException if a database access error occurs.
     */
    public User authenticateUser(String email, String password) throws SQLException {
        String query = "SELECT * FROM Users WHERE Email = ? AND Password = ?";
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return new User(
                        resultSet.getInt("UserID"),
                        resultSet.getString("Name"),
                        resultSet.getString("Email"),
                        resultSet.getString("Password"),
                        resultSet.getString("UserType")
                );
            }
        }
        return null;
    }
    
}
