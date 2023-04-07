package com.example.iwaifu;

import com.example.iwaifu.models.Preset;
import com.example.iwaifu.models.Speaker;
import com.example.iwaifu.models.SpeakerInfo;
import com.example.iwaifu.models.VoicevoxAudioQuery;

import java.util.Collection;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface VoicevoxService {
    @POST("audio_query")
    Call<VoicevoxAudioQuery>  getAudioQuery(@Query("text") String japText,
                                            @Query("speaker") Integer speakerId);
    @Streaming
    @POST("synthesis")
    Call<ResponseBody> synthesizeSpeech(@Body VoicevoxAudioQuery audioQuery,
                                        @Query("speaker") Integer speakerId);
    @GET("speakers")
    Call<Collection<Speaker>> getSpeakers();

    @GET("speaker_info")
    Call<SpeakerInfo> getSpeakerInfo(@Query("speaker_uuid") String uuid);
}
