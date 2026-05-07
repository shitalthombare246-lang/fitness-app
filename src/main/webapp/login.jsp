<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>

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
    background: #ffffff;
    width: 340px;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 20px rgba(0,0,0,0.1);
    border-top: 4px solid #4a90e2;
    text-align: center;
}

h2 {
    margin-bottom: 20px;
    color: #2c3e50;
}

input {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    border-radius: 6px;
    border: 1px solid #dcdfe6;
    outline: none;
    transition: 0.3s;
}

input:focus {
    border-color: #4a90e2;
    box-shadow: 0 0 5px rgba(74,144,226,0.3);
}

input[type="submit"] {
    background-color: #4a90e2;
    color: white;
    border: none;
    cursor: pointer;
    font-weight: bold;
    margin-top: 10px;
}

input[type="submit"]:hover {
    background-color: #357ab8;
}

/* error message */
.error {
    color: red;
    margin-bottom: 10px;
    font-weight: bold;
}

/* Register link */
a {
    display: block;
    margin-top: 15px;
    color: #4a90e2;
    text-decoration: none;
    font-weight: bold;
}

a:hover {
    text-decoration: underline;
}
</style>

</head>
<body>

<div class="container">
    <h2>Login</h2>

    <!-- 🔴 Error Message Display -->
    <%
        String error = request.getParameter("error");
        if ("1".equals(error)) {
    %>
        <div class="error">Invalid Email or Password ❌</div>
    <%
        }
    %>

    <form action="login" method="post">
        <input type="email" name="email" placeholder="Enter Email" required>
        <input type="password" name="password" placeholder="Enter Password" required>

        <input type="submit" value="Login">
    </form>

    <a href="register.jsp">New User? Register Here</a>
</div>

</body>
</html>