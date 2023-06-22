package com.example.composition.presentation;

import static com.example.composition.CONSTANT.KEY_LEVEL;

import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.composition.R;
import com.example.composition.databinding.FragmentGameBinding;
import com.example.composition.domain.entity.GameResult;
import com.example.composition.domain.entity.Level;
import com.example.composition.domain.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class GameFragment extends Fragment {

    private Level level;

    private FragmentGameBinding binding;
    private GameViewModel viewModel;

    private List<TextView> tvOptions = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseArgs();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentGameBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(GameViewModel.class);
        addAllTVs();
        observeViewModel();
        viewModel.startGame(level);
        setOnClickListenersToOptions();
    }

    private void setOnClickListenersToOptions() {
        for (TextView tvOption : tvOptions) {
            tvOption.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    viewModel.chooseAnswer(Integer.parseInt(tvOption.getText().toString()));
                }
            });
        }
    }

    private void observeViewModel() {
        viewModel.getQuestion().observe(getViewLifecycleOwner(), new Observer<Question>() {
            @Override
            public void onChanged(Question question) {
                binding.textViewSum.setText(String.valueOf(question.getSum()));
                binding.textViewOperand.setText(String.valueOf(question.getVisibleNumber()));
                for (int i = 0; i < tvOptions.size(); i++) {
                    tvOptions.get(i).setText(String.valueOf(question.getOptions().get(i)));
                }
                viewModel.getPercentOfRightAnswers().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        binding.progressBar.setProgress(integer, true);
                    }
                });
                viewModel.getEnoughCountOfRightAnswers().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        binding.tvAnswersProgress.setTextColor(getColorByState(aBoolean));
                    }
                });
                viewModel.getEnoughPercentOfRightAnswers().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean aBoolean) {
                        int color = getColorByState(aBoolean);
                        binding.progressBar.setProgressTintList(ColorStateList.valueOf(color));
                    }
                });
                viewModel.getFormattedTime().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        binding.textViewTimer.setText(s);
                    }
                });
                viewModel.getMinPercent().observe(getViewLifecycleOwner(), new Observer<Integer>() {
                    @Override
                    public void onChanged(Integer integer) {
                        binding.progressBar.setSecondaryProgress(integer);
                    }
                });
                viewModel.getGameResult().observe(getViewLifecycleOwner(), new Observer<GameResult>() {
                    @Override
                    public void onChanged(GameResult gameResult) {
                        launchGameFinished(gameResult);
                    }
                });
                viewModel.getProgressAnswers().observe(getViewLifecycleOwner(), new Observer<String>() {
                    @Override
                    public void onChanged(String s) {
                        binding.tvAnswersProgress.setText(s);
                    }
                });
            }
        });
    }

    private Integer getColorByState(Boolean goodState) {
        int colorResId;
        if (goodState) {
            colorResId = android.R.color.holo_green_light;
        } else {
            colorResId = android.R.color.holo_red_light;
        }
        return ContextCompat.getColor(requireContext(), colorResId);
    }

    private void launchGameFinished(GameResult gameResult) {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, GameFinishedFragment.newInstance(gameResult))
                .addToBackStack(null)
                .commit();
    }

    private void parseArgs() {
        level = (Level) requireArguments().getSerializable(KEY_LEVEL);
    }

    public static GameFragment newInstance(Level level) {
        GameFragment fragment = new GameFragment();
        Bundle args = new Bundle();
        args.putSerializable(KEY_LEVEL, level);
        fragment.setArguments(args);
        return fragment;
    }

    private void addAllTVs() {
        tvOptions.add(binding.tvOption1);
        tvOptions.add(binding.tvOption2);
        tvOptions.add(binding.tvOption3);
        tvOptions.add(binding.tvOption4);
        tvOptions.add(binding.tvOption5);
        tvOptions.add(binding.tvOption6);
    }
}