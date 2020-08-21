package com.example.draganddrop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MyCanvasView myCanvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.container);
        myCanvasView = new MyCanvasView(this);
        constraintLayout.addView(myCanvasView);
    }
}
