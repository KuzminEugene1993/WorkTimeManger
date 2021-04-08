package com.example.worktimemeter;
import android.os.Build;

import androidx.annotation.RequiresApi;

import java.time.*;

public  class TimeCalculator {


    private int result;
    private String textResult;
    private String start;
    private String end;

    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public void setTextResult(String textResult) {
        this.textResult = textResult;
    }

    public int getResult() {
        return result;
    }

    public String getTextResult() {
        return textResult;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public String timeCalculate(){

        LocalTime startDay = LocalTime.parse(start);
        LocalTime endDay = LocalTime.parse(end);
        Duration day = Duration.between(startDay, endDay);
        int dayTime = (int)day.toMinutes();
        int allDay = 540;
        this.result = result + (allDay - dayTime);

        if (result > 0){
            return textResult = "Ваша недоработка - " + "\n"+ Math.abs(result) + " мин.";
        }else if (result < 0){
            return textResult = "Ваша перерботка - " + "\n" + Math.abs(result) + " мин";
        }else{
            return textResult = "У вас нет недоработки";
        }


    }

    public  String eraseTime(){
        this.result = 0;
        return  textResult = "Начался новый месяц";
    }


}

