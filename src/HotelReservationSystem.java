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
                       viewReservesRoom(conn, scn);
                       break;
                   case 3:
                       getReserveRoomByRoomNumber(conn, scn);
                       break;
                   case 4:
                       updateReserveRoom(conn, scn);
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

    // Get all data
    private static void viewReservesRoom(Connection conn, Scanner scn){

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


                                System.out.println(reservId +"\t"+ gustName + "\t\t" + roomNum + "\t\t" + contactNum);
                            }




            }catch(SQLException e){

                    System.out.println("Error: "+ e.getMessage());
            }


    }

    // Get Data By Room Number
    private static void getReserveRoomByRoomNumber(Connection conn, Scanner scn){

        System.out.println("Enter Your Room Number: ");
        int roomNumber = scn.nextInt();
        try {

            String sql = "SELECT * FROM reservation WHERE room_number=?";

            PreparedStatement prstm = conn.prepareStatement(sql);
            prstm.setInt(1, roomNumber);
            ResultSet  result = prstm.executeQuery();

            if(result.next()){

                System.out.println("ID\tGust Name\tRoom Number\tContact Number ");
                    int reservId = result.getInt("reser_id");
                    String gustName = result.getString("guest_name");
                    int roomNum = result.getInt("room_number");
                    String contactNum = result.getString("contact_number");

                    System.out.println(reservId +"\t"+ gustName + "\t" + roomNum + "\t" + contactNum);

            }else{
                System.out.println("Data not found");
            }
        }catch (SQLException e){
            System.out.print("Error: " + e.getMessage());
        }

    }

    //Delete Reserve Room

    private static void deleteReserveRoom(Connection conn, Scanner scn){

        try{
            System.out.println("Enter Room Number: ");
            int roomNumber = scn.nextInt();

            String sql = "DELETE FROM reservation WHERE room_number=?";
            PreparedStatement stm = conn.prepareStatement(sql);

            stm.setInt(1, roomNumber);
            int result = stm.executeUpdate();
            if(result > 0){

                System.out.println("Reserve Room "+ roomNumber +" Deleted Successfully");

            }else{

                System.out.println( roomNumber + " Room delete failed");

            }


        }catch(SQLException e){

                System.out.println("Error: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Invalid input. Please enter valid room number");
        }


    }

    //Updated Reserve Room
    private static void updateReserveRoom(Connection conn, Scanner scn){


        try{
            System.out.println("Enter Updated Room ID :");
            int roomId = scn.nextInt();
            scn.nextLine();
            System.out.println("Enter updated guest name: ");
            String guest_name = scn.nextLine();
            System.out.println("Enter Updated Room Number : ");
            int roomNumber = scn.nextInt();
            System.out.println("Enter Updated Contact Number: ");
            String contactNumber = scn.next();

            String sql = "UPDATE reservation SET guest_name=?, room_number=?,contact_number=? WHERE reser_id= ?";

            PreparedStatement stm = conn.prepareStatement(sql);
            stm.setString(1, guest_name);
            stm.setInt(2, roomNumber);
            stm.setString(3, contactNumber);
            stm.setInt(4, roomId);

            int rowsUpdate = stm.executeUpdate();
            if(rowsUpdate > 0){
                System.out.println(roomId + " Reserve Room Updated Successfully");
            }
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());

        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}


