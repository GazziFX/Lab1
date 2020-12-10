package com.example.lab1.helpers.permissions;

import android.content.pm.PackageManager;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class PermissionHelper {
    /**
     * Выполняет проверку прав доступа
     *
     * @param context контекст проверки прав
     * @param callback действие если пользователь дал права
     * @param permission права которые проверяем
     */
    public static void checkPermission(PermissionActivity context, PermissionActivity.PermissionCallback callback,
                                       String permission) {
        context.setCallback(callback);
//Проверяет есть ли права
        int READ_EXTERNAL_PERMISSION = ContextCompat.checkSelfPermission(context, permission);
        if ((READ_EXTERNAL_PERMISSION != PackageManager.PERMISSION_GRANTED)) {
//Запросить разрешение у пользователя, открывает окно с запросом прав
            ActivityCompat.requestPermissions(context, new String[]{permission},
                    PermissionActivity.PERMISSION_READ);
            return;
        }
        callback.onGrant();
    }
}