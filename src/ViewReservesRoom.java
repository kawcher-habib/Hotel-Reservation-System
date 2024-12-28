import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ViewReservesRoom {

    private Connection conn;
    private Scanner scn;

    ViewReservesRoom(Connection conn, Scanner scn){
        this.conn = conn;
        this.scn = scn;
    }


    public void viewReservesRoom(){

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


}
