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