<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Manage Packages - DropBeatZ</title>
    <link rel="stylesheet" href="css/packages.css">
</head>
<body>
    <!-- Dynamic Background -->
    <div class="berth"></div>

    <!-- Navigation Bar -->
    <nav>
        <a href="index.html"><b>Drop</b>BeatZ</a>
        <ul>
            <li><a href="index.html">Home</a></li>
            <li><a href="songs.html">Songs</a></li>
            <li><a href="albums.html">Albums</a></li>
            <li><a href="about.html">About</a></li>
            <li><a href="packages" class="active">Packages</a></li>
        </ul>
        <a href="logout" class="login">Logout</a>
    </nav>

    <!-- Main Content -->
    <div class="container">
        <div class="card">
            <h2 class="text-center">Manage Packages</h2>

            <!-- Success/Error Messages -->
            <c:if test="${not empty message}">
                <div class="alert alert-success">
                    <h5>${message}</h5>
                </div>
            </c:if>
            <c:if test="${not empty error}">
                <div class="alert alert-danger">
                    <h5>${error}</h5>
                </div>
            </c:if>

            <!-- Add Package Button -->
            <button class="btn btn-primary" onclick="openModal('addPackageModal')">Add New Package</button>

            <!-- Packages Table -->
            <div class="table-responsive">
                <table class="table">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Price ($)</th>
                            <th>Discount (%)</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="pkg" items="${packages}">
                            <tr>
                                <td>${pkg.id}</td>
                                <td>${pkg.name}</td>
                                <td>${pkg.price}</td>
                                <td>${pkg.discount}</td>
                                <td>
                                    <button class="btn btn-warning btn-sm" 
                                            onclick="setEditModal(${pkg.id}, '${pkg.name.replace("'", "\\'")}', ${pkg.price}, ${pkg.discount}); openModal('editPackageModal')">
                                        Edit
                                    </button>
                                    <form action="packages" method="post" style="display:inline;" 
                                          onsubmit="return confirm('Are you sure you want to delete package ${pkg.name.replace("'", "\\'")}?');">
                                        <input type="hidden" name="action" value="delete">
                                        <input type="hidden" name="id" value="${pkg.id}">
                                        <button type="submit" class="btn btn-danger btn-sm">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

    <!-- Add Package Modal -->
    <div class="modal" id="addPackageModal">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Add Package</h5>
                <button class="btn-close" onclick="closeModal('addPackageModal')">&times;</button>
            </div>
            <form action="packages" method="post" class="needs-validation" onsubmit="return validateForm(this)">
                <div class="modal-body">
                    <input type="hidden" name="action" value="create">
                    <div class="form-group">
                        <label for="addName">Name</label>
                        <input type="text" class="form-control" id="addName" name="name" required>
                        <div class="invalid-feedback">Please enter a package name.</div>
                    </div>
                    <div class="form-group">
                        <label for="addPrice">Price ($)</label>
                        <input type="number" step="0.01" min="0" class="form-control" id="addPrice" name="price" required>
                        <div class="invalid-feedback">Please enter a valid price (non-negative).</div>
                    </div>
                    <div class="form-group">
                        <label for="addDiscount">Discount (%)</label>
                        <input type="number" step="0.01" min="0" max="100" class="form-control" id="addDiscount" name="discount" value="0" required>
                        <div class="invalid-feedback">Please enter a discount between 0 and 100.</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeModal('addPackageModal')">Close</button>
                    <button type="submit" class="btn btn-primary">Add Package</button>
                </div>
            </form>
        </div>
    </div>

    <!-- Edit Package Modal -->
    <div class="modal" id="editPackageModal">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Edit Package</h5>
                <button class="btn-close" onclick="closeModal('editPackageModal')">&times;</button>
            </div>
            <form action="packages" method="post" class="needs-validation" onsubmit="return validateForm(this)">
                <div class="modal-body">
                    <input type="hidden" name="action" value="update">
                    <input type="hidden" name="id" id="editId">
                    <div class="form-group">
                        <label for="editName">Name</label>
                        <input type="text" class="form-control" id="editName" name="name" required>
                        <div class="invalid-feedback">Please enter a package name.</div>
                    </div>
                    <div class="form-group">
                        <label for="editPrice">Price ($)</label>
                        <input type="number" step="0.01" min="0" class="form-control" id="editPrice" name="price" required>
                        <div class="invalid-feedback">Please enter a valid price (non-negative).</div>
                    </div>
                    <div class="form-group">
                        <label for="editDiscount">Discount (%)</label>
                        <input type="number" step="0.01" min="0" max="100" class="form-control" id="editDiscount" name="discount" value="0" required>
                        <div class="invalid-feedback">Please enter a discount between 0 and 100.</div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" onclick="closeModal('editPackageModal')">Close</button>
                    <button type="submit" class="btn btn-primary">Update Package</button>
                </div>
            </form>
        </div>
    </div>

    <script>
        // Modal control
        function openModal(modalId) {
            document.getElementById(modalId).classList.add('show');
        }

        function closeModal(modalId) {
            document.getElementById(modalId).classList.remove('show');
        }

        // Form validation
        function validateForm(form) {
            let isValid = true;
            const inputs = form.querySelectorAll('input[required]');
            inputs.forEach(input => {
                if (!input.value.trim()) {
                    input.classList.add('invalid');
                    isValid = false;
                } else {
                    input.classList.remove('invalid');
                }
                if (input.type === 'number') {
                    const value = parseFloat(input.value);
                    if (input.name === 'price' && value < 0) {
                        input.classList.add('invalid');
                        isValid = false;
                    }
                    if (input.name === 'discount' && (value < 0 || value > 100)) {
                        input.classList.add('invalid');
                        isValid = false;
                    }
                }
            });
            return isValid;
        }

        // Set edit modal values
        function setEditModal(id, name, price, discount) {
            document.getElementById('editId').value = id;
            document.getElementById('editName').value = name;
            document.getElementById('editPrice').value = price;
            document.getElementById('editDiscount').value = discount;
        }
    </script>
</body>
</html>