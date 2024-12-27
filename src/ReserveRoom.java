import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
            String guestName = scn.next();
            scn.nextLine();

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

}
