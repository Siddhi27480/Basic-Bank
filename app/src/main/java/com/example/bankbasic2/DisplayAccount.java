package com.example.bankbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DisplayAccount extends AppCompatActivity {
    private Button button;
    private Button button1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_account);

        button=(Button)findViewById(R.id.transact);
        button1=(Button)findViewById(R.id.view_P_trans);

        Intent intent=getIntent();
        String name=intent.getStringExtra("SName");
        String account=intent.getStringExtra("SAccount");
        String phone= intent.getStringExtra("SPhone");
        String email=intent.getStringExtra("SEmail");
        String balance=intent.getStringExtra("SBalance");

        TextView nameTextView=findViewById(R.id.dName);
        nameTextView.setText(name);
        TextView accTextView=findViewById(R.id.dAccount_number);
        accTextView.setText(account);
        TextView phoneTextView=findViewById(R.id.dPhoneNumber);
        phoneTextView.setText(phone);
        TextView emailTextView=findViewById(R.id.dEmailID);
        emailTextView.setText(email);
        TextView balanceTextView=findViewById(R.id.dBalance);
        balanceTextView.setText(balance);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openTransact();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                indTransactions();
            }
        });

    }
    public void openTransact()
    {
        Intent intent=new Intent(this,Transact.class);
        //to get the intent of previous activity to next one
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    public void indTransactions()
    {
        Intent intent1=new Intent(this,individualTransactions.class);
        //to get the intent of previous activity to next one
        Bundle bundle=getIntent().getExtras();
        if(bundle!=null){
            intent1.putExtras(bundle);
        }
        startActivity(intent1);

    }

}