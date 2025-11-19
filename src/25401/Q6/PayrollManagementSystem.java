import java.util.*;
import java.time.LocalDate;

// System 6: Payroll Management System (RSSB)
class PayrollEntity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public PayrollEntity(int id, LocalDate createdDate, LocalDate updatedDate) {
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

class PayrollOrganization extends PayrollEntity {
    private String orgName;
    private String orgCode;
    private String rssbNumber;
    private String contactEmail;

    public PayrollOrganization(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail) {
        super(id, createdDate, updatedDate);
        if (rssbNumber.length() != 8 || !rssbNumber.matches("\\d+")) 
            throw new IllegalArgumentException("RSSB number must be 8 digits");
        if (!contactEmail.contains("@")) throw new IllegalArgumentException("Invalid email");
        if (orgCode.length() < 3) throw new IllegalArgumentException("Org code must be >= 3 chars");
        this.orgName = orgName;
        this.orgCode = orgCode;
        this.rssbNumber = rssbNumber;
        this.contactEmail = contactEmail;
    }

    public String getOrgName() { return orgName; }
    public String getOrgCode() { return orgCode; }
    public String getRssbNumber() { return rssbNumber; }
    public String getContactEmail() { return contactEmail; }
}

class PayrollDepartment extends PayrollOrganization {
    private String deptName;
    private String deptCode;
    private String managerName;

    public PayrollDepartment(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail);
        if (deptCode.length() < 3) throw new IllegalArgumentException("Dept code must be >= 3 chars");
        if (deptName.isEmpty() || managerName.isEmpty()) 
            throw new IllegalArgumentException("Names cannot be empty");
        this.deptName = deptName;
        this.deptCode = deptCode;
        this.managerName = managerName;
    }

    public String getDeptName() { return deptName; }
    public String getDeptCode() { return deptCode; }
    public String getManagerName() { return managerName; }
}

class Employee extends PayrollDepartment {
    private int employeeID;
    private String fullName;
    private String position;
    private double baseSalary;
    private boolean rssbRegistered;

    public Employee(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName);
        if (employeeID < 1000) throw new IllegalArgumentException("Employee ID must be >= 1000");
        if (baseSalary <= 0) throw new IllegalArgumentException("Base salary must be > 0");
        this.employeeID = employeeID;
        this.fullName = fullName;
        this.position = position;
        this.baseSalary = baseSalary;
        this.rssbRegistered = rssbRegistered;
    }

    public int getEmployeeID() { return employeeID; }
    public String getFullName() { return fullName; }
    public String getPosition() { return position; }
    public double getBaseSalary() { return baseSalary; }
    public boolean isRssbRegistered() { return rssbRegistered; }
}

class PayrollPeriod extends Employee {
    private int month;
    private int year;
    private LocalDate startDate;
    private LocalDate endDate;

    public PayrollPeriod(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, LocalDate startDate, LocalDate endDate) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered);
        if (month < 1 || month > 12) throw new IllegalArgumentException("Month must be 1-12");
        if (year < 2000) throw new IllegalArgumentException("Year must be >= 2000");
        if (startDate == null || endDate == null) throw new IllegalArgumentException("Dates cannot be null");
        this.month = month;
        this.year = year;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getMonth() { return month; }
    public int getYear() { return year; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
}

class SalaryStructure extends PayrollPeriod {
    private double basicPay;
    private double transportAllowance;
    private double housingAllowance;

    public SalaryStructure(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, LocalDate startDate, LocalDate endDate, double basicPay, double transportAllowance, double housingAllowance) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate);
        if (basicPay < 0 || transportAllowance < 0 || housingAllowance < 0) 
            throw new IllegalArgumentException("All amounts must be >= 0");
        this.basicPay = basicPay;
        this.transportAllowance = transportAllowance;
        this.housingAllowance = housingAllowance;
    }

    public double getBasicPay() { return basicPay; }
    public double getTransportAllowance() { return transportAllowance; }
    public double getHousingAllowance() { return housingAllowance; }
}

class Deduction extends SalaryStructure {
    private double rssbContribution;
    private double payeTax;
    private double loanDeduction;

    public Deduction(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, LocalDate startDate, LocalDate endDate, double basicPay, double transportAllowance, double housingAllowance, double rssbContribution, double payeTax, double loanDeduction) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance);
        if (rssbContribution < 0 || payeTax < 0 || loanDeduction < 0) 
            throw new IllegalArgumentException("Deductions must be >= 0");
        this.rssbContribution = rssbContribution;
        this.payeTax = payeTax;
        this.loanDeduction = loanDeduction;
    }

    public double getRssbContribution() { return rssbContribution; }
    public double getPayeTax() { return payeTax; }
    public double getLoanDeduction() { return loanDeduction; }
}

class Allowance extends Deduction {
    private double overtimeHours;
    private double overtimeRate;
    private double bonus;

    public Allowance(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, LocalDate startDate, LocalDate endDate, double basicPay, double transportAllowance, double housingAllowance, double rssbContribution, double payeTax, double loanDeduction, double overtimeHours, double overtimeRate, double bonus) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance, rssbContribution, payeTax, loanDeduction);
        if (overtimeHours < 0 || overtimeRate < 0 || bonus < 0) 
            throw new IllegalArgumentException("Values must be >= 0");
        this.overtimeHours = overtimeHours;
        this.overtimeRate = overtimeRate;
        this.bonus = bonus;
    }

    public double getOvertimeHours() { return overtimeHours; }
    public double getOvertimeRate() { return overtimeRate; }
    public double getBonus() { return bonus; }
}

class Payroll extends Allowance {
    private double grossSalary;
    private double totalDeductions;
    private double netSalary;

    public Payroll(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, LocalDate startDate, LocalDate endDate, double basicPay, double transportAllowance, double housingAllowance, double rssbContribution, double payeTax, double loanDeduction, double overtimeHours, double overtimeRate, double bonus, double grossSalary, double totalDeductions, double netSalary) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance, rssbContribution, payeTax, loanDeduction, overtimeHours, overtimeRate, bonus);
        this.grossSalary = grossSalary;
        this.totalDeductions = totalDeductions;
        this.netSalary = netSalary;
    }

    public double getGrossSalary() { return grossSalary; }
    public double getTotalDeductions() { return totalDeductions; }
    public double getNetSalary() { return netSalary; }
}

final class Payslip extends Payroll {
    private String payslipNumber;
    private LocalDate issueDate;

    public Payslip(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String orgCode, String rssbNumber, String contactEmail, String deptName, String deptCode, String managerName, int employeeID, String fullName, String position, double baseSalary, boolean rssbRegistered, int month, int year, LocalDate startDate, LocalDate endDate, double basicPay, double transportAllowance, double housingAllowance, double rssbContribution, double payeTax, double loanDeduction, double overtimeHours, double overtimeRate, double bonus, double grossSalary, double totalDeductions, double netSalary, String payslipNumber, LocalDate issueDate) {
        super(id, createdDate, updatedDate, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, startDate, endDate, basicPay, transportAllowance, housingAllowance, rssbContribution, payeTax, loanDeduction, overtimeHours, overtimeRate, bonus, grossSalary, totalDeductions, netSalary);
        this.payslipNumber = payslipNumber;
        this.issueDate = issueDate;
    }

    public String generatePayslip() {
        double computedRSSB = getBasicPay() * 0.05;
        double computedPAYE = getGrossSalary() * 0.15;
        double computedNet = getGrossSalary() - getTotalDeductions();
        
        return String.format("PAYSLIP - Employee: %s, RSSB: $%.2f, PAYE: $%.2f, Net: $%.2f", 
                           getFullName(), computedRSSB, computedPAYE, computedNet);
    }

    public String getPayslipNumber() { return payslipNumber; }
    public LocalDate getIssueDate() { return issueDate; }
}

public class PayrollManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Payroll Management System (RSSB) ===");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter organization name: ");
        String orgName = scanner.nextLine();
        
        System.out.print("Enter org code: ");
        String orgCode = scanner.nextLine();
        
        System.out.print("Enter RSSB number (8 digits): ");
        String rssbNumber = scanner.nextLine();
        
        System.out.print("Enter contact email: ");
        String contactEmail = scanner.nextLine();
        
        System.out.print("Enter department name: ");
        String deptName = scanner.nextLine();
        
        System.out.print("Enter department code: ");
        String deptCode = scanner.nextLine();
        
        System.out.print("Enter manager name: ");
        String managerName = scanner.nextLine();
        
        System.out.print("Enter employee ID (>=1000): ");
        int employeeID = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter full name: ");
        String fullName = scanner.nextLine();
        
        System.out.print("Enter position: ");
        String position = scanner.nextLine();
        
        System.out.print("Enter base salary: ");
        double baseSalary = scanner.nextDouble();
        
        System.out.print("Is RSSB registered (true/false): ");
        boolean rssbRegistered = scanner.nextBoolean();
        
        System.out.print("Enter month (1-12): ");
        int month = scanner.nextInt();
        
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        
        System.out.print("Enter basic pay: ");
        double basicPay = scanner.nextDouble();
        
        System.out.print("Enter transport allowance: ");
        double transportAllowance = scanner.nextDouble();
        
        System.out.print("Enter housing allowance: ");
        double housingAllowance = scanner.nextDouble();
        
        System.out.print("Enter RSSB contribution: ");
        double rssbContribution = scanner.nextDouble();
        
        System.out.print("Enter PAYE tax: ");
        double payeTax = scanner.nextDouble();
        
        System.out.print("Enter loan deduction: ");
        double loanDeduction = scanner.nextDouble();
        
        System.out.print("Enter overtime hours: ");
        double overtimeHours = scanner.nextDouble();
        
        System.out.print("Enter overtime rate: ");
        double overtimeRate = scanner.nextDouble();
        
        System.out.print("Enter bonus: ");
        double bonus = scanner.nextDouble();
        
        System.out.print("Enter gross salary: ");
        double grossSalary = scanner.nextDouble();
        
        System.out.print("Enter total deductions: ");
        double totalDeductions = scanner.nextDouble();
        
        System.out.print("Enter net salary: ");
        double netSalary = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter payslip number: ");
        String payslipNumber = scanner.nextLine();
        
        LocalDate now = LocalDate.now();
        
        Payslip payslip = new Payslip(id, now, now, orgName, orgCode, rssbNumber, contactEmail, deptName, deptCode, managerName, employeeID, fullName, position, baseSalary, rssbRegistered, month, year, now, now, basicPay, transportAllowance, housingAllowance, rssbContribution, payeTax, loanDeduction, overtimeHours, overtimeRate, bonus, grossSalary, totalDeductions, netSalary, payslipNumber, now);
        
        System.out.println("\n=== PAYROLL MANAGEMENT DATA ===");
        System.out.println("ID: " + payslip.getId());
        System.out.println("Organization: " + payslip.getOrgName());
        System.out.println("Org Code: " + payslip.getOrgCode());
        System.out.println("RSSB Number: " + payslip.getRssbNumber());
        System.out.println("Contact Email: " + payslip.getContactEmail());
        System.out.println("Department: " + payslip.getDeptName());
        System.out.println("Dept Code: " + payslip.getDeptCode());
        System.out.println("Manager: " + payslip.getManagerName());
        System.out.println("Employee ID: " + payslip.getEmployeeID());
        System.out.println("Full Name: " + payslip.getFullName());
        System.out.println("Position: " + payslip.getPosition());
        System.out.println("Base Salary: $" + payslip.getBaseSalary());
        System.out.println("RSSB Registered: " + payslip.isRssbRegistered());
        System.out.println("Month: " + payslip.getMonth());
        System.out.println("Year: " + payslip.getYear());
        System.out.println("Basic Pay: $" + payslip.getBasicPay());
        System.out.println("Transport Allowance: $" + payslip.getTransportAllowance());
        System.out.println("Housing Allowance: $" + payslip.getHousingAllowance());
        System.out.println("RSSB Contribution: $" + payslip.getRssbContribution());
        System.out.println("PAYE Tax: $" + payslip.getPayeTax());
        System.out.println("Loan Deduction: $" + payslip.getLoanDeduction());
        System.out.println("Overtime Hours: " + payslip.getOvertimeHours());
        System.out.println("Overtime Rate: $" + payslip.getOvertimeRate());
        System.out.println("Bonus: $" + payslip.getBonus());
        System.out.println("Gross Salary: $" + payslip.getGrossSalary());
        System.out.println("Total Deductions: $" + payslip.getTotalDeductions());
        System.out.println("Net Salary: $" + payslip.getNetSalary());
        System.out.println("Payslip Number: " + payslip.getPayslipNumber());
        System.out.println("\n" + payslip.generatePayslip());
        
        scanner.close();
    }
}