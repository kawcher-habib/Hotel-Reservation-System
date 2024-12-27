import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ReserveRoom {
    private Connection conn;
    private Scanner scn;

    public ReserveRoom(Connection conn, Scanner scn){
        this.conn = conn;
        this.scn = scn;
    }

    public void reserveRoom(){
        try{

            System.out.println("Enter guest name: ");
            scn.nextLine();
            String guestName = scn.nextLine();

            System.out.println("Enter room number: ");
            int roomNum = scn.nextInt();
            System.out.println("Enter contact number: ");
            String contactNum = scn.next();

            // Room is available or not
            String queryForValidation = "SELECT room_number FROM reservation WHERE room_number=?";
            PreparedStatement validationStm = conn.prepareStatement(queryForValidation);
            validationStm.setInt(1, roomNum);

            ResultSet rs = validationStm.executeQuery();

            if(!rs.next()){

                String insertQuery = "INSERT INTO reservation(guest_name, room_number, contact_number) VALUES(?,?,?)";
                PreparedStatement insertStm = conn.prepareStatement(insertQuery);

                insertStm.setString(1, guestName);
                insertStm.setInt(2, roomNum);
                insertStm.setString(3, contactNum);


                int affectedRows = insertStm.executeUpdate();

                if(affectedRows > 0){
                    System.out.println("Room reserve successfully");
                }else{
                    System.out.println("Room reserve failed");
                }

                // Close Insert Statement
                insertStm.close();

            }else{
                System.out.println(roomNum +" Room is already reserved");
                return;
            }

            //Close validation Statement
            validationStm.close();

        }catch (SQLException e){
            e.printStackTrace();

        }

    }

}
