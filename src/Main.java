
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            Connection con;
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projectexam", "root", "Ganitha");
            Statement stmt = con.createStatement();
            Scanner sc = new Scanner(System.in);
            System.out.println("USER TYPES:");
            System.out.println("1.Admin");
            System.out.println("2.Student");
            System.out.print("Enter an option(1/2) to select user mode:");
            int n = sc.nextInt();
            System.out.println();
            switch (n) {
                case 1:
                    System.out.println("ADMIN LOGIN");
                    System.out.print("Enter username:");
                    String adminid = sc.next();
                    System.out.print("Enter password:");
                    String adminpswd = sc.next();
                    boolean adminloggedin = adminLogin(stmt, adminid, adminpswd);
                    if (adminloggedin) {
                        adminOperations(stmt, con, sc);
                    } else {
                        System.out.println();
                        System.out.println("Wrong details!");
                        System.out.println("Please enter correct details to login..");
                    }
                    break;
                case 2:
                    System.out.println("STUDENT LOGIN");
                    System.out.print("Enter your login id:");
                    int studentid = sc.nextInt();
                    System.out.print("Enter password: ");
                    String studentpswd = sc.next();
                    System.out.print("Enter Branch: ");
                    String branchname = sc.next();
                    changeStudentPassword(con, studentid, studentpswd, sc);
                    break;
                default:
                    System.out.println("Exited");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean adminLogin(Statement stmt, String adminid, String adminpswd) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM admin WHERE username = '" + adminid + "' AND password = '" + adminpswd + "'");
        return rs.next();
    }

    public static void adminOperations(Statement stmt, Connection con, Scanner sc) {
        try {
            int choice;
            do {
                displayAdminMenu();
                choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.print("Enter student id:");
                        int studentid = sc.nextInt();
                        System.out.print("Enter student name:");
                        String studentname = sc.next();
                        System.out.print("Enter student password:");
                        String studentpswd = sc.next();
                        System.out.print("Enter student branch:");
                        String studentbranch = sc.next();
                        addStudent(stmt, studentid, studentname, studentpswd, studentbranch);
                        break;
                    case 2:
                        displayStudents(stmt, con);
                        break;
                    case 3:
                        displayTable(stmt);
                        break;
                    case 4:
                        System.out.print("Enter student id to delete:");
                        int studentIdToDelete = sc.nextInt();
                        deleteStudent(stmt, studentIdToDelete);
                        break;
                    case 5:
                        modifyStudentDetails(stmt, sc);
                        break;
                    case 6:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice!");
                }
            } while (choice != 6);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addStudent(Statement stmt, int studentid, String studentname, String studentpswd, String studentbranch) throws SQLException {
        String query = "INSERT INTO student_details VALUES (" + studentid + ", '" + studentname + "', '" + studentpswd + "', '" + studentbranch + "')";
        stmt.executeUpdate(query);
        System.out.println("Student added successfully!");
    }

    public static void displayStudents(Statement stmt, Connection con) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM student_details");
        while (rs.next()) {
            System.out.println("Student ID: " + rs.getInt("studentid"));
            System.out.println("Name: " + rs.getString("studentname"));
            System.out.println("Branch: " + rs.getString("studentbranch"));
            System.out.println("----------------------------------");
        }
    }

    public static void displayTable(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery("SELECT * FROM classroom");
        while (rs.next()) {
            System.out.println("Seat Number: " + rs.getInt("seat_number"));
            System.out.println("Student ID: " + rs.getInt("student_id"));
            System.out.println("----------------------------------");
        }
        // Display classroom seating arrangement
        // Adjust according to your table structure
    }

    public static void deleteStudent(Statement stmt, int studentIdToDelete) throws SQLException {
        String deleteQuery = "DELETE FROM student_details WHERE studentid = " + studentIdToDelete;
        int rowsAffected = stmt.executeUpdate(deleteQuery);
        if (rowsAffected > 0) {
            System.out.println("Student with ID " + studentIdToDelete + " deleted successfully!");
        } else {
            System.out.println("No student found with ID " + studentIdToDelete);
        }
    }

    public static void modifyStudentDetails(Statement stmt, Scanner sc) throws SQLException {
        System.out.print("Enter student id to modify:");
        int studentIdToModify = sc.nextInt();
        System.out.print("Enter new student name: ");
        String newStudentName = sc.next();
        System.out.print("Enter new student branch: ");
        String newStudentBranch = sc.next();
        String query = "UPDATE student_details SET studentname = '" + newStudentName + "', studentbranch = '" + newStudentBranch + "' WHERE studentid = " + studentIdToModify;
        int updatedRows = stmt.executeUpdate(query);
        if (updatedRows > 0) {
            System.out.println("Student details updated successfully!");
        } else {
            System.out.println("Failed to update student details. Student ID not found.");
        }
    }

    public static void changeStudentPassword(Connection con, int studentid, String studentpswd, Scanner sc) throws SQLException {
        System.out.print("Enter new password: ");
        String newPassword = sc.next();
        Statement stmt = con.createStatement();
        String query = "UPDATE student_details SET studentpswd = '" + newPassword + "' WHERE studentid = " + studentid;
        stmt.executeUpdate(query);
        System.out.println("Password updated successfully!");
    }

    public static void displayAdminMenu() {
        System.out.println("Admin Menu:");
        System.out.println("1. Add Student");
        System.out.println("2. Display Student Database");
        System.out.println("3. Display Classroom Seating Arrangement");
        System.out.println("4. Delete Student from the Database");
        System.out.println("5. Modify Student Details");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }
}
