package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Doctor {
    String viewAllDoctorQuery="SELECT * FROM Doctor;";
    String viewDoctorQuery = "SELECT * FROM Doctor WHERE d_id=?;";
    Scanner scanner=new Scanner(System.in);
    Connection connection;
    public Doctor(Connection connection){
        this.connection = connection;
    }
    public void viewAllDoctor(){
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(viewAllDoctorQuery);
            ResultSet resultSet=preparedStatement.executeQuery();
            System.out.println("+-----------+--------------------------+--------------------+");
            System.out.println("| Doctor ID | Name                     | Specializaiton     |");
            System.out.println("+-----------+--------------------------+--------------------+");
            while(resultSet.next()){
                int id=resultSet.getInt("d_id");
                String name=resultSet.getString("d_name");

                String specs=resultSet.getString("specialization");
                System.out.printf("| %-10d | %-23s | %-18s |",id,name,specs);
                System.out.println();
            }
            System.out.println("+-----------+--------------------------+--------------------+");
        }catch (SQLException sqe){
            System.out.println(sqe);
        }
    }
    public boolean viewDoctor(){
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(viewDoctorQuery);
            System.out.println("Enter Doctor Id: ");
            int id=scanner.nextInt();
            preparedStatement.setInt(1,id);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()) return true;
            else return false;
        }catch (SQLException sqe){
            System.out.println(sqe);
        }
        return false;
    }
}
