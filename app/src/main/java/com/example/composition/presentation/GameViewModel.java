package com.example.composition.presentation;

import static com.example.composition.CONSTANT.*;

import android.app.Application;
import android.content.Context;
import android.os.CountDownTimer;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.composition.R;
import com.example.composition.data.GameRepositoryImpl;
import com.example.composition.domain.entity.GameResult;
import com.example.composition.domain.entity.GameSettings;
import com.example.composition.domain.entity.Level;
import com.example.composition.domain.entity.Question;
import com.example.composition.domain.usecases.GenerateQuestionUseCase;
import com.example.composition.domain.usecases.GetGamesSettingsUseCase;

import java.util.Locale;

public class GameViewModel extends AndroidViewModel {

    private Context context;
    private GameSettings gameSettings;
    private Level level;
    private CountDownTimer timer = null;

    private GameRepositoryImpl repository = new GameRepositoryImpl();

    private GenerateQuestionUseCase generateQuestionUseCase = new GenerateQuestionUseCase(repository);
    private GetGamesSettingsUseCase getGamesSettingsUseCase = new GetGamesSettingsUseCase(repository);

    private MutableLiveData<String> formattedTime = new MutableLiveData<>();
    private MutableLiveData<Question> question = new MutableLiveData<>();

    private int countOfRightAnswers = 0;
    private int countOfQuestions = 0;
    private MutableLiveData<Integer> percentOfRightAnswers = new MutableLiveData<>();
    private MutableLiveData<String> progressAnswers = new MutableLiveData<>();

    private MutableLiveData<Boolean> enoughCountOfRightAnswers = new MutableLiveData<>();
    private MutableLiveData<Boolean> enoughPercentOfRightAnswers = new MutableLiveData<>();
    private MutableLiveData<Integer> minPercent = new MutableLiveData<>();
    private MutableLiveData<GameResult> gameResult = new MutableLiveData<>();

    public LiveData<GameResult> getGameResult() {
        return gameResult;
    }

    public LiveData<Integer> getMinPercent() {
        return minPercent;
    }

    public LiveData<Boolean> getEnoughCountOfRightAnswers() {
        return enoughCountOfRightAnswers;
    }

    public LiveData<Boolean> getEnoughPercentOfRightAnswers() {
        return enoughPercentOfRightAnswers;
    }

    public GameViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<String> getProgressAnswers() {
        return progressAnswers;
    }

    public LiveData<String> getFormattedTime() {
        return formattedTime;
    }

    public LiveData<Question> getQuestion() {
        return question;
    }

    public LiveData<Integer> getPercentOfRightAnswers() {
        return percentOfRightAnswers;
    }

    public void startGame(Level level){
        getGamesSettings(level);
        startTimer();
        generateQuestion();
        updateProgress();
    }

    private void getGamesSettings(Level level){
        this.level = level;
        this.gameSettings = getGamesSettingsUseCase.getGameSettings(level);
        minPercent.setValue(gameSettings.getMinPercentOfRightAnswers());
    }

    private void generateQuestion(){
        question.setValue(generateQuestionUseCase.generateQuestionUseCase(gameSettings.getMaxSumValue()));
    }

    public void chooseAnswer(Integer number){
        checkAnswer(number);
        updateProgress();
        generateQuestion();
    }

    private void checkAnswer(Integer number){
        Integer rightAnswer = question.getValue().getRightAnswer();
        if (number.equals(rightAnswer)){
            countOfRightAnswers++;
        }
        countOfQuestions++;
    }

    private void startTimer(){
        timer = new CountDownTimer(
                gameSettings.getGameTimeInSeconds()*MILLIS_IN_SECONDS,
                MILLIS_IN_SECONDS
        ) {
            @Override
            public void onTick(long millisUntilFinished) {
                formattedTime.setValue(formatTime(millisUntilFinished));
            }

            @Override
            public void onFinish() {
                finishGame();
            }
        };
        timer.start();
    }

    private void finishGame(){
        gameResult.setValue(new GameResult(
                enoughCountOfRightAnswers.getValue()==true && enoughPercentOfRightAnswers.getValue()==true,
                countOfRightAnswers,
                countOfQuestions,
                gameSettings

        ));
    }

    private String formatTime(Long millis){
        long seconds = (millis/MILLIS_IN_SECONDS);
        int minutes = (int) (seconds/SECONDS_IN_MINUTES);
        int leftSeconds = (int) (seconds - (minutes*SECONDS_IN_MINUTES));
        return String.format(Locale.getDefault(), "%02d:%02d", minutes, leftSeconds);
    }

    private void updateProgress(){
        int percent = calculatePercentOfRightAnswers();
        percentOfRightAnswers.setValue(percent);
        progressAnswers.setValue(String.format(
                Locale.getDefault(),
                context.getString(R.string.answers_progress),
                countOfRightAnswers, gameSettings.getMinCountOfRightAnswers()
        ));
        enoughCountOfRightAnswers.setValue(countOfRightAnswers>=gameSettings.getMinCountOfRightAnswers());
        enoughPercentOfRightAnswers.setValue(percent>=gameSettings.getMinPercentOfRightAnswers());
    }

    private Integer calculatePercentOfRightAnswers(){
        if (countOfQuestions==0) return 0;
        return (int)((((double)countOfRightAnswers)/countOfQuestions)*100);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        timer = null;
    }
}
