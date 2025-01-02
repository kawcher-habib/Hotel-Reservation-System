import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

            String validationQuery = "SELECT room_number FROM reservation WHERE room_number = ?";
            PreparedStatement validationStm = conn.prepareStatement(validationQuery);

            validationStm.setInt(1, roomNumber);

            ResultSet rs = validationStm.executeQuery();
            if(rs.next()){

                String sql = "DELETE FROM reservation WHERE room_number=?";
                PreparedStatement stm = conn.prepareStatement(sql);

                stm.setInt(1, roomNumber);
                int result = stm.executeUpdate();

                if(result > 0){

                    System.out.println("Reserve Room "+ roomNumber +" Deleted Successfully");

                }else{

                    System.out.println( roomNumber + " Room delete failed");

                }

            }else{
                System.out.println("Invalid Room Number");
            }

            rs.close();

        }catch(SQLException e){

            System.out.println("Error: " + e.getMessage());
        }catch(Exception e){
            System.out.println("Invalid input. Please enter valid room number");
        }


    }

}
