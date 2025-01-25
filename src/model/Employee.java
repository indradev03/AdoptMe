package src.model;


public class Employee {
    private String employeeId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String username;
    private String password;
    private String phone;
    private int workingHours;
    private double salary;
    private String adminId;
    private String email;

    // Constructor
    public Employee(String employeeId, String firstName, String lastName, int age, String gender,
                    String username, String password, String phone, int workingHours,
                    double salary, String adminId, String email) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.workingHours = workingHours;
        this.salary = salary;
        this.adminId = adminId;
        this.email = email;
    }

    public String getEmployeeId() {
            return employeeId;
    }

    public String getFirstName() {
            return firstName;

    }

    public String getLastName() {
            return lastName;

    }

    public String getGender() {
            return gender;
    }

    public int getAge() {
            return age;

    }

    public String getUsername() {
            return username;

    }

    public String getPassword() {
            return password;

    }

    public String getPhone() {
            return phone;

    }

    public int getWorkingHours() {
            return workingHours;

    }

    public double getSalary() {
            return salary;

    }

    public String getEmail() {
            return email;

    }

    public String getAdminId() {
            return adminId;

    }

}
