<%-- 
    Document   : academicInstitutionProfile
    Created on : 01-Dec-2024, 8:35:33 am
    Author     : Amrit
    Description : This page will call the SaveAcademicInstitutionProfileServlet to save the 
                  profile details i.e. address and courses offered in the "academicinstitutions" 
                  table in the "aep" database in mysql.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Academic Institution Profile</title>
    <style>
        /* CSS styling for profile completion page of the academic institution*/
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
        <form action="SaveAcademicInstitutionProfileServlet" method="post">
            <label for="address">Address:</label>
            <input type="text" id="address" name="address" placeholder="Enter the institution address" required>

            <label for="coursesOffered">Courses Offered:</label>
            <textarea id="coursesOffered" name="coursesOffered" placeholder="List the courses offered" required></textarea>

            <button type="submit">Submit</button>
        </form>
    </div>
</body>
</html>
