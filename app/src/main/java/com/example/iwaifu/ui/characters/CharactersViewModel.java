package com.example.iwaifu.ui.characters;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.iwaifu.VoicevoxService;
import com.example.iwaifu.models.Preset;
import com.example.iwaifu.models.Speaker;
import com.example.iwaifu.models.SpeakerInfo;
import com.example.iwaifu.models.Style;
import com.example.iwaifu.models.VoicevoxAudioQuery;
import com.example.iwaifu.models.WaifuModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharactersViewModel extends ViewModel {
    VoicevoxService service;
    private MutableLiveData<List<WaifuModel>> waifuList;
    private static final int MAX_WAIFUS = 19;

    public CharactersViewModel() {
        Gson gson = new GsonBuilder().serializeNulls().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:50021/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        service = retrofit.create(VoicevoxService.class);
    }

    public LiveData<List<WaifuModel>> getWaifus() {
        if (waifuList == null) {
            waifuList = new MutableLiveData<>();
            waifuList.setValue(new ArrayList<>());
            loadWaifus();
        }

        return waifuList;
    }

    public void loadWaifus() {
        Call<Collection<Speaker>> callSpeakers = service.getSpeakers();
        callSpeakers.enqueue(new Callback<Collection<Speaker>>() {
            @Override
            public void onResponse(Call<Collection<Speaker>> call, Response<Collection<Speaker>> response) {
                Collection<Speaker> speakers = response.body();
                if (speakers != null) {
                    for (Speaker speaker : speakers) {
                        Call<SpeakerInfo> callSpeakerInfo = service.getSpeakerInfo(speaker.getSpeakerUuid());
                        callSpeakerInfo.enqueue(new Callback<SpeakerInfo>() {
                            @Override
                            public void onResponse(Call<SpeakerInfo> call, Response<SpeakerInfo> response) {
                                SpeakerInfo speakerInfo = response.body();

                                // Create waifu model
                                String portrait = speakerInfo.getPortrait();
                                String name = speaker.getName();
                                List<Integer> styleIds = speaker.getStyles().stream()
                                        .map(Style::getId).collect(Collectors.toList());
                                WaifuModel waifu = new WaifuModel(styleIds, name, portrait);
                                waifuList.getValue().add(waifu);
                                waifuList.postValue(waifuList.getValue());
                            }

                            @Override
                            public void onFailure(Call<SpeakerInfo> call, Throwable t) {
                                System.out.println("==============================================================");
                                System.out.println(t.toString());
                                System.out.println("==============================================================");
                            }
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<Collection<Speaker>> call, Throwable t) {
                System.out.println("==============================================================");
                System.out.println(t.toString());
                System.out.println("==============================================================");
            }
        });
    }
}