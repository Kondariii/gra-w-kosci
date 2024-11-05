package com.example.gra_w_kosci;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView dice1TextView;
    private TextView dice2TextView;
    private TextView dice3TextView;
    private TextView dice4TextView;
    private TextView dice5TextView;
    private TextView currentRollLabel;
    private TextView gameResultLabel;
    private TextView rollCountTextView;
    private Button rollDiceButton;
    private Button resetButton;

    private int gameResult = 0;
    private int rollCount = 0;
    private Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1TextView = findViewById(R.id.dice1TextView);
        dice2TextView = findViewById(R.id.dice2TextView);
        dice3TextView = findViewById(R.id.dice3TextView);
        dice4TextView = findViewById(R.id.dice4TextView);
        dice5TextView = findViewById(R.id.dice5TextView);
        currentRollLabel = findViewById(R.id.currentRollLabel);
        gameResultLabel = findViewById(R.id.gameResultLabel);
        rollCountTextView = findViewById(R.id.rollCountTextView);
        rollDiceButton = findViewById(R.id.rollDiceButton);
        resetButton = findViewById(R.id.resetButton);

        rollDiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollDice();
            }
        });

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
            }
        });
    }

    private void rollDice() {
        int[] diceValues = new int[5];
        diceValues[0] = random.nextInt(6) + 1;
        diceValues[1] = random.nextInt(6) + 1;
        diceValues[2] = random.nextInt(6) + 1;
        diceValues[3] = random.nextInt(6) + 1;
        diceValues[4] = random.nextInt(6) + 1;

        displayDiceResults(diceValues);

        int currentRollResult = calculateRollResult(diceValues);
        updateScore(currentRollResult);
        updateRollCount();
    }

    private int calculateRollResult(int[] diceValues) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int value : diceValues) {
            if (counts.containsKey(value)) {
                counts.put(value, counts.get(value) + 1);
            } else {
                counts.put(value, 1);
            }
        }

        int sum = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() > 1) {
                sum += entry.getKey() * entry.getValue();
            }
        }
        return sum;
    }

    private void resetGame() {
        dice1TextView.setText("?");
        dice2TextView.setText("?");
        dice3TextView.setText("?");
        dice4TextView.setText("?");
        dice5TextView.setText("?");

        gameResult = 0;
        rollCount = 0;

        currentRollLabel.setText("Wynik tego losowania: 0");
        gameResultLabel.setText("Wynik gry: 0");
        rollCountTextView.setText("Liczba rzutów: 0");
    }

    private void updateScore(int newScore) {
        gameResult += newScore;
        gameResultLabel.setText("Wynik gry: " + gameResult);
    }

    private void updateRollCount() {
        rollCount++;
        rollCountTextView.setText("Liczba rzutów: " + rollCount);
    }

    private void displayDiceResults(int[] diceResults) {
        dice1TextView.setText(String.valueOf(diceResults[0]));
        dice2TextView.setText(String.valueOf(diceResults[1]));
        dice3TextView.setText(String.valueOf(diceResults[2]));
        dice4TextView.setText(String.valueOf(diceResults[3]));
        dice5TextView.setText(String.valueOf(diceResults[4]));
    }
}
