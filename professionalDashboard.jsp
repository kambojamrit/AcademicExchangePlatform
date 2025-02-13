<%-- 
    Document   : professionalDashboard
    Created on : 01-Dec-2024, 3:41:11 pm
    Author     : Amrit
    Description: This page will display profile details of the academic professional when they login 
                 into the platform. The profile details are filled by using saved session attributes
                 when the user logs in."Seach Course" section will use "CourseSearchServlet" to search 
                 for course using the parameters entered by the user and display the result in the "Search 
                 Results" section.
--%>

<%@page import="java.util.List"%>
<%@page import="models.Course"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Academic Professional Dashboard</title>
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

        input, button {
            background-color: #2c2c2c;
            color: white;
            border: 1px solid #444;
            border-radius: 5px;
            padding: 10px;
            font-size: 14px;
        }

        input::placeholder {
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

        #searchResults {
            margin-top: 20px;
        }

        hr {
            border: none;
            border-top: 1px solid #444;
            margin: 10px 0;
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
                <p><strong>Name:</strong> <%= session.getAttribute("userName") %></p>
                <p><strong>Email:</strong> <%= session.getAttribute("userEmail") %></p>
                <p><strong>Institution:</strong> <%= session.getAttribute("userInstitution") %></p>
                <p><strong>Academic Position:</strong> <%= session.getAttribute("userAcademicPosition") %></p>
                <p><strong>Education Background:</strong> <%= session.getAttribute("userEducationBackground") %></p>
                <p><strong>Area of Expertise:</strong> <%= session.getAttribute("userAreaOfExpertise") %></p>
            </div>

            <!-- Right Section -->
            <div class="section">
                <h2>Search Courses</h2>
                <form action="CourseSearchServlet" method="get">
                    <label for="courseId">Course ID:</label>
                    <input type="text" id="courseId" name="courseId" placeholder="Enter course ID">

                    <label for="courseName">Course Name:</label>
                    <input type="text" id="courseName" name="courseName" placeholder="Enter course name">

                    <label for="institutionId">Institution ID:</label>
                    <input type="text" id="institutionId" name="institutionId" placeholder="Enter institution ID">

                    <button type="submit">Search</button>
                </form>
            </div>

            <!-- Full-Width Section -->
            <div class="section full-width">
                <h3>Search Results</h3>
                <div id="searchResults">
                    <% 
                        List<Course> courses = (List<Course>) request.getAttribute("courses");
                        if (courses != null && !courses.isEmpty()) {
                            for (Course course : courses) {
                    %>
                        <p><strong>Title:</strong> <%= course.getTitle() %></p>
                        <p><strong>Code:</strong> <%= course.getCode() %></p>
                        <p><strong>Term:</strong> <%= course.getTerm() %></p>
                        <p><strong>Schedule:</strong> <%= course.getSchedule() %></p>
                        <p><strong>Delivery Method:</strong> <%= course.getDeliveryMethod() %></p>
                        <p><strong>Qualifications Required:</strong> <%= course.getQualificationsRequired() %></p>
                        <p><strong>Compensation:</strong> <%= course.getCompensation() %></p>
                        <hr>
                        
                        <h3>Want to Teach a Course?</h3>
                        <form action="CourseRequestServlet" method="post">
                         <input type="hidden" name="courseId" value="<%= course.getCourseId() %>">
                         <button type="submit" style="background-color: #3700b3; padding: 10px 20px;">Request to Teach a Course</button>
                         </form>
                        
                    <%  
                            }
                        } else {
                    %>
                        <p>No courses found.</p>
                    <%  
                        }
                    %>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
