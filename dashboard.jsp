<%-- 
    Document   : dashboard
    Created on : 01-Dec-2024, 1:54:35 pm
    Author     : Amrit
    Description: This page will load the profile of academic institution. The name, email, addess and courses offered
                 will be loaded from the saved sessiona attributes that the user used to login into their profile. The
                 "Manage Courses" section will allow the user to add courses to the "courses" table in the databases
                 using the "SaveCouseDetailsServlet". Once the course is successfully added the "Current Courses Offered"
                 section updates with the most recently added course to display on the profile page.
--%>

<%@page import="java.util.List"%>
<%@page import="models.Course"%>
<%@page import="dao.CourseDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Academic Institution Dashboard</title>
    <style>
        
        body {
            background-color: #121212;
            color: white;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1000px;
            margin: 20px auto;
            padding: 20px;
            background-color: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
        }

        h1, h2, h3 {
            color: #ffffff;
            border-bottom: 2px solid #6200ea;
            padding-bottom: 5px;
        }

        p, label {
            color: #ccc;
        }

        .dashboard-layout {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 20px;
        }

        .section {
            padding: 20px;
            background-color: #2c2c2c;
            border-radius: 10px;
        }

        .full-width {
            grid-column: span 2;
        }

        form {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }

        input, select, textarea, button {
            background-color: #2c2c2c;
            color: white;
            border: 1px solid #444;
            border-radius: 5px;
            padding: 10px;
            font-size: 14px;
        }

        input::placeholder, textarea::placeholder {
            color: #aaa;
        }

        button {
            grid-column: span 2;
            background-color: #6200ea;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #3700b3;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table thead {
            background-color: #333;
        }

        table th, table td {
            border: 1px solid #444;
            padding: 10px;
            text-align: left;
            color: white;
        }

        table th {
            background-color: #6200ea;
        }

        table tbody tr:nth-child(even) {
            background-color: #2c2c2c;
        }

        table tbody tr:hover {
            background-color: #444;
        }
        .logout-button {
    color: #ffffff;
    background-color: #6200ea;
    padding: 10px 20px;
    border-radius: 5px;
    text-decoration: none;
    font-weight: bold;
    transition: background-color 0.3s ease;
    display: inline-block;
}

.logout-button:hover {
    background-color: #3700b3;
}

    </style>
</head>
<body>
    <div class="container">
        <h1>Welcome to Your Dashboard</h1>
        <div style="text-align: right; margin-bottom: 10px;">
    <a href="LogoutServlet" class="logout-button">Logout</a>
</div>

        <div class="dashboard-layout">
            <!-- Left Section -->
            <div class="section">
                <h2>Your Profile</h2>
                <h3>Institution Details</h3>
                <p><strong>Name:</strong> <%= session.getAttribute("userName") %></p>
                <p><strong>Email:</strong> <%= session.getAttribute("userEmail") %></p>
                <p><strong>Address:</strong> <%= session.getAttribute("institutionAddress") %></p>
                <p><strong>Courses Offered:</strong> <%= session.getAttribute("coursesOffered") %></p>
            </div>

            <!-- Right Section -->
            <div class="section">
                <h2>Manage Courses</h2>
                <form action="SaveCourseDetailsServlet" method="post">
                    <label for="courseTitle">Course Title:</label>
                    <input type="text" id="courseTitle" name="courseTitle" placeholder="Enter course title" required>

                    <label for="courseCode">Course Code:</label>
                    <input type="text" id="courseCode" name="courseCode" placeholder="Enter course code" required>

                    <label for="courseTerm">Term:</label>
                    <input type="text" id="courseTerm" name="courseTerm" placeholder="Enter term (e.g., Fall 2024)" required>

                    <label for="courseSchedule">Schedule:</label>
                    <select id="courseSchedule" name="courseSchedule">
                        <option value="Morning">Morning</option>
                        <option value="Afternoon">Afternoon</option>
                        <option value="Evening">Evening</option>
                    </select>

                    <label for="courseDeliveryMethod">Delivery Method:</label>
                    <select id="courseDeliveryMethod" name="courseDeliveryMethod">
                        <option value="In-Person">In-Person</option>
                        <option value="Remote">Remote</option>
                        <option value="Hybrid">Hybrid</option>
                    </select>

                    <label for="qualificationsRequired">Qualifications Required:</label>
                    <textarea id="qualificationsRequired" name="qualificationsRequired" placeholder="Enter required qualifications" required></textarea>

                    <label for="compensation">Compensation:</label>
                    <input type="number" id="compensation" name="compensation" step="0.01" placeholder="Enter compensation" required>

                    <button type="submit">Save Course</button>
                </form>
            </div>
            

            <!-- Full-Width Section -->
            <div class="section full-width">
    <h3>Your Course Requests</h3>
    <div>
        <% 
            // Added a DAO call to fetch requests by the logged-in professional
            dao.RequestsDAO requestsDAO = new dao.RequestsDAO();
            int professionalId = (int) session.getAttribute("userId");

            try {
                List<models.CourseRequest> requests = requestsDAO.getRequestsByProfessionalId(professionalId);

                if (requests != null && !requests.isEmpty()) {
                    for (models.CourseRequest req : requests) {
        %>
            <p><strong>Request ID:</strong> <%= req.getRequestId() %></p>
            <p><strong>Course ID:</strong> <%= req.getCourseId() %></p>
            <p><strong>Request Date:</strong> <%= req.getRequestDate() %></p>
            <p><strong>Status:</strong> <%= req.getStatus() %></p>
            <hr>
        <% 
                    }
                } else { 
        %>
            <p>No requests found.</p>
        <% 
                }
            } catch (Exception e) {
                e.printStackTrace();
            } 
        %>
    </div>
</div>
            <div class="section full-width">
                <h3>Current Courses Offered:</h3>
                <table>
                    <thead>
                        <tr>
                            <th>Course Title</th>
                            <th>Course Code</th>
                            <th>Term</th>
                            <th>Schedule</th>
                            <th>Delivery Method</th>
                            <th>Qualifications Required</th>
                            <th>Compensation</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            // Retrieve courses from database and display them
                            CourseDAO courseDAO = new CourseDAO();
                            int institutionId = (int) session.getAttribute("userId");
                            try {
                                List<Course> courses = courseDAO.getCoursesByInstitution(institutionId);
                                for (Course course : courses) {
                        %>
                            <tr>
                                <td><%= course.getTitle() %></td>
                                <td><%= course.getCode() %></td>
                                <td><%= course.getTerm() %></td>
                                <td><%= course.getSchedule() %></td>
                                <td><%= course.getDeliveryMethod() %></td>
                                <td><%= course.getQualificationsRequired() %></td>
                                <td><%= course.getCompensation() %></td>
                            </tr>
                        <%
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>
