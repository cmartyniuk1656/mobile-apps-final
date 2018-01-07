package com.chrismartyniuk.assignment_2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.chrismartyniuk.assignment_2.data.model.Member;
import com.chrismartyniuk.assignment_2.data.model.Money;
import com.chrismartyniuk.assignment_2.data.model.Score;
import com.chrismartyniuk.assignment_2.data.repo.MemberRepo;
import com.chrismartyniuk.assignment_2.data.repo.MoneyRepo;
import com.chrismartyniuk.assignment_2.data.repo.ScoreRepo;

public class MainActivity extends AppCompatActivity {

    private final String TAG = MainActivity.class.getSimpleName().toString();
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void userLogin(View view) {

        EditText userNameTextview = (EditText) findViewById(R.id.usernameTxt);
        EditText passwordTextview = (EditText) findViewById(R.id.passwordTxt);

        Double tes = 0.0;

        String username = userNameTextview.getText().toString();
        String password = passwordTextview.getText().toString();

        MemberRepo memberRepo = new MemberRepo();


       if (memberRepo.checkUser(username,password)) {

            Intent intent = new Intent(this, LobbyScreen.class);
            intent.putExtra(EXTRA_MESSAGE, username);
            startActivity(intent);
        }
        else {

            Toast.makeText(MainActivity.this, "Fail login:" + username +" and "+ password , Toast.LENGTH_LONG).show();

        }
    }

    public void createNewAccount(View view) {

        EditText userNameTextview = (EditText) findViewById(R.id.usernameTxt);
        EditText passwordTextview = (EditText) findViewById(R.id.passwordTxt);

        Double tes = 0.0;

        String username = userNameTextview.getText().toString();
        String password = passwordTextview.getText().toString();

        MemberRepo memberRepo = new MemberRepo();
        MoneyRepo moneyRepo = new MoneyRepo();
        ScoreRepo scoreRepo = new ScoreRepo();

        Member member = new Member();
        Money money = new Money();
        Score score = new Score();

        int zero_money = 10000;
        int zero_win =0;
        int zero_lose =0;

        if (!memberRepo.checkUser(username) ) {


            member.setMemberName(username);
            member.setMemberPassword(password);

            money.setMemberName(username);
            money.setMemberMoney(zero_money);

            score.setMemberName(username);
            score.setGameWin(zero_win);
            score.setGameLose(zero_lose);

            memberRepo.insert(member);
            moneyRepo.insert(money);
            scoreRepo.insert(score);

            Toast.makeText(MainActivity.this, "Account " + username +" successfully added" , Toast.LENGTH_LONG).show();


        } else {

                Toast.makeText(MainActivity.this, "Fail Insert:" + username +" and "+ password , Toast.LENGTH_LONG).show();
        }
    }
}
