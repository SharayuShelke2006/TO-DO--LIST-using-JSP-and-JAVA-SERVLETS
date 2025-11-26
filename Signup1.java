import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Signup1 extends HttpServlet{
    public  void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        

        String url = "jdbc:mysql://localhost:3306/to_do_list"; 
        String username = "root"; 
        String password = ""; 
        String uname=request.getParameter("uname");
        String pass=request.getParameter("pass");
        String cpass=request.getParameter("cpass");
        
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected to the database successfully!");
        } catch (Exception e) {
            System.out.println("Connection failed!");
            e.printStackTrace();
        }

        if(pass.equals(cpass)){
          
            try {
                PreparedStatement ps = connection.prepareStatement("INSERT INTO users1 (uname, pass) VALUES (?, ?)");
                ps.setString(1, uname);  
                ps.setString(2, pass);  
                ps.executeUpdate();
                response.sendRedirect("index.html");
            } catch (SQLException e) {
                e.printStackTrace(); 
            }
            

        }
    }
}
