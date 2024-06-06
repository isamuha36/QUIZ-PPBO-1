public class HRStaff extends Employee {
    private static final double BASE_SALARY = 5000000;

    public HRStaff(String name, String birthDate, String department) {
        super(name, birthDate, "HR Staff", department, BASE_SALARY);
    }

    @Override
    public double calculateTotalSalary() {
        return getSalary();
    }
}
