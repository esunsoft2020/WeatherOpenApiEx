package com.esunsoft2020.weatheropenapiex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

    ListView listView;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;

    String apiKey ="Cx3AHqy2pe4jWIsqipP3lRTJSPvNDLSYVDy0MHPQuImutkgUJn%2FlwlWaox6YkvXpm1%2FdJw4dy2knihJ2Hyc9vw%3D%3D";
    String stnId = "";

    ArrayAdapter<CharSequence> spinner1,spinner2, spinner3;
    String choice_sec ="";
    String choice_area ="";
    String choice_zone ="";

    Spinner spin1;
    Spinner spin2;
    Spinner spin3;

    String[] stnIdsSec2_1 = new String[]{
            "11B10101",
            "11B20201",
            "11B20601",
            "11B20605",
            "11B20602",
            "11B10103",
            "11B10102",
            "11B20606",
            "11B20603",
            "11B20609",
            "11B20612",
            "11B20610",
            "11B20611",
            "11B20604",
            "11B20503",
            "11B20501",
            "11B20502",
            "11B20504",
            "11B20701",
            "11B20703",
            "11B20702",
            "11B20301",
            "11B20302",
            "11B20305",
            "11B20304",
            "11B20401",
            "11B20402",
            "11B20403",
            "11B20404",
            "11B20101",
            "11B20102",
            "11B20202",
            "11B20204",
            "11B20203",
            "11A00101",};
    String[] stnIdsSec2_2 = new String[]{
            "11H20201",
            "11H20101",
            "11H20304",
            "11H20102",
            "11H20301",
            "11H20601",
            "11H20603",
            "11H20604",
            "11H20602",
            "11H20701",
            "11H20704",
            "11H20402",
            "11H20502",
            "11H20503",
            "11H20703",
            "11H20501",
            "11H20401",
            "11H20403",
            "11H20404",
            "11H20405",
    };

    String[] stnIdsSec2_3 = new String[]{
            "11H10701",
            "11H10702",
            "11H10703",
            "11H10704",
            "11H10705",
            "11H10601",
            "11H10602",
            "11H10603",
            "11H10604",
            "11H10605",
            "11H10501",
            "11H10502",
            "11H10503",
            "11H10302",
            "11H10301",
            "11H10303",
            "11H10401",
            "11H10402",
            "11H10403",
            "11H10101",
            "11H10102",
            "11H10201",
            "11H10202",
            "11E00101",
            "11E00102"
    };
    String[] stnIdsSec2_4 = new String[]{
            "11F20501",
            "11F20503",
            "11F20502",
            "11F20504",
            "11F20505",
            "21F20102",
            "21F20101",
            "21F20801",
            "21F20804",
            "21F20802",
            "21F20201",
            "21F20803",
            "11F20701",
            "11F20603",
            "11F20405",
            "11F20402",
            "11F20601",
            "11F20602",
            "11F20301",
            "11F20303",
            "11F20304",
            "11F20302",
            "11F20401",
            "11F20403",
            "11F20404"
    };
    String[] stnIdsSec2_5 = new String[]{
            "11F10201",
            "11F10202",
            "21F10501",
            "11F10203",
            "21F10502",
            "11F10401",
            "21F10601",
            "11F10302",
            "21F10602",
            "11F10403",
            "11F10204",
            "11F10402",
            "11F10301",
            "11F10303"
    };
    String[] stnIdsSec2_6 = new String[]{
            "11C20401",
            "11C20404",
            "11C20402",
            "11C20602",
            "11C20403",
            "11C20601",
            "11C20301",
            "11C20302",
            "11C20303",
            "11C20101",
            "11C20102",
            "11C20103",
            "11C20104",
            "11C20201",
            "11C20202",
            "11C20502",
            "11C20501"
    };
    String[] stnIdsSec2_7 = new String[]{
            "11C10301",
            "11C10304",
            "11C10303",
            "11C10102",
            "11C10101",
            "11C10103",
            "11C10201",
            "11C10202",
            "11C10302",
            "11C10403",
            "11C10402",
            "11C10401"
    };
    String[] stnIdsSec2_8 = new String[]{
            "11D10101",
            "11D10102",
            "11D10201",
            "11D10202",
            "11D10301",
            "11D10302",
            "11D10401",
            "11D10402",
            "11D10501",
            "11D10502",
            "11D10503",
            "11D20201",
            "11D20401",
            "11D20402",
            "11D20403",
            "11D20501",
            "11D20601",
            "11D20602",
            "11D20301"
    };
    String[] stnIdsSec2_9 = new String[]{
            "11G00201",
            "11G00401",
            "11G00101",
            "11G00501",
            "11G00302",
            "11G00601",
            "11G00800"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spin1 = findViewById(R.id.spinner_section);
        spin2 = findViewById(R.id.spinner_area);
        spin3 = findViewById(R.id.spinner_zone);

//        items.add("aaaaa");
//        items.add("bbb");

    }


    @Override
    protected void onResume() {
        super.onResume();

        listView = findViewById(R.id.list_view);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,items);
        listView.setAdapter(adapter);


        spinner1 = ArrayAdapter.createFromResource(this,R.array.spinner_section,android.R.layout.simple_spinner_dropdown_item);
        spin1.setAdapter(spinner1);



        spin1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choice_sec = spinner1.getItem(position).toString();

                if(spinner1.getItem(position).equals("개황")){
                    spinner2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.spinner_area1,android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(spinner2);
                }else {
                    spinner2 = ArrayAdapter.createFromResource(MainActivity.this,R.array.spinner_area2,android.R.layout.simple_spinner_dropdown_item);
                    spin2.setAdapter(spinner2);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        spin2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choice_area = spinner2.getItem(position).toString();

                if(choice_sec.equals("개황")){
                    switch (spinner2.getItem(position).toString()){
                        case "전국":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_1,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=108+"";
                            break;
                        case "서울,인천,경기도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_2,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=109+"";
                            break;
                        case "부산,울산,경상남도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_3,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=159+"";
                            break;
                        case "대구,경상북도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_4,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=143+"";
                            break;
                        case "광주,전라남도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_5,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=156+"";
                            break;
                        case "전라북도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_6,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=146+"";
                            break;
                        case "대전,세종,충청남도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_7,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=133+"";
                            break;
                        case "충청북도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_8,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=131+"";
                            break;
                        case "강원도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_9,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=105+"";
                            break;
                        case "제주도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec1_10,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            stnId=184+"";
                            break;
                    }
                }else if(choice_sec.equals("육상")){
                    switch (spinner2.getItem(position).toString()){
                        case "서울,인천,경기도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_1,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "부산,울산,경상남도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_2,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "대구,경상북도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_3,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "광주,전라남도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_4,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "전라북도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_5,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "대전,세종,충청남도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_6,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "충청북도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_7,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "강원도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_8,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                        case "제주도":
                            spinner3 = ArrayAdapter.createFromResource(MainActivity.this,R.array.sec2_9,android.R.layout.simple_spinner_dropdown_item);
                            spin3.setAdapter(spinner3);
                            break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "선택되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        spin3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                choice_zone = spinner3.getItem(position).toString();
//                Toast.makeText(MainActivity.this, choice_zone, Toast.LENGTH_SHORT).show();

                switch (choice_area){
                    case "서울,인천,경기도":
                        stnId = stnIdsSec2_1[position];
                        break;
                    case "부산,울산,경상남도":
                        stnId = stnIdsSec2_2[position];
                        break;
                    case "대구,경상북도":
                        stnId = stnIdsSec2_3[position];
                        break;
                    case "광주,전라남도":
                        stnId = stnIdsSec2_4[position];
                        break;
                    case "전라북도":
                        stnId = stnIdsSec2_5[position];
                        break;
                    case "대전,세종,충청남도":
                        stnId = stnIdsSec2_6[position];
                        break;
                    case "충청북도":
                        stnId = stnIdsSec2_7[position];
                        break;
                    case "강원도":
                        stnId = stnIdsSec2_8[position];
                        break;
                    case "제주도":
                        stnId = stnIdsSec2_9[position];
                        break;
                }
//                Toast.makeText(MainActivity.this, choice_zone+":"+stnId, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(MainActivity.this, "선택되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void clickBtn(View view) {

        if (stnId==null) return;

//        Toast.makeText(this, choice_area+" : "+choice_sec, Toast.LENGTH_SHORT).show();
        items.clear();
        adapter.notifyDataSetChanged();

        if(choice_sec.equals("개황")){
            new Thread(){
                @Override
                public void run() {

                    String address ="http://apis.data.go.kr/1360000/VilageFcstMsgService/getWthrSituation"
                            +"?serviceKey="+apiKey
                            +"&numOfRows="+10
                            +"&pageNo="+1
                            +"&dataType="+"XMl"
                            +"&stnId="+stnId;

                    try {
                        URL url = new URL(address);
                        InputStream is = url.openStream();
                        InputStreamReader isr = new InputStreamReader(is);

                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        XmlPullParser xpp = factory.newPullParser();

                        xpp.setInput(isr);

                        int eventType = xpp.getEventType();

                        StringBuffer buffer = null;

                        while(eventType!=XmlPullParser.END_DOCUMENT){

                            switch (eventType){
                                case XmlPullParser.START_DOCUMENT:
                                    break;

                                case XmlPullParser.START_TAG:
                                    String tagName = xpp.getName();

                                    if(tagName.equals("item")){
                                        buffer = new StringBuffer();
                                        buffer.append("\n");
                                    } else if (tagName.equals("tmFc")){
                                        xpp.next();
                                        Date date = new SimpleDateFormat("yyyyMMddHHmm").parse(xpp.getText());
                                        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
                                        String time = sdf.format(date);

                                        buffer.append("발표시간 : "+time+"\n");
                                    } else if(tagName.equals("wfSv1")){
                                        xpp.next();
                                        buffer.append("\n기상상황 \n"+xpp.getText()+"\n");
                                    } else if(tagName.equals("wn")){
                                        xpp.next();
                                        buffer.append("\n특보사항 : "+xpp.getText()+"\n");
                                    } else if(tagName.equals("wr")){
                                        xpp.next();
                                        buffer.append("\n예비특보 : "+xpp.getText()+"\n");
                                    }

                                    break;

                                case XmlPullParser.TEXT:
                                    break;

                                case XmlPullParser.END_TAG:
                                    String tagName2 = xpp.getName();
                                    if(tagName2.equals("item")){
                                        items.add(buffer.toString());

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.notifyDataSetChanged();
                                            }
                                        });

                                    }
                                    break;
                            }
                            eventType = xpp.next();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }.start();

        }else if(choice_sec.equals("육상")){
            new Thread(){
                @Override
                public void run() {

                    String address ="http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"
                            +"?serviceKey="+apiKey
                            +"&numOfRows="+10
                            +"&pageNo="+1
                            +"&dataType="+"XMl"
                            +"&regId="+stnId;

                    try {
                        URL url = new URL(address);
                        InputStream is = url.openStream();
                        InputStreamReader isr = new InputStreamReader(is);

                        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                        XmlPullParser xpp = factory.newPullParser();

                        xpp.setInput(isr);

                        int eventType = xpp.getEventType();

                        StringBuffer buffer = null;
                        while(eventType!=XmlPullParser.END_DOCUMENT){

                            switch (eventType){
                                case XmlPullParser.START_DOCUMENT:
                                    break;
                                case XmlPullParser.START_TAG:
                                    String tagName = xpp.getName();
                                    if(tagName.equals("item")){
                                        buffer = new StringBuffer();
                                    }else if(tagName.equals("announceTime")){
                                        xpp.next();

                                        Date date = new SimpleDateFormat("yyyyMMddHHmm").parse(xpp.getText());
                                        SimpleDateFormat sdf = new SimpleDateFormat(" yyyy-MM-dd HH:mm");
                                        String time = sdf.format(date);

                                        buffer.append("\n발표시간 : "+time+"\n");

                                    } else if (tagName.equals("numEf")){
                                        xpp.next();
                                        buffer.append("발효번호 : "+xpp.getText()+"\n");
                                    } else if(tagName.equals("wd1")){
                                        xpp.next();
                                        buffer.append("\n풍향 : "+xpp.getText()+"\n");
                                    } else if(tagName.equals("wsIt")){
                                        xpp.next();
                                        String wI = "";

                                        if(xpp.getText()==null) break;

                                        if (xpp.getText().equals("1")){
                                            wI = "약간 강";
                                        }else if(xpp.getText().equals("2")){
                                            wI = "강";
                                        }else if(xpp.getText().equals("3")){
                                            wI = "매우 강";
                                        }
                                        buffer.append("\n풍속강도 : "+wI+"\n");
                                    }else if(tagName.equals("ta")){
                                        xpp.next();

                                        if(xpp.getText()==null) break;

                                        buffer.append("\n예상기온 : "+xpp.getText()+"\n");
                                    } else if(tagName.equals("rnSt")){
                                        xpp.next();

                                        if(xpp.getText()==null) break;

                                        buffer.append("\n강수확률 : "+xpp.getText()+"\n");
                                    } else if(tagName.equals("wf")){
                                        xpp.next();

                                        if(xpp.getText()==null) break;

                                        buffer.append("날씨 : "+xpp.getText()+"\n");
                                    } else if(tagName.equals("wfCd")){
                                        xpp.next();
                                        String wfcd = "";

                                        if(xpp.getText()==null) break;

                                        if(xpp.getText().equals("DB01")){
                                            wfcd = "맑음";
                                        } else if(xpp.getText().equals("DB03")){
                                            wfcd = "구름많음";
                                        } else if(xpp.getText().equals("DB04")){
                                            wfcd = "흐림";
                                        }
                                        buffer.append("하늘상태 : "+wfcd+"\n");
                                    } else if(tagName.equals("rnYn")){
                                        xpp.next();
                                        String rn ="";

                                        if(xpp.getText()==null) break;

                                        switch (xpp.getText()){
                                            case "0":
                                                rn = "강수없음.";
                                                break;
                                            case "1":
                                                rn = "비";
                                                break;
                                            case "2":
                                                rn = "비 or 눈";
                                                break;
                                            case "3":
                                                rn = "눈";
                                                break;
                                            case "4":
                                                rn = "소나기";
                                                break;
                                        }
                                        buffer.append("강수형태 : "+rn);
                                    }

                                    break;

                                case XmlPullParser.TEXT:
                                    break;

                                case XmlPullParser.END_TAG:
                                    String tagName2 = xpp.getName();
                                    if(tagName2.equals("item")){
                                        items.add(buffer.toString());

                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                adapter.notifyDataSetChanged();
                                            }
                                        });

                                    }
                                    break;
                            }
                            eventType = xpp.next();
                        }

                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                       } catch (IOException e) {
                        e.printStackTrace();
                    } catch (XmlPullParserException e) {
                        e.printStackTrace();
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
            }.start();
        }


    }

}