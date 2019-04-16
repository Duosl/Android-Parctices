package com.duoshilin.activity_lifecycle_demo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button alertDialogBtn;
    private Button toDialogActivityBtn;
    private Button popupWindowBtn;
    private Button dialogFragmentBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TAG = "MainActivity";
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alertDialogBtn = findViewById(R.id.alert_dialog);
        toDialogActivityBtn = findViewById(R.id.to_dialog_activity);
        popupWindowBtn = findViewById(R.id.popup_window);
        dialogFragmentBtn = findViewById(R.id.dialog_fragment);

        alertDialogBtn.setOnClickListener(this);
        toDialogActivityBtn.setOnClickListener(this);
        popupWindowBtn.setOnClickListener(this);
        dialogFragmentBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.alert_dialog:
                showAlertDialog();
                break;
            case R.id.to_dialog_activity:
                toDialogActivity();
                break;
            case R.id.popup_window:
                showPopupWindow();
                break;
            case R.id.dialog_fragment:
                showDialogFragment();
                break;
            default:
                break;
        }
    }

    private void showDialogFragment() {
        new MyDialogFragment().show(getSupportFragmentManager(), "dialog_fragment");
    }

    private void showPopupWindow() {
        View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_main2, null);
        view.setBackgroundColor(0xFFEEEEEE);
        PopupWindow window = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        window.showAsDropDown(popupWindowBtn);
    }

    private void toDialogActivity() {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        startActivity(intent);
    }

    private void showAlertDialog() {
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this)
                .setTitle("ceshi")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "you click the OK button", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this, "you click the CANCEL button", Toast.LENGTH_SHORT).show();
                    }
                })
                .create();
        WindowManager.LayoutParams layoutParams = alertDialog.getWindow().getAttributes();
        alertDialog.setContentView(R.layout.activity_main2);
//        layoutParams.gravity = Gravity.BOTTOM;
        alertDialog.show();

        //可直接调用show()方法，
        new AlertDialog.Builder(MainActivity.this).setTitle("列表框").setItems(new String[]{"列表1", "列表2", "列表3"}, null).setPositiveButton("确定", null).show();
    }
}
