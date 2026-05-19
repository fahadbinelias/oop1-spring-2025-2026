package hospitalmanagementsystem.gui;
import hospitalmanagementsystem.entity.Patient;
import hospitalmanagementsystem.fileio.PatientFileIO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;

public class PatientGUI extends JFrame {

    private JTextField serialField;
    private JTextField nameField;
    private JTextField ageField;
    private JTextField diseaseField;
    private JTextField searchField;

    private JTable table;
    private DefaultTableModel tableModel;

    public PatientGUI() {

        setTitle(" Infinite Hospital & Diagnostic Centre ");
        setSize(850, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel inputPanel =new JPanel(new GridLayout(4, 2, 12, 12));

        inputPanel.setBorder(BorderFactory.createTitledBorder("Patient Details"));
        inputPanel.add(new JLabel("Serial (exactly 8 digits):"));
        inputPanel.setBackground(new Color(220,188,129));

        serialField = new JTextField();
        serialField.setBackground(new Color(164,189,71));
        inputPanel.add(serialField);
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        nameField.setBackground(new Color(164,189,71));
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        ageField.setBackground(new Color(164,189,71));
        inputPanel.add(new JLabel("Disease:"));
        diseaseField = new JTextField();
        inputPanel.add(diseaseField);
        diseaseField.setBackground(new Color(164,189,71));

        JPanel searchPanel =new JPanel(new BorderLayout(5, 5));
        searchPanel.setBackground(new Color(165,165,203));
        searchPanel.setBorder(BorderFactory.createTitledBorder("Search (by Serial or Name)"));
        searchField = new JTextField();
        searchField.setBackground(new Color(156,163,168));
        JButton searchBtn = new JButton("Search");

        searchPanel.add(searchField, BorderLayout.NORTH);
        searchPanel.add(searchBtn, BorderLayout.SOUTH);

        JPanel buttonPanel =new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(new Color(212,144,189));

        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton viewAllBtn = new JButton("View All");
        JButton clearBtn = new JButton("Clear");
        //Adding Buttonsto Button Panel---------
       
        buttonPanel.add(addBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(viewAllBtn);
        buttonPanel.add(clearBtn);
        //Setting Color----------------
        
        addBtn.setBackground(new Color(193,202,222));
        updateBtn.setBackground(new Color(123,182,232));
        deleteBtn.setBackground(new Color(219,201,166));
        viewAllBtn.setBackground(new Color(0,143,156));
        clearBtn.setBackground(new Color(75,151,75));
        searchBtn.setBackground(new Color(175,148,131));

        JPanel topPanel =new JPanel(new BorderLayout(5, 5));
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(searchPanel, BorderLayout.NORTH);
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(topPanel, BorderLayout.NORTH);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        String[] columns = {"Serial","Name","Age","Disease"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);
        table.setBackground(new Color(4,175,236));
        table.setRowHeight(22);

        JScrollPane scrollPane =new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Patient Records"));
        add(northPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        addBtn.addActionListener(e -> addPatient());
        updateBtn.addActionListener(e -> updatePatient());
        deleteBtn.addActionListener(e -> deletePatient());
        searchBtn.addActionListener(e -> searchPatient());
        viewAllBtn.addActionListener(e -> {
            searchField.setText("");
            viewAll();
        });
        clearBtn.addActionListener(e -> clearFields());
        table.getSelectionModel().addListSelectionListener(e -> {
                    int row = table.getSelectedRow();
                    if (row >= 0) {
                        serialField.setText(String.valueOf(tableModel.getValueAt(row, 0)));
                        nameField.setText(String.valueOf(tableModel.getValueAt(row, 1)));
                        ageField.setText(String.valueOf(tableModel.getValueAt(row, 2)));
                        diseaseField.setText(String.valueOf(tableModel.getValueAt(row, 3)));
                    }
                });
        try {
            PatientFileIO.createFileIfNotExists();
        } catch (IOException ex) {
            showError("Error creating file: " + ex.getMessage());
        }
        viewAll();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    private boolean isValidSerial(String serial) {
        if (serial.isEmpty()) {
            showError("Serial is required!");
            return false;
        }
        if (!serial.matches("\\d{8}")) {
            showError("Serial must be exactly 8 digits.");
            return false;
        }
        return true;
    }
    private boolean isValidAllFields(String serial,String name,String age,String disease) {
        if (name.isEmpty() || age.isEmpty() || disease.isEmpty()) {
            showError("All fields are required!");
            return false;
        }
        if (!isValidSerial(serial))
            return false;
        if (name.contains(",") || age.contains(",") || disease.contains(",")) {
            showError("Commas are not allowed!");
            return false;
        }
        try {
            Integer.parseInt(age);
        } catch (NumberFormatException ex) {
            showError("Age must be a number!");
            return false;
        }
        return true;
    }
    private void addPatient() {
        String serial = serialField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String disease = diseaseField.getText().trim();
        if (!isValidAllFields(serial,name,age,disease))
            return;
        if (PatientFileIO.idExists(serial)) {
            showError("Duplicate Serial! A patient with serial " + serial + " already exists.");
            return;
        }
        try {
            PatientFileIO.addPatient(new Patient(serial,name,age,disease));
            showInfo("Patient added successfully!");
            clearFields();
            viewAll();
        } catch (IOException ex) {
            showError("Error: " + ex.getMessage());
        }
    }
    private void updatePatient() {
        String serial = serialField.getText().trim();
        String name = nameField.getText().trim();
        String age = ageField.getText().trim();
        String disease = diseaseField.getText().trim();
        if (!isValidAllFields(serial,name,age,disease))
            return;
        try {
            boolean updated = PatientFileIO.updatePatient(new Patient(serial,name,age,disease));
            if (updated) {
                showInfo("Patient updated successfully!");
                clearFields();
                viewAll();
            } else {
                showError("Patient Serial not found!");
            }
        } catch (IOException ex) {
            showError("Error: " + ex.getMessage());
        }
    }
    private void deletePatient(){
        String serial = serialField.getText().trim();
        if (!isValidSerial(serial))
            return;
        int confirm = JOptionPane.showConfirmDialog(this,"Are you sure you want to delete patient serial: "+ serial + "?","Confirm Delete",JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION)
            return;
        try{        
            boolean deleted = PatientFileIO.deletePatient(serial);       
            if (deleted) {
                showInfo("Patient deleted successfully!");
                clearFields();
                viewAll();
            } 
            else {
                showError("Patient Serial not found!");
            }        
        }
        catch (IOException ex){
            showError("Error: " + ex.getMessage());
        }
    }
    private void searchPatient() {
        String keyword = searchField.getText().trim();
        if (keyword.isEmpty()) {
            showError("Enter Serial or Name to search!");
            return;
        }
        Object[][] results =PatientFileIO.searchPatients(keyword);
        tableModel.setRowCount(0);
        for (int i = 0; i < results.length; i++) {
            tableModel.addRow(results[i]);
        }
        if (results.length == 0)
            showInfo("No matching patient found.");
    }
    private void viewAll() {
        Object[][] rows = PatientFileIO.getAllPatients();
        tableModel.setRowCount(0);
        for (int i = 0; i < rows.length; i++) {
            if (rows[i][0] != null)
                tableModel.addRow(rows[i]);
        }
    }
    private void clearFields() {
        serialField.setText("");
        nameField.setText("");
        ageField.setText("");
        diseaseField.setText("");
        searchField.setText("");
        table.clearSelection();
    }
    private void showInfo(String msg) {
        JOptionPane.showMessageDialog(this,msg,"Info",JOptionPane.INFORMATION_MESSAGE);
    }
    private void showError(String msg) {
        JOptionPane.showMessageDialog(this,msg,"Error",JOptionPane.ERROR_MESSAGE);
    }
}
