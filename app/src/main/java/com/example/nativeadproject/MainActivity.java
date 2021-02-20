package com.example.nativeadproject;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.nativead.NativeAd;
import com.google.android.gms.ads.nativead.NativeAdOptions;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "--->Native Ad";
    private RecyclerView recyclerView;
    private MyAdapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<ItemClass> myList;
    private List<NativeAd> nativeAdList;

    private ArrayList<Object> objects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "------Native Ad Project runs------");

        recyclerView = findViewById(R.id.id_recyclerView);
        recyclerView.setHasFixedSize(true);

        myList = new ArrayList<>();

        myList.add(new ItemClass(R.drawable.img_01,"James", "James@email.com"));
        myList.add(new ItemClass(R.drawable.img_02,"Alex", "Alex@email.com"));
        myList.add(new ItemClass(R.drawable.img_03,"Ian", "Ian@email.com"));
        myList.add(new ItemClass(R.drawable.img_04,"Mark", "Mark@email.com"));
        myList.add(new ItemClass(R.drawable.img_05,"Francesco", "Francesco@email.com"));
        myList.add(new ItemClass(R.drawable.img_06,"Maria", "Maria@email.com"));
        myList.add(new ItemClass(R.drawable.img_07,"Mohamed", "Mohamed@email.com"));

        layoutManager = new LinearLayoutManager(this);
        myAdapter= new MyAdapter();
        myAdapter.setList(myList);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(myAdapter);


        nativeAdList = new ArrayList<>();

        createNativeAd();


    }

    private void createNativeAd() {

        objects = new ArrayList<>();

        AdClass adClass = new AdClass();

        //---> initializing Google Ad SDK
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
                Log.d(TAG, "Google SDK Initialized");

                AdLoader adLoader = new AdLoader.Builder(MainActivity.this, "ca-app-pub-3940256099942544/2247696110")

                        .forNativeAd(new NativeAd.OnNativeAdLoadedListener() {
                            @Override
                            public void onNativeAdLoaded(NativeAd nativeAd) {
                                Log.d(TAG, "Native Ad Loaded");

                                if (isDestroyed()) {
                                    nativeAd.destroy();
                                    Log.d(TAG, "Native Ad Destroyed");
                                    return;
                                }

                                nativeAdList.add(nativeAd);

                                if(!adClass.getAdLoader().isLoading()){

                                    objects.add(myList.get(0));
                                    objects.add(myList.get(1));
                                    objects.add(myList.get(2));
                                    objects.add(nativeAdList.get(0));
                                    objects.add(myList.get(3));
                                    objects.add(myList.get(4));
                                    objects.add(myList.get(5));
                                    objects.add(nativeAdList.get(1));
                                    objects.add(myList.get(6));

                                    myAdapter.setObject(objects);
                                }

                            }
                        })

                        .withAdListener(new AdListener() {
                            @Override
                            public void onAdFailedToLoad(LoadAdError adError) {
                                // Handle the failure by logging, altering the UI, and so on.
                                Log.d(TAG, "Native Ad Failed To Load");

                                new CountDownTimer(10000, 1000) {

                                    @Override
                                    public void onTick(long millisUntilFinished) {
                                        Log.d(TAG, "Timer : " + millisUntilFinished / 1000);
                                    }

                                    @Override
                                    public void onFinish() {
                                        Log.d(TAG, "Reloading NativeAd");

                                        createNativeAd();
                                    }
                                }.start();

                            }
                        })
                        .withNativeAdOptions(new NativeAdOptions.Builder()
                                .build())
                        .build();

                adLoader.loadAds(new AdRequest.Builder().build(), 2);
                adClass.setAdLoader(adLoader);
            }
        });
    }
}