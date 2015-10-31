package ie.ait.touristapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ie.ait.touristapp.user.User;

/**
 * Created by brendan on 27/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tourist_app";
    private static final String TABLE_NAME = "contacts";
    public static final String USERS_TABLE_NAME = "user";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
//    SQLiteDatabase db;

    private static final String CONTACT_TABLE_CREATE = "create table contacts(id primary key not null auto_increment, "+
    "name text not null, email text not null, uname text not null, pass text not null):";

    private static final String USER_TABLE_CREATE = "create table user(" +
            "id integer primary key asc, " +
            "username text not null, " +
            "password text not null, " +
            "age integer not null, " +
            "email text not null, " +
            "gender text not null, " +
            "name text not null);";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String AGE = "age";
    public static final String EMAIL = "email";
    public static final String GENDER = "gender";
    public static final String NAME = "name";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE);
    }


    public void insertUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME, user.getUsername());
        values.put(PASSWORD, user.getPassword());
        values.put(AGE, user.getAge());
        values.put(EMAIL, user.getEmailAddress());
        values.put(GENDER, user.getGender().toString());
        values.put(NAME, user.getName());

        db.insert(USERS_TABLE_NAME, null, values);
        db.close();

    }

    public String searchPass(String uname)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query = "select uname, pass from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b="not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(uname))
                {
                    b = cursor.getString(1);
                    break;
                }

            }
            while(cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS"+TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }

    public boolean valueExistsForColumnInTable(String table, String value, String column) {
        String query = String.format("SELECT * FROM %s WHERE %s='%s';", table, column, value);
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor cursor = db.rawQuery(query, null);
        if(cursor.getCount()!=0) {
            return true;
        }else {
            return false;
        }
    }
}