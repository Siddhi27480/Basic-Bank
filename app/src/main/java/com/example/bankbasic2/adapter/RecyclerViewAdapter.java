package com.example.bankbasic2.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bankbasic2.DisplayAccount;
import com.example.bankbasic2.R;
import com.example.bankbasic2.model.Accounts;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Accounts> accountsList;

    public RecyclerViewAdapter(Context context, List<Accounts> accountsList)
    {
        this.context = context;
        this.accountsList=accountsList;
    }


    //Where will the card come from?
    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    //What to write in the card?
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position)
    {
        Accounts accounts=accountsList.get(position);

        holder.AccountName.setText(accounts.getName());
        holder.AccountNumber.setText(accounts.getEmail());
    }

//How many items are present in the list?
    @Override
    public int getItemCount()
    {
        return accountsList.size();
    }


    //To perform actions on clicking on the class
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        public TextView AccountName;
        public TextView AccountNumber;
        public ImageView Next;


        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            itemView.setOnClickListener(this);

            AccountName = itemView.findViewById(R.id.dName);
            AccountNumber = itemView.findViewById(R.id.Acc_number);
            Next = itemView.findViewById(R.id.details);

            Next.setOnClickListener(this);
        }

        @Override
        public void onClick(View v)
        {
            int position = this.getAdapterPosition();
            Accounts accounts=accountsList.get(position);
            String name=accounts.getName();
            int Acc_no=accounts.getId1();
            String Email=accounts.getEmail();
            String Phone=accounts.getPhone();
            int Balance=accounts.getBalance();

            Intent intent=new Intent(context, DisplayAccount.class);
            intent.putExtra("SName",name);
            intent.putExtra("SAccount",String.valueOf(Acc_no));
            intent.putExtra("SEmail",Email);
            intent.putExtra("SPhone",Phone);
            intent.putExtra("SBalance",String.valueOf(Balance));
            context.startActivity(intent);

        }
    }
}
