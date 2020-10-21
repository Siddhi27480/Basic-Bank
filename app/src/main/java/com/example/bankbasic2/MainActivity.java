package com.example.bankbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.bankbasic2.data.myDbHandler;
import com.example.bankbasic2.model.Accounts;
import com.example.bankbasic2.model.Transactions;

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    private Button button;
    private Button button1;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button=(Button)findViewById(R.id.viewusers);
        button1=(Button)findViewById(R.id.viewtransactions);

        myDbHandler db=new myDbHandler(MainActivity.this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(!prefs.getBoolean("firstTime",false))
        {
            Accounts a1=new Accounts();
            a1.setName("Tian Almeda");
            a1.setEmail("tian27@gmail.com");
            a1.setPhone("9123456789");
            a1.setBalance(5000);
            db.addAccount(a1);


            Accounts a2=new Accounts();
            a2.setName("Swaie Shinde");
            a2.setEmail("swaie.21@gmail.com");
            a2.setPhone("9213456789");
            a2.setBalance(5250);
            db.addAccount(a2);

            Accounts a3=new Accounts();
            a3.setName("Ziya Asar");
            a3.setEmail("ziya.08@gmail.com");
            a3.setPhone("9998887766");
            a3.setBalance(7680);
            db.addAccount(a3);

            Accounts a4=new Accounts();
            a4.setName("Shantanu");
            a4.setEmail("shant.08@gmail.com");
            a4.setPhone("9212345678");
            a4.setBalance(2500);
            db.addAccount(a4);

            Accounts a5=new Accounts();
            a5.setName("Sakhi Rao");
            a5.setEmail("sakhi238@gmail.com");
            a5.setPhone("9998888566");
            a5.setBalance(9625);
            db.addAccount(a5);

            Accounts a6=new Accounts();
            a6.setName("Subodh Roy");
            a6.setEmail("subodh11@gmail.com");
            a6.setPhone("9311234567");
            a6.setBalance(3286);
            db.addAccount(a6);

            Accounts a7=new Accounts();
            a7.setName("Om Dhuri");
            a7.setEmail("om1@gmail.com");
            a7.setPhone("9998988566");
            a7.setBalance(9560);
            db.addAccount(a7);

            Accounts a8=new Accounts();
            a8.setName("Saish Tawade");
            a8.setEmail("saish26@gmail.com");
            a8.setPhone("9999995662");
            a8.setBalance(3855);
            db.addAccount(a8);

            Accounts a9=new Accounts();
            a9.setName("Khushi Singh");
            a9.setEmail("khushi26@gmail.com");
            a9.setPhone("9199995661");
            a9.setBalance(11000);
            db.addAccount(a9);

            Accounts a10=new Accounts();
            a10.setName("Aayushi Shah");
            a10.setEmail("aayushi6@gmail.com");
            a10.setPhone("8291746863");
            a10.setBalance(7850);
            db.addAccount(a10);
        }


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountList();
            }
        });

       button1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v)
           {
               openTransactionList();
           }
       });


    }
    public void openAccountList()
    {
        Intent intent=new Intent(this,AccountList.class);
        startActivity(intent);
    }

    public void openTransactionList()
    {
        Intent intent1=new Intent(this,transactionList.class);
        startActivity(intent1);
    }
}