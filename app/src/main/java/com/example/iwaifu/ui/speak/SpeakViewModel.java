package com.example.iwaifu.ui.speak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iwaifu.R;
import com.example.iwaifu.VoicevoxService;
import com.example.iwaifu.models.VoicevoxAudioQuery;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;
import com.google.common.io.Files;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SpeakViewModel extends ViewModel {

    VoicevoxService service;
    VoicevoxAudioQuery audioQuery;

    public SpeakViewModel() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:50021/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(VoicevoxService.class);
    }

    public void updateEnglishCardText(ActivityResult result, TextView englishCardText) {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            ArrayList<String> text = result.getData().getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            englishCardText.setText(text.get(0));
        }
    }

    public void speakJapanese(String japText, Integer speakerId) {
        Call<VoicevoxAudioQuery> callQuery = service.getAudioQuery(japText, speakerId);
        callQuery.enqueue(new Callback<VoicevoxAudioQuery>() {
            @Override
            public void onResponse(Call<VoicevoxAudioQuery> call, Response<VoicevoxAudioQuery> response) {
                audioQuery = response.body();

                Call<ResponseBody> callSynthesize = service.synthesizeSpeech(audioQuery, speakerId);
                callSynthesize.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.body() != null) {
                            new AsyncTask<Void, Void, Void>() {
                                @Override
                                protected Void doInBackground(Void... voids) {
                                    try {
                                        playMp3(response.body().bytes());
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                    return null;
                                }
                            }.execute();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        System.out.println(t.toString());
                    }
                });
            }

            @Override
            public void onFailure(Call<VoicevoxAudioQuery> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void speakJapanese(String japText) {
        speakJapanese(japText, 1);
    }

    private void playMp3(byte[] mp3SoundByteArray) {
        try {
            // create temp file that will hold byte array
            File tempMp3 = File.createTempFile("kurchina", "wav");
            tempMp3.deleteOnExit();
            FileOutputStream fos = new FileOutputStream(tempMp3);
            fos.write(mp3SoundByteArray);
            fos.close();
            MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.reset();

            FileInputStream fis = new FileInputStream(tempMp3);
            mediaPlayer.setDataSource(fis.getFD());

            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException ex) {
            String s = ex.toString();
            ex.printStackTrace();
        }
    }
}