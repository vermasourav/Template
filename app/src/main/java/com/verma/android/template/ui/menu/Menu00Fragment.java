package com.verma.android.template.ui.menu;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.view.MenuProvider;
import androidx.databinding.DataBindingUtil;

import com.verma.android.dashboard.DashBoardItem;
import com.verma.android.dashboard.DashBoardManager;
import com.verma.android.dashboard.DashboardClickListener;
import com.verma.android.template.R;
import com.verma.android.template.databinding.Fragment00Binding;

import java.util.ArrayList;

import timber.log.Timber;

public class Menu00Fragment extends MenuBaseFragment {
    private Fragment00Binding binding;
    private static final String TAG = "Menu00Fragment";


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_00, container, false);
        setOptionMenu(true);
        return binding.getRoot();
    }

    @Override
    public void setOptionMenu(boolean hasMenu) {
        setOptionMenu(hasMenu, new MenuProvider() {
            @Override
            public void onCreateMenu(@NonNull Menu menu, @NonNull MenuInflater menuInflater) {
                menuInflater.inflate(R.menu.main00, menu);
            }

            @Override
            public boolean onMenuItemSelected(@NonNull MenuItem menuItem) {
                if(menuItem.getItemId() == R.id.action_rate_us){
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public String getScreenName() {
        return getString(R.string.menu_nav_home);
    }
    @Override
    public void initComponent() {
        setupDashboard();
    }


    private void setupDashboard() {
        setupGrid();
        DashBoardManager dashBoardManager = new DashBoardManager();
        ArrayList<DashBoardItem> dashBoardItems = dashBoardManager.getDashBoardItems(getContext(),"content_dashboard.json");
        //Collections.sort(dashBoardItems, Comparator.comparing(o -> o.getName().toLowerCase()));
        dashBoardManager.setupDashboard(getContext(),binding.dashBoardGrid,2,dashBoardItems,dashboardClickListener);
    }

    DashboardClickListener dashboardClickListener = (v, dashBoardItem) -> {
        if(dashBoardItem.getChilds() != null){
            Timber.tag(TAG).d("onClick: %s- %s ",dashBoardItem.getId(), dashBoardItem.getName());

        }
    };

    private void setupGrid() {
        int orientation = getResources().getConfiguration().orientation;
        int spanCount;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            spanCount = 2;
        } else {
            spanCount = 3;
        }
        binding.dashBoardGrid.setColumnCount(spanCount);
    }

}