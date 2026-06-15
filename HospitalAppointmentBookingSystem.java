import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;

class Doctor {
    int id;
    String name;
    String specialization;

    Doctor(int id, String name, String specialization) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
    }

    @Override
    public String toString() {
        return "Dr. " + name + " (" + specialization + ")";
    }
}

class Patient {
    int id;
    String name;
    int age;

    Patient(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}

class Appointment {
    Doctor doctor;
    Patient patient;
    String slot;
    boolean booked;

    Appointment(Doctor doctor, Patient patient, String slot) {
        this.doctor = doctor;
        this.patient = patient;
        this.slot = slot;
        booked = true;
    }

    @Override
    public String toString() {
        return "Doctor: " + doctor.name +
                " | Patient: " + patient.name +
                " | Slot: " + slot +
                " | Status: " + (booked ? "Booked" : "Cancelled");
    }
}

class AppointmentManager {

    ArrayList<Appointment> appointments = new ArrayList<>();

    public boolean checkAvailability(Doctor doctor, String slot) {
        for (Appointment a : appointments) {
            if (a.booked && a.doctor.id == doctor.id &&
                    a.slot.equalsIgnoreCase(slot)) {
                return false;
            }
        }
        return true;
    }

    public void bookAppointment(Doctor doctor, Patient patient, String slot) {

        if (checkAvailability(doctor, slot)) {
            appointments.add(new Appointment(doctor, patient, slot));
            System.out.println("\nAppointment Booked Successfully.");
        } else {
            System.out.println("\nSlot Already Booked.");
        }
    }

    public void cancelAppointment(String slot) {

        for (Appointment a : appointments) {
            if (a.slot.equalsIgnoreCase(slot) && a.booked) {
                a.booked = false;
                System.out.println("\nAppointment Cancelled.");
                return;
            }
        }

        System.out.println("\nAppointment Not Found.");
    }

    public void displayAppointments() {

        if (appointments.isEmpty()) {
            System.out.println("\nNo Appointments Found.");
            return;
        }

        System.out.println("\n----- Appointments -----");

        for (Appointment a : appointments) {
            System.out.println(a);
        }
    }
}
public class HospitalAppointmentBookingSystem {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Doctor d1 = new Doctor(1, "Ashish", "Cardiologist");
        Doctor d2 = new Doctor(2, "Rahul", "Dentist");

        AppointmentManager manager = new AppointmentManager();

        while (true) {

            System.out.println("\n========== Hospital Appointment Booking System ==========");
            System.out.println("1. Book Appointment");
            System.out.println("2. Cancel Appointment");
            System.out.println("3. View Appointments");
            System.out.println("4. Exit");
            System.out.print("Enter Choice: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {

                case 1:

                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter Patient Name: ");
                    String pname = sc.nextLine();

                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();

                    Patient patient = new Patient(pid, pname, age);

                    System.out.println("\nAvailable Doctors");
                    System.out.println("1. " + d1);
                    System.out.println("2. " + d2);

                    System.out.print("Choose Doctor (1/2): ");
                    int docChoice = sc.nextInt();
                    sc.nextLine();

                    Doctor selectedDoctor;

                    if (docChoice == 1)
                        selectedDoctor = d1;
                    else
                        selectedDoctor = d2;

                    System.out.print("Enter Time Slot (10AM/11AM/12PM/2PM): ");
                    String slot = sc.nextLine();

                    manager.bookAppointment(selectedDoctor, patient, slot);

                    break;

                case 2:

                    System.out.print("Enter Time Slot to Cancel: ");
                    String cancelSlot = sc.nextLine();

                    manager.cancelAppointment(cancelSlot);

                    break;

                case 3:

                    manager.displayAppointments();

                    break;

                case 4:

                    System.out.println("Thank You!");
                    sc.close();
                    System.exit(0);

                default:

                    System.out.println("Invalid Choice.");
            }
        }
    }
}

// ========== Hospital Appointment Booking System ==========
// 1. Book Appointment
// 2. Cancel Appointment
// 3. View Appointments
// 4. Exit
// Enter Choice: 1

// Enter Patient ID: 101
// Enter Patient Name: Rahul
// Enter Age: 20

// Available Doctors
// 1. Dr. Ashish (Cardiologist)
// 2. Dr. Rahul (Dentist)

// Choose Doctor (1/2): 1
// Enter Time Slot (10AM/11AM/12PM/2PM): 10AM

// Appointment Booked Successfully.
