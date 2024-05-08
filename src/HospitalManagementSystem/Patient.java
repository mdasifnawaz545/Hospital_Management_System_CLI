package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Patient {
        String  addPatientQuery="INSERT INTO Patient (p_name,age,gender) VALUES (?,?,?);";
        String viewAllPatientQuery="SELECT * FROM Patient;";
        String viewPatientQuery = "SELECT * FROM Patient WHERE p_id=?;";
        Scanner scanner=new Scanner(System.in);
        Connection connection;
        String p_name;
        String gender;
        int age;
        public Patient(Connection connection){
            this.connection = connection;
        }
        public void addPatient(){
            System.out.println("Enter the Name of the Patient: ");
            p_name=scanner.nextLine();
            System.out.println("Enter Age: ");
            age=scanner.nextInt();
            scanner.nextLine();
            System.out.println("Enter Gender(M/F): ");
            gender= scanner.nextLine();
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(addPatientQuery);
                preparedStatement.setString(1,p_name);
                preparedStatement.setInt(2,age);
                preparedStatement.setString(3,gender);
                int rowsAffected=preparedStatement.executeUpdate();
                if(rowsAffected>0) System.out.println(p_name+" was added as a Patient Successfully.");
                else System.out.println("Patient Not Added.");
            }catch(SQLException sqe){
                System.out.println(sqe);
            }
        }
        public void showAllPatient(){
            try{
                PreparedStatement preparedStatement=connection.prepareStatement(viewAllPatientQuery);
                ResultSet resultSet=preparedStatement.executeQuery();
                System.out.println("+------------+--------------------------+---------+---------+");
                System.out.println("| Patient ID | Name                     | Age     | Gender  |");
                System.out.println("+------------+--------------------------+---------+---------+");
                while(resultSet.next()){
                    int id=resultSet.getInt("p_id");
                    String name=resultSet.getString("p_name");
                    int age=resultSet.getInt("age");
                    String gender=resultSet.getString("gender");
                    System.out.printf("| %-10d | %-24s | %-7d | %-7s |",id,name,age,gender);
                    System.out.println();
                }
                System.out.println("+------------+--------------------------+---------+---------+");
            }catch (SQLException sqe){
                System.out.println(sqe);
            }
        }
        public boolean viewPatient(){
            try{
                PreparedStatement preparedStatement=connection.prepareStatement(viewPatientQuery);
                System.out.println("Enter Patient Id: ");
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

