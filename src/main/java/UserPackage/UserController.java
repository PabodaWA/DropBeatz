package UserPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import AdminProduct.AdminProductDBconection;

public class UserController {

    private static Connection con = null;
    private static Statement stmt = null;
    private static ResultSet rs = null;

    // login validate
    public static List<UserModel> loginValidate(String username, String password) {

        ArrayList<UserModel> user = new ArrayList<>();

        try {
            // DB connection
            con = AdminProductDBconection.getConnection();
            stmt = con.createStatement();

            String sql = "SELECT * FROM user WHERE username ='" + username + "' AND password='" + password + "'";
            rs = stmt.executeQuery(sql); 

            if (rs.next()) {
                int id = rs.getInt(1);
                String fullname = rs.getString(2);
                String Username = rs.getString(3);
                String country = rs.getString(4);
                String contactnimber = rs.getString(5);
                String email = rs.getString(6);
                String Password = rs.getString(7);
               
               

                UserModel u = new UserModel(id, fullname, Username, country, contactnimber, Password, email);
                user.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user; 
    }
}
