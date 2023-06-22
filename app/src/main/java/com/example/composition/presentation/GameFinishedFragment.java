package com.example.composition.presentation;

import static com.example.composition.CONSTANT.*;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.composition.databinding.FragmentGameFinishedBinding;
import com.example.composition.domain.entity.GameResult;
import com.example.composition.domain.entity.GameSettings;

public class GameFinishedFragment extends Fragment {

    GameResult result;

    FragmentGameFinishedBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGameFinishedBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        result = parseResults();
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                retryGame();
            }
        });

        binding.buttonTryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryGame();
            }
        });
    }

    private void retryGame(){
        requireActivity().getSupportFragmentManager().popBackStack(GAME_FRAGMENT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    private GameResult parseResults(){
        assert getArguments() != null;
        GameResult result = getArguments().getParcelable(KEY_GAME_RESULTS);
        return result;
    }

    public static GameFinishedFragment newInstance(){
        GameFinishedFragment fragment = new GameFinishedFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_GAME_RESULTS, new GameResult(false, 0, 0, new GameSettings(0, 0, 0, 0)));
        fragment.setArguments(args);
        return fragment;
    }
}