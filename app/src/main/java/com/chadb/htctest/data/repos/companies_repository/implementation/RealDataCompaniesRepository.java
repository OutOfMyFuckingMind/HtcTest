package com.chadb.htctest.data.repos.companies_repository.implementation;

import android.util.Log;

import com.chadb.htctest.data.dto.CompanyDTO;
import com.chadb.htctest.data.dto.EmployeeDTO;
import com.chadb.htctest.data.repos.companies_repository.CompaniesRepository;
import com.chadb.htctest.data.responses.GetCompanyResponse;
import com.chadb.htctest.utils.StringUtils;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.Comparator;

public class RealDataCompaniesRepository implements CompaniesRepository {

    static final String tag = "RealCompaniesRepository";

    @Override
    public CompanyDTO getOne() {
        try {
            URL url = new URL("http://www.mocky.io/v2/5ddcd3673400005800eae483");
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("accept", "application/json");
            InputStream responseStream = connection.getInputStream();
            String responseAsString = StringUtils.readFromInputStream(responseStream);

            Gson gson = new Gson();
            GetCompanyResponse response = gson.fromJson(responseAsString, GetCompanyResponse.class);

            CompanyDTO company = response.company;
            Collections.sort(company.employees, new Comparator<EmployeeDTO>() {
                @Override
                public int compare(EmployeeDTO current, EmployeeDTO other) {
                    Log.i("Comparator", "Current EmployeeDTO: " + current);
                    Log.i("Comparator", "Other EmployeeDTO: " + other);
                    if (current.name == null && other.name == null) {
                        return 0;
                    }
                    if (current.name == null) {
                        return -1;
                    }
                    if (other.name == null) {
                        return 1;
                    }
                    return current.name.compareTo(other.name);
                }
            });

            Log.i(tag, company.employees.toString());

            return company;
        } catch (Exception exception) {
            Log.e(tag, "Error while requesting data: " + exception.toString());
        }
        return null;
    }
}
