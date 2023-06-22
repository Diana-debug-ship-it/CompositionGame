package com.example.composition.domain.usecases;

import com.example.composition.domain.entity.GameSettings;
import com.example.composition.domain.entity.Level;
import com.example.composition.domain.repository.GameRepository;


public class GetGamesSettingsUseCase {

    private GameRepository gameRepository;

    public GameSettings getGameSettings(Level level) {
        return gameRepository.getGameSettings(level);
    }
}
