package com.example.bankbasic2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bankbasic2.data.myDbHandler;
import com.example.bankbasic2.model.Accounts;

import java.util.List;

public class Transact extends AppCompatActivity /*implements AdapterView.OnItemSelectedListener*/ {
    private Button button;
    private Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        final int[] flag = {0};
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transact);

        myDbHandler db = new myDbHandler(Transact.this);

        Intent intent=getIntent();
        final String s=intent.getStringExtra("SName");
        final String sAcc=intent.getStringExtra("SAccount");
        final String bal=intent.getStringExtra("SBalance");



        TextView nameTextView=findViewById(R.id.s_name);
        nameTextView.setText(s);

        final List<Accounts> checklist = db.getAllAccounts();


        //creating button
        button=(Button)findViewById(R.id.proceed);

        //dropdown creation
       spinner =findViewById(R.id.r_spinner);
       String[] receivers=getResources().getStringArray(R.array.receivers);
       ArrayAdapter adapter=new ArrayAdapter(this,R.layout.spinner_layout,receivers);
       adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       spinner.setAdapter(adapter);

       //set On click listener
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                String input1=s;

                String input2=spinner.getSelectedItem().toString();

                EditText input3=(EditText)findViewById(R.id.amount);
                String val=input3.getText().toString();

                EditText input4=(EditText)findViewById(R.id.receiveracc);
                String rAcc=input4.getText().toString();

                String input5=sAcc;

                int flag=0;

                    for (Accounts c : checklist) {
                        if (input2.equals(c.getName())) {
                            if (c.getId1() == Integer.parseInt(rAcc)) {
                                flag = 1;
                                if (rAcc.equals(sAcc)) {
                                    Toast.makeText(getApplicationContext(), "Sender and receiver cannot be same!", Toast.LENGTH_SHORT).show();
                                } else if (Integer.parseInt(val) > Integer.parseInt(bal)) {
                                    Toast.makeText(getApplicationContext(), "Balance insufficient!", Toast.LENGTH_SHORT).show();
                                } else {
                                    toNextPage(input1, input2, val, rAcc, input5);
                                }

                            }

                        }
                    }
                    Log.d("flag", String.valueOf(flag));
                    if (flag == 0) {
                        Toast.makeText(getApplicationContext(), "Receiver's name and account number don't match,try again!", Toast.LENGTH_SHORT).show();
                    }
                }

        });
    }



    public void toNextPage(String s,String r,String a,String rA,String sA)
    {
        Intent intent1=new Intent(this,newTransaction.class);
        intent1.putExtra("sName",s);
        intent1.putExtra("rName",r);
        intent1.putExtra("amt",a);
        intent1.putExtra("racc",rA);
        intent1.putExtra("sacc",sA);
        startActivity(intent1);
    }
}