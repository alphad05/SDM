

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Servlet implementation class GetAttributes
 */
@WebServlet("/GetAttributes")
public class GetAttributes extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAttributes() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String atts = request.getParameter("atts"); //pass the atts attributes to this page
		//connect to database and search for matching policy
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://localhost:3306/CloudDatabase"; //database
        String user = ""; //username
        String password = ""; //password
        
        
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected");
            st = con.createStatement();
            rs = st.executeQuery("SELECT VERSION()"); //query: Select userid, filename from healthFiles where secpolicy like 'atts';
            ArrayList<String[]> fi = new ArrayList<String[]>(); //stores the userid and file name
            while (rs.next()) {
                String[] uidFile = new String[2];
                uidFile[0] = rs.getString("userid");
                uidFile[1] = rs.getString("filename");
                //System.out.println();
                fi.add(uidFile);
            }
            request.setAttribute("fileLocs", fi);
            request.getRequestDispatcher("/Admitted.jsp").forward(request, response);

        } catch (SQLException ex) {
        	System.out.println(ex);
        }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
