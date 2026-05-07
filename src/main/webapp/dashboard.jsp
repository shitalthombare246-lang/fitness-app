<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page session="true" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>

<style>

/* 🌤️ SOFT LIGHT BACKGROUND */
body {
    margin: 0;
    padding: 0;
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #f5f7fb, #e8eef7);
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

/* 📦 MAIN CONTAINER */
.container {
    background: #ffffff;
    width: 600px;
    padding: 30px;
    border-radius: 14px;
    box-shadow: 0 8px 25px rgba(0,0,0,0.08);
    text-align: center;
}

/* TITLE */
h2 {
    color: #1f2d3d;
}

.sub-title {
    color: #6b7c93;
}

/* 📊 CARDS */
.cards {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
}

.card {
    width: 30%;
    background: #f8fafc;
    padding: 15px;
    border-radius: 10px;
    border: 1px solid #e3e8f0;
}

.card h3 {
    color: #2c3e50;
}

.card p {
    font-size: 22px;
    font-weight: bold;
    color: #2c3e50;
}

/* EXTRA BOX */
.extra {
    margin-top: 20px;
    padding: 15px;
    background: #f6f9fc;
    border: 1px solid #e3e8f0;
    border-radius: 10px;
    color: #2c3e50;
}

/* INPUT */
.input-box input {
    padding: 10px;
    width: 70%;
    border: 1px solid #d0d7e2;
    border-radius: 6px;
    margin-top: 20px;
}

/* BUTTONS */
button {
    margin-top: 10px;
    padding: 10px 25px;
    border: none;
    border-radius: 8px;
    cursor: pointer;
    font-weight: 500;
}

/* COLORS */
.calc-btn {
    background: #4f8cff;
    color: white;
}

.sim-btn {
    background: #ffb347;
    color: white;
}

.save-btn {
    background: #4caf7d;
    color: white;
}

/* LOGOUT */
.logout-btn {
    display: inline-block;
    margin-top: 20px;
    padding: 10px 25px;
    background: #ff5c5c;
    color: white;
    text-decoration: none;
    border-radius: 8px;
}

</style>
</head>

<body>

<%
    String user = (String) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    Integer stepsVal = (Integer) request.getAttribute("steps");

    Number calNum = (Number) request.getAttribute("calories");
    Number distNum = (Number) request.getAttribute("distance");
    Number discNum = (Number) request.getAttribute("discount");

    Integer points = (Integer) request.getAttribute("points");

    double calVal = (calNum != null) ? calNum.doubleValue() : 0;
    double distVal = (distNum != null) ? distNum.doubleValue() : 0;
    double discount = (discNum != null) ? discNum.doubleValue() : 0;
%>

<div class="container">

    <h2>Fitness Dashboard</h2>
    <p class="sub-title">Welcome, <%= user %></p>

    <div class="cards">

        <div class="card">
            <h3>Steps</h3>
            <p><%= (stepsVal != null ? stepsVal : 0) %></p>
        </div>

        <div class="card">
            <h3>Calories</h3>
            <p><%= (int)calVal %></p>
        </div>

        <div class="card">
            <h3>Distance</h3>
            <p><%= String.format("%.2f", distVal) %></p>
        </div>

    </div>

    <div class="extra">
        <p>Rewards: <%= (points != null ? points : 0) %></p>
        <p>Insurance: <%= discount %>%</p>
    </div>

    <div class="input-box">
        <form action="calculate" method="post">
            <input type="number" name="steps" placeholder="Enter Steps" required>
            <br>
            <button class="calc-btn">Calculate</button>
        </form>

        <form action="simulate" method="get">
            <button class="sim-btn">Simulate</button>
        </form>
    </div>

    <% if(stepsVal != null) { %>

    <form action="save" method="post">
        <input type="hidden" name="steps" value="<%= stepsVal %>">
        <input type="hidden" name="calories" value="<%= calVal %>">
        <input type="hidden" name="distance" value="<%= distVal %>">

        <button class="save-btn">Save</button>
    </form>

    <% } %>

    <a href="logout" class="logout-btn">Logout</a>

</div>

</body>
</html>