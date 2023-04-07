package com.example.iwaifu.ui.speak;


import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.speech.RecognizerIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.iwaifu.MainActivity;
import com.example.iwaifu.R;
import com.example.iwaifu.databinding.FragmentSpeakBinding;
import com.google.android.material.card.MaterialCardView;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class SpeakFragment extends Fragment {
    private FragmentSpeakBinding binding;
    private Translate translate;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SpeakViewModel speakViewModel =
                new ViewModelProvider(this).get(SpeakViewModel.class);

        binding = FragmentSpeakBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        TextView englishCardText = binding.englishCardText;
        TextView japaneseCardText = binding.japaneseCardText;
        Button speakButton = binding.speakButton;

        ActivityResultLauncher<Intent> startForResult = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    updateEnglishCardText(result, englishCardText);
                    if (checkInternetConnection()) {
                        getTranslateService();
                        String japText = translate(englishCardText.getText().toString());
                        japaneseCardText.setText(japText);

                        if (getArguments() != null) {
                            speakViewModel.speakJapanese(japText, getArguments().getInt("waifu"));
                        } else {
                            speakViewModel.speakJapanese(japText, 1);
                        }
                    } else {
                        Toast.makeText(getActivity(), R.string.no_connection, Toast.LENGTH_SHORT).show();
                    }
                });

        speakButton.setOnClickListener(v -> {
            Intent intent
                    = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                    RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                    Locale.getDefault());
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text");

            try {
                startForResult.launch(intent);
            } catch (Exception e) {
                Toast.makeText(getActivity(), "Please try again", Toast.LENGTH_SHORT).show();
                System.out.println(e.getMessage());
            }
        });

        return root;
    }

    private void updateEnglishCardText(ActivityResult result, TextView englishCardText) {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
            ArrayList<String> text = result.getData().getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            englishCardText.setText(text.get(0));
        }
    }

    public boolean checkInternetConnection() {

        //Check internet connection:
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        //Means that we are connected to a network (mobile or wi-fi)
        assert connectivityManager != null;
        boolean connected = connectivityManager.getActiveNetwork() != null;

        return connected;
    }

    public void getTranslateService() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try (InputStream is = getResources().openRawResource(R.raw.credentials)) {

            //Get credentials:
            final GoogleCredentials myCredentials = GoogleCredentials.fromStream(is);

            //Set credentials and get translate service:
            TranslateOptions translateOptions = TranslateOptions.newBuilder().setCredentials(myCredentials).build();
            translate = translateOptions.getService();

        } catch (IOException ioe) {
            ioe.printStackTrace();

        }
    }

    public String translate(String englishText) {
        Translation translation = translate.translate(englishText,
                Translate.TranslateOption.targetLanguage("ja"),
                Translate.TranslateOption.model("base"));

        return translation.getTranslatedText();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}