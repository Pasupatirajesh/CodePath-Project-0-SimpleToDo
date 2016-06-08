package testapp.codepath.com.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity implements Serializable{
    private static final String INTENT_EXTRA = "testapp.codepath.com.simpletodo.intent_extra";
    private static final int REQUEST_CODE = 0;

    private EditText mEditText;
    private Button mAddButton;
    private ListView mListView;
    private ArrayList mItems;
    private ArrayAdapter<String> mItemsAdapter;
    private int gposition;
    private ToDo mToDo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        mEditText = (EditText) findViewById(R.id.to_do_edit_text);
        mAddButton = (Button) findViewById(R.id.button_add);
        mListView = (ListView) findViewById(R.id.listView);
        mItems = ToDoLab.get(getApplicationContext()).getTexts();
        mItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItems);
        mListView.setAdapter(mItemsAdapter);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = mEditText.getText().toString();
                mToDo = new ToDo(Integer.toString(gposition),message);
                ToDoLab.get(getApplicationContext()).addText(mToDo);
                mEditText.setText("");
                mItems.add(mToDo.getListArrayContent());
                gposition+=1;
            }
        });
        setupListViewListener();
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                gposition = position;
                String value =(String) mItems.get(position);
                Intent i = EditItemActivity.newIntent(getApplicationContext());
                i.putExtra(INTENT_EXTRA, value);
                startActivityForResult(i,REQUEST_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode== Activity.RESULT_OK && requestCode == REQUEST_CODE) // ensures app doesnt crash even if user doesnt change values
        {
            String newName = data.getStringExtra(EditItemActivity.DATA_EXTRA);
            mItems.set(gposition, newName);
            mToDo.setListArrayContent(newName);
            ToDoLab.get(getApplicationContext()).updateAddText(mToDo);
            mItemsAdapter.notifyDataSetChanged();
            Toast.makeText(this, newName, Toast.LENGTH_SHORT).show();
        }
    }
    private void setupListViewListener()
    {
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View item, int pos,long id)
            {
                mItems.remove(pos);
                mItemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}
