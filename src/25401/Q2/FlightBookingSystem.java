import java.util.*;
import java.time.LocalDate;

// System 2: Flight Booking System
class FlightEntity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public FlightEntity(int id, LocalDate createdDate, LocalDate updatedDate) {
        if (id <= 0) throw new IllegalArgumentException("ID must be > 0");
        if (createdDate == null || updatedDate == null) throw new IllegalArgumentException("Dates cannot be null");
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getId() { return id; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }
}

class Airport extends FlightEntity {
    private String airportName;
    private String code;
    private String location;

    public Airport(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location) {
        super(id, createdDate, updatedDate);
        if (code.length() != 3 || !code.matches("[A-Z]+")) 
            throw new IllegalArgumentException("Code must be 3 uppercase letters");
        this.airportName = airportName;
        this.code = code;
        this.location = location;
    }

    public String getAirportName() { return airportName; }
    public String getCode() { return code; }
    public String getLocation() { return location; }
}

class Airline extends Airport {
    private String airlineName;
    private String airlineCode;
    private String contactEmail;

    public Airline(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail) {
        super(id, createdDate, updatedDate, airportName, code, location);
        if (airlineCode.length() < 2 || airlineCode.length() > 4 || !airlineCode.matches("[A-Za-z]+")) 
            throw new IllegalArgumentException("Airline code must be 2-4 letters");
        if (!contactEmail.contains("@")) throw new IllegalArgumentException("Invalid email");
        this.airlineName = airlineName;
        this.airlineCode = airlineCode;
        this.contactEmail = contactEmail;
    }

    public String getAirlineName() { return airlineName; }
    public String getAirlineCode() { return airlineCode; }
    public String getContactEmail() { return contactEmail; }
}

class Flight extends Airline {
    private String flightNumber;
    private String departure;
    private String destination;
    private double baseFare;

    public Flight(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail);
        if (baseFare <= 0) throw new IllegalArgumentException("Fare must be > 0");
        if (flightNumber.isEmpty() || departure.isEmpty() || destination.isEmpty()) 
            throw new IllegalArgumentException("Fields cannot be empty");
        this.flightNumber = flightNumber;
        this.departure = departure;
        this.destination = destination;
        this.baseFare = baseFare;
    }

    public String getFlightNumber() { return flightNumber; }
    public String getDeparture() { return departure; }
    public String getDestination() { return destination; }
    public double getBaseFare() { return baseFare; }
}

class Pilot extends Flight {
    private String pilotName;
    private String licenseNumber;
    private int experienceYears;

    public Pilot(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare, String pilotName, String licenseNumber, int experienceYears) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare);
        if (licenseNumber.isEmpty()) throw new IllegalArgumentException("License cannot be empty");
        if (experienceYears < 2) throw new IllegalArgumentException("Experience must be >= 2 years");
        this.pilotName = pilotName;
        this.licenseNumber = licenseNumber;
        this.experienceYears = experienceYears;
    }

    public String getPilotName() { return pilotName; }
    public String getLicenseNumber() { return licenseNumber; }
    public int getExperienceYears() { return experienceYears; }
}

class CabinCrew extends Pilot {
    private String crewName;
    private String role;
    private String shift;

    public CabinCrew(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare, String pilotName, String licenseNumber, int experienceYears, String crewName, String role, String shift) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears);
        if (role.isEmpty()) throw new IllegalArgumentException("Role cannot be empty");
        if (!shift.equals("Day") && !shift.equals("Night")) 
            throw new IllegalArgumentException("Shift must be Day or Night");
        this.crewName = crewName;
        this.role = role;
        this.shift = shift;
    }

    public String getCrewName() { return crewName; }
    public String getRole() { return role; }
    public String getShift() { return shift; }
}

class Passenger extends CabinCrew {
    private String passengerName;
    private int age;
    private String gender;
    private String contact;

    public Passenger(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare, String pilotName, String licenseNumber, int experienceYears, String crewName, String role, String shift, String passengerName, int age, String gender, String contact) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift);
        if (age <= 0) throw new IllegalArgumentException("Age must be > 0");
        if (!gender.equals("Male") && !gender.equals("Female")) 
            throw new IllegalArgumentException("Gender must be Male or Female");
        this.passengerName = passengerName;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    public String getPassengerName() { return passengerName; }
    public int getAge() { return age; }
    public String getGender() { return gender; }
    public String getContact() { return contact; }
}

class Booking extends Passenger {
    private LocalDate bookingDate;
    private String seatNumber;
    private String travelClass;

    public Booking(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare, String pilotName, String licenseNumber, int experienceYears, String crewName, String role, String shift, String passengerName, int age, String gender, String contact, LocalDate bookingDate, String seatNumber, String travelClass) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact);
        if (!travelClass.equals("Economy") && !travelClass.equals("Business") && !travelClass.equals("First")) 
            throw new IllegalArgumentException("Class must be Economy/Business/First");
        this.bookingDate = bookingDate;
        this.seatNumber = seatNumber;
        this.travelClass = travelClass;
    }

    public LocalDate getBookingDate() { return bookingDate; }
    public String getSeatNumber() { return seatNumber; }
    public String getTravelClass() { return travelClass; }
}

class Payment extends Booking {
    private LocalDate paymentDate;
    private String paymentMethod;
    private double amountPaid;

    public Payment(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare, String pilotName, String licenseNumber, int experienceYears, String crewName, String role, String shift, String passengerName, int age, String gender, String contact, LocalDate bookingDate, String seatNumber, String travelClass, LocalDate paymentDate, String paymentMethod, double amountPaid) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact, bookingDate, seatNumber, travelClass);
        if (amountPaid <= 0) throw new IllegalArgumentException("Amount must be > 0");
        if (paymentMethod.isEmpty()) throw new IllegalArgumentException("Payment method cannot be empty");
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.amountPaid = amountPaid;
    }

    public LocalDate getPaymentDate() { return paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public double getAmountPaid() { return amountPaid; }
}

final class Ticket extends Payment {
    private String ticketNumber;
    private LocalDate issueDate;

    public Ticket(int id, LocalDate createdDate, LocalDate updatedDate, String airportName, String code, String location, String airlineName, String airlineCode, String contactEmail, String flightNumber, String departure, String destination, double baseFare, String pilotName, String licenseNumber, int experienceYears, String crewName, String role, String shift, String passengerName, int age, String gender, String contact, LocalDate bookingDate, String seatNumber, String travelClass, LocalDate paymentDate, String paymentMethod, double amountPaid, String ticketNumber, LocalDate issueDate) {
        super(id, createdDate, updatedDate, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact, bookingDate, seatNumber, travelClass, paymentDate, paymentMethod, amountPaid);
        this.ticketNumber = ticketNumber;
        this.issueDate = issueDate;
    }

    public double calculateFare() {
        double taxes = getBaseFare() * 0.15;
        double discount = getTravelClass().equals("Economy") ? 0 : getBaseFare() * 0.1;
        return getBaseFare() + taxes - discount;
    }

    public String getTicketNumber() { return ticketNumber; }
    public LocalDate getIssueDate() { return issueDate; }
}

public class FlightBookingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Flight Booking System ===");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter airport name: ");
        String airportName = scanner.nextLine();
        
        System.out.print("Enter airport code (3 uppercase letters): ");
        String code = scanner.nextLine();
        
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter airline name: ");
        String airlineName = scanner.nextLine();
        
        System.out.print("Enter airline code (2-4 letters): ");
        String airlineCode = scanner.nextLine();
        
        System.out.print("Enter contact email: ");
        String contactEmail = scanner.nextLine();
        
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        
        System.out.print("Enter departure: ");
        String departure = scanner.nextLine();
        
        System.out.print("Enter destination: ");
        String destination = scanner.nextLine();
        
        System.out.print("Enter base fare: ");
        double baseFare = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter pilot name: ");
        String pilotName = scanner.nextLine();
        
        System.out.print("Enter license number: ");
        String licenseNumber = scanner.nextLine();
        
        System.out.print("Enter experience years: ");
        int experienceYears = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter crew name: ");
        String crewName = scanner.nextLine();
        
        System.out.print("Enter role: ");
        String role = scanner.nextLine();
        
        System.out.print("Enter shift (Day/Night): ");
        String shift = scanner.nextLine();
        
        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();
        
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter gender (Male/Female): ");
        String gender = scanner.nextLine();
        
        System.out.print("Enter contact: ");
        String contact = scanner.nextLine();
        
        System.out.print("Enter seat number: ");
        String seatNumber = scanner.nextLine();
        
        System.out.print("Enter travel class (Economy/Business/First): ");
        String travelClass = scanner.nextLine();
        
        System.out.print("Enter payment method: ");
        String paymentMethod = scanner.nextLine();
        
        System.out.print("Enter amount paid: ");
        double amountPaid = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter ticket number: ");
        String ticketNumber = scanner.nextLine();
        
        LocalDate now = LocalDate.now();
        
        Ticket ticket = new Ticket(id, now, now, airportName, code, location, airlineName, airlineCode, contactEmail, flightNumber, departure, destination, baseFare, pilotName, licenseNumber, experienceYears, crewName, role, shift, passengerName, age, gender, contact, now, seatNumber, travelClass, now, paymentMethod, amountPaid, ticketNumber, now);
        
        System.out.println("\n=== FLIGHT BOOKING DATA ===");
        System.out.println("ID: " + ticket.getId());
        System.out.println("Airport: " + ticket.getAirportName());
        System.out.println("Airport Code: " + ticket.getCode());
        System.out.println("Location: " + ticket.getLocation());
        System.out.println("Airline: " + ticket.getAirlineName());
        System.out.println("Airline Code: " + ticket.getAirlineCode());
        System.out.println("Contact Email: " + ticket.getContactEmail());
        System.out.println("Flight Number: " + ticket.getFlightNumber());
        System.out.println("Departure: " + ticket.getDeparture());
        System.out.println("Destination: " + ticket.getDestination());
        System.out.println("Base Fare: $" + ticket.getBaseFare());
        System.out.println("Pilot: " + ticket.getPilotName());
        System.out.println("License: " + ticket.getLicenseNumber());
        System.out.println("Experience: " + ticket.getExperienceYears() + " years");
        System.out.println("Crew: " + ticket.getCrewName());
        System.out.println("Role: " + ticket.getRole());
        System.out.println("Shift: " + ticket.getShift());
        System.out.println("Passenger: " + ticket.getPassengerName());
        System.out.println("Age: " + ticket.getAge());
        System.out.println("Gender: " + ticket.getGender());
        System.out.println("Contact: " + ticket.getContact());
        System.out.println("Seat: " + ticket.getSeatNumber());
        System.out.println("Class: " + ticket.getTravelClass());
        System.out.println("Payment Method: " + ticket.getPaymentMethod());
        System.out.println("Amount Paid: $" + ticket.getAmountPaid());
        System.out.println("Ticket Number: " + ticket.getTicketNumber());
        System.out.println("Final Fare: $" + ticket.calculateFare());
        
        scanner.close();
    }
}