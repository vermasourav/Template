package com.verma.android.template.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.verma.android.template.R;
import com.verma.android.template.databinding.Fragment05Binding;

public class Menu05Fragment extends MenuBaseFragment {

    private Fragment05Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_05, container, false);
        return binding.getRoot();
    }
    @Override
    public String getScreenName() {
        return getString(R.string.menu_nav_five);
    }
    @Override
    public void initComponent() {

    }
}