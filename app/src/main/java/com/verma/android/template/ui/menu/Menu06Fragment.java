package com.verma.android.template.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.verma.android.template.R;
import com.verma.android.template.databinding.Fragment06Binding;

public class Menu06Fragment extends MenuBaseFragment {
    private Fragment06Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_06, container, false);
        setHasOptionsMenu(false);
        return binding.getRoot();
    }
    @Override
    public String getScreenName() {
        return getString(R.string.menu_nav_six);
    }
    @Override
    public void initComponent() {

    }
}
