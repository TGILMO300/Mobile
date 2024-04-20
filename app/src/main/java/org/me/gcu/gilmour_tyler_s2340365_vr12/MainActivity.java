package org.me.gcu.gilmour_tyler_s2340365_vr12;



import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import androidx.appcompat.app.AppCompatActivity;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<String> urlSources = new ArrayList<>();

    private List<String> urlSources2 = new ArrayList<>();



    // Lists to store parsed data for each city
    List<Thing> glasgowDataList = new ArrayList<>();
    List<Thing> londonDataList = new ArrayList<>();
    List<Thing> newYorkDataList = new ArrayList<>();
    List<Thing> omanDataList = new ArrayList<>();
    List<Thing> mauritiusDataList = new ArrayList<>();
    List<Thing> bangladeshDataList = new ArrayList<>();

    List<Thing> glasgowDataList2 = new ArrayList<>();
    List<Thing> londonDataList2 = new ArrayList<>();
    List<Thing> newYorkDataList2 = new ArrayList<>();
    List<Thing> omanDataList2 = new ArrayList<>();
    List<Thing> mauritiusDataList2 = new ArrayList<>();
    List<Thing> bangladeshDataList2 = new ArrayList<>();


    private TextView displayData;

    private TextView salutation;

    private TextView displayData2;

    private TextView salutation2;




    private Button btnGlasgow;

    private Button btnLondon;

    private Button btnNewYork;
    private Button btnOman;
    private Button btnbtnMauritius;

    private Button btnBangladesh;

    private Button btnLatest;

    private Button btnGlasgow2;

    private Button btnLondon2;

    private Button btnNewYork2;
    private Button btnOman2;
    private Button btnbtnMauritius2;

    private Button btnBangladesh2;

    private Button btn3Days;



    private static final List<String> cityEndings = Arrays.asList("2648579", "2643743", "5128581", "287286", "934154", "1185241");



    private ViewSwitcher aVS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        displayData = findViewById(R.id.displayData);
        salutation = findViewById(R.id.salutation);

        displayData2 = findViewById(R.id.displayData2);
        salutation2 = findViewById(R.id.salutation2);


        btnGlasgow = (Button) findViewById(R.id.btnGlasgow);
        btnLondon = (Button) findViewById(R.id.btnLondon);
        btnNewYork = (Button) findViewById(R.id.btnNewYork);
        btnOman = (Button) findViewById(R.id.btnOman);
        btnbtnMauritius = (Button) findViewById(R.id.btnMauritius);
        btnBangladesh = (Button) findViewById(R.id.btnBangladesh);
        btnLatest = (Button) findViewById(R.id.btnLatest);

        btnGlasgow2 = (Button) findViewById(R.id.btnGlasgow2);
        btnLondon2 = (Button) findViewById(R.id.btnLondon2);
        btnNewYork2 = (Button) findViewById(R.id.btnNewYork2);
        btnOman2 = (Button) findViewById(R.id.btnOman2);
        btnbtnMauritius2 = (Button) findViewById(R.id.btnMauritius2);
        btnBangladesh2 = (Button) findViewById(R.id.btnBangladesh2);
        btn3Days = (Button) findViewById(R.id.btn3Days);


        btnGlasgow.setOnClickListener(this);
        btnLondon.setOnClickListener(this);
        btnNewYork.setOnClickListener(this);
        btnOman.setOnClickListener(this);
        btnbtnMauritius.setOnClickListener(this);
        btnBangladesh.setOnClickListener(this);
        btnLatest.setOnClickListener(this);

        btnGlasgow2.setOnClickListener(this);
        btnLondon2.setOnClickListener(this);
        btnNewYork2.setOnClickListener(this);
        btnOman2.setOnClickListener(this);
        btnbtnMauritius2.setOnClickListener(this);
        btnBangladesh2.setOnClickListener(this);
        btn3Days.setOnClickListener(this);


        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2648579");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643743");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/5128581");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/287286");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/934154");
        urlSources.add("https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/1185241");

        urlSources2.add("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2648579");
        urlSources2.add("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2643743");
        urlSources2.add("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/5128581");
        urlSources2.add("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/287286");
        urlSources2.add("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/934154");
        urlSources2.add("https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/1185241");


        aVS = (ViewSwitcher) findViewById(R.id.aVs);


        try {
            startProgress();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }

        try {
            startProgress2();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void startProgress2() throws MalformedURLException {
        // Create and start downloader threads
        for (String url : urlSources2) {
            new Thread(new DownloaderTask2(url)).start();
        }
    }

    public void startProgress() throws MalformedURLException {
        // Create and start downloader threads
        for (String url : urlSources) {
            new Thread(new DownloaderTask(url)).start();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnGlasgow:
                salutation.setText("Glasgow");
                displayData(glasgowDataList);
                break;
            case R.id.btnLondon:
                salutation.setText("London");
                displayData(londonDataList);
                break;
            case R.id.btnNewYork:
                salutation.setText("NewYork");
                displayData(newYorkDataList);
                break;
            case R.id.btnOman:
                salutation.setText("Oman");
                displayData(omanDataList);
                break;
            case R.id.btnMauritius:
                salutation.setText("Mauritius");
                displayData(mauritiusDataList);
                break;
            case R.id.btnBangladesh:
                salutation.setText("Bangladesh");
                displayData(bangladeshDataList);
                break;
        }

        switch (v.getId()) {
            case R.id.btnGlasgow2:
                salutation2.setText("Glasgow");
                displayData2(glasgowDataList2);
                break;
            case R.id.btnLondon2:
                salutation2.setText("London");
                displayData2(londonDataList2);
                break;
            case R.id.btnNewYork2:
                salutation2.setText("NewYork");
                displayData2(newYorkDataList2);
                break;
            case R.id.btnOman2:
                salutation2.setText("Oman");
                displayData2(omanDataList2);
                break;
            case R.id.btnMauritius2:
                salutation2.setText("Mauritius");
                displayData2(mauritiusDataList2);
                break;
            case R.id.btnBangladesh2:
                salutation2.setText("Bangladesh");
                displayData2(bangladeshDataList2);
                break;
        }

        if(v == btnLatest){
            aVS.showNext();
        } else if (v == btn3Days) {
            aVS.showPrevious();
        }

    }


    class DownloaderTask implements Runnable {
        private String url;

        public DownloaderTask(String aurl) throws MalformedURLException {
            url = aurl;
        }

        @Override
        public void run() {
            try {
                String xmlData = downloadXmlData(url);
                parseXmlData(xmlData);
            } catch (IOException | XmlPullParserException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        private String downloadXmlData(String urlString) throws IOException {
            URL aurl = new URL(url);
            URLConnection yc = aurl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder result = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();
            return result.toString();
        }


        private void parseXmlData(String xmlData) throws XmlPullParserException, IOException {

            //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
            int i = xmlData.indexOf(">");
            xmlData = xmlData.substring(i + 1);
            Log.e("MyTag", xmlData);

            Thing aThing = null;
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(xmlData));
                int eventType = xpp.getEventType();
                boolean useTags = false;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            Log.d("MyTag", "New Item found!");
                            aThing = new Thing();
                            useTags = true;
                        } else if ((xpp.getName().equalsIgnoreCase("title")) && (useTags)) {
                            String temp = xpp.nextText();
                            Log.d("MyTag", "Title is " + temp);
                            aThing.SetTitle(temp);
                        } else if (xpp.getName().equalsIgnoreCase("description") && (useTags)) {
                            String temp = xpp.nextText();
                            Log.d("MyTag", "Description is " + temp);
                            aThing.SetDescription(temp);
                        } else if (xpp.getName().equalsIgnoreCase("Date") && (useTags)) {
                            String temp = xpp.nextText();
                            Log.d("MyTag", "Date is " + temp);
                            aThing.SetPubDate(temp);
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {

                            String urlEnding = url.substring(url.lastIndexOf("/") + 1);
                            if (cityEndings.contains(urlEnding)) {
                                if (xpp.getName().equalsIgnoreCase("Thing")) {
                                    Log.d("MyTag", "Thing parsing completed!");
                                }
                                switch (urlEnding) {
                                    case "2648579":
                                        glasgowDataList.add(aThing);
                                        break;
                                    case "2643743":
                                        londonDataList.add(aThing);
                                        break;
                                    case "5128581":
                                        newYorkDataList.add(aThing);
                                        break;
                                    case "287286":
                                        omanDataList.add(aThing);
                                        break;
                                    case "934154":
                                        mauritiusDataList.add(aThing);
                                        break;
                                    case "1185241":
                                        bangladeshDataList.add(aThing);
                                        break;
                                }
                            }
                        }

                    }
                    eventType = xpp.next();
                }
            } catch (XmlPullParserException ae1) {
                Log.e("MyTag", "Parsing error" + ae1.toString());
            } catch (IOException ae1) {
                Log.e("MyTag", "IO error during parsing");
            }


            xmlData = String.valueOf(glasgowDataList);
            String finalXmlData = xmlData;
            MainActivity.this.runOnUiThread(new Runnable() {
                public void run() {
                    Log.d("UI thread", "I am the UI thread");
                    displayData.setText(finalXmlData);
                }
            });

        }
    }

    class DownloaderTask2 implements Runnable {
        private String url;

        public DownloaderTask2(String aurl) throws MalformedURLException {
            url = aurl;
        }

        @Override
        public void run() {
            try {
                String xmlData = downloadXmlData2(url);
                parseXmlData2(xmlData);
            } catch (IOException e) {
                System.err.println("Error: " + e.getMessage());
            }


        }



        private String downloadXmlData2(String url) throws IOException {
            URL aurl = new URL(url);
            URLConnection yc = aurl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            StringBuilder result = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                result.append(inputLine);
            }
            in.close();
            return result.toString();

        }

        private void parseXmlData2(String xmlData2) {

            //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
            int i = xmlData2.indexOf(">");
            xmlData2 = xmlData2.substring(i + 1);
            Log.e("MyTag", xmlData2);

            Thing aThing = null;
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(true);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(new StringReader(xmlData2));
                int eventType = xpp.getEventType();
                boolean useTags = false;
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {
                            Log.d("MyTag", "New Item found!");
                            aThing = new Thing();
                            useTags = true;
                        } else if ((xpp.getName().equalsIgnoreCase("title")) && (useTags)) {
                            String temp = xpp.nextText();
                            Log.d("MyTag", "Title is " + temp);
                            aThing.SetTitle(temp);
                        } else if (xpp.getName().equalsIgnoreCase("description") && (useTags)) {
                            String temp = xpp.nextText();
                            Log.d("MyTag", "Description is " + temp);
                            aThing.SetDescription(temp);
                        } else if (xpp.getName().equalsIgnoreCase("Date") && (useTags)) {
                            String temp = xpp.nextText();
                            Log.d("MyTag", "Date is " + temp);
                            aThing.SetPubDate(temp);
                        }
                    } else if (eventType == XmlPullParser.END_TAG) {
                        if (xpp.getName().equalsIgnoreCase("item")) {

                            String urlEnding = url.substring(url.lastIndexOf("/") + 1);
                            if (cityEndings.contains(urlEnding)) {
                                if (xpp.getName().equalsIgnoreCase("Thing")) {
                                    Log.d("MyTag", "Thing parsing completed!");
                                }
                                switch (urlEnding) {
                                    case "2648579":
                                        glasgowDataList2.add(aThing);
                                        break;
                                    case "2643743":
                                        londonDataList2.add(aThing);
                                        break;
                                    case "5128581":
                                        newYorkDataList2.add(aThing);
                                        break;
                                    case "287286":
                                        omanDataList2.add(aThing);
                                        break;
                                    case "934154":
                                        mauritiusDataList2.add(aThing);
                                        break;
                                    case "1185241":
                                        bangladeshDataList2.add(aThing);
                                        break;
                                }
                            }
                        }

                    }
                    eventType = xpp.next();
                }
            } catch (XmlPullParserException ae1) {
                Log.e("MyTag", "Parsing error" + ae1.toString());
            } catch (IOException ae1) {
                Log.e("MyTag", "IO error during parsing");
            }

        }
    }


    private void displayData(List<Thing> dataList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (dataList != null && !dataList.isEmpty()) {
            for (Thing data : dataList) {
                stringBuilder.append(data.toString()).append("\n");
            }
            displayData.setText(stringBuilder.toString());
        } else {
            displayData.setText("Data not available");
        }
    }

    private void displayData2(List<Thing> dataList) {
        StringBuilder stringBuilder = new StringBuilder();
        if (dataList != null && !dataList.isEmpty()) {
            for (Thing data : dataList) {
                stringBuilder.append(data.toString()).append("\n");
            }
            displayData2.setText(stringBuilder.toString());
        } else {
            displayData2.setText("Data not available");
        }
    }


}