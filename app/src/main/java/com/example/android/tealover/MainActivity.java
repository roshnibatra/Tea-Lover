package com.example.android.tealover;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.android.tealover.model.Tea;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Intent mTeaIntent;

    public final static String EXTRA_TEA_NAME = "com.example.android.teatime.EXTRA_TEA_NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar menuToolbar = (Toolbar) findViewById(R.id.menu_toolbar);
        setSupportActionBar(menuToolbar);
        getSupportActionBar().setTitle(getString(R.string.menu_title));

        // Create an ArrayList of teas
        final ArrayList<Tea> teas = new ArrayList<>();
        teas.add(new Tea(getString(R.string.black_tea_name), R.drawable.black_tea));
        teas.add(new Tea(getString(R.string.green_tea_name), R.drawable.green_tea));
        teas.add(new Tea(getString(R.string.white_tea_name), R.drawable.white_tea));
        teas.add(new Tea(getString(R.string.oolong_tea_name), R.drawable.oolong_tea));
        teas.add(new Tea(getString(R.string.honey_lemon_tea_name), R.drawable.honey_lemon_tea));
        teas.add(new Tea(getString(R.string.chamomile_tea_name), R.drawable.chamomile_tea));

        // Create a {@link TeaAdapter}, whose data source is a list of {@link Tea}s.
        // The adapter know how to create grid items for each item in the list.
        GridView gridview =  findViewById(R.id.tea_grid_view);
        TeaMenuAdapter adapter = new TeaMenuAdapter(this, R.layout.grid_item_layout, teas);
        gridview.setAdapter(adapter);


        // Set a click listener on that View
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

                Tea item = (Tea) adapterView.getItemAtPosition(position);
                // Set the intent to open the {@link OrderActivity}
                mTeaIntent = new Intent(MainActivity.this, OrderActivity.class);
                String teaName = item.getTeaName();
                mTeaIntent.putExtra(EXTRA_TEA_NAME, teaName);
                startActivity(mTeaIntent);

            }
        });

    }

}

