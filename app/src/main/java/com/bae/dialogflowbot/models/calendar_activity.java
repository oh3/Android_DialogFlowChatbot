package com.bae.dialogflowbot.models;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bae.dialogflowbot.R;
import com.google.android.material.snackbar.Snackbar;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class calendar_activity extends AppCompatActivity {

    public String fname=null;
    public String str=null;
    public CalendarView calendarView;
    public Button cha_Btn,del_Btn,save_Btn;
    public TextView diaryTextView,textView2,eventText;
    public EditText contextEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar_activity);

        calendarView = findViewById(R.id.calendarView);
        diaryTextView = findViewById(R.id.diaryTextView);
        save_Btn = findViewById(R.id.save_Btn);
        del_Btn = findViewById(R.id.del_Btn);
        cha_Btn = findViewById(R.id.cha_Btn);
        textView2 = findViewById(R.id.textView2);
        contextEditText = findViewById(R.id.contextEditText);
        eventText = findViewById(R.id.eventText);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
                textView2.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                eventText.setVisibility(View.INVISIBLE);
                diaryTextView.setText(String.format("%d / %d / %d", year, month + 1, dayOfMonth));
                contextEditText.setText(null);
                checkDay(year, month, dayOfMonth);
                checkEvent(year,month,dayOfMonth);
            }
        });
        save_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveDiary(fname);
                str = contextEditText.getText().toString();
                textView2.setText(str);
                save_Btn.setVisibility(View.INVISIBLE);
                cha_Btn.setVisibility(View.VISIBLE);
                del_Btn.setVisibility(View.VISIBLE);
                contextEditText.setVisibility(View.INVISIBLE);
                textView2.setVisibility(View.VISIBLE);
            }
        });
    }

    public void  checkDay(int cYear,int cMonth,int cDay){
        fname=""+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//????????? ?????? ????????????
        FileInputStream fis=null;//FileStream fis ??????

        try{
            fis=openFileInput(fname);

            byte[] fileData=new byte[fis.available()];
            fis.read(fileData);
            fis.close();

            str=new String(fileData);

            contextEditText.setVisibility(View.INVISIBLE);
            textView2.setVisibility(View.VISIBLE);
            textView2.setText(str);

            save_Btn.setVisibility(View.INVISIBLE);
            cha_Btn.setVisibility(View.VISIBLE);
            del_Btn.setVisibility(View.VISIBLE);

            cha_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    contextEditText.setVisibility(View.VISIBLE);
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText(str);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    textView2.setText(contextEditText.getText());
                }

            });
            del_Btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    textView2.setVisibility(View.INVISIBLE);
                    contextEditText.setText("");
                    contextEditText.setVisibility(View.VISIBLE);
                    save_Btn.setVisibility(View.VISIBLE);
                    cha_Btn.setVisibility(View.INVISIBLE);
                    del_Btn.setVisibility(View.INVISIBLE);
                    removeDiary(fname);
                }
            });
            if(textView2.getText()==null){
                textView2.setVisibility(View.INVISIBLE);
                diaryTextView.setVisibility(View.VISIBLE);
                save_Btn.setVisibility(View.VISIBLE);
                cha_Btn.setVisibility(View.INVISIBLE);
                del_Btn.setVisibility(View.INVISIBLE);
                contextEditText.setVisibility(View.VISIBLE);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void checkEvent(int eYear,int eMonth,int eDay){
        if(eYear == 2021){
            if(eMonth+1 == 6){
                if(eDay == 9){
                    eventText.setText(" ???????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if((eDay >= 15 && eDay <=18)){
                    eventText.setText(" ????????????\n"+ " ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if((eDay >= 19 && eDay <= 30)){
                    eventText.setText(" ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 21){
                    eventText.setText(" ????????????\n" + " ???1?????? ??????\n" +" ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 25){
                    eventText.setText(" ?????? ???????????? ??????\n"+" ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 28){
                    eventText.setText(" ???1?????? ???????????? ??????\n" +" ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
            }
            else if(eMonth+1 == 7){
                if(eDay == 1){
                    eventText.setText(" ???1?????? ???????????? ??????\n" + " ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 2){
                    eventText.setText(" ???1?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 20){
                    eventText.setText(" ?????? ???????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
            }
            else if(eMonth+1==8){
                if(eDay == 2 || eDay ==3){
                    eventText.setText(" ?????????????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 6){
                    eventText.setText(" ???2?????? ????????????(4??????)");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 9){
                    eventText.setText(" ???2?????? ????????????(3??????)");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 10){
                    eventText.setText(" ???2?????? ????????????(2??????)");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 11){
                    eventText.setText(" ???2?????? ????????????(1??????)");
                    eventText.setVisibility(View.VISIBLE);
                }
                if (eDay == 12 || eDay == 13){
                    eventText.setText(" ???2?????? ????????????(??????)");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay >= 23 && eDay <= 26){
                    eventText.setText(" ???2?????? ?????? ??? ????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                if(eDay == 26) {
                    eventText.setText("???2?????? ?????? ??? ????????? ??????\n" + "2020????????? ?????? ???????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
            }
            else if(eMonth+1 == 9){
                if(eDay == 1){
                    eventText.setText("??????\n" + "???2?????? ???????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay >= 2 && eDay <= 7){
                    eventText.setText("???2?????? ???????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
            }
            else if(eMonth+1 == 10){
                if(eDay >= 11 && eDay <= 17){
                    eventText.setText("???2?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay >= 18 && eDay <= 22){
                    eventText.setText("???2?????? ?????? ????????????\n" +" ???2?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
            }
            else if(eMonth+1 == 12){
                if(eDay >= 13 && eDay <= 16){
                    eventText.setText("???2?????? ?????? ????????????\n" + "???2?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay == 17){
                    eventText.setText("???2?????? ?????? ????????????\n" + "????????????\n" +" ???2?????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay == 24){
                    eventText.setText("???2?????? ?????? ????????????\n" + "???2?????? ???????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay == 30){
                    eventText.setText("???2?????? ?????? ????????????\n" + "???2?????? ???????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay == 31){
                    eventText.setText("???2?????? ?????? ????????????\n" + "\t???2?????? ???????????? ??????");
                    eventText.setVisibility(View.VISIBLE);
                }
                else if(eDay >= 18 && eDay <= 31){
                    eventText.setText("???2?????? ?????? ????????????");
                    eventText.setVisibility(View.VISIBLE);
                }
            }
        }


    }
    @SuppressLint("WrongConstant")
    public void removeDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content="";
            fos.write((content).getBytes());
            fos.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @SuppressLint("WrongConstant")
    public void saveDiary(String readDay){
        FileOutputStream fos=null;

        try{
            fos=openFileOutput(readDay,MODE_NO_LOCALIZED_COLLATORS);
            String content=contextEditText.getText().toString();
            fos.write((content).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
