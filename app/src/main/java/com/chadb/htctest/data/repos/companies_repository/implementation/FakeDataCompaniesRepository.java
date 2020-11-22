package com.chadb.htctest.data.repos.companies_repository.implementation;

import android.util.Log;

import com.chadb.htctest.data.repos.companies_repository.CompaniesRepository;
import com.chadb.htctest.data.dto.CompanyDTO;
import com.chadb.htctest.data.dto.EmployeeDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FakeDataCompaniesRepository implements CompaniesRepository {

    static String tag = "FakeDataCompaniesRepository";

    @Override
    public CompanyDTO getOne() {

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            Log.e(tag, "Error while sleep");
        }

        final List<String> competences = new ArrayList<String>();
        competences.add("Android");
        competences.add("IOS");
        competences.add("PHP");
        competences.add("Smart-TV");
        competences.add("Java");

        final List<String> employeeSkills = new ArrayList<String>();
        employeeSkills.add("Java");
        employeeSkills.add("Android");

        final List<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();
        for (int i = 0; i < 10; i++) {
            employees.add(new EmployeeDTO(
                    "John",
                    "769453",
                    employeeSkills
            ));
        }

        return new CompanyDTO(
            "High Technologies Center",
            "18",
            competences,
            employees
        );
    }
}
