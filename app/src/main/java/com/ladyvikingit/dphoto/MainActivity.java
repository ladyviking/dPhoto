package com.ladyvikingit.dphoto;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    CarouselService carousel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fullScreenMode();

        imageView = findViewById(R.id.imageView);

        carousel = new CarouselService();

        carousel.start(imageView);

    }

    private void fullScreenMode() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }
}