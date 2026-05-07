<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<style>
body {
    font-family: Arial, sans-serif;
    background: #f2f4f7;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    margin: 0;
}

.container {
    background: #ffffff;
    padding: 30px;
    border-radius: 10px;
    width: 340px;
    box-shadow: 0 4px 20px rgba(0,0,0,0.1);
    border-top: 4px solid #4a90e2;
}

h2 {
    text-align: center;
    color: #2c3e50;
    margin-bottom: 20px;
}

input {
    width: 100%;
    padding: 10px;
    margin: 8px 0;
    border: 1px solid #dcdfe6;
    border-radius: 6px;
    outline: none;
}

input:focus {
    border-color: #4a90e2;
    box-shadow: 0 0 5px rgba(74,144,226,0.3);
}

input[type="submit"] {
    background: #4a90e2;
    color: white;
    font-weight: bold;
    cursor: pointer;
}

input[type="submit"]:hover {
    background: #357ab8;
}
</style>

</head>
<body>

<div class="container">

<h2>Register Page</h2>

<form action="register" method="post">

    Name:
    <input type="text" name="name" required>

    Email:
    <input type="email" name="email" required>

    🔥 Age:
    <input type="number" name="age" required>

    🔥 Weight (kg):
    <input type="number" name="weight" step="0.1" required>

    Password:
    <input type="password" name="password" required>

    <input type="submit" value="Register">

</form>

</div>

</body>
</html>