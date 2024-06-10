import java.sql.*;
import java.util.*;

public class student {
    static int sid1;
    static String sname1;
    static String clsrm1;
    static  int seatno1;
    static String pswd1;
    static String branch1;
    static String chngepswd;

    public static void studentloginanddisplay(Statement stmt,int studentid,String studentpswd)
    {


        try {
            ResultSet rs1=stmt.executeQuery("select * from Student_Details");




            while(rs1.next())
            {
                sid1=rs1.getInt("id");
                pswd1=rs1.getString("password");
                branch1=rs1.getString("branch");
                sname1=rs1.getString("name");
                clsrm1=rs1.getString("classroom");
                seatno1=rs1.getInt("seat");


                if(sid1==studentid  &&  pswd1.equals(studentpswd))
                {

                    System.out.println("STUDENT ID    BRANCH    CLASSROOM    SEAT NUMBER   STUDENT NAME");
    	    	/* String sql="update Student_Details set branch='"+studentbranch+"' where id="+studentid;
    	    	 stmt.executeUpdate(sql);*/

                    {
                        System.out.println("     "+sid1+"     "+"   "
                                +branch1+"     "+"    "+
                                clsrm1+"     "+"    "+seatno1+"      "+"      "+sname1);

                    }
                }
            }




        }
        catch(Exception e)
        {
            System.out.println(e);

        }

    }
    public static void changestudentpassword(Connection con,int studentid,String studentpswd,Scanner sc)
    {
        int count=0;


        try {
            Statement stmt1=con.createStatement();
            Statement stmt2=con.createStatement();
            ResultSet rs=stmt1.executeQuery("select * from Student_Details");




            while(rs.next())
            {
                sid1=rs.getInt("id");
                pswd1=rs.getString("password");
                chngepswd=rs.getString("changedpassword");
                //  String temp=chngepswd;



                if(sid1==studentid  &&  pswd1.equals(studentpswd))
                {
                    if(studentpswd.equals("12345")) {
                        //studentloginanddisplay(stmt1, studentid, studentpswd);


                        System.out.print("Enter new password: ");
                        String newpswd=sc.next();
                        System.out.print("Confirm password: ");
                        String confirmpswd=sc.next();
                        if(newpswd.equals(confirmpswd))
                        {
                            String sql="update student_details set password='"+newpswd+"' where id="+studentid+";";
    	    		/*String updatepswd="update student_details set changedpassword= 'YES' where id="+studentid+";";
    	    		stmt1.executeUpdate(updatepswd);*/
                            stmt1.executeUpdate(sql);
                            count++;
                            System.out.println("password updated!");

                            studentloginanddisplay(stmt2, studentid, studentpswd);
                        }
                    }
                    else {
                        studentloginanddisplay(stmt2, studentid, studentpswd);
                        count++;
                    }

                }
            }

            if(count<=0)
            {
                System.out.println();
                System.out.println("wrong details!");
                System.out.println("Please enter correct details to login..");
            }



        }
        catch(Exception e)
        {
            System.out.println(e);

        }

    }






}


