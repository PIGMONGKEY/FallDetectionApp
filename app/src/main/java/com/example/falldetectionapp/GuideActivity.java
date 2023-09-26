package com.example.falldetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 가이드를 보여주는 창입니다.
 * activity_guide.xml과 연결됩니다.
 */
public class GuideActivity extends AppCompatActivity {

    private ImageButton backButton;
    private ImageSlider guideSlider;

    private List<SlideModel> slideModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);

        init();
    }

    //    초기 설정을 넣어주세요
    private void init() {
        setTitle("가이드");
        setView();
        setListener();
    }

    private void setView() {
        backButton = findViewById(R.id.imageButton4);
        guideSlider = findViewById(R.id.slider);

        slideModels = new ArrayList<>();

        slideModels.add(new SlideModel(R.drawable.guide1));
        slideModels.add(new SlideModel(R.drawable.guide2));
        slideModels.add(new SlideModel(R.drawable.guide3));

    }

    //    리스너는 여기 모아주세요
    private void setListener() {

    }
}