package com.chadb.htctest.ui.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.chadb.htctest.data.repos.companies_repository.CompaniesRepository;
import com.chadb.htctest.data.repos.companies_repository.implementation.FakeDataCompaniesRepository;
import com.chadb.htctest.data.dto.CompanyDTO;
import com.chadb.htctest.data.repos.companies_repository.implementation.RealDataCompaniesRepository;

public class MainActivityViewModel extends ViewModel {

    private final MutableLiveData<MainActivityState> state = new MutableLiveData<MainActivityState>();
    private final CompaniesRepository realCompanyRepository = new RealDataCompaniesRepository();

    public LiveData<MainActivityState> getState() {
        return state;
    }

    public void getEmployeesList() {
        state.setValue(MainActivityState.loading());
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
            try {
                CompanyDTO result = realCompanyRepository.getOne();
                state.postValue(MainActivityState.success(result));
            } catch (Exception e) {
                state.postValue(MainActivityState.failure(e));
            }
            }
        };
        final Thread thread = new Thread(runnable);
        thread.start();
    }
}
