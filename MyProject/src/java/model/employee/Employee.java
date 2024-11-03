/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.employee;

/**
 *
 * @author ADMIN
 */
public class Employee {

    private String eid;
    private String ename;
    private String salaryLevel;
    private int did;
    private String createdBy;

    public Employee() {
    }

    public Employee(String eid, String ename, String salaryLevel, int did, String createdBy) {
        this.eid = eid;
        this.ename = ename;
        this.salaryLevel = salaryLevel;
        this.did = did;
        this.createdBy = createdBy;
    }

    public String getEid() {
        return eid;
    }

    public void setEid(String eid) {
        this.eid = eid;
    }

    public String getEname() {
        return ename;
    }

    public void setEname(String ename) {
        this.ename = ename;
    }

    public String getSalaryLevel() {
        return salaryLevel;
    }

    public void setSalaryLevel(String salaryLevel) {
        this.salaryLevel = salaryLevel;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
    
    
}
