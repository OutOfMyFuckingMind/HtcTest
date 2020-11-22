package com.chadb.htctest.ui.viewmodel;

import com.chadb.htctest.data.dto.CompanyDTO;

public class MainActivityState {

    static MainActivityState loading() {
        return new MainActivityState.LoadingState();
    }

    static MainActivityState success(CompanyDTO data) {
        return new MainActivityState.SuccessState(data);
    }

    static MainActivityState failure(Exception reason) {
        return new MainActivityState.FailureState(reason);
    }

    public static class LoadingState extends MainActivityState {}

    public static class SuccessState extends MainActivityState {

        public CompanyDTO data;

        SuccessState(CompanyDTO data) {
            this.data = data;
        }
    }

    public static class FailureState extends MainActivityState {

        public Exception reason;

        FailureState(Exception reason) {
            this.reason = reason;
        }
    }
}
