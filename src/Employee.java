import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public abstract class Employee {
    private String id;
    private String name;
    private String birthDate;
    private String position;
    private String department;
    private double salary;

    public Employee(String name, String birthDate, String position, String department, double salary) {
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
        this.department = department;
        this.salary = salary;
        this.id = generateID();
    }

    private String generateID() {
        String deptCode = department.equalsIgnoreCase("product") ? "PROD" : "HR";
        String dateCode = new SimpleDateFormat("ddMMyyyy").format(new Date());
        String randomDigits = String.format("%03d", new Random().nextInt(1000));
        return "000" + deptCode + birthDate.replaceAll("/", "") + randomDigits;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getPosition() {
        return position;
    }

    public double getSalary() {
        return salary;
    }

    public abstract double calculateTotalSalary();

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Birth Date: " + birthDate +
                ", Position: " + position + ", Department: " + department + ", Salary: " + salary;
    }

    public static String[] getDepartment() {
        return new String[]{"Product", "Human Resources"};
    }
}
