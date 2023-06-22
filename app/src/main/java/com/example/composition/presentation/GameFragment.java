package com.example.composition.presentation;

import static com.example.composition.CONSTANT.KEY_LEVEL;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.composition.R;
import com.example.composition.databinding.FragmentGameBinding;
import com.example.composition.domain.entity.Level;

public class GameFragment extends Fragment {

    private Level level;

    private FragmentGameBinding binding;

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
        binding.textViewSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchGameFinished();
            }
        });
    }

    private void launchGameFinished() {
        requireActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, GameFinishedFragment.newInstance())
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
}