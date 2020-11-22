package com.chadb.htctest.data.dto;

import java.util.List;

public class CompanyDTO {

    public final String name;
    public final String age;
    public final List<String> competences;
    public final List<EmployeeDTO> employees;

    public CompanyDTO(String name, String age, List<String> competences, List<EmployeeDTO> employees) {
        this.name = name;
        this.age = age;
        this.competences = competences;
        this.employees = employees;
    }
}
