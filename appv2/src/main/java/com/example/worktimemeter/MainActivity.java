package com.example.worktimemeter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText startDay;
    private EditText endDay;
    private TextView result;
    private TimeCalculator calculator;


    private SharedPreferences sPref;
    private static final String RESULT = "result";
    private static final String INT_RESULT = "intResult";
    private static final String STRING_RESULT = "stringResult";
    private static final String START_DAY_TIME = "starDay";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startDay = findViewById(R.id.et_startDay);
        endDay = findViewById(R.id.et_endDay);
        Button calculate = findViewById(R.id.b_calculate);
        Button erase = findViewById(R.id.b_erase);
        result = findViewById(R.id.tv_result);
        calculator = new TimeCalculator();

        calculate.setOnClickListener(this);
        erase.setOnClickListener(this);

        loadStart();
        loadResult();
        loadText();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.b_calculate):
                calculator.setStart(startDay.getText().toString());
                calculator.setEnd(endDay.getText().toString());
                result.setText(calculator.timeCalculate());
                saveResult();
                saveText();
                break;
            case (R.id.b_erase):
                result.setText(calculator.eraseTime());
                saveResult();
                saveText();
                break;
        }
    }


    private void saveResult(){
        sPref = getSharedPreferences(RESULT, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putInt(INT_RESULT, calculator.getResult());
        editor.apply();
    }

    private void loadResult(){
        sPref = getSharedPreferences(RESULT, Context.MODE_PRIVATE);
        int savedResult = sPref.getInt(INT_RESULT, 0);
        calculator.setResult(savedResult);
    }

    private void saveText(){
        sPref = getSharedPreferences(RESULT, Context.MODE_PRIVATE);
        SharedPreferences.Editor textEditor = sPref.edit();
        textEditor.putString(STRING_RESULT, calculator.getTextResult());
        textEditor.apply();
    }

    private void loadText(){
        sPref = getSharedPreferences(RESULT, Context.MODE_PRIVATE);
        String savedText = sPref.getString(STRING_RESULT, "");
        calculator.setTextResult(savedText);
        result.setText(calculator.getTextResult());
    }

    private void saveStart(){
        sPref = getSharedPreferences(RESULT,Context.MODE_PRIVATE);
        SharedPreferences.Editor startEditor = sPref.edit();
        startEditor.putString(START_DAY_TIME, startDay.getText().toString());
        startEditor.apply();
    }

    private void loadStart(){
        sPref = getSharedPreferences(RESULT, Context.MODE_PRIVATE);
        String savedStart = sPref.getString(START_DAY_TIME, "");
        startDay.setText(savedStart);
    }

    private void nextDay(){
        if (!startDay.getText().toString().equals("") & !endDay.getText().toString().equals("") ){
            sPref = getSharedPreferences(RESULT,MODE_PRIVATE);
            SharedPreferences.Editor startEditor = sPref.edit();
            startEditor.putString(START_DAY_TIME, "");
            startEditor.apply();
            String savedStart = sPref.getString(START_DAY_TIME, "");
            startDay.setText(savedStart);
            endDay.setText("");
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        saveStart();
        nextDay();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadStart();

    }


}
