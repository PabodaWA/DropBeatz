<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./CSS/AdminDashboard2.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
<title>Admin Dashboard</title>
</head>
<body>

  <header>
  
  <div class="header">
            <h1>Admin Dashboard</h1>
</div>
    
  </header>

  <!-- MAIN CONTAINER -->
  <div class="container">
    <%
    if (session.getAttribute("username") == null) {
        response.sendRedirect("Signin.jsp");
        return;
    }
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css">
<link rel="stylesheet" href="./CSS/NewAdmin.css">
<title>Admin Dashboard</title>
</head>
<body>

<div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <div class="logo">
                <img src="Pictures/logo.png" alt="DropBeatz Logo">
                <h1>Drop<span>Beatz</span></h1>
            </div>
            
            <div class="nav-section">
                <h3>Main</h3>
                <a href="admindashboardjsp.jsp" class="nav-item active">
                    <i class="fas fa-home"></i>
                    <span>Dashboard</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Management</h3>
                <a href="AdminProducts.jsp" class="nav-item">
                    <i class="fas fa-music"></i>
                    <span>Products</span>
                </a>
                <a href="AdminArtists.jsp" class="nav-item">
                    <i class="fas fa-user-friends"></i>
                    <span>Artists</span>
                </a>
                <a href="AdminUser.jsp" class="nav-item">
                    <i class="fas fa-users"></i>
                    <span>Users</span>
                </a>
                <a href="AdminMessages.jsp" class="nav-item">
                    <i class="fas fa-envelope"></i>
                    <span>Messages</span>
                </a>
            </div>
            
            <div class="nav-section">
                <h3>Settings</h3>
                <a href="profile.jsp" class="nav-item">
                    <i class="fas fa-user-cog"></i>
                    <span>Profile</span>
                </a>

                <a href="adminlogout.jsp" class="nav-item">
                    <i class="fas fa-sign-out-alt"></i>
                    <span>Logout</span>
                </a>
            </div>
        </div>
        
        <!-- Main Content -->
        <div class="main-content">
            <div class="header">
                <h2>Admin Dashboard</h2>
                
                <div class="user-info">
                    <div class="search-bar">
                        <i class="fas fa-search" style="color: #777;"></i>
                        <input type="text" placeholder="Search...">
                    </div>
                    
                    <div class="notification">
                        <i class="fas fa-bell"></i>
                        <span class="badge"></span>
                    </div>
                    
                    <div class="notification">
                        <i class="fas fa-envelope"></i>
                        <span class="badge"></span>
                    </div>
                    
                    <div class="user-profile">
    <img src="/api/placeholder/35/35" alt="Admin Profile">
    <span><%= session.getAttribute("username") %></span>
</div>
                </div>
            </div>
            
            <!-- Admin List Table -->
            <div class="table-container">
                <div class="table-header">
                    <h3>Admin List</h3>
                    <button class="add-button">
                        <i class="fas fa-plus"></i> Add Admin
                    </button>
                </div>
                
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Username</th>
                            <th>Email</th>
                            <th>Role</th>
                            <th>Status</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>admin</td>
                            <td>admin@dropbeatz.com</td>
                            <td>Super Admin</td>
                            <td><span class="status active">Active</span></td>
                            <td>
                                <button class="action-button edit-button">Edit</button>
                                <button class="action-button delete-button">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>manager</td>
                            <td>manager@dropbeatz.com</td>
                            <td>Content Manager</td>
                            <td><span class="status active">Active</span></td>
                            <td>
                                <button class="action-button edit-button">Edit</button>
                                <button class="action-button delete-button">Delete</button>
                            </td>
                        </tr>
                        <tr>
                            <td>3</td>
                            <td>editor</td>
                            <td>editor@dropbeatz.com</td>
                            <td>Editor</td>
                            <td><span class="status inactive">Inactive</span></td>
                            <td>
                                <button class="action-button edit-button">Edit</button>
                                <button class="action-button delete-button">Delete</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            
            <!-- Stats Cards -->
            <div style="display: grid; grid-template-columns: repeat(4, 1fr); gap: 20px; margin-bottom: 30px;">
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">Total Products</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">254</h3>
                        </div>
                        <div style="background-color: rgba(233, 30, 99, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-music" style="color: #e91e63; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #2ecc71; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-up"></i> 12.5% from last month
                    </p>
                </div>
                
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">Total Artists</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">75</h3>
                        </div>
                        <div style="background-color: rgba(33, 150, 243, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-user-friends" style="color: #2196f3; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #2ecc71; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-up"></i> 5.3% from last month
                    </p>
                </div>
                
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">Total Users</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">3,521</h3>
                        </div>
                        <div style="background-color: rgba(46, 204, 113, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-users" style="color: #2ecc71; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #2ecc71; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-up"></i> 8.7% from last month
                    </p>
                </div>
                
                <div style="background-color: #222; padding: 20px; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2);">
                    <div style="display: flex; justify-content: space-between; align-items: center;">
                        <div>
                            <p style="color: #999; font-size: 14px;">New Messages</p>
                            <h3 style="font-size: 24px; margin-top: 5px;">28</h3>
                        </div>
                        <div style="background-color: rgba(233, 30, 99, 0.2); padding: 10px; border-radius: 50%;">
                            <i class="fas fa-envelope" style="color: #e91e63; font-size: 20px;"></i>
                        </div>
                    </div>
                    <p style="color: #e74c3c; font-size: 12px; margin-top: 10px;">
                        <i class="fas fa-arrow-down"></i> 2.1% from last month
                    </p>
                </div>
            </div>
            
            <footer>
                <p>Copyright © DropBeatz 2025. All rights reserved.</p>
            </footer>
        </div>
    </div>

</body>
</html>

    <!-- LEFT SIDEBAR MENU -->
<nav class="sidebar">
  <ul>
    <li><a href="AdminOrders.jsp">Orders</a></li>
    <li><a href="AdminProducts.jsp">Products</a></li>
    <li><a href="AdminMessages.jsp">Messages</a></li>
    <li><a href="AdminArtist.jsp">Artist Control</a></li>
    <li><a href="AdminUser.jsp">User Control</a></li>
    <li><a href="Admin.jsp">Admin Control</a></li>
  </ul>
</nav>

<div class="dashboard">
            <!-- Stats Cards -->
            <div class="stat-cards">
                <div class="stat-card">
                    <div class="stat-icon">
                        <i class="fas fa-chart-line"></i>
                    </div>
                    <div class="stat-info">
                        <div class="stat-label">Today Sales</div>
                        <div class="stat-value">$1,234</div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon">
                        <i class="fas fa-chart-bar"></i>
                    </div>
                    <div class="stat-info">
                        <div class="stat-label">Total Sales</div>
                        <div class="stat-value">$87,432</div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon">
                        <i class="fas fa-download"></i>
                    </div>
                    <div class="stat-info">
                        <div class="stat-label">Total Downloads</div>
                        <div class="stat-value">12,543</div>
                    </div>
                </div>
                
                <div class="stat-card">
                    <div class="stat-icon">
                        <i class="fas fa-pie-chart"></i>
                    </div>
                    <div class="stat-info">
                        <div class="stat-label">Active Products</div>
                        <div class="stat-value">432</div>
                    </div>
                </div>
            </div>
            
            <!-- Charts Row -->
            <div class="charts-row">
                <div class="chart-card">
                    <div class="chart-header">
                        <h3 class="chart-title">Worldwide Sales</h3>
                        <a href="#" class="show-all">Show All</a>
                    </div>
                    <div class="chart-container">
                        <canvas id="worldwideSalesChart"></canvas>
                    </div>
                </div>
                
                <div class="chart-card">
                    <div class="chart-header">
                        <h3 class="chart-title">Sales & Revenue</h3>
                        <a href="#" class="show-all">Show All</a>
                    </div>
                    <div class="chart-container">
                        <canvas id="salesRevenueChart"></canvas>
                    </div>
                </div>
            </div>
            
            <!-- Recent Table -->
            <div class="recent-table">
                <div class="chart-header">
                    <h3 class="chart-title">Recent Sales</h3>
                    <a href="#" class="show-all">Show All</a>
                </div>
                
                <table>
                    <thead>
                        <tr>
                            <th></th>
                            <th>Date</th>
                            <th>Invoice</th>
                            <th>Customer</th>
                            <th>Amount</th>
                            <th>Status</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>08 May 2025</td>
                            <td>INV-0123</td>
                            <td>John Smith</td>
                            <td>$123</td>
                            <td><span class="status-badge status-paid">Paid</span></td>
                            <td><button class="action-btn-sm">Detail</button></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>07 May 2025</td>
                            <td>INV-0122</td>
                            <td>Lisa Johnson</td>
                            <td>$239</td>
                            <td><span class="status-badge status-paid">Paid</span></td>
                            <td><button class="action-btn-sm">Detail</button></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox"></td>
                            <td>07 May 2025</td>
                            <td>INV-0121</td>
                            <td>Michael Rodriguez</td>
                            <td>$99</td>
                            <td><span class="status-badge status-paid">Paid</span></td>
                            <td><button class="action-btn-sm">Detail</button></td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    

  </div>
  
   <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.9.1/chart.min.js"></script>
    <script>
        // Charts setup
        document.addEventListener('DOMContentLoaded', function() {
            // Worldwide Sales Chart
            const worldwideSalesCtx = document.getElementById('worldwideSalesChart').getContext('2d');
            const worldwideSalesChart = new Chart(worldwideSalesCtx, {
                type: 'bar',
                data: {
                    labels: ['2016', '2017', '2018', '2019', '2020', '2021', '2022'],
                    datasets: [
                        {
                            label: 'USA',
                            data: [15, 30, 55, 65, 60, 80, 95],
                            backgroundColor: '#ff3b30',
                        },
                        {
                            label: 'UK',
                            data: [8, 35, 40, 60, 70, 55, 75],
                            backgroundColor: '#ff9500',
                        },
                        {
                            label: 'AU',
                            data: [12, 25, 45, 55, 65, 70, 60],
                            backgroundColor: '#ff2d55',
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            beginAtZero: true,
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            },
                            ticks: {
                                color: '#aaa'
                            }
                        },
                        x: {
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            },
                            ticks: {
                                color: '#aaa'
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                color: '#aaa'
                            }
                        }
                    }
                }
            });
            
            // Sales & Revenue Chart
            const salesRevenueCtx = document.getElementById('salesRevenueChart').getContext('2d');
            const salesRevenueChart = new Chart(salesRevenueCtx, {
                type: 'line',
                data: {
                    labels: ['2016', '2017', '2018', '2019', '2020', '2021', '2022'],
                    datasets: [
                        {
                            label: 'Sales',
                            data: [15, 40, 55, 40, 70, 65, 85],
                            fill: true,
                            backgroundColor: 'rgba(255, 59, 48, 0.3)',
                            borderColor: '#ff3b30',
                            tension: 0.4
                        },
                        {
                            label: 'Revenue',
                            data: [100, 120, 170, 130, 190, 180, 270],
                            borderColor: '#ffffff',
                            tension: 0.4
                        }
                    ]
                },
                options: {
                    responsive: true,
                    maintainAspectRatio: false,
                    scales: {
                        y: {
                            beginAtZero: true,
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            },
                            ticks: {
                                color: '#aaa'
                            }
                        },
                        x: {
                            grid: {
                                color: 'rgba(255, 255, 255, 0.1)'
                            },
                            ticks: {
                                color: '#aaa'
                            }
                        }
                    },
                    plugins: {
                        legend: {
                            labels: {
                                color: '#aaa'
                            }
                        }
                    }
                }
            });
        });
    </script>

</body>
</html>