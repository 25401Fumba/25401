import java.util.*;
import java.time.LocalDate;

// System 4: Procurement Management System
class ProcurementEntity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public ProcurementEntity(int id, LocalDate createdDate, LocalDate updatedDate) {
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

class Organization extends ProcurementEntity {
    private String orgName;
    private String address;
    private String contactEmail;

    public Organization(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail) {
        super(id, createdDate, updatedDate);
        if (!contactEmail.contains("@")) throw new IllegalArgumentException("Invalid email");
        this.orgName = orgName;
        this.address = address;
        this.contactEmail = contactEmail;
    }

    public String getOrgName() { return orgName; }
    public String getAddress() { return address; }
    public String getContactEmail() { return contactEmail; }
}

class Department extends Organization {
    private String deptName;
    private String deptCode;

    public Department(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail);
        if (deptCode.length() < 3 || !deptCode.matches("[a-zA-Z0-9]+")) 
            throw new IllegalArgumentException("Code must be alphanumeric and >= 3 chars");
        this.deptName = deptName;
        this.deptCode = deptCode;
    }

    public String getDeptName() { return deptName; }
    public String getDeptCode() { return deptCode; }
}

class ProcurementSupplier extends Department {
    private String supplierName;
    private String supplierTIN;
    private String contact;

    public ProcurementSupplier(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode);
        if (supplierTIN.length() != 9 || !supplierTIN.matches("\\d+")) 
            throw new IllegalArgumentException("TIN must be 9 digits");
        if (contact.length() != 10 || !contact.matches("\\d+")) 
            throw new IllegalArgumentException("Phone must be 10 digits");
        this.supplierName = supplierName;
        this.supplierTIN = supplierTIN;
        this.contact = contact;
    }

    public String getSupplierName() { return supplierName; }
    public String getSupplierTIN() { return supplierTIN; }
    public String getContact() { return contact; }
}

class ProcurementProduct extends ProcurementSupplier {
    private String productName;
    private double unitPrice;
    private int quantity;

    public ProcurementProduct(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact);
        if (unitPrice <= 0) throw new IllegalArgumentException("Unit price must be > 0");
        if (quantity < 0) throw new IllegalArgumentException("Quantity must be >= 0");
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.quantity = quantity;
    }

    public String getProductName() { return productName; }
    public double getUnitPrice() { return unitPrice; }
    public int getQuantity() { return quantity; }
}

class PurchaseOrder extends ProcurementProduct {
    private String poNumber;
    private LocalDate orderDate;
    private double totalAmount;

    public PurchaseOrder(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, LocalDate orderDate, double totalAmount) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity);
        if (totalAmount <= 0) throw new IllegalArgumentException("Total must be > 0");
        this.poNumber = poNumber;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
    }

    public String getPoNumber() { return poNumber; }
    public LocalDate getOrderDate() { return orderDate; }
    public double getTotalAmount() { return totalAmount; }
}

class Delivery extends PurchaseOrder {
    private LocalDate deliveryDate;
    private String deliveredBy;

    public Delivery(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, LocalDate orderDate, double totalAmount, LocalDate deliveryDate, String deliveredBy) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, totalAmount);
        if (deliveryDate == null) throw new IllegalArgumentException("Delivery date cannot be null");
        this.deliveryDate = deliveryDate;
        this.deliveredBy = deliveredBy;
    }

    public LocalDate getDeliveryDate() { return deliveryDate; }
    public String getDeliveredBy() { return deliveredBy; }
}

class Inspection extends Delivery {
    private String inspectorName;
    private String status;
    private String remarks;

    public Inspection(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, LocalDate orderDate, double totalAmount, LocalDate deliveryDate, String deliveredBy, String inspectorName, String status, String remarks) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, totalAmount, deliveryDate, deliveredBy);
        if (!status.equals("Passed") && !status.equals("Failed")) 
            throw new IllegalArgumentException("Status must be Passed or Failed");
        this.inspectorName = inspectorName;
        this.status = status;
        this.remarks = remarks;
    }

    public String getInspectorName() { return inspectorName; }
    public String getStatus() { return status; }
    public String getRemarks() { return remarks; }
}

class Invoice extends Inspection {
    private String invoiceNo;
    private double invoiceAmount;

    public Invoice(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, LocalDate orderDate, double totalAmount, LocalDate deliveryDate, String deliveredBy, String inspectorName, String status, String remarks, String invoiceNo, double invoiceAmount) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, totalAmount, deliveryDate, deliveredBy, inspectorName, status, remarks);
        if (invoiceAmount <= 0) throw new IllegalArgumentException("Invoice amount must be > 0");
        this.invoiceNo = invoiceNo;
        this.invoiceAmount = invoiceAmount;
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getInvoiceAmount() { return invoiceAmount; }
}

final class ProcurementReport extends Invoice {
    private LocalDate reportDate;
    private String summary;

    public ProcurementReport(int id, LocalDate createdDate, LocalDate updatedDate, String orgName, String address, String contactEmail, String deptName, String deptCode, String supplierName, String supplierTIN, String contact, String productName, double unitPrice, int quantity, String poNumber, LocalDate orderDate, double totalAmount, LocalDate deliveryDate, String deliveredBy, String inspectorName, String status, String remarks, String invoiceNo, double invoiceAmount, LocalDate reportDate, String summary) {
        super(id, createdDate, updatedDate, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, orderDate, totalAmount, deliveryDate, deliveredBy, inspectorName, status, remarks, invoiceNo, invoiceAmount);
        this.reportDate = reportDate;
        this.summary = summary;
    }

    public double calculateTotal() {
        return getInvoiceAmount(); // Sum of all invoice amounts
    }

    public LocalDate getReportDate() { return reportDate; }
    public String getSummary() { return summary; }
}

public class ProcurementManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Procurement Management System ===");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter organization name: ");
        String orgName = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter contact email: ");
        String contactEmail = scanner.nextLine();
        
        System.out.print("Enter department name: ");
        String deptName = scanner.nextLine();
        
        System.out.print("Enter department code: ");
        String deptCode = scanner.nextLine();
        
        System.out.print("Enter supplier name: ");
        String supplierName = scanner.nextLine();
        
        System.out.print("Enter supplier TIN (9 digits): ");
        String supplierTIN = scanner.nextLine();
        
        System.out.print("Enter contact (10 digits): ");
        String contact = scanner.nextLine();
        
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter PO number: ");
        String poNumber = scanner.nextLine();
        
        System.out.print("Enter total amount: ");
        double totalAmount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter delivered by: ");
        String deliveredBy = scanner.nextLine();
        
        System.out.print("Enter inspector name: ");
        String inspectorName = scanner.nextLine();
        
        System.out.print("Enter status (Passed/Failed): ");
        String status = scanner.nextLine();
        
        System.out.print("Enter remarks: ");
        String remarks = scanner.nextLine();
        
        System.out.print("Enter invoice number: ");
        String invoiceNo = scanner.nextLine();
        
        System.out.print("Enter invoice amount: ");
        double invoiceAmount = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter summary: ");
        String summary = scanner.nextLine();
        
        LocalDate now = LocalDate.now();
        
        ProcurementReport report = new ProcurementReport(id, now, now, orgName, address, contactEmail, deptName, deptCode, supplierName, supplierTIN, contact, productName, unitPrice, quantity, poNumber, now, totalAmount, now, deliveredBy, inspectorName, status, remarks, invoiceNo, invoiceAmount, now, summary);
        
        System.out.println("\n=== PROCUREMENT MANAGEMENT DATA ===");
        System.out.println("ID: " + report.getId());
        System.out.println("Organization: " + report.getOrgName());
        System.out.println("Address: " + report.getAddress());
        System.out.println("Contact Email: " + report.getContactEmail());
        System.out.println("Department: " + report.getDeptName());
        System.out.println("Department Code: " + report.getDeptCode());
        System.out.println("Supplier: " + report.getSupplierName());
        System.out.println("Supplier TIN: " + report.getSupplierTIN());
        System.out.println("Contact: " + report.getContact());
        System.out.println("Product: " + report.getProductName());
        System.out.println("Unit Price: $" + report.getUnitPrice());
        System.out.println("Quantity: " + report.getQuantity());
        System.out.println("PO Number: " + report.getPoNumber());
        System.out.println("Total Amount: $" + report.getTotalAmount());
        System.out.println("Delivered By: " + report.getDeliveredBy());
        System.out.println("Inspector: " + report.getInspectorName());
        System.out.println("Status: " + report.getStatus());
        System.out.println("Remarks: " + report.getRemarks());
        System.out.println("Invoice No: " + report.getInvoiceNo());
        System.out.println("Invoice Amount: $" + report.getInvoiceAmount());
        System.out.println("Summary: " + report.getSummary());
        System.out.println("Total Purchase: $" + report.calculateTotal());
        
        scanner.close();
    }
}