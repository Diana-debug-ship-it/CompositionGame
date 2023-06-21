package com.example.composition.domain.usecases;

import static com.example.composition.CONSTANT.*;

import com.example.composition.domain.entity.Question;
import com.example.composition.domain.repository.GameRepository;

public class GenerateQuestionUseCase {
    private GameRepository gameRepository;

    public GenerateQuestionUseCase(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Question generateQuestionUseCase(Integer maxSumValue) {
        return gameRepository.generateQuestion(maxSumValue, COUNT_OF_OPTIONS);
    }
}
