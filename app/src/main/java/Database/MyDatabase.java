package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;
import android.util.Log;

/**
 * Created by preetikataly on 10/2/14.
 */
public class MyDatabase extends SQLiteOpenHelper {

    public static final String column_id = "_id";
    public static final String Key_Poster = "MoviePoster";
    public static final String Key_Name = "MovieName";
    private static final String Database_Name = "MyMovies";
    private static final int db_version = 1;


    public static final String DATABASE_CREATE = "create table " + Database_Name + "(" + column_id + " integer primary key autoincrement, " + "MovieName text not null, MoviePoster String);";

    public MyDatabase(Context context) {
        super(context, Database_Name, null, 1);
    }

    @Override

    public void onCreate(SQLiteDatabase database){
        database.execSQL(DATABASE_CREATE);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MyDatabase.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + Database_Name);
        onCreate(db);
    }

    public void addMovie(String Name, String Poster){
        ContentValues dbentry = new ContentValues();
        dbentry.put(Key_Name, Name);
        dbentry.put(Key_Poster, Poster);
        getWritableDatabase().insert("MyMovies", "Name", dbentry);

    }

    public Cursor getAllMyMovies(){
        Cursor cursor = getReadableDatabase().rawQuery("select * from MyMovies", null);
        return cursor;
    }

}
