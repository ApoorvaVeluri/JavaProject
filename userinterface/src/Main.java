

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Servlet implementation class Main
 */
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter pw=response.getWriter();
		
		Date d = new Date();
		SimpleDateFormat dft = new SimpleDateFormat("dd-MM-yyyy");
		pw.println(dft.format(d));
		
		String TicketID = "101";
		HttpSession aSession = request.getSession();
		String id = (String) aSession.getAttribute("id");
		String TroubleServiceID = request.getParameter("TID");
		String TroubleDescription = request.getParameter("Trouble Description");
		String TicketType = "Single";
		String Remarks = request.getParameter("Remarks");
		String DispositionID = request.getParameter("Disposition");
		String Status = request.getParameter("status");
		
//		Date due = null;
//		if("open".equals(Status))
		
			Date due = new Date();
			Calendar c = Calendar.getInstance(); 
			c.setTime(due); 
			c.add(Calendar.DATE, 4);
			due = c.getTime();
		
		
		String GroupID="011";
		String CreatedBy = "Admin";
		
		TicketBean tb = new TicketBean(TicketID,id,TroubleServiceID,TroubleDescription,TicketType,d, d, due, Remarks,DispositionID,Status,GroupID ,CreatedBy );
		AddNewTicket adt = new AddNewTicket();
		if (adt.addTicket(tb) == 1) System.out.println("Ticket created Successfully!!!");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
