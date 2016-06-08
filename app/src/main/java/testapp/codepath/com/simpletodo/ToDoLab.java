package testapp.codepath.com.simpletodo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import testapp.codepath.com.simpletodo.TodoItemBaseScheme.ToDoTable;

/**
 * Created by SSubra27 on 6/7/16.
 */
public class ToDoLab  {
    private static ToDoLab sToDoLab;

    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static ToDoLab get(Context context)
    {
        if(sToDoLab==null)
        {
            sToDoLab= new ToDoLab(context);
        }
        return sToDoLab;
    }

    public ToDoLab(Context context)
    {
        mContext = context.getApplicationContext();
        mDatabase = new TodoItemDatabase(mContext).getWritableDatabase();
    }

    public ArrayList getTexts()
    {
        ArrayList toDoText = new ArrayList<>();
        TodoCursorWrapper cursor = queryCrimes(null,null);
        try
        {
            cursor.moveToFirst();
            while(!cursor.isAfterLast())
            {
                toDoText.add(cursor.getToDo());
                cursor.moveToNext();
            }
        } finally
        {
            cursor.close();
        }
        return toDoText;
    }
    public void addText(ToDo t)
    {
        ContentValues values = getContentValues(t);
        mDatabase.insert(ToDoTable.NAME, null, values);
    }
    public void updateAddText(ToDo t)
    {
        String ListArrayContent = t.getListArrayContent();
        ContentValues values = getContentValues(t);
        mDatabase.update(ToDoTable.NAME, values, ToDoTable.Cols.ListArrayContent + "=?", new String[] {ListArrayContent});
    }

    private TodoCursorWrapper queryCrimes(String whereClause, String[] whereArgs)
    {
        Cursor cursor = mDatabase.query(ToDoTable.NAME, null,whereClause,whereArgs,null,null,null);
        return new TodoCursorWrapper(cursor);
    }
    private static ContentValues getContentValues(ToDo todo)
    {
        ContentValues values = new ContentValues();
        values.put(ToDoTable.Cols.ListArrayIndex, todo.getListArrayIndex());
        values.put(ToDoTable.Cols.ListArrayContent, todo.getListArrayContent());
        return values;
    }

}
