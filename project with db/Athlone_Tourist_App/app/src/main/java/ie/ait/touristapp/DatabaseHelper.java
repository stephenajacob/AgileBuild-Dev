package ie.ait.touristapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;

/**
 * Created by brendan on 27/10/2015.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "contacts.db";
    private static final String TABLE_NAME = "contacts";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_UNAME = "uname";
    private static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE = "create table contacts(id primary key not null auto_increment, "+
    "name text not null, email text not null, uname text not null, pass text not null):";


    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db=db;
    }

    public void insertContact(Contact c)
    {
        db=this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME , c.getName());
        values.put(COLUMN_EMAIL , c.getEmail());
        values.put(COLUMN_UNAME , c.getUname());
        values.put(COLUMN_PASS , c.getPass());

        db.insert(TABLE_NAME, null, values);
        db.close();

    }

    public String searchPass(String uname)
    {
        db=this.getReadableDatabase();
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
}
