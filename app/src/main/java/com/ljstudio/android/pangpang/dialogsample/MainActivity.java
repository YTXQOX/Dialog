package com.ljstudio.android.pangpang.dialogsample;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.ljstudio.pangpang.dialog.AlertDialog;
import com.ljstudio.pangpang.dialog.ConfirmDialog;
import com.ljstudio.pangpang.dialog.DatePickerDialog;
import com.ljstudio.pangpang.dialog.InputDialog;
import com.ljstudio.pangpang.dialog.LinearNumberSelectDialog;
import com.ljstudio.pangpang.dialog.OptionsDialog;
import com.ljstudio.pangpang.dialog.Select2Dialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    int year = 1991;
    int month = 2;
    int day = 23;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnProgress).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProgressDialog dialog = new ProgressDialog(MainActivity.this);
                dialog.setCancelable(true);
                dialog.show();
            }
        });

        findViewById(R.id.btnInput).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                InputDialog dialog = new InputDialog(MainActivity.this);

                dialog.setText("bac");
                dialog.show();
            }
        });

        findViewById(R.id.btnGender).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OptionsDialog optionsDialog = new OptionsDialog(MainActivity.this, new OptionsDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onSelected(int index, String option) {

                    }
                }, getString(R.string.gender), 0, getString(R.string.male), getString(R.string.female), getString(R.string.male), getString(R.string.female));
                optionsDialog.show();
            }
        });

        findViewById(R.id.btnConfirm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfirmDialog dialog = new ConfirmDialog(MainActivity.this, null, getString(R.string.confirm), getString(R.string.areyousure));
                dialog.setButtonText("Not Sure", "Sure");
                dialog.show();
            }
        });

        findViewById(R.id.btnSelect3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new DatePickerDialog(MainActivity.this, new DatePickerDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onDone(int year, int month, int day) {
                        MainActivity.this.year = year;
                        MainActivity.this.month = month;
                        MainActivity.this.day = day;

                        Toast.makeText(MainActivity.this, "" + year + ":" + month + ":" + day, Toast.LENGTH_SHORT).show();
                    }
                }, getString(R.string.birthday), year, month, day, Calendar.getInstance().get(Calendar.YEAR) - 6).show();
            }
        });

        findViewById(R.id.btnSelect).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LinearNumberSelectDialog dialog = new LinearNumberSelectDialog(MainActivity.this, new LinearNumberSelectDialog.IListener() {
                    @Override
                    public void onCancel() {

                    }

                    @Override
                    public void onDone(Number number) {
                        Toast.makeText(MainActivity.this, "" + number, Toast.LENGTH_SHORT).show();
                    }
                }, getString(R.string.weight), getString(R.string.kg), 130, 230, 1, 0);
                dialog.show();
            }
        });

        findViewById(R.id.btnAlert).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog dialog = new AlertDialog(MainActivity.this, new AlertDialog.IListener() {
                    @Override
                    public void onDone() {

                    }
                }, getString(R.string.dialog_alert), getString(R.string.thisisaalert));
                dialog.setButtonText("OK");
                dialog.setWindowType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                dialog.show();
            }
        });

        findViewById(R.id.btnSelect2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Select2Dialog dialog = new Select2Dialog(MainActivity.this, null, "2", "", "");
                List<String> left = new ArrayList<>();
                left.add("福建");

                List<String> right = new ArrayList<>();
                right.add("福州");
                right.add("厦门");

                dialog.setItems(left, 0);
                dialog.setItems2(right, 0);
                dialog.show();
            }
        });

    }
}
