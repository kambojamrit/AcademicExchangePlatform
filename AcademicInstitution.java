
package models;

public class AcademicInstitution {
    private int institutionId;
    private String address;
    private String coursesOffered;

    // Constructor with all fields
    public AcademicInstitution(int institutionId, String address, String coursesOffered) {
        this.institutionId = institutionId;
        this.address = address;
        this.coursesOffered = coursesOffered;
    }

    // Default constructor
    public AcademicInstitution() {}

    // Getters and Setters
    public int getInstitutionId() {
        return institutionId;
    }

    public void setInstitutionId(int institutionId) {
        this.institutionId = institutionId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCoursesOffered() {
        return coursesOffered;
    }

    public void setCoursesOffered(String coursesOffered) {
        this.coursesOffered = coursesOffered;
    }
}