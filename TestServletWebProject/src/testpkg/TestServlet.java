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
		
		String outputStr = "Hello Liberty!! Lets run on Openshift cluster deployed on AWS";
		outputStr = outputStr + "\n" + "Database folders are \n";
		
		
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
		 outputStr = outputStr + rset.getString("name") + "\n";
		 }
		 }
		 }
		 catch(SQLException e)
		 {
		 e.printStackTrace();
		 }

		 //PROPS_FILE is defined in jvm.options
		 String propsFileName = System.getProperty("PROPS_FILE");
		 System.out.println(propsFileName);
		 
		 final Properties properties = new Properties();
		 try (final InputStream stream = this.getClass().getClassLoader().getResourceAsStream(propsFileName)) {
		     properties.load(stream);
		 }
		 
		 String propsValue = properties.getProperty("testPropertyName");
		 
		 outputStr = outputStr + "\n" + propsValue;
		 
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
