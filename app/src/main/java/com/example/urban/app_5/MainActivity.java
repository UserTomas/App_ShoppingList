package com.example.urban.app_5;


import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Rect;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements EditDialogFragment.EditDialogListener{

    public int action = 0;
    public View itemPosition;

    private boolean isNightModeEnabled = false;

    public void customDialog(View view) {
        EditDialogFragment eDialog = new EditDialogFragment();
        eDialog.show(getFragmentManager(), "Edit item");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog, String itemName) {
//        Toast.makeText(getApplicationContext(), "Ok = " + itemName, Toast.LENGTH_SHORT).show();
        TextView txtView = (TextView) itemPosition.findViewById(R.id.TextView);
        txtView.setText(itemName);
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
//        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setItemsCanFocus(false);

        String[] phones = new String[]{
                "Eggs", "Milk", "Bread", "Butter", "Ham", "Cheese",
                "Sugar", "Salt", "flour", "Apples", "Onion", "Potatos"
        };

        // add data to ArrayList
        final ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < phones.length; ++i) {
            list.add(phones[i]);
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.row_layout, R.id.TextView, list);

        // set data to listView with adapter
        listView.setAdapter(adapter);

        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        // item listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // get list row data (now String as a phone name)
                String item = list.get(position);


                switch (action) {
                    case 1:
//                        Toast.makeText(getBaseContext(), "Dolezite " + position, Toast.LENGTH_LONG).show();
                        CheckBox chbox = (CheckBox) view.findViewById(R.id.checkBox);

                        if (!chbox.isChecked()) {
                            chbox.setChecked(true);
                        } else chbox.setChecked(false);
                        break;
                    case 2:
//                        Toast.makeText(getBaseContext(), "v kosiku " + position, Toast.LENGTH_LONG).show();
                        CheckBox chbox2 = (CheckBox) view.findViewById(R.id.checkBox2);

                        if (!chbox2.isChecked()) {
                            chbox2.setChecked(true);
                        } else chbox2.setChecked(false);

                        break;
                    default:
//                        Toast.makeText(getBaseContext(), "default " + position, Toast.LENGTH_LONG).show();
                }
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id){
//                Toast.makeText(getBaseContext(),"longClick", Toast.LENGTH_SHORT).show();

                itemPosition = view;
                String item = list.get(position);
                Bundle arg = new Bundle();
                arg.putString("itemName",item);
                EditDialogFragment eDialog = new EditDialogFragment();
                eDialog.setArguments(arg);
                eDialog.show(getFragmentManager(), "Edit item");

                return true;
            }

        });

    }



    @Override
    public void onBackPressed(){
        ExitDialogFragment eDialog = new ExitDialogFragment();
        eDialog.show(getFragmentManager(), "exit");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.moje_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
//                Toast.makeText(getBaseContext(), "Dolezite ", Toast.LENGTH_LONG).show();

                // User chose the "Settings" item, show the app settings UI...
//                Intent intent = new Intent(MainActivity.this,DayNightView.class);
//                startActivity(intent);
                action = 1;
                return true;

            case R.id.action_favorite:
//                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
//                Toast.makeText(getBaseContext(), "v kosiku ", Toast.LENGTH_LONG).show();
//                Intent intentt = new Intent(MainActivity.this,DayNightView.class);
//                startActivity(intentt);
                // User chose the "Favorite" action, mark the current item
                // as a favorite...
                action = 2;
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    public void setFocusable(View view){
        Toast.makeText(getBaseContext(), "setFocusable ", Toast.LENGTH_LONG).show();
    }

}
