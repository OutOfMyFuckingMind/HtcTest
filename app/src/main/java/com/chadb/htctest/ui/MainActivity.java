package com.chadb.htctest.ui;

import android.app.AlertDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chadb.htctest.R;
import com.chadb.htctest.data.dto.CompanyDTO;
import com.chadb.htctest.ui.adapters.EmployeeAdapter;
import com.chadb.htctest.ui.viewmodel.MainActivityState;
import com.chadb.htctest.ui.viewmodel.MainActivityViewModel;
import com.chadb.htctest.utils.StringUtils;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private View progressIndicator;
    private View contentView;
    private RecyclerView employeeList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        viewModel =
            new ViewModelProvider(this, new ViewModelProvider.NewInstanceFactory())
            .get(MainActivityViewModel.class);

        viewModel.getState().observe(this, new Observer<MainActivityState>() {
            @Override
            public void onChanged(MainActivityState state) {
                if (state instanceof MainActivityState.SuccessState) {
                    hideLoadingIndicator();
                    showContent(((MainActivityState.SuccessState) state).data);
                } else if (state instanceof MainActivityState.FailureState) {
                    hideLoadingIndicator();
                    showError(((MainActivityState.FailureState)state).reason);
                } else if (state instanceof MainActivityState.LoadingState) {
                    hideContent();
                    showLoadingIndicator();
                }
            }
        });

        setContentView(R.layout.main_activity);
        progressIndicator = findViewById(R.id.progress_indicator);
        contentView = findViewById(R.id.content);

        employeeList = findViewById(R.id.employees_list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        employeeList.setLayoutManager(layoutManager);

        viewModel.getEmployeesList();
    }

    void showLoadingIndicator() {
        progressIndicator.setVisibility(View.VISIBLE);
    }

    void hideLoadingIndicator() {
        progressIndicator.setVisibility(View.GONE);
    }

    void showContent(CompanyDTO company) {
        contentView.setVisibility(View.VISIBLE);

        TextView companyNameView = findViewById(R.id.company_name);
        TextView companyAgeView = findViewById(R.id.company_age);
        TextView companyCompetencesView = findViewById(R.id.company_competences);

        companyNameView.setText(company.name);
        companyAgeView.setText(company.age);
        companyCompetencesView.setText(StringUtils.join(company.competences));

        RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> adapter =
            new EmployeeAdapter(company.employees);
        employeeList.setAdapter(adapter);
    }

    void hideContent() {
        contentView.setVisibility(View.GONE);
    }

    void showError(Exception error) {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        AlertDialog alertDialog = dialogBuilder
            .setTitle(R.string.error_has_occurred)
            .setMessage(error.toString())
            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).setNeutralButton(R.string.repeat, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    viewModel.getEmployeesList();
                    dialog.cancel();
                }
            }).create();

        alertDialog.show();
    }
}
