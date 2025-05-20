<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    session.invalidate(); // Destroy the session
    response.sendRedirect("loging.jsp"); // Redirect to login page
%>
