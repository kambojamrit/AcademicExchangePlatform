
package models;


public class AcademicProfessional {
    private int professionalId;
    private String institution;
    private String academicPosition;
    private String educationBackground;
    private String areaOfExpertise;

    // Constructor with all fields
    public AcademicProfessional(int professionalId, String institution, String academicPosition, 
                                String educationBackground, String areaOfExpertise) {
        this.professionalId = professionalId;
        this.institution = institution;
        this.academicPosition = academicPosition;
        this.educationBackground = educationBackground;
        this.areaOfExpertise = areaOfExpertise;
    }

    // Default constructor
    public AcademicProfessional() {}

    // Getters and Setters
    public int getProfessionalId() {
        return professionalId;
    }

    public void setProfessionalId(int professionalId) {
        this.professionalId = professionalId;
    }

    public String getInstitution() {
        return institution;
    }

    public void setInstitution(String institution) {
        this.institution = institution;
    }

    public String getAcademicPosition() {
        return academicPosition;
    }

    public void setAcademicPosition(String academicPosition) {
        this.academicPosition = academicPosition;
    }

    public String getEducationBackground() {
        return educationBackground;
    }

    public void setEducationBackground(String educationBackground) {
        this.educationBackground = educationBackground;
    }

    public String getAreaOfExpertise() {
        return areaOfExpertise;
    }

    public void setAreaOfExpertise(String areaOfExpertise) {
        this.areaOfExpertise = areaOfExpertise;
    }
}
