public class Manager extends Employee {
    private static final double BASE_SALARY = 20000000;

    public Manager(String name, String birthDate, String department) {
        super(name, birthDate, "Manager", department, BASE_SALARY);
    }

    @Override
    public double calculateTotalSalary() {
        return getSalary();
    }
}
