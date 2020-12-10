package com.example.lab1.helpers.permissions;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Базовый класс для Activity которые работают с правами доступа
 */
public class PermissionActivity extends AppCompatActivity {
    public static final int PERMISSION_READ = 0;
    private PermissionCallback callback;
    /**
     * Обрабатывает реакцию пользователя на запрос прав
     *
     * @param requestCode код запроса проверки прав
     * @param permissions запрашиваемые права
     * @param grantResults результат(разрешил ли пользователь доступ)
     */
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_READ: {
                if (grantResults.length > 0 && permissions[0].equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    if (grantResults[0] == PackageManager.PERMISSION_DENIED) {
                        Toast.makeText(getApplicationContext(), "Please allow storage permission",
                                Toast.LENGTH_LONG).show();
                    } else {
                        callback.onGrant();
                    }
                }
            }
        }
    }
    /**
     * Устанавливает обработчик случая когда пользователь дал права
     *
     * @param callback обработчик
     */
    public void setCallback(PermissionCallback callback) {
        this.callback = callback;
    }
    /**
     * Интерфейс обработки результата проверки прав
     */
    public interface PermissionCallback {
        /**
         * Вызывается когда пользователь дал права на действие
         */
        void onGrant();
    }
}