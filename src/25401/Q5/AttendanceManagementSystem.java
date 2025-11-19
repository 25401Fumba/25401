import java.util.*;
import java.time.LocalDate;

// System 5: Attendance Management System
class AttendanceEntity {
    private int id;
    private LocalDate createdDate;
    private LocalDate updatedDate;

    public AttendanceEntity(int id, LocalDate createdDate, LocalDate updatedDate) {
        if (id <= 0) throw new IllegalArgumentException("ID must be > 0");
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getId() { return id; }
    public LocalDate getCreatedDate() { return createdDate; }
    public LocalDate getUpdatedDate() { return updatedDate; }
}

class Institution extends AttendanceEntity {
    private String institutionName;
    private String code;
    private String address;

    public Institution(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address) {
        super(id, createdDate, updatedDate);
        if (code.length() < 3) throw new IllegalArgumentException("Code must be >= 3 chars");
        this.institutionName = institutionName;
        this.code = code;
        this.address = address;
    }

    public String getInstitutionName() { return institutionName; }
    public String getCode() { return code; }
    public String getAddress() { return address; }
}

class AttendanceDepartment extends Institution {
    private String departmentName;
    private String departmentHead;

    public AttendanceDepartment(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead) {
        super(id, createdDate, updatedDate, institutionName, code, address);
        if (departmentName.isEmpty() || departmentHead.isEmpty()) 
            throw new IllegalArgumentException("Names cannot be empty");
        this.departmentName = departmentName;
        this.departmentHead = departmentHead;
    }

    public String getDepartmentName() { return departmentName; }
    public String getDepartmentHead() { return departmentHead; }
}

class Course extends AttendanceDepartment {
    private String courseName;
    private String courseCode;
    private int credits;

    public Course(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead);
        if (credits <= 0) throw new IllegalArgumentException("Credits must be > 0");
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.credits = credits;
    }

    public String getCourseName() { return courseName; }
    public String getCourseCode() { return courseCode; }
    public int getCredits() { return credits; }
}

class Instructor extends Course {
    private String instructorName;
    private String email;
    private String phone;

    public Instructor(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits);
        if (!email.contains("@")) throw new IllegalArgumentException("Invalid email");
        if (phone.length() != 10 || !phone.matches("\\d+")) 
            throw new IllegalArgumentException("Phone must be 10 digits");
        this.instructorName = instructorName;
        this.email = email;
        this.phone = phone;
    }

    public String getInstructorName() { return instructorName; }
    public String getEmail() { return email; }
    public String getPhone() { return phone; }
}

class Student extends Instructor {
    private String studentName;
    private String studentID;
    private int age;

    public Student(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone);
        if (age <= 0) throw new IllegalArgumentException("Age must be > 0");
        this.studentName = studentName;
        this.studentID = studentID;
        this.age = age;
    }

    public String getStudentName() { return studentName; }
    public String getStudentID() { return studentID; }
    public int getAge() { return age; }
}

class ClassSession extends Student {
    private LocalDate sessionDate;
    private String topic;

    public ClassSession(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, LocalDate sessionDate, String topic) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age);
        if (sessionDate == null) throw new IllegalArgumentException("Session date cannot be null");
        this.sessionDate = sessionDate;
        this.topic = topic;
    }

    public LocalDate getSessionDate() { return sessionDate; }
    public String getTopic() { return topic; }
}

class AttendanceRecord extends ClassSession {
    private String recordStudentID;
    private String sessionID;
    private String status;

    public AttendanceRecord(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, LocalDate sessionDate, String topic, String recordStudentID, String sessionID, String status) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, sessionDate, topic);
        if (!status.equals("Present") && !status.equals("Absent")) 
            throw new IllegalArgumentException("Status must be Present or Absent");
        this.recordStudentID = recordStudentID;
        this.sessionID = sessionID;
        this.status = status;
    }

    public String getRecordStudentID() { return recordStudentID; }
    public String getSessionID() { return sessionID; }
    public String getStatus() { return status; }
}

class LeaveRequest extends AttendanceRecord {
    private LocalDate requestDate;
    private String reason;
    private boolean approved;

    public LeaveRequest(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, LocalDate sessionDate, String topic, String recordStudentID, String sessionID, String status, LocalDate requestDate, String reason, boolean approved) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, sessionDate, topic, recordStudentID, sessionID, status);
        if (reason.isEmpty()) throw new IllegalArgumentException("Reason cannot be empty");
        this.requestDate = requestDate;
        this.reason = reason;
        this.approved = approved;
    }

    public LocalDate getRequestDate() { return requestDate; }
    public String getReason() { return reason; }
    public boolean isApproved() { return approved; }
}

final class AttendanceSummary extends LeaveRequest {
    private LocalDate reportDate;
    private int totalPresent;
    private int totalAbsent;

    public AttendanceSummary(int id, LocalDate createdDate, LocalDate updatedDate, String institutionName, String code, String address, String departmentName, String departmentHead, String courseName, String courseCode, int credits, String instructorName, String email, String phone, String studentName, String studentID, int age, LocalDate sessionDate, String topic, String recordStudentID, String sessionID, String status, LocalDate requestDate, String reason, boolean approved, LocalDate reportDate, int totalPresent, int totalAbsent) {
        super(id, createdDate, updatedDate, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, sessionDate, topic, recordStudentID, sessionID, status, requestDate, reason, approved);
        this.reportDate = reportDate;
        this.totalPresent = totalPresent;
        this.totalAbsent = totalAbsent;
    }

    public double generateSummary() {
        int totalSessions = totalPresent + totalAbsent;
        return totalSessions > 0 ? (totalPresent * 100.0) / totalSessions : 0;
    }

    public LocalDate getReportDate() { return reportDate; }
    public int getTotalPresent() { return totalPresent; }
    public int getTotalAbsent() { return totalAbsent; }
}

public class AttendanceManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Attendance Management System ===");
        System.out.print("Enter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter institution name: ");
        String institutionName = scanner.nextLine();
        
        System.out.print("Enter institution code: ");
        String code = scanner.nextLine();
        
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        
        System.out.print("Enter department head: ");
        String departmentHead = scanner.nextLine();
        
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        
        System.out.print("Enter credits: ");
        int credits = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter instructor name: ");
        String instructorName = scanner.nextLine();
        
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        
        System.out.print("Enter phone (10 digits): ");
        String phone = scanner.nextLine();
        
        System.out.print("Enter student name: ");
        String studentName = scanner.nextLine();
        
        System.out.print("Enter student ID: ");
        String studentID = scanner.nextLine();
        
        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Enter topic: ");
        String topic = scanner.nextLine();
        
        System.out.print("Enter session ID: ");
        String sessionID = scanner.nextLine();
        
        System.out.print("Enter status (Present/Absent): ");
        String status = scanner.nextLine();
        
        System.out.print("Enter leave reason: ");
        String reason = scanner.nextLine();
        
        System.out.print("Is leave approved (true/false): ");
        boolean approved = scanner.nextBoolean();
        
        System.out.print("Enter total present: ");
        int totalPresent = scanner.nextInt();
        
        System.out.print("Enter total absent: ");
        int totalAbsent = scanner.nextInt();
        
        LocalDate now = LocalDate.now();
        
        AttendanceSummary summary = new AttendanceSummary(id, now, now, institutionName, code, address, departmentName, departmentHead, courseName, courseCode, credits, instructorName, email, phone, studentName, studentID, age, now, topic, studentID, sessionID, status, now, reason, approved, now, totalPresent, totalAbsent);
        
        System.out.println("\n=== ATTENDANCE MANAGEMENT DATA ===");
        System.out.println("ID: " + summary.getId());
        System.out.println("Institution: " + summary.getInstitutionName());
        System.out.println("Code: " + summary.getCode());
        System.out.println("Address: " + summary.getAddress());
        System.out.println("Department: " + summary.getDepartmentName());
        System.out.println("Department Head: " + summary.getDepartmentHead());
        System.out.println("Course: " + summary.getCourseName());
        System.out.println("Course Code: " + summary.getCourseCode());
        System.out.println("Credits: " + summary.getCredits());
        System.out.println("Instructor: " + summary.getInstructorName());
        System.out.println("Email: " + summary.getEmail());
        System.out.println("Phone: " + summary.getPhone());
        System.out.println("Student: " + summary.getStudentName());
        System.out.println("Student ID: " + summary.getStudentID());
        System.out.println("Age: " + summary.getAge());
        System.out.println("Topic: " + summary.getTopic());
        System.out.println("Session ID: " + summary.getSessionID());
        System.out.println("Status: " + summary.getStatus());
        System.out.println("Leave Reason: " + summary.getReason());
        System.out.println("Approved: " + summary.isApproved());
        System.out.println("Total Present: " + summary.getTotalPresent());
        System.out.println("Total Absent: " + summary.getTotalAbsent());
        System.out.println("Attendance Percentage: " + summary.generateSummary() + "%");
        
        scanner.close();
    }
}