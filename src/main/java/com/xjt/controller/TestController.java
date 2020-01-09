package com.xjt.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * @author liqing
 * @version 1.0
 * @date 2020-01-04 09:10
 */
public class TestController {
    public static void main(String[] args) {

       calendarOfWeek();
       Employee[] staff = new Employee[3];
       staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
       staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
       staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        for (Employee employee : staff) {
            employee.raiseSalary(5);

        }

        for (Employee employee : staff) {
            System.out.println("name:"+ employee.getName()+" salary:"+employee.getSalary()+" hireDay:"+employee.getHireDay());

        }


    }

    public static void calendarOfWeek(){
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();
        date = date.minusDays(today -  1);//获取本月第一天的日期
        DayOfWeek weekday = date.getDayOfWeek();
        int value = weekday.getValue();


        System.out.println("Mon Tue Wed Thu Fri Sat Sun");
        for (int i = 1; i <value ; i++) {
            System.out.print("    ");

        }
        while (date.getMonthValue() == month){

            System.out.printf("%3d",date.getDayOfMonth());
            if (date.getDayOfMonth() == today)
                System.out.print("*");
            else
                System.out.print(" ");
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1)
                System.out.println();
        }

        if (date.getDayOfWeek().getValue() !=1 )
            System.out.println();

    }
}


class Employee{
    private String name;
    private double salary;
    private LocalDate hireDay;
    public Employee(String n,double s,int year,int month,int day){
        this.name = n;
        this.salary = s;
        this.hireDay = LocalDate.of(year,month,day);
    }

    public void raiseSalary(double byPersent){

        double raise = this.salary * byPersent /100;
        salary += raise;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getHireDay() {
        return hireDay;
    }

    public void setHireDay(LocalDate hireDay) {
        this.hireDay = hireDay;
    }
}

