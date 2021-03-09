package com.esunsoft2020.weatheropenapiex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

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

public class CompleteActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> items = new ArrayList<>();
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);


        listView = findViewById(R.id.list_view);
        adapter = new ArrayAdapter(this,R.layout.textview_item,items);
        listView.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();


        if (G.stnId==null) return;

//        Toast.makeText(this, choice_area+" : "+choice_sec, Toast.LENGTH_SHORT).show();
        items.clear();
        adapter.notifyDataSetChanged();

        if(G.choice_sec.equals("개황")){
            new Thread(){
                @Override
                public void run() {

                    String address ="http://apis.data.go.kr/1360000/VilageFcstMsgService/getWthrSituation"
                            +"?serviceKey="+G.apiKey
                            +"&numOfRows="+10
                            +"&pageNo="+1
                            +"&dataType="+"XMl"
                            +"&stnId="+G.stnId;

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
                                        buffer.append("\n----기상상황----\n"+xpp.getText()+"\n");
                                    } else if(tagName.equals("wn")){
                                        xpp.next();
                                        buffer.append("\n----특보사항----\n"+xpp.getText()+"\n");
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
                                        if(buffer.toString()==null || buffer.toString().equals("\n")) items.add(address);
                                        else items.add(buffer.toString());

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

        }else if(G.choice_sec.equals("육상")){
            new Thread(){
                @Override
                public void run() {

                    String address ="http://apis.data.go.kr/1360000/VilageFcstMsgService/getLandFcst"
                            +"?serviceKey="+G.apiKey
                            +"&numOfRows="+10
                            +"&pageNo="+1
                            +"&dataType="+"XMl"
                            +"&regId="+G.stnId;

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(0,R.anim.anim_slide_out_right);
    }
}