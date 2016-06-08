package testapp.codepath.com.simpletodo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity
{
    private static final String TAG = "edititemactivity";
    public static final String DATA_EXTRA= "intentextra";
    private static final String INTENT_EXTRA = "testapp.codepath.com.simpletodo.intent_extra";
    private Button mSaveButton;
    private EditText mEditText;

    @org.jetbrains.annotations.Contract("_ -> !null")
    public static Intent newIntent(Context context)
    {
        return new Intent(context,EditItemActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        String val = getIntent().getStringExtra(INTENT_EXTRA);
        Log.i(TAG, "The string that was clicked:" + val);

        mEditText = (EditText) findViewById(R.id.re_edit_text);
        mSaveButton = (Button) findViewById(R.id.button_save);

        mEditText.setText(val);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newVal = mEditText.getText().toString();
                Intent data = new Intent();
                data.putExtra(DATA_EXTRA, newVal);
                setResult(Activity.RESULT_OK,data);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem)
    {
        switch(menuItem.getItemId())
        {
            case R.id.action_settings:
                 Log.i("TAG","Settings clicked");
                 return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

}
