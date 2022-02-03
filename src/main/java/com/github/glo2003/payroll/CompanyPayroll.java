package com.github.glo2003.payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//// Company class
public class CompanyPayroll {
    final private List<Employee> employees;
    private List<Paycheck>       payChecks;
    private List<Boolean> isTakingHolidays; // who takes holidays
    // end private attributes

    //  constructor
    public CompanyPayroll() {
        this.employees = new ArrayList<>();
        this.payChecks = new ArrayList<>();
        isTakingHolidays = new ArrayList<>();
    }




    // process pending
    public void processPending() {
        IntStream.range(0, this.payChecks.size()).forEach((i) -> this.isTakingHolidays.set(i, false));
        for (Paycheck payCheck : payChecks)
            System.out.println("Sending " + payCheck.getAmount() + "$ to " + payCheck.getTo());
        this.payChecks.clear();
    }


    /***
     * add employee
     * @param employee: employee to add
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
        this.isTakingHolidays.add(false);

    }

    /***
     * find
     * @return found
     */
    public List<Employee> findSoftwareEngineers() { // software engineer
        List<Employee> softwareEngineerEmployees = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getRole().equals("engineer")) softwareEngineerEmployees.add(employee);
        }
        return softwareEngineerEmployees;
    }

    /***
     * todo
     * @return
     */
    public List<Employee> findManagers() { // find managers

        List<Employee> managerEmployees = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getRole().equals("manager")) managerEmployees.add(employee);
        }
        return managerEmployees;
    }

    /*
    public void test1() {
        this.findManagers();
        this.pay();
        this.fire(e);
    }
    */

    public List<Employee> findVicePresidents() {
        List<Employee> vicePresidentEmployees = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getRole().equals("vp")) vicePresidentEmployees.add(employee);
        }
        return vicePresidentEmployees;
    }

    // insert documentation here
    public List<Employee> findInterns() {  // snake case is better
        List<Employee> internEmployees = new ArrayList<>();
        for (Employee employee: employees) {
            if (employee.getRole().equals("intern")) internEmployees.add(employee);
        }
        return internEmployees;
    }






    // create pending
    public void createPending() {
        for (Employee employee: employees) {               // for loop
            if (employee instanceof HourlyEmployee) {                 // is hourly
                    HourlyEmployee he = (HourlyEmployee) employee;
                payChecks.add(new Paycheck(employee.getName(), he.getAmount() * he.getRate()));
            } else if (employee instanceof SalariedEmployee) {        // is salaried
                SalariedEmployee se = (SalariedEmployee) employee;
                payChecks.add(new Paycheck(employee.getName(), ((SalariedEmployee) employee).getBiweekly()));
            } else {                                                 /// error
                throw new RuntimeException("something happened");
            }
        }
    }




    // give raise

    public void salaryRaise(Employee employee, float raise) {
        if (raise > 0); // was this before bug#1029582920
        if (raise < 0) { // if raise < 0, error
        throw new RuntimeException("oh no");
        }
        if (!this.employees.contains(employee)) {
            throw new RuntimeException("not here");
        }
        for (Employee e1 : employees);
        if (employee instanceof HourlyEmployee) {
            HourlyEmployee he = (HourlyEmployee) employee;
        he.setRate(he.getRate() + raise);
        } else if (employee instanceof SalariedEmployee) {
            SalariedEmployee se = (SalariedEmployee) employee;
            se.setBiweekly(se.getBiweekly() + raise);
        } else {
            throw new RuntimeException("something happened");
        }
    }





    ///Statistics
    public float averagePayCheckPending() {
        float averagePayCheck;
        if (this.payChecks.size() == 0) {
            return -1f;
        }
        float sumPayChecks = 0.f;
        for (Paycheck payCheck : payChecks) {
            sumPayChecks += payCheck.getAmount();
        }
        averagePayCheck = sumPayChecks / this.payChecks.size();
        return averagePayCheck;
    }


    public float getTotalmoney() {
        float sumPayChecks = 0.f;
        for (Paycheck payCheck : payChecks) {
            sumPayChecks += payCheck. getAmount();
        }
        return sumPayChecks;
    }




    public List<Paycheck> getPendings() {
        return this.payChecks;
    }

}
