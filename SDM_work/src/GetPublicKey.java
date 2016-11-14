

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpabe.Cpabe;

/**
 * Servlet implementation class GetPublicKey
 */
@WebServlet("/GetPublicKey")
public class GetPublicKey extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetPublicKey() {
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
		String userid = request.getParameter("userid");
		//Random r = new Random();
		//int val = r.nextInt(50000);
		String policy = "patient_write or hospital_write or healthclub_write"; //can write policy
		File writeFile = new File("writefile_norm.txt"); //file to encrypt with write policies
		if(!writeFile.exists()) {
			//file doesn't exist so create it and write to it
			writeFile.createNewFile();
			FileWriter fw = new FileWriter(writeFile.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write("this is a test");
			bw.close();
		}
		String servpath = getServletContext().getRealPath("/WEB-INF/files/");
		System.out.println(servpath);
		File decFile = new File(servpath+"/writefile_dec.txt"); //encrypted file
		//Cpabe enc = new Cpabe();
		File pubkey = new File(servpath+"/pk.pkey"); //public key file
		try {
			//Cpabe enc = new Cpabe();
			Cpabe.encrypt(pubkey, policy, writeFile, decFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String decFilePath = decFile.getAbsolutePath();
		System.out.println(decFilePath);
		request.setAttribute("pathname", decFilePath);
		//Send path to user and they have to decrypt the file and send back contents of file
		request.getRequestDispatcher("/Checkuseratts").forward(request,response);
		//request.getRequestDispatcher("/dictgloss/DictGlossGame.jsp").forward(request,response);
	}

}
