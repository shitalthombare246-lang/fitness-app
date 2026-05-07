<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Fitness App</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background: #f2f4f7;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .container {
            text-align: center;
            background: white;
            padding: 50px 40px;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0,0,0,0.1);
            border-top: 4px solid #4a90e2;
        }

        h1 {
            font-size: 32px;
            color: #2c3e50;
            margin-bottom: 20px;
        }

        p {
            color: #7f8c8d;
            margin-bottom: 25px;
        }

        a {
            background-color: #4a90e2;
            color: white;
            padding: 10px 25px;
            text-decoration: none;
            border-radius: 6px;
            font-weight: bold;
            transition: 0.3s;
        }

        a:hover {
            background-color: #357ab8;
        }
    </style>

</head>
<body>

<div class="container">

    <h1>Welcome to Fitness App</h1>
    <p>Track your health and fitness easily</p>

    <a href="login.jsp">Go to Login</a>

</div>

</body>
</html>