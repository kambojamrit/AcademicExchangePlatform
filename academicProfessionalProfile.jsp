<%-- 
    Document   : academicProfessionalProfile
    Created on : 01-Dec-2024, 8:34:48 am
    Author     : Amrit 
    Description: This page will call the SaveAcademicProfessionalProfileServlet to save the information
                 added by the academic professional while setting up their profile. On this page the
                 use will enter current institution, position, education background and area of expertise.
                 Once the submit button is clicked by the user, SaveAcademicProfessionalProfileServlet will
                 interact with the ProfileDAO class to save the details in the "academicprofessionals" table
                 in the "aep" table
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Academic Professional Profile</title>
    <style>
/* CSS styling for Profile completion page of the academic professional*/
        body {
            background-color: #121212;
            color: white;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 600px;
            margin: 20px auto;
            padding: 20px;
            background-color: #1e1e1e;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
        }

        h1 {
            color: #ffffff;
            border-bottom: 2px solid #6200ea;
            padding-bottom: 5px;
            text-align: center;
        }

        form {
            display: grid;
            gap: 15px;
        }

        label {
            color: #ccc;
        }

        input, textarea, button {
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

        textarea {
            resize: vertical;
        }

        button {
            background-color: #6200ea;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #3700b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Complete Your Profile</h1>
        <form action="SaveAcademicProfessionalProfileServlet" method="post">
            <label for="institution">Institution:</label>
            <input type="text" id="institution" name="institution" placeholder="Enter your institution" required>

            <label for="academicPosition">Academic Position:</label>
            <input type="text" id="academicPosition" name="academicPosition" placeholder="Enter your position" required>

            <label for="educationBackground">Education Background:</label>
            <textarea id="educationBackground" name="educationBackground" placeholder="Provide your education background" required></textarea>

            <label for="areaOfExpertise">Area of Expertise:</label>
            <textarea id="areaOfExpertise" name="areaOfExpertise" placeholder="Mention your expertise area" required></textarea>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
