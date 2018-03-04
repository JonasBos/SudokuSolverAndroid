package com.example.jonas.sudokusolverforandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {


    private GridView gridview;
    private NumberAdapter a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gridview = findViewById(R.id.myGrid);
        Button clearButton = findViewById(R.id.clearBtn);
        a = new NumberAdapter(this);

        gridview.setAdapter(a);



    }

    public void solving(View v) {


        a.solveIt();



    }

    public void clearing(View v) {

        a.clearIt();
    }

}
