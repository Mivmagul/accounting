package com.mivmagul.stakelogic.accounting.application.domain;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.StringJoiner;


@Entity
public class Employee extends BaseEntity {

    @NotNull
    private String name;
    private Float salary;
    private String address;

    public Employee(){
        super();
    }

    public Employee(@NotNull String name, Float salary, String address) {
        this.name = name;
        this.salary = salary;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(getId(), employee.getId()) &&
                name.equals(employee.name) &&
                Objects.equals(salary, employee.salary) &&
                Objects.equals(address, employee.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), name, salary, address);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Employee.class.getSimpleName() + "[", "]")
                .add("Id=" + getId())
                .add("name='" + name + "'")
                .add("salary=" + salary)
                .add("address='" + address + "'")
                .toString();
    }
}
