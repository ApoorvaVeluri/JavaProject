

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class ProjIndex
 */
public class ProjIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				
		HttpSession aSession = request.getSession();
		String id=request.getParameter("val");
		aSession.setAttribute("id", id);
		
		PrintWriter pw=response.getWriter();
		if(id==null || id.trim().equals(""))
		{  
			pw.print("Please enter Customer id");  
		}
		else

		{

			try{  
				Class.forName("oracle.jdbc.OracleDriver");  
				Connection con=   DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
				PreparedStatement ps =   con.prepareStatement("select c.*, p.Name from Customer c inner join Products p on c.SERVICE_ID=P.PRODUCT_ID where Customer_Id = ? ");

				ps.setString(1,id);
				ResultSet rs=ps.executeQuery();

				if(!rs.isBeforeFirst()){

					pw.println("No Customer with Customer Id"+id+"exists.<br>Please retype Customer Id");
				}
				else{
					pw.println("<html>\n"); 

					pw.println("<body>\n"); 

					pw.println("<table CELLPADDING=4>");

					//					pw.println("<tr><th><b>Customer_Id:</b><th><b>Customer_F_Name</b><th><b>Customer_L_Name</b></th><th><b>Customer_Status</b><th><b>Customer_Acct_No</b><th><b>Billing_addr_Id</b><th><b>Shipping_addr_Id</b><th><b>Customer_Email</b></tr>");
					//					while(rs.next()){  
					//						pw.println("<tr><td><b>"+rs.getString(1)+"</b></td><td><b>"+rs.getString(2)+"</b></td><td><b>"+rs.getString(3)+"</b></td><td><b>"+rs.getString(4)+"</b></td><td><b>"+rs.getString(5)+"</b></td><td><b>"+rs.getString(6)+"</b></td><td><b>"+rs.getString(7)+"</b></td><td><b>"+rs.getString(8)+"</b></td></tr>"); 

					while(rs.next()){					
						pw.println("<tr><td>Customer FirstName:</td>");	
						pw.println("<td>"+rs.getString(2)+"&nbsp"+rs.getString(3));	

						pw.println("<tr><td>Customer Account No:</td>");	
						pw.println("<td>"+rs.getString(5));
						pw.println("<tr><td>Billing address:</td>");	
						pw.println("<td>"+rs.getString(6));
						pw.println("<tr><td>Shipping address:</td>");	
						pw.println("<td>"+rs.getString(7));
						pw.println("<tr><td>Customer Email:</td>");	
						pw.println("<td>"+rs.getString(8));
						pw.println("<tr><td>Mobile No:</td>");
						pw.println("<td>  </td>");
						pw.println("<tr><td>Service Availed:</td>");
						pw.println("<td>"+rs.getString(10));
					}
					pw.println("</table>");
					pw.println("<br><br>");
					String docType =
							"<!doctype html public \"-//w3c//dtd html 4.0 " +
									"transitional//en\">\n";

					pw.println(docType +

							"<form action='Main'>" +
							"<table CELLPADDING=4>" +
							"<tr> <td>" + 
							"<select name='TID'>" +
							"<option value='' disabled selected >Select Trouble Service</option>" +
							"<option value='1'>Enterprise</option>" +
							"<option value='2'>Wireless</option>" +
							"<option value='3'>Television</option>" +
							"<option value=\"4\">Fios</option>" +
							"</select>" +
							"</td> </tr>" +
							
"<tr> <td>" +"<input type='text' placeholder=\"Trouble Description\" name='Trouble Description'>" + "</td> </tr>" +
							"<tr> <td>" +"<button type='button'>Ping Device</button>" + "</td>" +
							" <td>" +"<button type=\"button\">Reboot Device</button>" + "</td> </tr>" +
							
"<tr> <td>" + 
"<select name='Disposition'>" +
"<option value='' disabled selected >Select Disposition</option>" +
"<option value='1'>Closed Out</option>" +
"<option value='2'>Tech Dispatch</option>" +
"<option value='3'>Hardware Mail</option>" +
"</select>" +
"</td> </tr>" +

"<tr> <td>" +"<textarea rows=\"5\" cols=\"25\" placeholder=\"Closure Comments\" name ='Remarks'>" +"</textarea>" + "</td> </tr>" +

"<tr> <td>" +"<input type=\"radio\" name=\"status\" value ='open'>Open" + "</td>" +
"<td>" +"<input type=\"radio\" name=\"status\" value ='close'>Closed" + "</td> </tr>" +
							
							"<tr> <td>" +"<input type='submit' value='Submit'/>" + "</td> </tr>" +
							"</table>" +
							"</form>" +

							"</body></html>");
				}  
				con.close();  
			}

			catch(Exception e){e.printStackTrace();}  
		}  
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
