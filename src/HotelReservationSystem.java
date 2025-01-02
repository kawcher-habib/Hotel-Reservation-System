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
               System.out.println("2. View Reserves Room");
               System.out.println("3. View Room By Room Number");
               System.out.println("4. Update Reserve Room");
               System.out.println("5. Delete Reserve Room");
               System.out.println("0. Exit");
               System.out.println("Choose an option: ");

               int choice = scn.nextInt();
               switch(choice){
                   case 1:

                       //Inert Data
                        ReserveRoom addReserveRoom = new ReserveRoom(conn, scn);
                        addReserveRoom.reserveRoom();
                       break;

                   case 2:

                       // Show All Reserves Room
                       ViewReservesRoom reserveRoom = new ViewReservesRoom(conn, scn);
                       reserveRoom.viewReservesRoom();
                       break;

                   case 3:

                       // Show Reserve Room By Room Number
                       ReserveRoomByRoomNumber reserveRoomByRoomNum = new ReserveRoomByRoomNumber(conn, scn);
                       reserveRoomByRoomNum.getReserveRoomByRoomNumber();
                       break;

                   case 4:

                       //Updated Reserve Room
                       UpdateReserveRoom  updateReRoom = new UpdateReserveRoom(conn, scn);
                       updateReRoom.updateReserveRoom();
                       break;
                       
                   case 5:
                   deleteReserveRoom(conn, scn);
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


    //Delete Reserve Room


}


