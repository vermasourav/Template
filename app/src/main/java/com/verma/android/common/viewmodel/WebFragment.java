package com.verma.android.common.viewmodel;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.verma.android.template.R;
import com.verma.android.template.ui.menu.web.WebViewModel;

public class WebFragment extends Fragment {

    private WebViewModel viewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(WebViewModel.class);

        if (getArguments() != null) {
            //TODO
            String url = WebFragmentArgs.fromBundle(getArguments()).getUrlLink();
            viewModel.setText(url);
        }

        View root = inflater.inflate(R.layout.fragment_web_view, container, false);
        final WebView webView = root.findViewById(R.id.web_view);
        final ProgressBar progressBar = root.findViewById(R.id.url_prog_bar);
        viewModel.getText().observe(getActivity(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String pUrl) {
                webView.loadUrl(pUrl);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onReceivedError(final WebView view, int errorCode, String description, final String failingUrl) {
                        webView.loadUrl(pUrl);
                        super.onReceivedError(view, errorCode, description, failingUrl);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        viewModel.isLoadFinish = true;
                        progressBar.setVisibility(View.GONE);
                        super.onPageFinished(view, url);
                    }
                });
            }
        });
        return root;
    }
}