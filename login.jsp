<%-- 
    Document   : login
    Created on : 01-Dec-2024, 8:01:43 am
    Author     : Amrit
    Description: The user will login using their email and password they used to register to the platform. The "AuthenticationServlet"
                  will authenticate these credential and based on usertype it will redirect them to either dashboard.jsp (for academic 
                  institutions) or professionalDashoboard.jsp (for academic professionals).
                  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <style>
        /* CSS styling for login page of the application*/
        body {
            background-color: #121212;
            color: white;
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
        }

        .container {
            background-color: #1e1e1e;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.5);
            text-align: center;
            width: 300px;
        }

        h1 {
            margin-bottom: 20px;
            font-size: 24px;
        }

        form {
            display: flex;
            flex-direction: column;
        }

        input, button {
            margin: 10px 0;
            padding: 10px;
            border: none;
            border-radius: 5px;
            font-size: 16px;
        }

        input {
            background-color: #2c2c2c;
            color: white;
        }

        input::placeholder {
            color: #aaa;
        }

        button {
            background-color: #6200ea;
            color: white;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #3700b3;
        }

        button:focus {
            outline: none;
        }
    </style>
    </head>
    <body>
        <form action="AuthenticationServlet" method="post">
    <input type="email" name="email" placeholder="Email" required>
    <input type="password" name="password" placeholder="Password" required>
    <button type="submit">Login</button>
</form>

    </body>
</html>
