import java.sql.*;
import java.util.Scanner;

public class HotelReservationSystem {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/hotel_reser_db";
    private static final String userName = "root";
    private static  final String password = "";



    public static void main(String[] args) throws ClassNotFoundException, SQLException {

       try{
            Class.forName("con.mysql.cj.jdbc.Driver");

       }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
       }

       try{
           Connection conn = DriverManager.getConnection(url, userName, password);

           while(true){
               System.out.println();
               System.out.println("HOTEL MANAGEMENT SYSTEM");
               Scanner scn = new Scanner(System.in);
               System.out.println("1. Reserve a Room");
               System.out.println("2. View Reserve Room");
               System.out.println("3. Get Room Number");
               System.out.println("4. Update Reserve Room");
               System.out.println("5. Delete Reserve Room");
               System.out.println("0. Exit");
               System.out.println("Choose an option: ");

               int choice = scn.nextInt();
               switch(choice){
                   case 1:
                       reserveRoom(conn, scn);
                       break;
                   case 2:
                       viewReserveRoom(conn, scn);
                       break;
                   case 3:
//                       getRoomNumber(conn, scn);
                       break;
                   case 4:
//                       updateReserveRoom(conn, scn);
                       break;
                   case 5:
//                       deleteReserveRoom(conn, scn);
                       break;
                   case 0:
//                       exit();
                       scn.close();
                       return;
                   default:
                       System.out.println("Invalid choice. Try again");

               }

           }

       }catch(SQLException e){
           System.out.println(e.getMessage());
       }
    }


    // Insert Data

    private static void reserveRoom(Connection conn, Scanner scn){
        try{
            System.out.println("Enter guest name: ");
            String guestName = scn.next();
            System.out.println("Enter room number: ");
            int roomNum = scn.nextInt();
            System.out.println("Enter contact number: ");
            String contactNum = scn.next();

            String sql = "INSERT INTO reservation(guest_name, room_number, contact_number)" +
                    "VALUES('"+guestName+"', " + roomNum + ", '"+ contactNum +"')";
            try(Statement stm = conn.createStatement()){
                int affectedRows = stm.executeUpdate(sql);

                if(affectedRows > 0){
                    System.out.println("Room Reserve Successfully");
                }else{
                    System.out.println("Room Reserve Failed");
                }

            }

        }catch (SQLException e){
            e.printStackTrace();

        }

    }

    // Get all data
    private static void viewReserveRoom(Connection conn, Scanner scn){

            String sql = "SELECT * FROM reservation";
            try(Statement stm = conn.createStatement()){

                    ResultSet result = stm.executeQuery(sql);

                    // Check is result is empty
                    if(!result.isBeforeFirst()){
                        System.out.println("Data not found");
                        return;
                    }


                            System.out.println("ID\tGust Name\tRoom Number\tContact Number ");

                        //Iterate on the result
                            while (result.next()) {

                                int reservId = result.getInt("reser_id");
                                String gustName = result.getString("guest_name");
                                int roomNum = result.getInt("room_number");
                                String contactNum = result.getString("contact_number");


                                System.out.println(reservId +"\t"+ gustName + "\t" + roomNum + "\t" + contactNum);
                            }




            }catch(SQLException e){

                    System.out.println("Error: "+ e.getMessage());
            }


    }
}


