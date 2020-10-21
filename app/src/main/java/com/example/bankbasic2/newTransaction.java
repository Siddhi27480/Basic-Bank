package com.example.bankbasic2;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankbasic2.data.myDbHandler;
import com.example.bankbasic2.model.Accounts;
import com.example.bankbasic2.model.Transactions;

import java.util.List;


public class newTransaction extends AppCompatActivity
{
    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_transaction);

        myDbHandler db = new myDbHandler(newTransaction.this);

        button=findViewById(R.id.done);

        //to get the values from previous activity
        Intent intent = getIntent();
        String sender = intent.getStringExtra("sName");
        String s_acc=intent.getStringExtra("sacc");
        String receiver = intent.getStringExtra("rName");
        String r_acc=intent.getStringExtra("racc");
        String t_amount = intent.getStringExtra("amt");




        //setting the values of textview equal to the values received from previous activity
        TextView nameTextView = findViewById(R.id.SENDER);
        nameTextView.setText(sender);
        TextView accTextView = findViewById(R.id.RECEIVER);
        accTextView.setText(receiver);
        TextView phoneTextView = findViewById(R.id.AMOUNT);
        phoneTextView.setText(t_amount);


        //initializing new transaction
        Transactions nTransaction = new Transactions();
        nTransaction.setS_name(sender);
        nTransaction.setR_name(receiver);
        nTransaction.setS_acc(Integer.parseInt(s_acc));
        nTransaction.setR_acc(Integer.parseInt(r_acc));
        nTransaction.setT_amt(Integer.parseInt(t_amount));

        //Checking the sender's name from the database ,getting their account number,adding it to new transaction,changing balance of sender
       List<Accounts> list = db.getAllAccounts();
        if (list != null) {
            for (Accounts a : list)
            {
                if(Integer.parseInt(s_acc)==a.getId1())
                {
                  nTransaction.setSbbt(a.getBalance());
                  a.setBalance(a.getBalance()-Integer.parseInt(t_amount));
                  db.updateAccount(a);
                  nTransaction.setSbat(a.getBalance());
                }
            }
        }

        //Checking the receiver's name from the database ,getting their account number,adding it to new transaction,changing balance of receiver

        if (list != null) {
            for (Accounts a : list)
            {
                if(Integer.parseInt(r_acc)==a.getId1())
                {
                    nTransaction.setRbbt(a.getBalance());
                    a.setBalance(a.getBalance()+Integer.parseInt(t_amount));
                    db.updateAccount(a);
                    nTransaction.setRbat(a.getBalance());

                }
            }
        }

        db.addTransaction(nTransaction);
        Toast.makeText(getApplicationContext(),"Transaction successful!",Toast.LENGTH_SHORT).show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirect();
            }
        });

    }
    public void redirect()
    {
        Intent r=getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
        r.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(r);
    }
}