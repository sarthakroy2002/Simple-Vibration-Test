package com.neolit.simplevibrationtest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CombinedVibration;
import android.os.VibrationEffect;
import android.os.VibratorManager;
import android.provider.Settings;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        promptToEnableHapticFeedback(this);

        final VibratorManager vibrator = (VibratorManager) getSystemService(Context.VIBRATOR_MANAGER_SERVICE);

        Button bNormalVibration = findViewById(R.id.normalVibrationButton);
        Button bClickVibration = findViewById(R.id.clickVibrationButton);
        Button bDoubleClickVibration = findViewById(R.id.doubleClickVibrationButton);
        Button bTickVibration = findViewById(R.id.tickVibrationButton);
        Button bHeavyClickVibration = findViewById(R.id.heavyClickVibrationButton);
        TextView tTitle = findViewById(R.id.textView);

        bNormalVibration.setOnClickListener(v -> {
            final VibrationEffect vibrationEffect1;
            vibrationEffect1 = VibrationEffect.createOneShot(1000, VibrationEffect.DEFAULT_AMPLITUDE);
            vibrator.cancel();
            vibrator.vibrate(CombinedVibration.createParallel(vibrationEffect1));
        });

        bClickVibration.setOnClickListener(v -> {
            final VibrationEffect vibrationEffect2;
            vibrationEffect2 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK);
            vibrator.cancel();
            vibrator.vibrate(CombinedVibration.createParallel(vibrationEffect2));
        });

        bDoubleClickVibration.setOnClickListener(v -> {
            final VibrationEffect vibrationEffect3;
            vibrationEffect3 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_DOUBLE_CLICK);
            vibrator.cancel();
            vibrator.vibrate(CombinedVibration.createParallel(vibrationEffect3));
        });

        bTickVibration.setOnClickListener(v -> {
            final VibrationEffect vibrationEffect4;
            vibrationEffect4 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_TICK);
            vibrator.cancel();
            vibrator.vibrate(CombinedVibration.createParallel(vibrationEffect4));
        });

        bHeavyClickVibration.setOnClickListener(v -> {
            final VibrationEffect vibrationEffect5;
            vibrationEffect5 = VibrationEffect.createPredefined(VibrationEffect.EFFECT_HEAVY_CLICK);
            vibrator.cancel();
            vibrator.vibrate(CombinedVibration.createParallel(vibrationEffect5));
        });

        tTitle.setOnClickListener(v -> {
                MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
                builder.setMessage("It is a simple Android app created by Sarthak Roy to test Vibration and its patterns.");
                builder.setTitle("About the App");
                builder.setNegativeButton("Done", (dialog, which) -> dialog.cancel());
                builder.show();
        });
    }

    public boolean isVibrationEnabled(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.VIBRATE_ON, 0) == 1;
    }

    public boolean isHapticFeedbackEnabled(Context context) {
        return Settings.System.getInt(context.getContentResolver(),
                Settings.System.HAPTIC_FEEDBACK_ENABLED, 0) == 1;
    }

    public void promptToEnableHapticFeedback(Context context) {
        if (!isVibrationEnabled(context) || !isHapticFeedbackEnabled(context)) {
            MaterialAlertDialogBuilder builder = getMaterialAlertDialogBuilder();
            builder.show();
        }
    }

    private @NonNull MaterialAlertDialogBuilder getMaterialAlertDialogBuilder() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(MainActivity.this);
        builder.setMessage("Touch vibration is currently disabled. Would you like to enable it?");
        builder.setTitle("Enable Touch Vibration");
        builder.setCancelable(false);
        builder.setPositiveButton("Go to Settings", (dialog, which) -> {
            Intent intent = new Intent(Settings.ACTION_SOUND_SETTINGS);
            startActivity(intent);
        });
        builder.setNegativeButton("No", (dialog, which) -> finish());
        return builder;
    }

}