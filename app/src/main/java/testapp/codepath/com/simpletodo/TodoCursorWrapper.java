package testapp.codepath.com.simpletodo;

import android.database.Cursor;
import android.database.CursorWrapper;

/**
 * Created by SSubra27 on 6/6/16.
 */
public class TodoCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public TodoCursorWrapper(Cursor cursor)
    {
        super(cursor);
    }

    public String getToDo()
    {
        String ListArrayContent = getString(getColumnIndex(TodoItemBaseScheme.ToDoTable.Cols.ListArrayContent));
        return ListArrayContent;

    }

}
