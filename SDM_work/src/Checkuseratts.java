

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cpabe.AbeDecryptionException;
import cpabe.Cpabe;

/**
 * Servlet implementation class Checkuseratts
 */
@WebServlet("/Checkuseratts")
/*
 * This was a test servlet mimicking user decrypting the encrypted file
 * created in the GetPublicKey servlet.
 */
public class Checkuseratts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Checkuseratts() {
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
		String path = (String)request.getAttribute("pathname");
		String fname = (String)request.getAttribute("filename");
		System.out.println("The path is:"+path+"/"+fname);
		File f = new File(path+"/"+fname);
		File pk = new File(path+"/prk.private");
		File decryptedFile = new File(path+"/encrypted-output2.dec");
		 try {
			Cpabe.decrypt(pk, f, decryptedFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Scanner input = new Scanner(new File(path+"/encrypted-output2.dec"));
		 String ans = "";
		 while(input.hasNextLine()) {
			 ans = input.nextLine();
		 }
		 System.out.println(ans);
		 request.setAttribute("answer", ans);
		 request.getRequestDispatcher("/checkAnswer.jsp").forward(request,response);
	}

}
