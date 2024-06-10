import java.sql.*;


public class studenttable {


    public static void main(String[] args) {
        Connection con;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/projectexam","Ganitha","root");
            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from Student_Details");
            //desc table
            String sql="update Student_details set password = '12345' where id=80";
            ResultSetMetaData metadata=rs.getMetaData();

            System.out.println("Student_Details table description:");
            System.out.println();
            System.out.println("number of columns: "+metadata.getColumnCount());
            for(int i=1;i<=metadata.getColumnCount();i++)
            {
                System.out.println(metadata.getColumnName(i)+":    "+metadata.getColumnTypeName(i));
            }
            //display table
            int sid;
            String sname;
            String clsrm;
            int seatno;
            String pswd;
            String branch;
            String chngepswd;
            // String sql=null;
            //  String sql1=null;
            System.out.println();
            System.out.println("STUDENT ID    PASSWORD     BRANCH    CLASSROOM    SEAT NUMBER	STUDENT NAME  CHANGED PASSWORD");
            while(rs.next())
            {
                sid=rs.getInt("id");
                pswd=rs.getString("password");
                branch=rs.getString("branch");
                sname=rs.getString("name");
                clsrm=rs.getString("classroom");
                seatno=rs.getInt("seat");
                chngepswd=rs.getString("changedpassword");
	        	  /*  sql="update classroom='null' in student_details where id="+sid;
	        	    sql1="update seat=0 in student_details where id="+sid;*/
                System.out.println("     "+sid+"     "+"   "+pswd+"    "+"    "
                        +branch+"     "+"    "+clsrm+"     "+"    "+seatno+"       "+"         "+sname+"    "+"   "+chngepswd);

            }
            //alter table
            // String   sql="alter table student_details modify classroom integer(10);";

            //create table
	      /*String sql="create table Student_Details"+
	                    "(id integer not null,"+
	        		     "password varchar(255),"+
	                    "name varchar(255),"+
	        		     "branch varchar(255),"+
	                    "classroom varchar(255),"+
	                    "seat integer,"+
	                    "primary key(id))";
            //insert values

	       /* String sql="insert into Student_Details(id,name,password) values "
	        		+ " "+
	                "(61,'ram','12345'),"+"(62,'sam','12345'),"+"(63,'alex','12345'),"+"(64,'sameer','12345'),"+"(65,'elena','12345'),"+
	                "(66,'suresh','12345'),"+"(67,'akash','12345'),"+"(68,'xavier','12345'),"+"(69,'ariana','12345'),"+"(70,'jenny','12345'),"+
	                "(71,'raj','12345'),"+"(72,'kishore','12345'),"+"(73,'ranjith','12345'),"+"(74,'bhavya','12345'),"+"(75,'laxman','12345'),"+
	                "(76,'rajesh','12345'),"+"(77,'joel','12345'),"+"(78,'dan','12345'),"+"(79,'diana','12345'),"+"(80,'rayansh','12345'),"+
	                "(81,'swapna','12345'),"+"(82,'venkat','12345'),"+"(83,'ramesh','12345'),"+"(84,'srinivas','12345'),"+"(85,'rinky','12345'),"+
	                "(86,'reeni','12345'),"+"(87,'jonathan','12345'),"+"(88,'advik','12345'),"+"(89,'mohan','12345'),"+"(90,'nikith','12345');";*/
            //update record
            //  String sql="alter table student_details modify changedpassword varchar(10) default 0;";
            stmt.executeUpdate(sql);
            //  stmt.executeUpdate(sql1);

            //System.out.println(" updated values into table in given database...");
            rs.close();
            stmt.close();

            con.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
