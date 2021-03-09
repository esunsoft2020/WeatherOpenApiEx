package com.esunsoft2020.weatheropenapiex;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        G.spin1 = findViewById(R.id.spinner_section);
        G.spin2 = findViewById(R.id.spinner_area);
        G.spin3 = findViewById(R.id.spinner_zone);

    }


    @Override
    protected void onResume() {
        super.onResume();

        G.spinner1 = ArrayAdapter.createFromResource(this,R.array.spinner_section,android.R.layout.simple_spinner_dropdown_item);
        G.spin1.setAdapter(G.spinner1);



        G.spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                G.choice_sec = G.spinner1.getItem(position).toString();

                if(G.spinner1.getItem(position).equals("개황")){
                    G.spinner2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.spinner_area1,android.R.layout.simple_spinner_dropdown_item);
                    G.spin2.setAdapter(G.spinner2);
                }else {
                    G.spinner2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.spinner_area2,android.R.layout.simple_spinner_dropdown_item);
                    G.spin2.setAdapter(G.spinner2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        G.spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                G.choice_area = G.spinner2.getItem(position).toString();

                if(G.choice_sec.equals("개황")){
                    switch (G.spinner2.getItem(position).toString()){
                        case "전국":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_1,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=108+"";
                            break;
                        case "서울,인천,경기도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_2,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=109+"";
                            break;
                        case "부산,울산,경상남도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_3,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=159+"";
                            break;
                        case "대구,경상북도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_4,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=143+"";
                            break;
                        case "광주,전라남도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_5,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=156+"";
                            break;
                        case "전라북도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_6,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=146+"";
                            break;
                        case "대전,세종,충청남도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_7,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=133+"";
                            break;
                        case "충청북도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_8,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=131+"";
                            break;
                        case "강원도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_9,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=105+"";
                            break;
                        case "제주도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_10,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            G.stnId=184+"";
                            break;
                    }
                }else if(G.choice_sec.equals("육상")){
                    switch (G.spinner2.getItem(position).toString()){
                        case "서울,인천,경기도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_1,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "부산,울산,경상남도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_2,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "대구,경상북도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_3,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "광주,전라남도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_4,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "전라북도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_5,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "대전,세종,충청남도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_6,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "충청북도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_7,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "강원도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_8,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                        case "제주도":
                            G.spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_9,android.R.layout.simple_spinner_dropdown_item);
                            G.spin3.setAdapter(G.spinner3);
                            break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "선택되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        G.spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                G.choice_zone = G.spinner3.getItem(position).toString();
//                Toast.makeText(MainActivity.this, choice_zone, Toast.LENGTH_SHORT).show();

                switch (G.choice_area){
                    case "서울,인천,경기도":
                        G.stnId = G.stnIdsSec2_1[position];
                        break;
                    case "부산,울산,경상남도":
                        G.stnId = G.stnIdsSec2_2[position];
                        break;
                    case "대구,경상북도":
                        G.stnId = G.stnIdsSec2_3[position];
                        break;
                    case "광주,전라남도":
                        G.stnId = G.stnIdsSec2_4[position];
                        break;
                    case "전라북도":
                        G.stnId = G.stnIdsSec2_5[position];
                        break;
                    case "대전,세종,충청남도":
                        G.stnId = G.stnIdsSec2_6[position];
                        break;
                    case "충청북도":
                        G.stnId = G.stnIdsSec2_7[position];
                        break;
                    case "강원도":
                        G.stnId = G.stnIdsSec2_8[position];
                        break;
                    case "제주도":
                        G.stnId = G.stnIdsSec2_9[position];
                        break;
                }
                Log.e("stnId",G.stnId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "선택되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void clickBtn(View view) {
        startActivity(new Intent(this,CompleteActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right,0);
    }

    @Override
    public void onBackPressed() {

        new AlertDialog.Builder(this).setMessage("정말 종료하시겠습니까?").setNegativeButton("취소",null).setPositiveButton("종료하기", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        }).show();
    }
}