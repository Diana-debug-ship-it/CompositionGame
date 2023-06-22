package com.example.composition.data;

import static com.example.composition.CONSTANT.*;

import com.example.composition.domain.entity.GameSettings;
import com.example.composition.domain.entity.Level;
import com.example.composition.domain.entity.Question;
import com.example.composition.domain.repository.GameRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import kotlin.random.Random;

public class GameRepositoryImpl implements GameRepository {
    @Override
    public Question generateQuestion(Integer maxSumValue, Integer countOfOptions) {
        Integer sum = Random.Default.nextInt(MIN_SUM_VALUE, maxSumValue+1);
        Integer visibleNumber = Random.Default.nextInt(MIN_ANSWER_VALUE, sum);
        Set<Integer> options = new HashSet<>();
        Integer rightAnswer = sum-visibleNumber;
        options.add(rightAnswer);
        int from = Math.max(rightAnswer-countOfOptions, MIN_ANSWER_VALUE);
        int to = Math.min(maxSumValue, rightAnswer+countOfOptions);
        while (options.size()<countOfOptions){
            options.add(Random.Default.nextInt(from, to));
        }
        return new Question(sum, visibleNumber, new ArrayList<>(options));
    }

    @Override
    public GameSettings getGameSettings(Level level) {
        switch (level) {
            case EASY:
                return new GameSettings(10, 10, 70, 60);
            case NORMAL:
                return new GameSettings(20, 20, 80, 40);
            case HARD:
                return new GameSettings(30, 30, 90, 40);
            default: //TEST
                return new GameSettings(10, 3, 50, 8);
        }
    }
}
