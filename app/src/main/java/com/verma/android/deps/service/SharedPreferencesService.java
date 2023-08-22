package com.verma.android.deps.service;

import android.content.SharedPreferences;

import javax.inject.Inject;

public class SharedPreferencesService {

    public final SharedPreferences preferences;

    @Inject
    public SharedPreferencesService(SharedPreferences sharedPreferences) {
        this.preferences = sharedPreferences;
    }

    public void setInt(String pKeyName, Integer pValue) {
        int value = 0;
        SharedPreferences.Editor edit = preferences.edit();
        if (pValue != null) {
            value = pValue;
        }
        edit.putInt(pKeyName, value);
        edit.apply();
    }

    public int getInt(String pKeyName, Integer pDefaultValue) {
        int value = 0;
        if (pDefaultValue != null) {
            value = pDefaultValue;
        }
        return preferences.getInt(pKeyName, value);
    }

    public void setBoolean(String pKeyName, Boolean pValue) {
        boolean value = false;
        SharedPreferences.Editor edit = preferences.edit();
        if (pValue != null) {
            value = pValue;
        }
        edit.putBoolean(pKeyName, value);
        edit.commit();
    }

    public boolean getBoolean(String pKeyName, Boolean pDefaultValue) {
        boolean value = false;
        if (pDefaultValue != null) {
            value = pDefaultValue;
        }
        return preferences.getBoolean(pKeyName, value);
    }

    public void setString(String pKeyName, String pValue) {
        SharedPreferences.Editor edit = preferences.edit();
        edit.putString(pKeyName, pValue);
        edit.apply();
    }

    public String getString(String pKeyName, String pDefaultValue) {
        String value = "n/a";
        if (pDefaultValue != null)
            value = pDefaultValue;
        return preferences.getString(pKeyName, value);

    }

    public String[] getString(String[] strArr) {
        String[] strArr2 = new String[strArr.length];
        for (int i = 0; i < strArr.length; i++) {
            strArr2[i] = preferences.getString(strArr[i], "default");
        }
        return strArr2;
    }

}
