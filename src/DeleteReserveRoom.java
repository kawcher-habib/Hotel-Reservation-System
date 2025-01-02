import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class DeleteReserveRoom {

    private Connection conn;
    private Scanner scn;

    DeleteReserveRoom(Connection conn, Scanner scn){
        this.conn = conn;
        this.scn = scn;
    }

    public void deleteReserveRoom(){

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

}
