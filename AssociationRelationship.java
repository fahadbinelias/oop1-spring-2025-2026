package problem;
 
class Company {
    private String companyName;
    private Employee[] employees;
    private int count = 0;

    public Company(String companyName, int numberOfEmployees) {
        this.companyName = companyName;
        employees = new Employee[numberOfEmployees];
    }

    public void addEmployee(Employee employee) {
        if (count < employees.length) {
            employees[count++] = employee;
        } else {
            System.out.println("Cannot add more employees to" + companyName);
        }
    }

    public void showEmployees() {
        System.out.println("Company: " + companyName);
        System.out.println("Employees:");

        for (int i = 0; i < count; i++) {
            employees[i].showEmployee();
            System.out.println();
        }
    }
}

// -------------------- EMPLOYEE  --------------------
class Employee {
    private String name;
    private Position position;

    public Employee(String name, Position position) {
        this.name = name;
        this.position = position;
    }

    public void showEmployee() {
        System.out.println("Employee Name: " + name);
        System.out.println("Position: " + position.getTitle());
    }
}

// -------------------- POSITION --------------------
class Position {
    private String title;

    public Position(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }
}

// -------------------- MAIN --------------------
public class AssociationRelationship {
    public static void main(String[] args) {

        // Position create
        Position T1 = new Position("TechExpert");
        Position T2 = new Position("Company General");

        // Employee create
        Employee E1 = new Employee("Fahad", T1);
        Employee E2 = new Employee("Rahim", T2);

        // Company create (Main)
        Company company = new Company("Tech World", 2);

        company.addEmployee(E1);
        company.addEmployee(E2);

        company.showEmployees();
    }
} 


