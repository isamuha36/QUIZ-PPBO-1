public class Developer extends Employee {
    private static final double BASE_SALARY = 10000000;

    public Developer(String name, String birthDate, String department) {
        super(name, birthDate, "Developer", department, BASE_SALARY);
    }

    @Override
    public double calculateTotalSalary() {
        return getSalary();
    }
}
