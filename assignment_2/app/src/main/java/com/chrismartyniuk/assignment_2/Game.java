package com.chrismartyniuk.assignment_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.chrismartyniuk.assignment_2.data.model.Money;
import com.chrismartyniuk.assignment_2.data.model.Score;
import com.chrismartyniuk.assignment_2.data.repo.MoneyRepo;
import com.chrismartyniuk.assignment_2.data.repo.ScoreRepo;

import java.util.ArrayList;

public class Game extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    private String username_Game;

    MoneyRepo moneyRepo = new MoneyRepo();
    ScoreRepo scoreRepo = new ScoreRepo();

    Money money = new Money();
    Score score = new Score();

    private Player player = new Player();
    private Player dealer = new Player();
    private Deck deck = new Deck();
    private TextView playerCardOne;
    private TextView playerCardTwo;
    private TextView playerCardThree;
    private TextView playerCardFour;
    private TextView dealerCardOne;
    private TextView dealerCardTwo;
    private TextView dealerCardThree;
    private TextView dealerCardFour;
    private TextView outcome;
    private TextView moneyTxt;
    private int playerCurrentValue;
    private int dealerCurrentValue;
    private String playerMoney;
    private Button hitBtn;
    private Button stayBtn;
    private Button playBtn;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Intent intent = getIntent();
        username_Game = intent.getStringExtra(LobbyScreen.EXTRA_MESSAGE);

        score = scoreRepo.getScoreByName(username_Game);
        money = moneyRepo.getMoneyByName(username_Game);

        playerCardOne = (TextView)findViewById(R.id.playerCardOneTxt);
        playerCardTwo = (TextView)findViewById(R.id.playerCardTwoTxt);
        playerCardThree = (TextView)findViewById(R.id.playerCardThreeTxt);
        playerCardFour = (TextView)findViewById(R.id.playerCardFourTxt);
        dealerCardOne = (TextView)findViewById(R.id.dealerCardOneTxt);
        dealerCardTwo = (TextView)findViewById(R.id.dealerCardTwoTxt);
        dealerCardThree = (TextView)findViewById(R.id.dealerCardThreeTxt);
        dealerCardFour = (TextView)findViewById(R.id.dealerCardFourTxt);
        outcome = (TextView)findViewById(R.id.outcomeTxt);
        moneyTxt = (TextView)findViewById(R.id.playerMoneyTxt);
        hitBtn = (Button) findViewById(R.id.hitBtn);
        stayBtn = (Button) findViewById(R.id.stayBtn);
        playBtn = (Button) findViewById(R.id.playBtn);
        hitBtn.setEnabled(false);
        stayBtn.setEnabled(false);
        playerMoney = "$" + Integer.toString(money.getMemberMoney());
        moneyTxt.setText(playerMoney);

        Log.i("Member", username_Game);
        Log.i("Wins", Integer.toString(score.GameWin));
        Log.i("Losses", Integer.toString(score.GameLose));
        Log.i("Money", Integer.toString(money.getMemberMoney()));

    }

    public void setNewPlayerCard() {

        ArrayList<Card> thisHand = player.getHand();
        TextView thisTextview = null;
        Card thisCard = thisHand.get(0);

        if (playerCardOne.getText().toString().equals("")) {
            thisTextview = playerCardOne;
        }
        else if (playerCardTwo.getText().toString().equals("")) {
            thisTextview = playerCardTwo;
        }
        else if (playerCardThree.getText().toString().equals("")) {
            thisTextview = playerCardThree;
        }
        else if (playerCardFour.getText().toString().equals("")) {
            thisTextview = playerCardFour;
        }

        for (int i = 0; i < thisHand.size(); i++)
        {
            thisCard = thisHand.get(i);

            if (!thisCard.played) {
                playerCurrentValue += thisCard.value;
                thisCard.played = true;
                thisHand.set(i, thisCard);
                player.setHand(thisHand);
                i = thisHand.size();
            }
        }
        if (thisTextview != null) {
            thisTextview.setText(thisCard.face);
        }
        if (playerCurrentValue > 21) {
            loseScreen();
        }
    }

    public void dealerTurn() {

        dealer.dealCard(deck);
        setNewDealerCard();

        if (dealerCurrentValue < 16) {
            dealerTurn();
        }
        else {
            determineWinner();
        }
    }

    public void setNewDealerCard() {

        ArrayList<Card> thisHand = dealer.getHand();
        TextView thisTextview = null;
        Card thisCard = thisHand.get(0);

        if (dealerCardOne.getText().toString().equals("")) {
            thisTextview = dealerCardOne;
        }
        else if (dealerCardTwo.getText().toString().equals("")) {
            thisTextview = dealerCardTwo;
        }
        else if (dealerCardThree.getText().toString().equals("")) {
            thisTextview = dealerCardThree;
        }
        else if (dealerCardFour.getText().toString().equals("")) {
            thisTextview = dealerCardFour;
        }

        for (int i = 0; i < thisHand.size(); i++)
        {
            thisCard = thisHand.get(i);

            if (!thisCard.played) {
                dealerCurrentValue += thisCard.value;
                thisCard.played = true;
                thisHand.set(i, thisCard);
                dealer.setHand(thisHand);
                i = thisHand.size();
            }
        }
        if (thisTextview != null) {
            thisTextview.setText(thisCard.face);
        }
    }

    public void playerHit(View view) {
        player.dealCard(deck);
        setNewPlayerCard();
    }

    public void playerStay(View view) {
        dealerTurn();
    }

    public void dealerHit(View view) {
        player.dealCard(deck);
        setNewPlayerCard();
    }

    public void newGame() {
        playerCurrentValue = 0;
        dealerCurrentValue = 0;

        deck.newDeck();

        player.dealCard(deck);
        dealer.dealCard(deck);

        playerCardOne.setText("");
        playerCardTwo.setText("");
        playerCardThree.setText("");
        playerCardFour.setText("");
        dealerCardOne.setText("");
        dealerCardTwo.setText("");
        dealerCardThree.setText("");
        dealerCardFour.setText("");
        outcome.setText("");

        playBtn.setVisibility(View.GONE);
        playBtn.setEnabled(false);

        setNewPlayerCard();
        setNewDealerCard();
    }

    public void startGame(View view) {
        hitBtn.setEnabled(true);
        stayBtn.setEnabled(true);
        newGame();
    }

    public void loseScreen() {

        //TODO - Increase player's losses by 1.

        outcome.setText("Lose");
        playBtn.setVisibility(View.VISIBLE);
        playBtn.setEnabled(true);
        hitBtn.setEnabled(false);
        stayBtn.setEnabled(false);

        score.GameLose++;
        scoreRepo.loseUpdate(score);
        Log.e("Member", username_Game);
        Log.e("Wins", Integer.toString(score.GameWin));
        Log.e("Losses", Integer.toString(score.GameLose));
        Log.i("Money", Integer.toString(money.getMemberMoney()));
    }

    public void winScreen() {

        //TODO - Increase player's money by 100.
        //TODO - Set playerMoney text view to new value.
        //TODO - Increase player's wins by 1.

        outcome.setText("Win");
        playBtn.setVisibility(View.VISIBLE);
        playBtn.setEnabled(true);
        hitBtn.setEnabled(false);
        stayBtn.setEnabled(false);


        score.GameWin++;
        money.incrementMoney();
        scoreRepo.winUpdate(score);
        moneyRepo.Update(money);
        playerMoney = "$" + Integer.toString(money.getMemberMoney());
        moneyTxt.setText(playerMoney);
        Log.i("Member", username_Game);
        Log.i("Wins", Integer.toString(score.GameWin));
        Log.i("Losses", Integer.toString(score.GameLose));
        Log.i("Money", Integer.toString(money.getMemberMoney()));



    }

    public void determineWinner() {
        if (dealerCurrentValue > 21 || playerCurrentValue > dealerCurrentValue) {
            winScreen();
        }
        else {
            loseScreen();
        }

    }
}
