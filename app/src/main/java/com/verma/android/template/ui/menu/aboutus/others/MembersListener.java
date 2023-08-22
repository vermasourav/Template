package com.verma.android.template.ui.menu.aboutus.others;

import com.verma.android.template.ui.menu.aboutus.models.OfficeInfo;

public interface MembersListener {
    void onJsonDataReceived(OfficeInfo officeInfo);

    void onError(String error);
}
