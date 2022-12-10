package com.example.course_sqlite;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {
    private Context context;
    public DbHelper(Context context) {
        super(context, "userdatainfo.db",null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Userdetails(name text primary key,contact text,dob text,email text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Userdetails");

    }

    public Boolean insertuserdata (String name, String contact, String dob, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",name);
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("email",email);
        long result = db.insert("Userdetails",null, contentValues);
            if (result == -1) {
                return false;
            } else
                return true;


    }

    public Boolean updateuserdata (String name, String contact, String dob, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("contact",contact);
        contentValues.put("dob",dob);
        contentValues.put("email",email);
        Cursor cursor = db.rawQuery("select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0) {
            long result = db.update("Userdetails", contentValues, "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else
                return true;
        }else
            return false   ;
    }

    public Boolean deleteuserdata (String name){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdetails where name = ?",new String[]{name});
        if (cursor.getCount()>0) {
            long result = db.delete("Userdetails", "name=?", new String[]{name});
            if (result == -1) {
                return false;
            } else
                return true;
        }else
            return false   ;
    }

    public Cursor getdata (){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from Userdetails",null);
        return  cursor;
    }

    public boolean RegisterUserLogin(String userName, String userPass){
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("userName",userName);
            contentValues.put("userPass",userPass);
            db.insert("usersTable",null,contentValues);
            return true;
        }catch(Exception e){
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_LONG).show();
            return false;
        }

    }

}
