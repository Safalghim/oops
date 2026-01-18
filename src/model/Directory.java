package model;


import java.util.ArrayList;
import java.util.List;


public class Directory {
private List<Staff> staffList = new ArrayList<>();
private List<Department> departmentList = new ArrayList<>();


public void addStaff(Staff s) { staffList.add(s); }
public void addDepartment(Department d) { departmentList.add(d); }


public List<Staff> getStaffList() { return staffList; }
public List<Department> getDepartmentList() { return departmentList; }
}