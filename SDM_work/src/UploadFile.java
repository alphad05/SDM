

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import java.text.DateFormat;
import java.text.SimpleDateFormat;


/**
 * Servlet implementation class UploadFile
 */
@WebServlet("/UploadFile")
@MultipartConfig
/*
 * This servlet is for uploading a user selected file to a server. The file name is also stored in the 
 * CloudDatabase database
 */
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String servpath = getServletContext().getRealPath("/WEB-INF/database_files/");
		
		Part filePart = request.getPart("file");
		String heads = filePart.getHeader("content-disposition");
		//System.out.println("content "+heads); // prints this 'form-data; name="file"; filename="MultipartConfig.docx"'
		String[] sHeads = heads.split(";");
		String fname = ""; //name of file that is uploaded
		for(String header: sHeads) {
			//System.out.println(header);
			if(header.trim().startsWith("filename")) {
				fname = header.substring(header.indexOf("=")+2, header.length()-1);
			}
		}
		String filePath = servpath+File.separator+fname;
		System.out.println("filepath: "+filePath);
		filePart.write(filePath);
		SQLInsert(3, filePath, "att2, att3, att6"); //need to change to SQLInsert(userid, filePath, secpolicy)
		request.setAttribute("uploadname", fname);
		request.getRequestDispatcher("/uploadSuccess.jsp").forward(request, response);

	}
	
	private void SQLInsert(int userid, String filePath, String secpolicy) {
		Connection con = null;
        Statement st = null;
        ResultSet rs = null;

        String url = "jdbc:mysql://192.168.239.128:3306/CloudDatabase"; //database
        String user = ""; //username
        String password = ""; //password
        
        /*example insert
         * INSERT INTO Customers (CustomerName, ContactName, Address, City, PostalCode, Country)
			VALUES ('Cardinal','Tom B. Erichsen','Skagen 21','Stavanger','4006','Norway');
         */
        DateFormat dform = new SimpleDateFormat("yyyy-MM-dd");
        //Date date = 
        Date date = new Date();
        //String query = "insert into healthFiles (userid, filename, fcreationdate, secpolicy) values ('"+userid+"', '"+filePath+"', '"+dform.format(date)+"', '"+secpolicy+"')";
        String query = "insert into healthFiles (userid, filename, fcreationdate, secpolicy) values(?, ?, ?, ?)";
        System.out.println(query);
        
        try {
            con = DriverManager.getConnection(url, user, password);
            System.out.println("Database connected");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, userid);
            ps.setString(2, filePath);
            ps.setString(3, dform.format(date));
            ps.setString(4, secpolicy);
            ps.executeUpdate();
            //st = con.createStatement();
            //rs = st.executeQuery(query);

        } catch (SQLException ex) {
        	System.out.println(ex);
        }
	}

}
