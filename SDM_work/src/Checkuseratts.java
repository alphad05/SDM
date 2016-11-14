

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
		String fname = request.getParameter("pathname");
		File f = new File(fname);
		File pk = new File("prk.private");
		File decryptedFile = new File("encrypted-output2.dec");
		 try {
			Cpabe.decrypt(pk, f, decryptedFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 Scanner input = new Scanner(new File(fname));
		 String ans = "";
		 while(input.hasNextLine()) {
			 ans = input.nextLine();
		 }
		 request.setAttribute("answer", ans);
		 request.getRequestDispatcher("/SDM_work/checkAnswer.jsp").forward(request,response);
	}

}
