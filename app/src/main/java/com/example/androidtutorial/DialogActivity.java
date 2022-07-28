package com.example.androidtutorial;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class DialogActivity extends AppCompatActivity {
    private View rootView;
    static private final String TAG = "apple";
    private int testI = 2, tempI = testI;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_activity);

        rootView = findViewById(R.id.btn_snack);


        findViewById(R.id.btn_alert_dialog).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setMessage("Message")
                    .setCancelable(false)
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        Log.d(TAG, "OK");
                    })
                    .setNegativeButton("Cancel", null)
                    .setNeutralButton("Later", null)
                    .create()
                    .show();
        });

        findViewById(R.id.btn_toast).setOnClickListener(v -> {
            Toast.makeText(this, "Message", Toast.LENGTH_SHORT).show();
        });

        String[] items = {"item1", "item2", "item3"};
        findViewById(R.id.btn_list_dialog).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setItems(items, (dialogInterface, i) -> {
                        Log.d(TAG, "item " + items[i]);
                    })
                    .create()
                    .show();
        });

        findViewById(R.id.btn_list_dialog2).setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Title")
                    .setSingleChoiceItems(items, testI, (dialogInterface, i) -> {
                        tempI = i;
                    })
                    .setPositiveButton("OK", (dialogInterface, i) -> {
                        testI = tempI;
                    })
                    .setCancelable(false)
                    .create()
                    .show();
        });


        findViewById(R.id.btn_snack).setOnClickListener(v -> {
            Snackbar snackbar = Snackbar.make(rootView, "Hello World", Snackbar.LENGTH_SHORT);
            snackbar.show();
        });
    }
}
