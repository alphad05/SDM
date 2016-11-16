import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class TestSQL {

	public static void main(String[] args) {
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://192.168.239.128:3306/schoolmate"; //database
        String user = "root"; //username
        String password = "Ou1smane"; //password
        
        
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected");
            st = con.createStatement();
            rs = st.executeQuery("Select * from students");
            //rs = st.executeQuery("SELECT VERSION()"); //query: Select userid, filename from healthFiles where secpolicy like 'atts';
            //ArrayList<String[]> fi = new ArrayList<String[]>(); //stores the userid and file name
            while (rs.next()) {
                //String[] uidFile = new String[2];
                String fname = rs.getString("fname");
                String lname = rs.getString("lname");
                System.out.println(fname+" "+lname);
                //fi.add(uidFile);
            }

        } catch (SQLException ex) {
        	System.out.println(ex);
        }

	}

}
