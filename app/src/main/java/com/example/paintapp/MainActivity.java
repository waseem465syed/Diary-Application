package com.example.paintapp;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RectangleColourView rcvColour;
    private CanvasView cvCanvas;
    private Button btnClear;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvCanvas= (CanvasView) findViewById(R.id.cvCanvas);
        rcvColour= (RectangleColourView) findViewById(R.id.rcvColour);
        btnClear=(Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);


        rcvColour.setOnColourClickListener(new RectangleColourView.onColourClickListener() {
            @Override
            public void changeColour(int colour) {
                cvCanvas.setCanvasTextColour(colour);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnClear:
                cvCanvas.clearCanvas();
                break;

        }

    }
}
