<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Start page</title>
    <style>
        .container {
            display: flex;
            justify-content: space-around;
        }

        a {
            text-decoration: none;
            color: black;
        }

        a:hover {
            color: crimson;
        }
    </style>
</head>
<body>
<form method="get">
    <div class="container">
        <div>
            <h3>CAR SHOWROOM CRUD</h3>
            <div><a href="car-showroom/create">CREATE CAR SHOWROOM</a></div>
            <div><a href="car-showroom/read">READ CAR SHOWROOMS</a></div>
            <div><a href="car-showroom/update">UPDATE CAR SHOWROOM</a></div>
            <div><a href="car-showroom/delete">DELETE CAR SHOWROOM</a></div>
        </div>
        <div>
            <h3>CATEGORY CRUD</h3>
            <div><a href="category/create">CREATE CATEGORY</a></div>
            <div><a href="category/read">READ CATEGORIES</a></div>
            <div><a href="category/update">UPDATE CATEGORY</a></div>
            <div><a href="category/delete">DELETE CATEGORY</a></div>

        </div>
        <div>
            <h3>CAR CRUD</h3>
            <div><a href="car/create">CREATE CAR</a></div>
            <div><a href="car/read">READ CARS</a></div>
            <div><a href="car/update">UPDATE CAR</a></div>
            <div><a href="car/delete">DELETE CAR</a></div>
        </div>
        <div>
            <h3>CLIENT CRUD</h3>
            <div><a href="client/create">CREATE CLIENT</a></div>
            <div><a href="client/read">READ CLIENTS</a></div>
            <div><a href="client/update">UPDATE CLIENT</a></div>
            <div><a href="client/delete">DELETE CLIENT</a></div>
        </div>
        <div>
            <h3>REVIEW CRUD</h3>
            <div><a href="review/create">CREATE REVIEW</a></div>
            <div><a href="review/read">READ REVIEWS</a></div>
            <div><a href="review/update">UPDATE REVIEW</a></div>
            <div><a href="review/delete">DELETE REVIEW</a></div>
        </div>
    </div>
</form>
</body>
</html>