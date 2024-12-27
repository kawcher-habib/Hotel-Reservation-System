import java.sql.*;
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

            // Validation checker
            String queryForValidation = "SELECT room_number FROM reservation WHERE room_number=?";
            PreparedStatement prstm = conn.prepareStatement(queryForValidation);
            prstm.setInt(1, roomNum);

            ResultSet rs = prstm.executeQuery();

            if(!rs.next()){

                String insertQuery = "INSERT INTO reservation(guest_name, room_number, contact_number) VALUES(?,?,?)";
                PreparedStatement insertStm = conn.prepareStatement(insertQuery);

                insertStm.setString(1, guestName);
                insertStm.setInt(2, roomNum);
                insertStm.setString(3, contactNum);


                int affectedRows = insertStm.executeUpdate();

                if(affectedRows > 0){
                    System.out.println("Room Reserve Successfully");
                }else{
                    System.out.println("Room Reserve Failed");
                }

            }else{
                System.out.println(roomNum +" Room is already reserved");
                return;
            }

        }catch (SQLException e){
            e.printStackTrace();

        }

    }

}
