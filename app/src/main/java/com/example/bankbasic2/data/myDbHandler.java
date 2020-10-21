package com.example.bankbasic2.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.bankbasic2.model.Accounts;
import com.example.bankbasic2.model.Transactions;
import com.example.bankbasic2.params.Params;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.jar.Attributes;

public class myDbHandler extends SQLiteOpenHelper
{
    public myDbHandler(Context context)
    {
        super(context, Params.DB_NAME,null,Params.DB_version);

    }
    //Create tables
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE "+Params.TABLE_NAME+"("
                +Params.KEY_ID+" INTEGER PRIMARY KEY ,"
                +Params.Name+" TEXT ,"
                +Params.Phone+" TEXT, "
                +Params.Email+" TEXT, "
                +Params.Balance+" INTEGER "+")");

        db.execSQL("CREATE TABLE "+Params.TABLE_NAME_1+"("
                +Params.KEY_ID1+" INTEGER PRIMARY KEY ,"
                +Params.Sender+" TEXT ,"
                +Params.Sender_acc+" INTEGER ,"
                +Params.Receiver+" TEXT ,"
                +Params.Receiver_acc+" INTEGER ,"
                +Params.Amount+" INTEGER ,"
                +Params.SenderBB+" INTEGER ,"
                +Params.SenderAB+" INTEGER ,"
                +Params.ReceiverBB+" INTEGER ,"
                +Params.ReceiverAB+" INTEGER ,"
                +Params.Time+" TEXT"+")");

        Log.d("db","Tables created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    { db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Params.TABLE_NAME_1);
        onCreate(db);
    }


    //Function to add accounts
    public void addAccount(Accounts accounts)
    {
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
        //values.put(Params.KEY_ID,accounts.getId1());
        values.put(Params.Name,accounts.getName());
        values.put(Params.Email,accounts.getEmail());
        values.put(Params.Phone,accounts.getPhone());
        values.put(Params.Balance,accounts.getBalance());

        db.insert(Params.TABLE_NAME,null,values);
        //db.execSQL("SELECT DISTINCT "+Params.Name+" , "+Params.Email+" , "+Params.Phone+" , "+Params.Balance+" FROM "+Params.TABLE_NAME);
        Log.d("db","Successfully inserted");
        db.close();
    }




    //Function to add transactions
    public void addTransaction(Transactions transactions)
    {
        Date currentTime = Calendar.getInstance().getTime();
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues values=new ContentValues();
       values.put(Params.Sender_acc,transactions.getS_acc());
       values.put(Params.Receiver_acc,transactions.getR_acc());
        values.put(Params.Sender,transactions.getS_name());
        values.put(Params.Receiver,transactions.getR_name());
        values.put(Params.Amount,transactions.getT_amt());
        values.put(Params.Time,String.valueOf(currentTime));
        values.put(Params.SenderBB,transactions.getSbbt());
        values.put(Params.SenderAB,transactions.getSbat());
        values.put(Params.ReceiverBB,transactions.getRbbt());
        values.put(Params.ReceiverAB,transactions.getRbat());

        db.insert(Params.TABLE_NAME_1,null,values);
        Log.d("db","Successfully inserted 2");
        db.close();

    }




    //To get list of all the accounts
    public List<Accounts> getAllAccounts()
   {
       List<Accounts> accountsList =new ArrayList<>();
       SQLiteDatabase db =this.getReadableDatabase();

       //Generate the query to read from the database
       String query="SELECT * FROM " + Params.TABLE_NAME;
       Cursor cursor=db.rawQuery(query,null);

       //Loop through rows
       if(cursor.moveToFirst())
       {
           do{
               Accounts accounts=new Accounts();
               accounts.setId1(cursor.getInt(0));
               accounts.setName(cursor.getString(1));
               accounts.setEmail(cursor.getString(3));
               accounts.setPhone(cursor.getString(2));
               accounts.setBalance(cursor.getInt(4));
               accountsList.add(accounts);
           }while (cursor.moveToNext());
       }
       return accountsList;
   }




    //To get list of all the transactions
    public List<Transactions> getAllTransactions()
    {
        List<Transactions> transactionsList =new ArrayList<>();
        SQLiteDatabase db =this.getReadableDatabase();

        //Generate the query to read from the database
        String query="SELECT * FROM " + Params.TABLE_NAME_1;
        Cursor cursor=db.rawQuery(query,null);

        //Loop through rows
        if(cursor.moveToFirst())
        {
            do{
                Transactions transactions=new Transactions();
                transactions.setId2(cursor.getInt(0));
                transactions.setS_name(cursor.getString(1));
                transactions.setS_acc(cursor.getInt(2));
                transactions.setR_name(cursor.getString(3));
                transactions.setR_acc(cursor.getInt(4));
                transactions.setT_amt(cursor.getInt(5));
                transactions.setSbbt(cursor.getInt(6));
                transactions.setSbat(cursor.getInt(7));
                transactions.setRbbt(cursor.getInt(8));
                transactions.setRbat(cursor.getInt(9));
                transactions.setTime(cursor.getString(10));
                transactionsList.add(transactions);
            }while (cursor.moveToNext());
        }
        return transactionsList;
    }


    //To update phone ,email,balance
    public int updateAccount(Accounts accounts)
        {
            SQLiteDatabase db= this.getWritableDatabase();
            ContentValues values=new ContentValues();
            values.put(Params.Email,accounts.getEmail());
            values.put(Params.Phone,accounts.getPhone());
            values.put(Params.Balance,accounts.getBalance());
            //Update now
            return db.update(Params.TABLE_NAME,values,Params.KEY_ID+"=?",new String[]{String.valueOf(accounts.getId1())});
        }



    //To know the number of accounts
        public int getAccountCount(){
        String query="SELECT * FROM "+Params.TABLE_NAME;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        return cursor.getCount();
        }

}
