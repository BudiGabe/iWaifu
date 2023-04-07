package com.example.iwaifu.ui.characters;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.iwaifu.MainActivity;
import com.example.iwaifu.R;
import com.example.iwaifu.adapters.WaifuAdapter;
import com.example.iwaifu.databinding.FragmentCharactersBinding;
import com.example.iwaifu.listeners.OnItemClickListener;
import com.example.iwaifu.models.WaifuModel;
import com.example.iwaifu.ui.speak.SpeakFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class CharactersFragment extends Fragment implements OnItemClickListener {

    private static final String WAIFU = "waifu";
    private FragmentCharactersBinding binding;
    private CharactersViewModel charactersViewModel;
    private RecyclerView waifuRecyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        charactersViewModel = new ViewModelProvider(this).get(CharactersViewModel.class);

        binding = FragmentCharactersBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        waifuRecyclerView = binding.recyclerView;


        charactersViewModel.getWaifus().observe(getViewLifecycleOwner(), waifuList -> {
            WaifuAdapter waifuAdapter = new WaifuAdapter(waifuList, CharactersFragment.this);
            waifuRecyclerView.setAdapter(waifuAdapter);
        });

        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onItemClick(WaifuModel waifu) {
//        Toast.makeText(getContext(), item.getTitle(), Toast.LENGTH_LONG).show();
//
        Bundle bundle = new Bundle();
        bundle.putInt(WAIFU, waifu.getRandomStyleId());

        //        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//
//        SpeakFragment fragment = new SpeakFragment();
//        fragment.setArguments(bundle);
//        fragmentTransaction.commit();

        NavHostFragment.findNavController(this)
                .navigate(R.id.action_navigation_characters_to_navigation_speak, bundle);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}