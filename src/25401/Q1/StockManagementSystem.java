import java.util.*;
import java.time.LocalDate;

// System 1: Stock Management System
class Entity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public Entity(int id, LocalDate createdDate, LocalDate updatedDate) {
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

class Warehouse extends Entity {
    private String warehouseName;
    private String location;
    private String contactNumber;

    public Warehouse(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber) {
        super(id, createdDate, updatedDate);
        if (contactNumber.length() != 10 || !contactNumber.matches("\\d+")) 
            throw new IllegalArgumentException("Phone must be 10 digits");
        this.warehouseName = warehouseName;
        this.location = location;
        this.contactNumber = contactNumber;
    }

    public String getWarehouseName() { return warehouseName; }
    public String getLocation() { return location; }
    public String getContactNumber() { return contactNumber; }
}

class Category extends Warehouse {
    private String categoryName;
    private String categoryCode;

    public Category(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber);
        if (categoryCode.length() < 3 || !categoryCode.matches("[a-zA-Z0-9]+")) 
            throw new IllegalArgumentException("Code must be alphanumeric and >= 3 chars");
        this.categoryName = categoryName;
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() { return categoryName; }
    public String getCategoryCode() { return categoryCode; }
}

class Supplier extends Category {
    private String supplierName;
    private String supplierEmail;
    private String supplierPhone;

    public Supplier(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode);
        if (!supplierEmail.contains("@")) throw new IllegalArgumentException("Invalid email");
        if (supplierPhone.length() != 10 || !supplierPhone.matches("\\d+")) 
            throw new IllegalArgumentException("Phone must be 10 digits");
        this.supplierName = supplierName;
        this.supplierEmail = supplierEmail;
        this.supplierPhone = supplierPhone;
    }

    public String getSupplierName() { return supplierName; }
    public String getSupplierEmail() { return supplierEmail; }
    public String getSupplierPhone() { return supplierPhone; }
}

class Product extends Supplier {
    private String productName;
    private double unitPrice;
    private int stockLimit;

    public Product(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone, String productName, double unitPrice, int stockLimit) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone);
        if (unitPrice <= 0) throw new IllegalArgumentException("Unit price must be > 0");
        if (stockLimit < 0) throw new IllegalArgumentException("Stock limit must be >= 0");
        this.productName = productName;
        this.unitPrice = unitPrice;
        this.stockLimit = stockLimit;
    }

    public String getProductName() { return productName; }
    public double getUnitPrice() { return unitPrice; }
    public int getStockLimit() { return stockLimit; }
}

class StockItem extends Product {
    private int quantityAvailable;
    private int reorderLevel;

    public StockItem(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone, String productName, double unitPrice, int stockLimit, int quantityAvailable, int reorderLevel) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit);
        if (quantityAvailable < 0 || reorderLevel < 0) throw new IllegalArgumentException("Values must be >= 0");
        this.quantityAvailable = quantityAvailable;
        this.reorderLevel = reorderLevel;
    }

    public int getQuantityAvailable() { return quantityAvailable; }
    public int getReorderLevel() { return reorderLevel; }
}

class Purchase extends StockItem {
    private LocalDate purchaseDate;
    private int purchasedQuantity;
    private String purchaseSupplierName;

    public Purchase(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone, String productName, double unitPrice, int stockLimit, int quantityAvailable, int reorderLevel, LocalDate purchaseDate, int purchasedQuantity, String purchaseSupplierName) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel);
        if (purchasedQuantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        if (purchaseDate == null) throw new IllegalArgumentException("Date cannot be null");
        this.purchaseDate = purchaseDate;
        this.purchasedQuantity = purchasedQuantity;
        this.purchaseSupplierName = purchaseSupplierName;
    }

    public LocalDate getPurchaseDate() { return purchaseDate; }
    public int getPurchasedQuantity() { return purchasedQuantity; }
    public String getPurchaseSupplierName() { return purchaseSupplierName; }
}

class Sale extends Purchase {
    private LocalDate saleDate;
    private int soldQuantity;
    private String customerName;

    public Sale(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone, String productName, double unitPrice, int stockLimit, int quantityAvailable, int reorderLevel, LocalDate purchaseDate, int purchasedQuantity, String purchaseSupplierName, LocalDate saleDate, int soldQuantity, String customerName) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, purchaseDate, purchasedQuantity, purchaseSupplierName);
        if (soldQuantity <= 0) throw new IllegalArgumentException("Sold quantity must be > 0");
        this.saleDate = saleDate;
        this.soldQuantity = soldQuantity;
        this.customerName = customerName;
    }

    public LocalDate getSaleDate() { return saleDate; }
    public int getSoldQuantity() { return soldQuantity; }
    public String getCustomerName() { return customerName; }
}

class Inventory extends Sale {
    private int totalItems;
    private double stockValue;

    public Inventory(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone, String productName, double unitPrice, int stockLimit, int quantityAvailable, int reorderLevel, LocalDate purchaseDate, int purchasedQuantity, String purchaseSupplierName, LocalDate saleDate, int soldQuantity, String customerName, int totalItems, double stockValue) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, purchaseDate, purchasedQuantity, purchaseSupplierName, saleDate, soldQuantity, customerName);
        if (totalItems < 0 || stockValue < 0) throw new IllegalArgumentException("Totals must be >= 0");
        this.totalItems = totalItems;
        this.stockValue = stockValue;
    }

    public int getTotalItems() { return totalItems; }
    public double getStockValue() { return stockValue; }
}

final class StockReport extends Inventory {
    private LocalDate reportDate;
    private String remarks;

    public StockReport(int id, LocalDate createdDate, LocalDate updatedDate, String warehouseName, String location, String contactNumber, String categoryName, String categoryCode, String supplierName, String supplierEmail, String supplierPhone, String productName, double unitPrice, int stockLimit, int quantityAvailable, int reorderLevel, LocalDate purchaseDate, int purchasedQuantity, String purchaseSupplierName, LocalDate saleDate, int soldQuantity, String customerName, int totalItems, double stockValue, LocalDate reportDate, String remarks) {
        super(id, createdDate, updatedDate, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, purchaseDate, purchasedQuantity, purchaseSupplierName, saleDate, soldQuantity, customerName, totalItems, stockValue);
        this.reportDate = reportDate;
        this.remarks = remarks;
    }

    public String generateReport() {
        return "Stock Report - Total Items: " + getTotalItems() + ", Stock Value: $" + getStockValue() + ", Sales: " + getSoldQuantity();
    }

    public LocalDate getReportDate() { return reportDate; }
    public String getRemarks() { return remarks; }
}

public class StockManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Stock Management System ===");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter warehouse name: ");
        String warehouseName = scanner.nextLine();
        
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        
        System.out.print("Enter contact number (10 digits): ");
        String contactNumber = scanner.nextLine();
        
        System.out.print("Enter category name: ");
        String categoryName = scanner.nextLine();
        
        System.out.print("Enter category code: ");
        String categoryCode = scanner.nextLine();
        
        System.out.print("Enter supplier name: ");
        String supplierName = scanner.nextLine();
        
        System.out.print("Enter supplier email: ");
        String supplierEmail = scanner.nextLine();
        
        System.out.print("Enter supplier phone: ");
        String supplierPhone = scanner.nextLine();
        
        System.out.print("Enter product name: ");
        String productName = scanner.nextLine();
        
        System.out.print("Enter unit price: ");
        double unitPrice = scanner.nextDouble();
        
        System.out.print("Enter stock limit: ");
        int stockLimit = scanner.nextInt();
        
        System.out.print("Enter quantity available: ");
        int quantityAvailable = scanner.nextInt();
        
        System.out.print("Enter reorder level: ");
        int reorderLevel = scanner.nextInt();
        
        System.out.print("Enter purchased quantity: ");
        int purchasedQuantity = scanner.nextInt();
        
        System.out.print("Enter sold quantity: ");
        int soldQuantity = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter customer name: ");
        String customerName = scanner.nextLine();
        
        System.out.print("Enter total items: ");
        int totalItems = scanner.nextInt();
        
        System.out.print("Enter stock value: ");
        double stockValue = scanner.nextDouble();
        scanner.nextLine();
        
        System.out.print("Enter remarks: ");
        String remarks = scanner.nextLine();
        
        LocalDate now = LocalDate.now();
        
        StockReport report = new StockReport(id, now, now, warehouseName, location, contactNumber, categoryName, categoryCode, supplierName, supplierEmail, supplierPhone, productName, unitPrice, stockLimit, quantityAvailable, reorderLevel, now, purchasedQuantity, supplierName, now, soldQuantity, customerName, totalItems, stockValue, now, remarks);
        
        System.out.println("\n=== STOCK MANAGEMENT DATA ===");
        System.out.println("ID: " + report.getId());
        System.out.println("Warehouse: " + report.getWarehouseName());
        System.out.println("Location: " + report.getLocation());
        System.out.println("Contact: " + report.getContactNumber());
        System.out.println("Category: " + report.getCategoryName());
        System.out.println("Category Code: " + report.getCategoryCode());
        System.out.println("Supplier: " + report.getSupplierName());
        System.out.println("Supplier Email: " + report.getSupplierEmail());
        System.out.println("Supplier Phone: " + report.getSupplierPhone());
        System.out.println("Product: " + report.getProductName());
        System.out.println("Unit Price: $" + report.getUnitPrice());
        System.out.println("Stock Limit: " + report.getStockLimit());
        System.out.println("Quantity Available: " + report.getQuantityAvailable());
        System.out.println("Reorder Level: " + report.getReorderLevel());
        System.out.println("Purchased Quantity: " + report.getPurchasedQuantity());
        System.out.println("Sold Quantity: " + report.getSoldQuantity());
        System.out.println("Customer: " + report.getCustomerName());
        System.out.println("Total Items: " + report.getTotalItems());
        System.out.println("Stock Value: $" + report.getStockValue());
        System.out.println("Remarks: " + report.getRemarks());
        System.out.println("\n" + report.generateReport());
        
        scanner.close();
    }
}