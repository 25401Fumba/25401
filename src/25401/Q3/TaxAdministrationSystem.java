import java.util.*;
import java.time.LocalDate;

// System 3: Tax Administration System
class TaxDataException extends Exception {
    public TaxDataException(String message) {
        super(message);
    }
}

class TaxEntity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public TaxEntity(int id, LocalDate createdDate, LocalDate updatedDate) throws TaxDataException {
        if (id <= 0) throw new TaxDataException("ID must be > 0");
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getId() { return id; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }
}

class TaxAuthority extends TaxEntity {
    private String authorityName;
    private String region;
    private String email;

    public TaxAuthority(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email) throws TaxDataException {
        super(id, createdDate, updatedDate);
        if (!email.contains("@")) throw new TaxDataException("Invalid email");
        this.authorityName = authorityName;
        this.region = region;
        this.email = email;
    }

    public String getAuthorityName() { return authorityName; }
    public String getRegion() { return region; }
    public String getEmail() { return email; }
}

class TaxCategory extends TaxAuthority {
    private String categoryName;
    private double rate;
    private String code;

    public TaxCategory(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email);
        if (rate <= 0) throw new TaxDataException("Rate must be > 0");
        if (code.length() < 3) throw new TaxDataException("Code must be >= 3 chars");
        this.categoryName = categoryName;
        this.rate = rate;
        this.code = code;
    }

    public String getCategoryName() { return categoryName; }
    public double getRate() { return rate; }
    public String getCode() { return code; }
}

class Taxpayer extends TaxCategory {
    private String tin;
    private String taxpayerName;
    private String address;

    public Taxpayer(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code);
        if (tin.length() != 9 || !tin.matches("\\d+")) throw new TaxDataException("TIN must be 9 digits");
        if (taxpayerName.isEmpty()) throw new TaxDataException("Name cannot be empty");
        this.tin = tin;
        this.taxpayerName = taxpayerName;
        this.address = address;
    }

    public String getTin() { return tin; }
    public String getTaxpayerName() { return taxpayerName; }
    public String getAddress() { return address; }
}

class Employer extends Taxpayer {
    private String employerName;
    private String employerTIN;
    private String contact;

    public Employer(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address, String employerName, String employerTIN, String contact) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address);
        if (employerTIN.length() != 9 || !employerTIN.matches("\\d+")) throw new TaxDataException("Employer TIN must be 9 digits");
        if (contact.length() != 10 || !contact.matches("\\d+")) throw new TaxDataException("Phone must be 10 digits");
        this.employerName = employerName;
        this.employerTIN = employerTIN;
        this.contact = contact;
    }

    public String getEmployerName() { return employerName; }
    public String getEmployerTIN() { return employerTIN; }
    public String getContact() { return contact; }
}

class Employee extends Employer {
    private String employeeName;
    private double salary;
    private String employeeTIN;

    public Employee(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address, String employerName, String employerTIN, String contact, String employeeName, double salary, String employeeTIN) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact);
        if (salary <= 0) throw new TaxDataException("Salary must be > 0");
        if (employeeTIN.length() != 9 || !employeeTIN.matches("\\d+")) throw new TaxDataException("Employee TIN must be 9 digits");
        this.employeeName = employeeName;
        this.salary = salary;
        this.employeeTIN = employeeTIN;
    }

    public String getEmployeeName() { return employeeName; }
    public double getSalary() { return salary; }
    public String getEmployeeTIN() { return employeeTIN; }
}

class TaxDeclaration extends Employee {
    private String declarationMonth;
    private double totalIncome;

    public TaxDeclaration(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address, String employerName, String employerTIN, String contact, String employeeName, double salary, String employeeTIN, String declarationMonth, double totalIncome) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN);
        if (totalIncome < 0) throw new TaxDataException("Income must be >= 0");
        this.declarationMonth = declarationMonth;
        this.totalIncome = totalIncome;
    }

    public String getDeclarationMonth() { return declarationMonth; }
    public double getTotalIncome() { return totalIncome; }
}

class TaxAssessment extends TaxDeclaration {
    private LocalDate assessmentDate;
    private double assessedTax;

    public TaxAssessment(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address, String employerName, String employerTIN, String contact, String employeeName, double salary, String employeeTIN, String declarationMonth, double totalIncome, LocalDate assessmentDate, double assessedTax) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome);
        if (assessedTax < 0) throw new TaxDataException("Tax must be >= 0");
        this.assessmentDate = assessmentDate;
        this.assessedTax = assessedTax;
    }

    public LocalDate getAssessmentDate() { return assessmentDate; }
    public double getAssessedTax() { return assessedTax; }
}

class TaxPayment extends TaxAssessment {
    private LocalDate paymentDate;
    private double paymentAmount;

    public TaxPayment(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address, String employerName, String employerTIN, String contact, String employeeName, double salary, String employeeTIN, String declarationMonth, double totalIncome, LocalDate assessmentDate, double assessedTax, LocalDate paymentDate, double paymentAmount) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome, assessmentDate, assessedTax);
        if (paymentAmount <= 0) throw new TaxDataException("Payment amount must be > 0");
        this.paymentDate = paymentDate;
        this.paymentAmount = paymentAmount;
    }

    public LocalDate getPaymentDate() { return paymentDate; }
    public double getPaymentAmount() { return paymentAmount; }
}

final class TaxRecord extends TaxPayment {
    private String receiptNo;
    private double totalTax;

    public TaxRecord(int id, LocalDate createdDate, LocalDate updatedDate, String authorityName, String region, String email, String categoryName, double rate, String code, String tin, String taxpayerName, String address, String employerName, String employerTIN, String contact, String employeeName, double salary, String employeeTIN, String declarationMonth, double totalIncome, LocalDate assessmentDate, double assessedTax, LocalDate paymentDate, double paymentAmount, String receiptNo, double totalTax) throws TaxDataException {
        super(id, createdDate, updatedDate, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome, assessmentDate, assessedTax, paymentDate, paymentAmount);
        this.receiptNo = receiptNo;
        this.totalTax = totalTax;
    }

    public double computeTax() {
        double credits = 50000; // Standard credit
        return (getSalary() * getRate()) - credits;
    }

    public String getReceiptNo() { return receiptNo; }
    public double getTotalTax() { return totalTax; }
}

public class TaxAdministrationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("=== Tax Administration System ===");
            System.out.print("Enter ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            
            System.out.print("Enter authority name: ");
            String authorityName = scanner.nextLine();
            
            System.out.print("Enter region: ");
            String region = scanner.nextLine();
            
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            
            System.out.print("Enter category name: ");
            String categoryName = scanner.nextLine();
            
            System.out.print("Enter tax rate: ");
            double rate = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Enter category code: ");
            String code = scanner.nextLine();
            
            System.out.print("Enter taxpayer TIN (9 digits): ");
            String tin = scanner.nextLine();
            
            System.out.print("Enter taxpayer name: ");
            String taxpayerName = scanner.nextLine();
            
            System.out.print("Enter address: ");
            String address = scanner.nextLine();
            
            System.out.print("Enter employer name: ");
            String employerName = scanner.nextLine();
            
            System.out.print("Enter employer TIN (9 digits): ");
            String employerTIN = scanner.nextLine();
            
            System.out.print("Enter contact (10 digits): ");
            String contact = scanner.nextLine();
            
            System.out.print("Enter employee name: ");
            String employeeName = scanner.nextLine();
            
            System.out.print("Enter salary: ");
            double salary = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Enter employee TIN (9 digits): ");
            String employeeTIN = scanner.nextLine();
            
            System.out.print("Enter declaration month: ");
            String declarationMonth = scanner.nextLine();
            
            System.out.print("Enter total income: ");
            double totalIncome = scanner.nextDouble();
            
            System.out.print("Enter assessed tax: ");
            double assessedTax = scanner.nextDouble();
            
            System.out.print("Enter payment amount: ");
            double paymentAmount = scanner.nextDouble();
            scanner.nextLine();
            
            System.out.print("Enter receipt number: ");
            String receiptNo = scanner.nextLine();
            
            System.out.print("Enter total tax: ");
            double totalTax = scanner.nextDouble();
            
            LocalDate now = LocalDate.now();
            
            TaxRecord record = new TaxRecord(id, now, now, authorityName, region, email, categoryName, rate, code, tin, taxpayerName, address, employerName, employerTIN, contact, employeeName, salary, employeeTIN, declarationMonth, totalIncome, now, assessedTax, now, paymentAmount, receiptNo, totalTax);
            
            System.out.println("\n=== TAX ADMINISTRATION DATA ===");
            System.out.println("ID: " + record.getId());
            System.out.println("Authority: " + record.getAuthorityName());
            System.out.println("Region: " + record.getRegion());
            System.out.println("Email: " + record.getEmail());
            System.out.println("Category: " + record.getCategoryName());
            System.out.println("Rate: " + record.getRate());
            System.out.println("Code: " + record.getCode());
            System.out.println("Taxpayer TIN: " + record.getTin());
            System.out.println("Taxpayer Name: " + record.getTaxpayerName());
            System.out.println("Address: " + record.getAddress());
            System.out.println("Employer: " + record.getEmployerName());
            System.out.println("Employer TIN: " + record.getEmployerTIN());
            System.out.println("Contact: " + record.getContact());
            System.out.println("Employee: " + record.getEmployeeName());
            System.out.println("Salary: $" + record.getSalary());
            System.out.println("Employee TIN: " + record.getEmployeeTIN());
            System.out.println("Declaration Month: " + record.getDeclarationMonth());
            System.out.println("Total Income: $" + record.getTotalIncome());
            System.out.println("Assessed Tax: $" + record.getAssessedTax());
            System.out.println("Payment Amount: $" + record.getPaymentAmount());
            System.out.println("Receipt No: " + record.getReceiptNo());
            System.out.println("Total Tax: $" + record.getTotalTax());
            System.out.println("Computed Tax: $" + record.computeTax());
            
        } catch (TaxDataException e) {
            System.out.println("Tax Data Error: " + e.getMessage());
        }
        
        scanner.close();
    }
}