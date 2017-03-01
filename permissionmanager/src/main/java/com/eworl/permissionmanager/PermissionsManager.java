package com.eworl.permissionmanager;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Maninder on 01-03-2017.
 */

public class PermissionsManager {
    private Activity activity;
    private List<String> listPermissionsNeeded = new ArrayList<>();

    public PermissionsManager(Activity activity) {
        this.activity = activity;
    }

    public static PermissionsManager build(Activity activity) {
        return new PermissionsManager(activity);
    }

    public PermissionsManager add(final String permission) {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return this;
        }

        if (activity.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(permission);
        }

        return this;
    }

    public void ask(int requestCode) {
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(activity,
                    listPermissionsNeeded.toArray(
                            new String[listPermissionsNeeded.size()]),
                    requestCode);
        }
    }

}
