package HospitalManagementSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

class MainProcess {
    Scanner scanner = new Scanner(System.in);
    Connection connection;
    Doctor doctor;
    Patient patient;
    int choice;

    public MainProcess(Patient patient, Doctor doctor, Connection connection) {
        this.connection = connection;
        this.doctor = doctor;
        this.patient = patient;
        while (true) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t-: HOSPITAL MANAGEMENT SYSTEM :-");
            System.out.println("1. Add Patient");
            System.out.println("2. View Patient");
            System.out.println("3. Show Patient");
            System.out.println("4. View Doctor");
            System.out.println("5. Show Doctors");
            System.out.println("6. Book Appointment");
            System.out.println("7. Exit");
            System.out.println("Enter Your Choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    patient.addPatient();
                    break;
                case 2:
                    if (patient.viewPatient()) System.out.println("Patient Exists.(Confidential)");
                    else System.out.println("Patient Doesn't Exists.");
                    break;
                case 3:
                    patient.showAllPatient();
                    break;
                case 4:
                    if (doctor.viewDoctor()) System.out.println("Doctor is Available.");
                    else System.out.println("Doctor is not Available.");
                    break;
                case 5:
                    doctor.viewAllDoctor();
                    break;
                case 6:
                    bookAppointment();
                    break;
                case 7:
                    System.out.print("Exiting From the Hospital Management System");
                    for (int i = 0; i < 3; i++) {
                        System.out.print(".");
                        try {
                            Thread.sleep(500);
                        } catch (InterruptedException ie) {
                            System.out.println(ie);
                        }
                    }
                    System.out.println();
                    System.out.println("Thanks For Choosing this Facility.Visit Again :)");
                    System.exit(1);
                default:
                    System.out.println("Invalid Choice, Please Enter a Valid Choice Again!");
            }
        }
    }

    public void bookAppointment() {
        int p_id, d_id;
        String date;
        System.out.println("Enter Patient ID: ");
        p_id = scanner.nextInt();
        System.out.println("Enter Doctor ID: ");
        d_id = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter Appointment Date(YYYY-MM-DD): ");
        date = scanner.nextLine();
        if (checkAvailability(d_id, date)) {
            String bookQuery = "INSERT INTO Appointment (p_id,d_id,app_date) VALUES (?,?,?);";
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(bookQuery);
                preparedStatement.setInt(1, p_id);
                preparedStatement.setInt(2, d_id);
                preparedStatement.setString(3, date);
                int rowsAffected = preparedStatement.executeUpdate();
                if(rowsAffected>0) System.out.println("Appointment Booked.");
            } catch (SQLException sqe) {
                System.out.println(sqe);
            }
        } else System.out.println("Appointment Not Booked Due to Unavailability of Doctor or Patient Doesn't Exists.");
    }

    public boolean checkAvailability(int d_id, String date) {
        String checkAppointmentQuery = "SELECT COUNT(*) FROM Appointment WHERE d_id=? AND app_date=?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(checkAppointmentQuery);
            preparedStatement.setInt(1, d_id);
            preparedStatement.setString(2, date);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                if (count == 0) return true;
                else return false;
            }
        } catch (SQLException sqe) {
            System.out.println(sqe);
        }
        return false;
    }
}
