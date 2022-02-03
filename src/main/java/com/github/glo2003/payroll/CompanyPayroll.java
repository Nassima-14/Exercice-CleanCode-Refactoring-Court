package com.github.glo2003.payroll;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


public class CompanyPayroll {
    final private List<Employee> employees;
    private List<Paycheck>       payChecks;
    private List<Boolean> isTakingHolidays;

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


    public void addEmployee(Employee employee) {
        employees.add(employee);
        this.isTakingHolidays.add(false);
    }

    public List<Employee> findEmployeesDependingOnRole(String roleOfEmployee) {
        List<Employee> foundEmployees = new ArrayList<>();
        for (Employee employee: employees) if (employee.getRole().equals(roleOfEmployee)) foundEmployees.add(employee);
        return foundEmployees;
    }

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

    public float averagePayCheckPending() {
        if (this.payChecks.size() == 0) {
            return -1f;
        }
        return this.getTotalPayChecks() / this.payChecks.size();
    }


    public float getTotalPayChecks() {
        float sumPayChecks = 0.f;
        for (Paycheck payCheck : payChecks) {
            sumPayChecks += payCheck. getAmount();
        }
        return sumPayChecks;
    }

    public List<Paycheck> getPendingPayChecks() {
        return this.payChecks;
    }

}
