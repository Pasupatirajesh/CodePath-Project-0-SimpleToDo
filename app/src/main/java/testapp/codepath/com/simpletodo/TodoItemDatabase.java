package testapp.codepath.com.simpletodo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import testapp.codepath.com.simpletodo.TodoItemBaseScheme.ToDoTable;

/**
 * Created by SSubra27 on 6/6/16.
 */
public class TodoItemDatabase extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="TodoItemDatabaseName";
    private static final int DATABASE_VERSION =(int) 1.0;


    public TodoItemDatabase(Context context)
    {
        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("Create Table " + ToDoTable.NAME + "(" + " _id integer primary key autoincrement,"
                + ToDoTable.Cols.ListArrayIndex + ","+ ToDoTable.Cols.ListArrayContent+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
