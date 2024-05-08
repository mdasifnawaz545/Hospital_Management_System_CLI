package HospitalManagementSystem;

public class Hospital {
    public static void main(String args[]){
        DatabaseConnection DBconnection = new DatabaseConnection();
        Patient patient = new Patient(DBconnection.connection);
        Doctor doctor = new Doctor(DBconnection.connection);
        MainProcess MP=new MainProcess(patient,doctor, DBconnection.connection);

    }
}
