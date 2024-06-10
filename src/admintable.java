import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class admintable {



    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Connection con;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","manu709*");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from Admin");
            // ResultSet rs=stmt.executeQuery("select * from Student_Details");
            //create table
		       /* String sql="create table Admin"+
		                     "(username varchar(225) not null,"+
		         		     "password varchar(255));";
		          System.out.println("created table..");*/
            //insert values
		        /*String sql="insert into Admin values('admin1','1234!@#$'),"+
		         		     "('admin2','1234!@#$'),"+
		        		     "('admin3','1234!@#$'),"+
		        		     "('admin4','1234!@#$'),"+
		        		     "('admin5','1234!@#$');";
		           System.out.println("inserted values..");*/
            //desc table
            ResultSetMetaData metadata=rs.getMetaData();

            System.out.println("Admin table description:");
            System.out.println();
            System.out.println("number of columns: "+metadata.getColumnCount());
            for(int i=1;i<=metadata.getColumnCount();i++)
            {
                System.out.println(metadata.getColumnName(i)+":    "+metadata.getColumnTypeName(i));
            }
            //display table
            String usname;
            String pswd;
            System.out.println();
            System.out.println("Admin table..");
            System.out.println();
            System.out.println("USER NAME      PASSWORD");
            while(rs.next())
            {
                usname=rs.getString("username");
                pswd=rs.getString("password");
                System.out.println(" "+usname+"     "+"   "+pswd);
            }






            //stmt.executeUpdate(sql);

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