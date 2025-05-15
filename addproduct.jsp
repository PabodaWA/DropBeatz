<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Music Product Management</title>
    <style>
        :root {
            --primary-color: #3498db;
            --secondary-color: #2ecc71;
            --error-color: #e74c3c;
            --text-color: #333;
            --light-bg: #f9f9f9;
        }

        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: var(--light-bg);
            color: var(--text-color);
            line-height: 1.6;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            background: white;
            padding: 30px;
            border-radius: 8px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
            color: var(--primary-color);
            position: relative;
            padding-bottom: 15px;
        }

        h1::after {
            content: '';
            position: absolute;
            width: 100px;
            height: 3px;
            background: var(--secondary-color);
            bottom: 0;
            left: 50%;
            transform: translateX(-50%);
        }

        .form-group {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 8px;
            font-weight: 600;
        }

        input[type="text"],
        input[type="number"],
        textarea,
        select {
            width: 100%;
            padding: 12px;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 16px;
            transition: all 0.3s;
        }

        input[type="file"] {
            padding: 10px 0;
        }

        input:focus,
        textarea:focus {
            outline: none;
            border-color: var(--primary-color);
            box-shadow: 0 0 5px rgba(52, 152, 219, 0.5);
        }

        textarea {
            resize: vertical;
            min-height: 100px;
        }

        .file-input-wrapper {
            position: relative;
            overflow: hidden;
            display: inline-block;
            width: 100%;
        }

        .file-input-wrapper input[type=file] {
            font-size: 100px;
            position: absolute;
            left: 0;
            top: 0;
            opacity: 0;
            cursor: pointer;
        }

        .file-input-button {
            display: inline-block;
            background-color: var(--primary-color);
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s;
        }

        .file-input-button:hover {
            background-color: #2980b9;
        }

        .file-name {
            margin-left: 10px;
            font-style: italic;
            color: #666;
        }

        .error-message {
            color: var(--error-color);
            font-size: 14px;
            margin-top: 5px;
            display: none;
        }

        .global-error {
            background-color: #f8d7da;
            color: var(--error-color);
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            display: none;
        }

        button, input[type="submit"] {
            background-color: var(--secondary-color);
            color: white;
            border: none;
            padding: 12px 25px;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            display: block;
            width: 100%;
            text-transform: uppercase;
            letter-spacing: 1px;
            transition: all 0.3s;
        }

        button:hover, input[type="submit"]:hover {
            background-color: #27ae60;
            transform: translateY(-2px);
            box-shadow: 0 5px 15px rgba(0, 0, 0, 0.1);
        }

        .success-message {
            background-color: #d4edda;
            color: #155724;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            display: <%= session.getAttribute("successMessage") != null ? "block" : "none" %>;
        }

        .file-preview {
            margin-top: 10px;
            padding: 10px;
            border: 1px dashed #ddd;
            border-radius: 4px;
            display: none;
        }

        .file-preview audio {
            width: 100%;
        }

        @media (max-width: 768px) {
            .container {
                padding: 20px;
            }

            button, input[type="submit"] {
                padding: 10px 15px;
            }
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Music Product Management</h1>

        <% if (session.getAttribute("successMessage") != null) { %>
            <div class="success-message" id="successMessage">
                <%= session.getAttribute("successMessage") %>
                <% session.removeAttribute("successMessage"); %>
            </div>
        <% } %>

        <% if (request.getAttribute("errorMessage") != null) { %>
            <div class="global-error" id="globalError" style="display: block;">
                <%= request.getAttribute("errorMessage") %>
            </div>
        <% } %>

        <form action="Addproduct" method="post" enctype="multipart/form-data" id="productForm">
            <div class="form-group">
                <label for="productName">Product Name *</label>
                <input type="text" id="productName" name="productName" required>
                <div class="error-message" id="productNameError"></div>
            </div>

            <div class="form-group">
                <label for="price">Price *</label>
                <input type="number" id="price" name="price" step="0.01" min="0" required>
                <div class="error-message" id="priceError"></div>
            </div>

            <div class="form-group">
                <label for="description">Description</label>
                <textarea id="description" name="description"></textarea>
            </div>

            <div class="form-group">
                <label for="audioFile">Music File (WAV, ALAC, FLAC, MQA only, max 50MB) *</label>
                <div class="file-input-wrapper">
                    <button type="button" class="file-input-button">Choose File</button>
                    <input type="file" id="audioFile" name="audioFile" accept=".wav,.flac,.alac,.mqa" required>
                    <span class="file-name" id="fileName">No file chosen</span>
                </div>
                <div class="error-message" id="audioFileError"></div>
                <div class="file-preview" id="audioPreview">
                    <audio controls id="audioPlayer">
                        Your browser does not support the audio element.
                    </audio>
                </div>
            </div>

            <input type="submit" value="Add Product">
        </form>
    </div>

    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const form = document.getElementById('productForm');
            const productNameInput = document.getElementById('productName');
            const priceInput = document.getElementById('price');
            const audioFileInput = document.getElementById('audioFile');
            const fileNameDisplay = document.getElementById('fileName');
            const audioPreview = document.getElementById('audioPreview');
            const audioPlayer = document.getElementById('audioPlayer');
            const successMessage = document.getElementById('successMessage');
            const globalError = document.getElementById('globalError');

            // File button click handler
            document.querySelector('.file-input-button').addEventListener('click', function() {
                audioFileInput.click();
            });

            // Update file name display and validate file size/type
            audioFileInput.addEventListener('change', function() {
                if (this.files && this.files[0]) {
                    const file = this.files[0];
                    fileNameDisplay.textContent = file.name;

                    // Validate file size (50MB = 50 * 1024 * 1024 bytes)
                    const maxSize = 50 * 1024 * 1024;
                    if (file.size > maxSize) {
                        showError('audioFileError', 'File size exceeds 50MB limit');
                        audioPreview.style.display = 'none';
                        this.value = '';
                        return;
                    }

                    // Validate file extension
                    const validExtensions = ['wav', 'flac', 'alac', 'mqa'];
                    const fileExtension = file.name.split('.').pop().toLowerCase();
                    if (!validExtensions.includes(fileExtension)) {
                        showError('audioFileError', 'Please select a valid audio file (WAV, FLAC, ALAC, or MQA)');
                        audioPreview.style.display = 'none';
                        this.value = '';
                        return;
                    }

                    // Clear previous error
                    hideError('audioFileError');

                    // Show audio preview if supported
                    if (fileExtension === 'wav' || fileExtension === 'flac') {
                        const url = URL.createObjectURL(file);
                        audioPlayer.src = url;
                        audioPreview.style.display = 'block';
                    } else {
                        audioPreview.style.display = 'none';
                    }
                } else {
                    fileNameDisplay.textContent = 'No file chosen';
                    audioPreview.style.display = 'none';
                }
            });

            // Form validation
            form.addEventListener('submit', function(e) {
                let isValid = true;

                // Validate product name
                if (!productNameInput.value.trim()) {
                    showError('productNameError', 'Product name is required');
                    isValid = false;
                } else {
                    hideError('productNameError');
                }

                // Validate price
                if (!priceInput.value || parseFloat(priceInput.value) <= 0) {
                    showError('priceError', 'Please enter a valid price greater than zero');
                    isValid = false;
                } else {
                    hideError('priceError');
                }

                // Validate audio file
                if (!audioFileInput.files || !audioFileInput.files[0]) {
                    showError('audioFileError', 'Please select an audio file');
                    isValid = false;
                } else {
                    const file = audioFileInput.files[0];
                    const maxSize = 50 * 1024 * 1024;
                    if (file.size > maxSize) {
                        showError('audioFileError', 'File size exceeds 50MB limit');
                        isValid = false;
                    } else {
                        const validExtensions = ['wav', 'flac', 'alac', 'mqa'];
                        const fileExtension = file.name.split('.').pop().toLowerCase();
                        if (!validExtensions.includes(fileExtension)) {
                            showError('audioFileError', 'Please select a valid audio file (WAV, FLAC, ALAC, or MQA)');
                            isValid = false;
                        } else {
                            hideError('audioFileError');
                        }
                    }
                }

                if (!isValid) {
                    e.preventDefault();
                }
            });

            // Auto-hide messages after 5 seconds
            if (successMessage) {
                setTimeout(function() {
                    successMessage.style.display = 'none';
                }, 5000);
            }
            if (globalError && globalError.style.display === 'block') {
                setTimeout(function() {
                    globalError.style.display = 'none';
                }, 5000);
            }

            function showError(elementId, message) {
                const errorElement = document.getElementById(elementId);
                errorElement.textContent = message;
                errorElement.style.display = 'block';
            }

            function hideError(elementId) {
                const errorElement = document.getElementById(elementId);
                errorElement.style.display = 'none';
            }
        });
    </script>
</body>
</html>