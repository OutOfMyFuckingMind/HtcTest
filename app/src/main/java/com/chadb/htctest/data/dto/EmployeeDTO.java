package com.chadb.htctest.data.dto;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDTO {

    public final String name;
    @SerializedName(value = "phone_number")
    public final String phoneNumber;
    public final List<String> skills;

    public EmployeeDTO(String name, String phoneNumber, List<String> skills) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.skills = skills;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
