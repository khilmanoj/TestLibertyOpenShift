package testpkg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	
	
	@Resource(name = "jdbc/sampleDS")
	private DataSource ds1;
	  private Connection con = null;
	  
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String outputStr = "<html><body>";
		
		
		outputStr = outputStr + "<br><br><font size='8'>" + "Hello Liberty Application - Pipelines v1.1" + "</font>";
		
		outputStr = outputStr + "<br><br><font size='6'>" + "Run on Openshift 4.5.4 cluster." + "</font>";
		
		
		outputStr = outputStr + "<br><br><font size='6'>" + "<b>Read AWS database table data </b>"+ "</font>";
		
		String showData = request.getParameter("showdata");
		if (showData != null)
		{
			PreparedStatement preparedStatement = null;
			 
			 try
			 {
				 con = ds1.getConnection();
				 preparedStatement = con.prepareStatement("Select * from folder");
				 ResultSet rset=preparedStatement.executeQuery();
				 if(rset!=null)
				 {
				 while(rset.next())
				 {
				 //System.out.println("Name: "+rset.getString("name"));
				 outputStr = outputStr + rset.getString("name") + "<br>";
				 }
				 }
			 }
			 catch(SQLException e)
			 {
				 //e.printStackTrace();
				 outputStr = outputStr + " Failed to read the database <br><br>";
				 outputStr = outputStr + e.toString();
				 outputStr = outputStr + "\n";
			 }
		}
		String showProps = request.getParameter("showprops");
		if (showProps != null)
		{
	
			 outputStr = outputStr + "<br><font size='6'>" + "<b>Read External Properties File </b>"+ "</font>";
			 //PROPS_FILE is defined in jvm.options
			 String propsFileName = System.getProperty("PROPS_FILE","testconfig.properties");
			 
			 System.out.println("Properties file name is:" + propsFileName);
			 
			 final Properties properties = new Properties();
			 try (final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propsFileName)) {
				 if (stream != null)
				 {
					 properties.load(stream);
					 String propsValue = properties.getProperty("testPropertyName");
				 
					 outputStr = outputStr + "\n" + propsValue;
				 }
				 else
				 {
					 outputStr = outputStr + " Failed to read properties <br><br>";
					 outputStr = outputStr + "\n";
				 }
			 }
			 catch(Exception e)
			 {
				 outputStr = outputStr + " Failed to read properties <br><br>";
				 outputStr = outputStr + e.toString();
				 outputStr = outputStr + "\n";
			 }
		}
		 
		outputStr = outputStr + "</body></html>";
		 
		 response.getWriter().println(outputStr);
		 
		 
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
