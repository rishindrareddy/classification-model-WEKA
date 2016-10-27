<%@page import="oracle.jdbc.OracleDriver"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
	String gre=request.getParameter("gre");
	String toefl=request.getParameter("toefl");
	String agg=request.getParameter("agg");
	String bk=request.getParameter("bk");
	 %>
	 <p>
	 scores:<br>
	 GRE<%out.println("         :"+gre); %><br>
	 TOEFL<%out.println("       :"+toefl); %><br>
	 Aggregate<%out.println("   :"+agg); %><br>
	 Backlog<%out.println("     :"+bk); %><br>
	 
	 <br>Recommendations :<br>
	 </p>
	 
	 
<table border="2">
	<%
	
	int a=Integer.parseInt(agg);
	int b=Integer.parseInt(bk);
	if(a<60)
		out.println("sorry !!! your aggregate score is too low to recommend any university");
	if(b>10)
	out.println("Sorry!!! you have too many backlogs for recommending any university");
	
	
	//session.setAttribute("uname",username);
		
	Driver d=new OracleDriver();
	DriverManager.registerDriver(d);
	
	String url="jdbc:oracle:thin:@//localhost:1521/xe";
	String u="scott";
	String p="tiger";
	Connection con=DriverManager.getConnection(url,u,p);
	

	
	Statement s=con.createStatement();
	//out.println("connection Established");
	
String sql="select name from univlist where min_gre <="+gre+" and min_toefl<="+toefl+" and min_agg<="+agg+" and max_bklog>="+bk+"" ;
	
	//String sql="select * from websites";
	ResultSet rs=s.executeQuery(sql);
	int count=0;
	while(rs.next())
	{
	count++;
	%>
	<tr>
		<td><%=rs.getString("name") %></td>
   		 
   	</tr>
	  <%
	  if(count==5)
		  break;
	}
	
	
	%>

	</table>
</body>
</html>