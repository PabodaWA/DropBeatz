<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Songs - DropBeatZ</title>
    <link rel="stylesheet" href="./CSS/Songs.css">
</head>
<body>
    <div class="container">
        <nav>
            <a href="index.jsp"><b>Drop</b>BeatZ</a>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="songs.jsp">Songs</a></li>
                <li><a href="#">Albums</a></li>
                <li><a href="#">About</a></li>
            </ul>
            <a href="profile.jsp" class="login"><i class="uil uil-user"></i>Profile</a>
        </nav>
        <div class="content-box">
            <!-- Empty content-box for spacing -->
        </div>
        <div class="additional-content">
            <c:choose>
                <c:when test="${not empty songs}">
                    <c:forEach var="song" items="${songs}">
                        <div class="song">
                            <h3>${song.title}</h3>
                            <p>Artist: ${song.artistName}</p>
                            <p>Album: ${not empty song.album ? song.album : 'N/A'}</p>
                            <p>Duration: ${not empty song.duration ? song.duration : 'N/A'}</p>
                            <p>Release Date: ${not empty song.releaseDate ? song.releaseDate : 'N/A'}</p>
                        </div>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <p>No songs found.</p>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</body>
</html>