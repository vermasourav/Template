package com.verma.android.common.viewmodel;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.gson.Gson;
import com.verma.android.common.Utils;
import com.verma.android.template.R;
import com.verma.android.template.ui.menu.aboutus.models.Member;
import com.verma.android.template.ui.menu.aboutus.models.OfficeInfo;
import com.verma.android.template.ui.menu.aboutus.others.GlobalMethods;

import java.util.ArrayList;

public class AboutUsViewModel extends ViewModel {

    final String urlIsNotProvidedYet = "URL is not provided yet";
    public ArrayList<Member> members = new ArrayList<>();
    public OfficeInfo officeInfo;
    private MutableLiveData<String> mText;

    public AboutUsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }


    public void init(Context pContext) {
        try {
            String aboutMe = Utils.getInstance().readFile(pContext, R.raw.about_me);
            if (!TextUtils.isEmpty(aboutMe)) {
                officeInfo = new Gson().fromJson(aboutMe, OfficeInfo.class);
                if (null != officeInfo) {
                    members.addAll(officeInfo.getMembers());
                }
            } else {
                officeInfo = new Gson().fromJson("", OfficeInfo.class);
            }
        } catch (Exception e) {
            officeInfo = new Gson().fromJson("", OfficeInfo.class);
        }
    }

    public void onClickGooglePlay(View pView) {
        if (GlobalMethods.isAvailable(officeInfo.getGooglePlayUrl())) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(officeInfo.getGooglePlayUrl()));
            pView.getContext().startActivity(browse);
        } else {
            Toast.makeText(pView.getContext(), urlIsNotProvidedYet, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickFacebook(View pView) {
        if (GlobalMethods.isAvailable(officeInfo.getFacebookPageUrl())) {
            pView.getContext().startActivity(GlobalMethods.getFacebookPageIntent(pView.getContext(), officeInfo));
        } else {
            Toast.makeText(pView.getContext(), urlIsNotProvidedYet, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickGroup(View pView) {
        if (GlobalMethods.isAvailable(officeInfo.getGroupUrl())) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(officeInfo.getGroupUrl()));
            pView.getContext().startActivity(browse);
        } else {
            Toast.makeText(pView.getContext(), urlIsNotProvidedYet, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickYoutube(View pView) {
        if (GlobalMethods.isAvailable(officeInfo.getYoutubeUrl())) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(officeInfo.getYoutubeUrl()));
            pView.getContext().startActivity(browse);
        } else {
            Toast.makeText(pView.getContext(), urlIsNotProvidedYet, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickGithub(View pView) {
        if (GlobalMethods.isAvailable(officeInfo.getGithubUrl())) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(officeInfo.getGithubUrl()));
            pView.getContext().startActivity(browse);
        } else {
            Toast.makeText(pView.getContext(), urlIsNotProvidedYet, Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickWeb(View pView) {
        if (GlobalMethods.isAvailable(officeInfo.getWebUrl())) {
            Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(officeInfo.getWebUrl()));
            pView.getContext().startActivity(browse);
        } else {
            Toast.makeText(pView.getContext(), urlIsNotProvidedYet, Toast.LENGTH_SHORT).show();
        }
    }

    public LiveData<String> getText() {
        return mText;
    }
}