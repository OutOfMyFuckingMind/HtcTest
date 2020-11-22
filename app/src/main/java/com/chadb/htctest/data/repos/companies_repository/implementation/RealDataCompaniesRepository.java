package com.chadb.htctest.data.repos.companies_repository.implementation;

import android.util.Log;

import com.chadb.htctest.data.dto.CompanyDTO;
import com.chadb.htctest.data.repos.companies_repository.CompaniesRepository;
import com.chadb.htctest.data.responses.GetCompanyResponse;
import com.chadb.htctest.utils.StringUtils;
import com.google.gson.Gson;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

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
            return response.company;
        } catch (Exception exception) {
            Log.e(tag, "Error while requesting data: " + exception.toString());
        }
        return null;
    }
}
