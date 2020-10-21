package com.example.bankbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.example.bankbasic2.data.myDbHandler;
import com.example.bankbasic2.model.Transactions;
import java.util.ArrayList;
import java.util.List;

public class transactionList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_list);
        ListView listView;
        listView = findViewById(R.id.transactionlist);

        ArrayList<String> transactions = new ArrayList<>();
        myDbHandler db = new myDbHandler(transactionList.this);
        List<Transactions> transactionList= db.getAllTransactions();
        for (Transactions transaction: transactionList)
        {

                    transactions.add("From: "+transaction.getS_name()+"("+transaction.getS_acc()+")"+
                            "\nTo: "+transaction.getR_name()+"("+transaction.getR_acc()+")"
                            +"\nAmount:"+transaction.getT_amt()+"\nTime:"+transaction.getTime());


        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,transactions);
        listView.setAdapter(arrayAdapter);

    }
}