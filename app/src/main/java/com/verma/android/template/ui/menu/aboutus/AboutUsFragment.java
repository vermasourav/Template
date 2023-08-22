package com.verma.android.template.ui.menu.aboutus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.verma.android.common.viewmodel.AboutUsViewModel;
import com.verma.android.template.R;
import com.verma.android.template.databinding.FragmentAboutUsBinding;
import com.verma.android.template.ui.menu.aboutus.adapters.AboutRecyclerviewAdapter;


public class AboutUsFragment extends Fragment {

    private FragmentAboutUsBinding binding;
    private AboutUsViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_about_us, container, false);
        viewModel = new AboutUsViewModel();
        viewModel.init(getContext());
        binding.setViewModel(viewModel);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        binding.officeAboutRecyclerView.setLayoutManager(manager);
        RecyclerView.Adapter<AboutRecyclerviewAdapter.ViewHolder> adapter = new AboutRecyclerviewAdapter(getContext(), viewModel.members);
        binding.officeAboutRecyclerView.setItemAnimator(new DefaultItemAnimator());
        binding.officeAboutRecyclerView.setAdapter(adapter);
        binding.srl.setRefreshing(true);
        binding.srl.setRefreshing(false);
        return binding.getRoot();

    }
}
