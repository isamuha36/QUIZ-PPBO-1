import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HRISGUI extends JFrame {
    private JFrame frame;
    private JComboBox<String> departementComboBox;
    private JButton addEmployeeButton;
    private JButton cancelButton;
    private JTextField findIdField;
    private JButton addButton;
    private JButton showAllButton;
    private JTextField nameField, birthDateField, positionField;
    private JTextArea displayArea;
    private ArrayList<Employee> employees;

    public HRISGUI() {
        employees = new ArrayList<>();
        frame = new JFrame("HRIS");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Menambahkan panel utama
        frame.add(showMainPanel(), BorderLayout.NORTH);
        frame.pack();
        frame.setSize(600, 400);
        frame.setVisible(true);
    }

    private void addEmployee() {
        String name = nameField.getText();
        String birthDate = birthDateField.getText();
        String position = positionField.getText();
        String department = (String) departementComboBox.getSelectedItem();

        Employee employee = null;

        switch (position.toLowerCase()) {
            case "manager":
                employee = new Manager(name, birthDate, department);
                break;
            case "developer":
                employee = new Developer(name, birthDate, department);
                break;
            case "hr staff":
                employee = new HRStaff(name, birthDate, department);
                break;
            default:
                JOptionPane.showMessageDialog(this, "Invalid Position");
                return;
        }

        employees.add(employee);
        displayEmployees();
    }

    private void displayEmployees() {
        StringBuilder sb = new StringBuilder();
        for (Employee e : employees) {
            sb.append(e.toString()).append("\n");
        }
        displayArea.setText(sb.toString());
    }

    private JPanel showMainPanel() {
        addButton = new JButton("Add Data");
        showAllButton = new JButton("Show Data");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));
        buttonPanel.add(addButton);
        buttonPanel.add(showAllButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintPanel(showInputPanel());
            }
        });

        showAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaintPanel(showAllDataPanel());
            }
        });

        return buttonPanel;
    }

    // Panel untuk menampilkan semua data pegawai
    private JPanel showAllDataPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        displayArea = new JTextArea(20, 50);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        panel.add(scrollPane, BorderLayout.CENTER);

        displayEmployees(); // Menampilkan semua data pegawai saat panel ditampilkan

        return panel;
    }

    // Method untuk merefresh panel
    private void repaintPanel(JPanel newPanel) {
        frame.getContentPane().removeAll();
        frame.getContentPane().add(showMainPanel(), BorderLayout.NORTH); // Menambahkan kembali panel utama
        frame.getContentPane().add(newPanel, BorderLayout.CENTER);
        frame.revalidate();
        frame.repaint();
    }

    // Panel untuk input data pegawai
    private JPanel showInputPanel() {
        JPanel inputPanel = new JPanel(new GridLayout(5, 2));

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Birth Date (DD/MM/YYYY):"));
        birthDateField = new JTextField();
        inputPanel.add(birthDateField);

        inputPanel.add(new JLabel("Position:"));
        positionField = new JTextField();
        inputPanel.add(positionField);

        inputPanel.add(new JLabel("Department:"));
        String[] departments = Employee.getDepartment();
        departementComboBox = new JComboBox<>(departments);
        inputPanel.add(departementComboBox);

        addEmployeeButton = new JButton("Add Employee");
        inputPanel.add(addEmployeeButton);

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addEmployee();
                JOptionPane.showMessageDialog(HRISGUI.this, "Employee added successfully");
            }
        });

        return inputPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HRISGUI().setVisible(true);
            }
        });
    }
}
