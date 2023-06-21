package com.example.composition.domain.repository;

import com.example.composition.domain.entity.GameSettings;
import com.example.composition.domain.entity.Question;

import java.util.logging.Level;

public interface GameRepository {

    public Question generateQuestion(Integer maxSumValue, Integer countOfOptions);

    public GameSettings getGameSettings(Level level);
}
