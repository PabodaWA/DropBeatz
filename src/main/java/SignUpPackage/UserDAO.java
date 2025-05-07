package SignUpPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
	
	private Connection connection;
	
	public UserDAO() {
        this.connection = DBConnection.getConnection();
    }
    
    public User checkLogin(String username, String password) {
        User user = null;
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, password);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String userType = resultSet.getString("user_type");
                
                // Create specific user type based on user_type column
                if ("admin".equals(userType)) {
                    String adminRole = getAdminRole(id);
                    user = new Admin(id, username, password, email, adminRole);
                } else if ("artist".equals(userType)) {
                    String[] artistDetails = getArtistDetails(id);
                    user = new Artist(id, username, password, email, artistDetails[0], artistDetails[1]);
                } else {
                    user = new User(id, username, password, email, userType);
                }
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return user;
    }
    
    private String getAdminRole(int userId) {
        String role = "";
        String sql = "SELECT admin_role FROM admin WHERE user_id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                role = resultSet.getString("admin_role");
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return role;
    }
    
    private String[] getArtistDetails(int userId) {
        String[] details = new String[2]; // [0] = artistName, [1] = bio
        String sql = "SELECT artist_name, bio FROM artist WHERE user_id = ?";
        
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, userId);
            
            ResultSet resultSet = statement.executeQuery();
            
            if (resultSet.next()) {
                details[0] = resultSet.getString("artist_name");
                details[1] = resultSet.getString("bio");
            }
            
            resultSet.close();
            statement.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return details;
    }
}



