import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReserveRoomByRoomNumber {

    private static void getReserveRoomByRoomNumber(Connection conn, Scanner scn){

        System.out.println("Enter Your Room Number: ");
        int roomNumber = scn.nextInt();
        try {

            String sql = "SELECT * FROM reservation WHERE room_number=?";

            PreparedStatement prstm = conn.prepareStatement(sql);
            prstm.setInt(1, roomNumber);
            ResultSet result = prstm.executeQuery();

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
}
