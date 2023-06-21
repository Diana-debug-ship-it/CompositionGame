package com.example.composition.domain.usecases;

import com.example.composition.domain.entity.GameSettings;
import com.example.composition.domain.repository.GameRepository;

import java.util.logging.Level;

public class GetGamesSettingsUseCase {

    private GameRepository gameRepository;

    public GameSettings getGameSettings(Level level) {
        return gameRepository.getGameSettings(level);
    }
}
