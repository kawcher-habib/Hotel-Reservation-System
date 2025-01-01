import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateReserveRoom {

    private Connection conn;
    private Scanner scn;

    UpdateReserveRoom(Connection conn, Scanner scn){
        this.conn = conn;
        this.scn = scn;
    }

    public  void updateReserveRoom(){


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

            String queryForValidation = "SELECT reser_id FROM reservation WHERE reser_id = ?";
            PreparedStatement validationStm = conn.prepareStatement(queryForValidation);
            validationStm.setInt(1, roomId);

            ResultSet rs = validationStm.executeQuery();
            if(rs.next()) {
                String sql = "UPDATE reservation SET guest_name=?, room_number=?,contact_number=? WHERE reser_id= ?";

                PreparedStatement stm = conn.prepareStatement(sql);
                stm.setString(1, guest_name);
                stm.setInt(2, roomNumber);
                stm.setString(3, contactNumber);
                stm.setInt(4, roomId);

                int rowsUpdate = stm.executeUpdate();
                if (rowsUpdate > 0) {
                    System.out.println(roomId + " Reserve Room Updated Successfully");
                } else {
                    System.out.println(roomId + " Reserve Room Updated Failed");
                }
            }else{
                System.out.println("Invalid Room Id");
            }

        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());

        }catch (Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
