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

            String sql = "SELECT * FROM users WHERE username ='" + username + "' AND password='" + password + "'";
            rs = stmt.executeQuery(sql); // <-- Corrected here

            if (rs.next()) {
                int id = rs.getInt(1);
                String Username = rs.getString(2);
                String Password = rs.getString(3);
                String email = rs.getString(4);
                String user_type = rs.getString(5);

                UserModel u = new UserModel(id, Username, Password, email, user_type);
                user.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user; // <-- Don't forget to return
    }
}
