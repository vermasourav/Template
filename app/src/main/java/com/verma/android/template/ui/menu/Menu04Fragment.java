package com.verma.android.template.ui.menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.verma.android.template.R;
import com.verma.android.template.databinding.Fragment04Binding;

public class Menu04Fragment extends MenuBaseFragment {
    private Fragment04Binding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(false);
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_04, container, false);

        return binding.getRoot();
    }
}