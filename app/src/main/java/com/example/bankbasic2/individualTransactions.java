package com.example.bankbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.bankbasic2.data.myDbHandler;
import com.example.bankbasic2.model.Transactions;

import java.util.ArrayList;
import java.util.List;

public class individualTransactions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_individual_transactions);

        Intent intent1=getIntent();

        String name=intent1.getStringExtra("SName");
        String account=intent1.getStringExtra("SAccount");

        TextView textView=findViewById(R.id.SNAME);
        textView.setText(name);


        ListView listView;
        listView = findViewById(R.id.ind_transactions);

        ArrayList<String> transactions = new ArrayList<>();
        myDbHandler db = new myDbHandler(individualTransactions.this);
        List<Transactions> transactionList= db.getAllTransactions();
        for (Transactions transaction: transactionList)
        {
            if(transaction.getS_acc()==Integer.parseInt(account))
            {
                transactions.add("To: "+transaction.getR_name()+"("+transaction.getR_acc()+")" +
                        "\nAmount:"+transaction.getT_amt()+
                        "\nTime:"+transaction.getTime()+
                        "\nBalance before:"+transaction.getSbbt()+
                        "\nBalance after:"+transaction.getSbat());
            }
            else if(transaction.getR_acc()==Integer.parseInt(account))
            {
                transactions.add("From: "+transaction.getS_name()+"("+transaction.getS_acc()+")"
                        +"\nAmount:"+transaction.getT_amt()
                        +"\nTime:"+transaction.getTime()
                        +"\nBalance before:"+transaction.getRbbt()+
                        "\nBalance after:"+transaction.getRbat());
            }
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transactions);
        listView.setAdapter(arrayAdapter);
    }
}