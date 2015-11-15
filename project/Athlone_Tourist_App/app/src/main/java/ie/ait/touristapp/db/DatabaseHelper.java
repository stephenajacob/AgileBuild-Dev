package ie.ait.touristapp.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Collections;
import java.util.List;

import ie.ait.touristapp.rating.ExperienceType;
import ie.ait.touristapp.rating.Rating;
import ie.ait.touristapp.rating.RatingBuilder;
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
    SQLiteDatabase db;

    private static final String USER_TABLE_CREATE_COMMAND = "create table user(" +
            "id integer primary key asc, " +
            "username text not null, " +
            "password text not null, " +
            "age integer not null, " +
            "email text not null, " +
            "gender text not null, " +
            "name text not null);";

    private static final String RATING_TABLE_CREATE_COMMAND = "create table rating(" +
            "id integer primary key asc, " +
            "experience text not null, " +
            "rating integer not null," +
            "experienceType text not null," +
            "username text not null);";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(USER_TABLE_CREATE_COMMAND);
        db.execSQL(RATING_TABLE_CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String query = "DROP TABLE IF EXISTS"+ USER_TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }

    public String searchPass(String username)
    {
        db=this.getReadableDatabase();
        String query = "select username, password from "+USER_TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        String a, b;
        b="not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(username))
                {
                    b = cursor.getString(1);
                    break;
                }

            }
            while(cursor.moveToNext());
        }

        return b;
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

    public List<Rating> getExperienceTypeRatingsForThisUser(final ExperienceType experienceType, final String username) {
        final String query = String.format("SELECT * FROM RATING WHERE experienceType='%s' AND username='%s';", experienceType, username);
        final SQLiteDatabase database=this.getReadableDatabase();
        final Cursor cursor = database.rawQuery(query, null);

        List<Rating> ratingList = Collections.emptyList();
        if (cursor.moveToFirst()) {
            RatingBuilder ratingBuilder = new RatingBuilder();
            do {
                Rating rating = ratingBuilder.setExperience(cursor.getString(1))
                        .setRating(cursor.getInt(2))
                        .setExperienceType(ExperienceType.valueOf(cursor.getString(3)))
                        .setUsername(cursor.getString(4))
                        .build();
                ratingList.add(rating);
            } while (cursor.moveToNext());
        }

        return ratingList;
    }
}