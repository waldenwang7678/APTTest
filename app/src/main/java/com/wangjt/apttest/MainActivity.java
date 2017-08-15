package com.wangjt.apttest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.RoutAnnotation;
import com.wangjt.apttest.generate.GeneratedClass;

@RoutAnnotation()
public class MainActivity extends AppCompatActivity {

    @RoutAnnotation()
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GeneratedClass.getMessage();
    }
}
