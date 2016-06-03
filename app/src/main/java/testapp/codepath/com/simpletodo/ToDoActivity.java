package testapp.codepath.com.simpletodo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;

public class ToDoActivity extends AppCompatActivity implements Serializable{
    private static final String packageName = "testapp.codepath.simple";
    private static final String INTENT_EXTRA = "testapp.codepath.com.simpletodo.intent_extra";
    private static final int REQUEST_CODE = 0;

    private EditText mEditText;
    private Button mAddButton;
    private ListView mListView;
    private ArrayList mItems;
    private ArrayAdapter<String> mItemsAdapter;
    private int gposition;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_do);
        mEditText = (EditText) findViewById(R.id.to_do_edit_text);
        mAddButton = (Button) findViewById(R.id.button_add);
        mListView = (ListView) findViewById(R.id.listView);
        mItems = readItems();
        mItemsAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mItems);
        mListView.setAdapter(mItemsAdapter);

        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String myText = mEditText.getText().toString();
                mEditText.setText("");
                mItems.add(myText);
                writeItems();
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
            mItemsAdapter.notifyDataSetChanged();
            writeItems();
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
                writeItems();
                return true;
            }
        });

    }

    private void writeItems()
    {
        ArrayList<String> aList;
        aList=mItems;
        String fileName = "ToDo.txt";
        File file = getFilesDir();

        try {
                FileWriter fw = new FileWriter(new File(file, fileName));
                Writer output = new BufferedWriter(fw);
                for (int i = 0; i < aList.size(); i++) {

                    String data = aList.get(i).toString();
                    output.write(data+ "\n");
                }
            output.close();
            } catch (IOException ioe) {

                ioe.printStackTrace();
        }
            Toast.makeText(this, "These items were written to:" + "ToDo.txt", Toast.LENGTH_SHORT).show();
        }
    private ArrayList<String> readItems() {
        String line;
        ArrayList<String> items = new ArrayList<>();
        String fileName = "ToDo.txt";
        File file = getFilesDir();

        try {
            BufferedReader input = new BufferedReader(new FileReader(new File(file,fileName)));

            while ((line = input.readLine()) != null) {

                items.add(line);
                Log.i("printing lines", line);

            }
            input.close();
            Log.i("Logging", "" + line);
        } catch (FileNotFoundException foe) {
            foe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return items;
    }

}
