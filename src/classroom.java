import java.sql.*;


public  class classroom {
    static int capacity=15;
    static int clsrm=201;
    static int count=0;

    public static void allocateclass(int stid,Connection con)
    {


        try {
            Statement stmt=con.createStatement();

            if(count<capacity)
            {
                String sql="update student_details set seat = "+(count+1)+" where id= "+stid;
                String sql1="update student_details set classroom = "+clsrm+" where id= "+stid;
                count++;
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql1);


            }
            else
            {
                clsrm++;
                count=0;
                String sql="update student_details set seat = "+(count+1)+" where id= "+stid;
                String sql1="update student_details set classroom = "+clsrm+" where id= "+stid;
                count++;
                stmt.executeUpdate(sql);
                stmt.executeUpdate(sql1);
            }



        }
        catch(Exception e)
        {

        }
    }
    public static void displayClassroom(Statement stmt, int classroomNumber) {
        try {


            int numRows = 5;
            int numColumns = 3;

            System.out.println("Classroom " + classroomNumber + " Seating Arrangement:");
            System.out.println("-----------------------------------------------------");



            for(int i=1;i<=numRows;i++)
            {
                for(int j=1;j<=numColumns;j++)
                {
                    int seatNumber = (i - 1) + (j - 1) * numRows + 1;
                    String sql = "SELECT id FROM student_details WHERE classroom = " +
                            classroomNumber + " AND seat = " + seatNumber;

                    ResultSet rs = stmt.executeQuery(sql);

                    if (rs.next()) {
                        int studentId = rs.getInt("id");
                        System.out.printf(" | R" +studentId+"-s"+seatNumber);
                    } else {
                        System.out.printf(" | ", "Empty");
                    }

                }
                System.out.println("|");

            }

            System.out.println("-----------------------------------------------------");
        } catch (Exception e) {
            System.out.println(e);
        }
    }


}
/*import java.sql.*;
import java.lang.*;


public class classroom {
	static int capacity=15;
	static int clsrm=201;
	static int seatno=1;
	/*static int seat;
	static int clsrm;
	static int sid;*/



//static String clsrm1;

/*	public static void allocateclass(int stid,Connection con)
	{

		//int sid=0;

		try {
			Statement stmt=con.createStatement();


			/*ResultSet rs=stmt.executeQuery("select * from student_details;");
			while(rs.next())*/
//	{
//	sid=rs.getInt("id");*/

				     	/*   if(seatno<capacity)
				     	    {
				     	    	String sql="update student_details set seat = "+seatno+" where id= "+stid;
				     	    	String sql1="update student_details set classroom = "+clsrm+" where id= "+stid;

				     	    	stmt.executeUpdate(sql);
				     	    	stmt.executeUpdate(sql1);


				     	    }
				     	    else
				     	    {
				     	    	clsrm++;
				     	    	//clsrm = String.valueOf(Integer.parseInt(clsrm1) + 1);
				     	    	seatno=0;
				     	    	String sql="update student_details set seat = "+(seatno+1)+" where id= "+stid;
				     	    	String sql1="update student_details set classroom = "+clsrm+" where id= "+stid;
				     	    	seatno++;
				     	    	stmt.executeUpdate(sql);
				     	    	stmt.executeUpdate(sql1);
				     	    }

			}
		}



		//}




		catch(Exception e)
		{

		}
	}

	/*public static void allocateclass(int stid, Connection con) {
	    try {
	        Statement stmt = con.createStatement();
	        ResultSet rs = stmt.executeQuery("SELECT classroom, seat FROM Student_Details WHERE id = " + stid);

	        if (rs.next()) {
	            String clsrm1 = rs.getString("classroom");
	            int seat = rs.getInt("seat");
	            String clsrm = String.valueOf(clsrm1);

	            if (seat < capacity) {
	                String sqlUpdateSeat = "UPDATE student_details SET seat = " + (seat + 1) + " WHERE id = " + stid;
	                stmt.executeUpdate(sqlUpdateSeat);
	            } else {
	                int newClassroom = Integer.parseInt(clsrm1) + 1;
	                String sqlUpdateSeat = "UPDATE student_details SET seat = 1, classroom = " + newClassroom + " WHERE id = " + stid;
	                stmt.executeUpdate(sqlUpdateSeat);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}*/

	/* public static void displayClassroom(Statement stmt, int classroomNumber) {
	        try {


	            int numRows = 5;
	            int numColumns = 3;

	            System.out.println("Classroom " + classroomNumber + " Seating Arrangement:");
	            System.out.println("-----------------------------------------------------");



	                for(int i=1;i<=numRows;i++)
	                {
	                	for(int j=1;j<=numColumns;j++)
	                	{
	                		int seatNumber = (i - 1) + (j - 1) * numRows + 1;
	                    String sql = "SELECT id FROM student_details WHERE classroom = " +
	                     classroomNumber + " AND seat = " + seatNumber;

	                    ResultSet rs = stmt.executeQuery(sql);

	                    if (rs.next()) {
	                        int studentId = rs.getInt("id");
	                        System.out.printf(" | " +studentId+"-"+seatNumber);
	                    } else {
	                        System.out.printf(" | ", "Empty");
	                    }

	                }
	                System.out.println("|");

	            }

	            System.out.println("-----------------------------------------------------");
	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }


}*/

