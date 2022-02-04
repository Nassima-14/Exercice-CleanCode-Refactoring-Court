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

    public void setTakingHolidaysToFalse(){
        IntStream.range(0, this.payChecks.size()).forEach((i) -> this.isTakingHolidays.set(i, false));
    }

    public void processPendingPayChecks() {
        setTakingHolidaysToFalse();
        payChecks.stream().map(payCheck -> "Sending " + payCheck.getAmount() + "$ to " + payCheck.getTo()).forEach(System.out::println);
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

    public Boolean isHourlyEmployee(Employee employee){
        return  (employee instanceof HourlyEmployee);
    }

    public void createPendingForHourlyEmployee(HourlyEmployee hourlyEmployee){
        payChecks.add(new Paycheck(hourlyEmployee.getName(), hourlyEmployee.getAmount() * hourlyEmployee.getRate()));
    }

    public void salaryRaiseForHourlyEmployee(HourlyEmployee hourlyEmployee, float raise) {
        hourlyEmployee.setRate(hourlyEmployee.getRate() + raise);
    }

    public Boolean isSalariedEmployee(Employee employee){
        return  (employee instanceof SalariedEmployee);
    }

    public void createPendingForSalariedEmployee(SalariedEmployee salariedEmployee){
        payChecks.add(new Paycheck(salariedEmployee.getName(), salariedEmployee.getBiweekly()));
    }

    public void salaryRaiseForSalariedEmployee(SalariedEmployee salariedEmployee, float raise) {
        salariedEmployee.setBiweekly(salariedEmployee.getBiweekly() + raise);
    }

    public void createPendingForAllEmployees() {
        for (Employee employee: employees) {
            if (isHourlyEmployee(employee)) createPendingForHourlyEmployee((HourlyEmployee) employee);
            else if (isSalariedEmployee(employee)) createPendingForSalariedEmployee((SalariedEmployee) employee);
            else throw new RuntimeException("Error: No specified payment's method for this employee");
        }
    }

    public void salaryRaise(Employee employee, float raise) {
        if (raise < 0) throw new RuntimeException("Error: Raise cannot be negative");
        if (this.employees.contains(employee)) {
            if (isHourlyEmployee(employee)) salaryRaiseForHourlyEmployee((HourlyEmployee) employee, raise);
            else if (isSalariedEmployee(employee)) salaryRaiseForSalariedEmployee((SalariedEmployee) employee, raise);
            else throw new RuntimeException("Error: No specified payment's method for this employee");
        } else throw new RuntimeException("Error: Employee does not exist");
    }

    public float getTotalPayChecks() {
        float sumPayChecks = 0.f;
        for (Paycheck payCheck : payChecks) sumPayChecks += payCheck.getAmount();
        return sumPayChecks;
    }

    public float averagePayCheckPending() {
        if (this.payChecks.size() == 0) return -1f;
        return this.getTotalPayChecks() / this.payChecks.size();
    }

    public List<Paycheck> getPendingPayChecks() {
        return this.payChecks;
    }

}
