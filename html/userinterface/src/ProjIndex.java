

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

//import com.sun.corba.se.pept.transport.Connection;

/**
 * Servlet implementation class ProjIndex
 */
public class ProjIndex extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id=request.getParameter("val");
		PrintWriter pw=response.getWriter();
		if(id==null || id.trim().equals(""))
		{  
			pw.print("Please enter Customer id");  
		}
		else

		{
			pw.print("Customer Id you Entered is "+id);  
			try{  
				Class.forName("oracle.jdbc.OracleDriver");  
				Connection con=   DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl","hr","hr");  
				PreparedStatement ps =   con.prepareStatement("select * from Customer where Customer_Id = ? ");

				ps.setString(1,id);
				ResultSet rs=ps.executeQuery();

				if(!rs.isBeforeFirst()){

					pw.println("No Customer with Customer Id"+id+"exists.Please retype Customer Id");
				}
				else{


					response.getWriter().write("<table CELLPADDING=10>");

					//					response.getWriter().write("<tr><th><b>Customer_Id:</b><th><b>Customer_F_Name</b><th><b>Customer_L_Name</b></th><th><b>Customer_Status</b><th><b>Customer_Acct_No</b><th><b>Billing_addr_Id</b><th><b>Shipping_addr_Id</b><th><b>Customer_Email</b></tr>");
					//					while(rs.next()){  
					//						response.getWriter().write("<tr><td><b>"+rs.getString(1)+"</b></td><td><b>"+rs.getString(2)+"</b></td><td><b>"+rs.getString(3)+"</b></td><td><b>"+rs.getString(4)+"</b></td><td><b>"+rs.getString(5)+"</b></td><td><b>"+rs.getString(6)+"</b></td><td><b>"+rs.getString(7)+"</b></td><td><b>"+rs.getString(8)+"</b></td></tr>"); 

					while(rs.next()){
						response.getWriter().write("<tr><td>Customer Id:</td>");
						response.getWriter().write("<td>"+rs.getString(1));					
						response.getWriter().write("<tr><td>Customer FirstName:</td>");	
						response.getWriter().write("<td>"+rs.getString(2));
						response.getWriter().write("<tr><td>Customer LastName:</td>");	
						response.getWriter().write("<td>"+rs.getString(3));
						response.getWriter().write("<tr><td>Customer Status:</td>");	
						response.getWriter().write("<td>"+rs.getString(4));
						response.getWriter().write("<tr><td>Customer Account No:</td>");	
						response.getWriter().write("<td>"+rs.getString(5));
						response.getWriter().write("<tr><td>Billing address Id:</td>");	
						response.getWriter().write("<td>"+rs.getString(6));
						response.getWriter().write("<tr><td>Shipping address Id:</td>");	
						response.getWriter().write("<td>"+rs.getString(7));
						response.getWriter().write("<tr><td>Customer Email:</td>");	
						response.getWriter().write("<td>"+rs.getString(8));
						
					}



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