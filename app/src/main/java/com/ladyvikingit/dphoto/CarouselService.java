package com.ladyvikingit.dphoto;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CarouselService {

        List<Drawable> images;

        private void loadImageUrls(){
            Thread thread = new Thread(new Runnable() {
                public void run() {
                    //TODO: connect to cloud location and get all non-video image urls
                    final Drawable image1 =
                            LoadImageFromWebOperations("https://images.pexels.com/photos/259915/pexels-photo-259915.jpeg");
                    images.add(image1);
                    final Drawable image2 =
                            LoadImageFromWebOperations("https://images.pexels.com/photos/235986/pexels-photo-235986.jpeg");
                    images.add(image2);
                }
            });

            thread.start();
            while(thread.isAlive()){}

        }

        public void start(ImageView imageView){
            images = new ArrayList();
            loadImageUrls();
            runSlideShow(imageView);
        }

        private void runSlideShow(ImageView imageView) {
            //counter = 0;
            int totalImages = images.size();

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <= totalImages; i++) {
                        if (i == totalImages){
                            i = 0;
                        }
                        final int value = i;
                        Drawable image = images.get(value);
                        imageView.post(new Runnable() {
                            public void run() {
                                imageView.setImageDrawable(image);
                            }
                        });
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            new Thread(runnable).start();
        }

        private static Drawable LoadImageFromWebOperations(String url) {
            try {
                InputStream is = (InputStream) new URL(url).getContent();
                Drawable d = Drawable.createFromStream(is, "src name");
                return d;
            } catch (Exception e) {
                return null;
            }
        }
}
