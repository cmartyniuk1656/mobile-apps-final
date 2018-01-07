package com.chrismartyniuk.assignment_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.chrismartyniuk.assignment_2.data.model.Money;
import com.chrismartyniuk.assignment_2.data.model.Score;
import com.chrismartyniuk.assignment_2.data.repo.MoneyRepo;
import com.chrismartyniuk.assignment_2.data.repo.ScoreRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatsScreen extends AppCompatActivity {


    private ArrayList<String> statLabels;
    private ArrayList<String> statValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_screen);

        Intent intent = getIntent();
        String username_Stats = intent.getStringExtra(LobbyScreen.EXTRA_MESSAGE);

        MoneyRepo moneyRepo = new MoneyRepo();
        ScoreRepo scoreRepo = new ScoreRepo();

        Money money = new Money();
        money = moneyRepo.getMoneyByName(username_Stats);
        Score score = new Score();
        score = scoreRepo.getScoreByName(username_Stats);

        statLabels = new ArrayList<String>();
        statValues = new ArrayList<String>();

        statLabels.add("Wins");
        statLabels.add("Losses");
        statLabels.add("Money");
        statValues.add(String.valueOf(score.getGameWin()));
        statValues.add(String.valueOf(score.getGameLose()));
        statValues.add(String.valueOf(money.getMemberMoney()));

        ListView statsList;
        statsList = (ListView) findViewById( R.id.statsList);

        //Fill the ListView with values from SQLite Database associated with current player

        List<HashMap<String, String>> listItems = new ArrayList<>();

        SimpleAdapter adapter = new SimpleAdapter(this, listItems,
                R.layout.list_item, new String[]{"first line", "second line"},
                new int[]{R.id.text1, R.id.text2});

        //Iterate through DataContainer to extract name/comment values into the listItems HashMap
        for(int i = 0; i < statLabels.size(); i++) {

            HashMap<String, String> resultsMap = new HashMap<>();
            resultsMap.put("first line", statLabels.get(i));
            resultsMap.put("second line", statValues.get(i));
            listItems.add(resultsMap);
        }

        statsList.setAdapter(adapter);







    }
}
