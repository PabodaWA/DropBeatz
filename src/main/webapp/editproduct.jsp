<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>DropBeatZ - Edit Product</title>
    <link rel="stylesheet" href="artist.css">
    <style>
        .form-container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            position: relative;
            z-index: 111;
        }
        .form-group {
            margin-bottom: 20px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
            color: #fff;
        }
        .form-container .form-control {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            background-color: #fff;
            color: #000;
            opacity: 1;
            pointer-events: auto;
        }
        .form-container .form-control:focus {
            border-color: #80bdff;
            outline: 0;
            box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
        }
        .form-container textarea.form-control {
            min-height: 100px;
            resize: vertical;
        }
        .form-container input[type="file"].form-control {
            padding: 8px;
            background-color: #fff;
            color: #000;
        }
        .form-footer {
            display: flex;
            justify-content: space-between;
            margin-top: 30px;
        }
        .error-message {
            color: #dc3545;
            background-color: #f8d7da;
            padding: 10px;
            margin-bottom: 20px;
            border-radius: 4px;
        }
        .current-file {
            margin-top: 5px;
            font-style: italic;
            color: #ccc;
        }
        .form-container .cta-button {
            display: inline-block;
            padding: 0.8em 2em;
            text-transform: uppercase;
            text-decoration: none;
            font-size: 1em;
            border-radius: 50px;
            cursor: pointer;
            pointer-events: auto;
            transition: 0.3s ease-in;
        }
        .form-container .cta-button.cancel {
            background-color: #6c757d;
            color: #fff;
        }
        .form-container .cta-button.submit {
            background-color: #337cf9;
            color: #fff;
        }
        .form-container .cta-button:hover {
            background-color: #fff;
            color: #337cf9;
        }
    </style>
</head>
<body>
<div class="container">
    <div class="berth"></div>
    <nav>
        <a href="/"><b>Drop</b>BeatZ</a>
        <ul>
            <li><a href="/">Home</a></li>
            <li><a href="/songs">Songs</a></li>
            <li><a href="/albums">Albums</a></li>
            <li><a href="/about">About</a></li>
        </ul>
        <a href="#" class="login"><i class="uil uil-user"></i>Logout</a>
    </nav>
    
    <div class="form-container">
        <h2>Edit Product</h2>
        
        <% 
        // Display error message if available
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null && !errorMessage.isEmpty()) {
            out.println("<div class='error-message'>" + errorMessage + "</div>");
        }
        
        // Get product details from request attributes
        Integer productId = (Integer) request.getAttribute("productId");
        String productName = (String) request.getAttribute("productName");
        String price = (String) request.getAttribute("price");
        String description = (String) request.getAttribute("description");
        String link = (String) request.getAttribute("link");
        
        // Default values if attributes are not set
        if (productId == null) productId = 0;
        if (productName == null) productName = "";
        if (price == null) price = "";
        if (description == null) description = "";
        if (link == null) link = "";
        %>
        
        <form id="editProductForm" action="UpdateProduct" method="post" enctype="multipart/form-data">
            <input type="hidden" name="productId" value="<%= productId %>">
            <input type="hidden" name="currentLink" value="<%= link %>">
            
            <div class="form-group">
                <label for="productName">Product Name *</label>
                <input type="text" class="form-control" id="productName" name="productName" value="<%= productName %>" required>
            </div>
            
            <div class="form-group">
                <label for="price">Price ($) *</label>
                <input type="text" class="form-control" id="price" name="price" value="<%= price %>" required>
            </div>
            
            <div class="form-group">
                <label for="description">Description</label>
                <textarea class="form-control" id="description" name="description"><%= description %></textarea>
            </div>
            
            <div class="form-group">
                <label for="audioFile">Audio File (WAV, FLAC, ALAC, MQA - Max 50MB)</label>
                <input type="file" class="form-control" id="audioFile" name="audioFile" accept=".wav,.flac,.alac,.mqa">
                <% if (link != null && !link.isEmpty()) { %>
                    <div class="current-file">Current file: <%= link.substring(link.lastIndexOf('/') + 1) %></div>
                    <small>Leave empty to keep current file</small>
                <% } %>
            </div>
            
            <div class="form-footer">
                <a href="artist.jsp" class="cta-button cancel">Cancel</a>
                <button type="submit" class="cta-button submit">Update Product</button>
            </div>
        </form>
    </div>
</div>

<script>
document.addEventListener('DOMContentLoaded', function() {
    // Ensure form controls are interactive
    const formControls = document.querySelectorAll('.form-control');
    formControls.forEach(control => {
        control.disabled = false;
        control.readOnly = false;
        control.style.pointerEvents = 'auto';
        control.style.opacity = '1';
    });

    // Ensure buttons are clickable
    const buttons = document.querySelectorAll('button, .cta-button');
    buttons.forEach(button => {
        button.disabled = false;
        button.style.pointerEvents = 'auto';
    });

    // Basic form validation
    const form = document.getElementById('editProductForm');
    form.addEventListener('submit', function(event) {
        const productName = document.getElementById('productName').value.trim();
        const price = document.getElementById('price').value.trim();
        
        if (!productName) {
            event.preventDefault();
            alert('Product name is required.');
            return;
        }
        
        if (!price || isNaN(price) || parseFloat(price) <= 0) {
            event.preventDefault();
            alert('Please enter a valid price greater than zero.');
            return;
        }
    });
});
</script>
</body>
</html>