package com.example.composition.domain.repository;

import com.example.composition.domain.entity.GameSettings;
import com.example.composition.domain.entity.Level;
import com.example.composition.domain.entity.Question;

public interface GameRepository {

    public Question generateQuestion(Integer maxSumValue, Integer countOfOptions);

    public GameSettings getGameSettings(Level level);
}
