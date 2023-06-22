package com.example.composition.presentation;

import static com.example.composition.CONSTANT.GAME_FRAGMENT;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.composition.R;
import com.example.composition.databinding.FragmentChooseLevelBinding;
import com.example.composition.domain.entity.Level;

public class ChooseLevelFragment extends Fragment {

    private FragmentChooseLevelBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChooseLevelBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.bthLevelTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGame(Level.TEST);
            }
        });
        binding.bthLevelEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGame(Level.EASY);
            }
        });
        binding.bthLevelNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGame(Level.NORMAL);
            }
        });
        binding.bthLevelHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGame(Level.HARD);
            }
        });
    }

    public static ChooseLevelFragment newInstance() {
        return new ChooseLevelFragment();
    }

    public void launchGame(Level level) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, GameFragment.newInstance(level))
                .addToBackStack(GAME_FRAGMENT)
                .commit();
    }
}