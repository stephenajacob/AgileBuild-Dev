package ie.ait.touristapp.db;

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

    public static final String USER_TABLE_NAME = "user";
    public static final String USERNAME_COLUMN = "username";
    public static final String PASSWORD_COLUMN = "password";
    public static final String AGE_COLUMN = "age";
    public static final String EMAIL_COLUMN = "email";
    public static final String GENDER_COLUMN = "gender";
    public static final String NAME_COLUMN = "name";

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "tourist_app";
    private static final String USER_TABLE_CREATE_COMMAND = "create table user(" +
            "id integer primary key asc, " +
            "username text not null, " +
            "password text not null, " +
            "age integer not null, " +
            "email text not null, " +
            "gender text not null, " +
            "name text not null);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS"+ USER_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public void insertUser(User user)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USERNAME_COLUMN, user.getUsername());
        values.put(PASSWORD_COLUMN, user.getPassword());
        values.put(AGE_COLUMN, user.getAge());
        values.put(EMAIL_COLUMN, user.getEmailAddress());
        values.put(GENDER_COLUMN, user.getGender().toString());
        values.put(NAME_COLUMN, user.getName());

        db.insert(USER_TABLE_NAME, null, values);
        db.close();
    }

    public boolean valueExistsForColumnInTable(String value, String column, String table) {
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