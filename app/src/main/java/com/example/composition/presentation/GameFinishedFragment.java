package com.example.composition.presentation;

import static com.example.composition.CONSTANT.*;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.composition.R;
import com.example.composition.databinding.FragmentGameFinishedBinding;
import com.example.composition.domain.entity.GameResult;

import java.util.Locale;

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
        showResults();
    }

    private Integer getPercentOfRightAnswers(){
        if (result.getCountOfQuestions()==0) return 0;
        return (int)((((double)result.getCountOfRightAnswers())/result.getCountOfQuestions())*100);
    }

    private void showResults(){
        int resImgId;
        if (result.isWinner()){
            resImgId = R.drawable.smile_good;
        } else {
            resImgId = R.drawable.loser;
        }
        Drawable drawable = ContextCompat.getDrawable(requireContext(), resImgId);
        binding.imageViewResult.setImageDrawable(drawable);
        binding.tvRequiredAnswers.setText(String.format(Locale.getDefault(),
                getString(R.string.required_answers),
                result.getGameSettings().getMinCountOfRightAnswers()));
        binding.tvUserScore.setText(String.format(Locale.getDefault(),
                getString(R.string.user_score),
                result.getCountOfRightAnswers()));
        binding.tvRequiredAnswersPercent.setText(String.format(Locale.getDefault(),
                getString(R.string.required_answers_percent),
                result.getGameSettings().getMinPercentOfRightAnswers()));
        binding.tvUserScorePercent.setText(String.format(Locale.getDefault(),
                getString(R.string.user_score_percent),
                getPercentOfRightAnswers()));
    }

    private void retryGame(){
        requireActivity().getSupportFragmentManager().popBackStack(GAME_FRAGMENT, FragmentManager.POP_BACK_STACK_INCLUSIVE);
    }
    private GameResult parseResults(){
        assert getArguments() != null;
        GameResult result = getArguments().getParcelable(KEY_GAME_RESULTS);
        return result;
    }

    public static GameFinishedFragment newInstance(GameResult gameResult){
        GameFinishedFragment fragment = new GameFinishedFragment();
        Bundle args = new Bundle();
        args.putParcelable(KEY_GAME_RESULTS, gameResult);
        fragment.setArguments(args);
        return fragment;
    }
}