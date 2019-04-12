package com.example.vendhana.radius;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class user {
    String name,age,imgurl;
    Bitmap image;
    listAdap lta;

    public user(String name, String age, String imgurl) {
        this.name = name;
        this.age = age;
        this.imgurl = imgurl;
        this.image = null;
    }

    public String getImgurl() {
        return imgurl;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public Bitmap getImage() {
        return image;
    }

    public void loadImage(listAdap lta) {
        this.lta = lta;
        new ImageLoadTask().execute(imgurl);
    }

    private class ImageLoadTask extends AsyncTask<String, String, Bitmap> {

        protected Bitmap doInBackground(String... param) {
            try {
                URL url = new URL(imgurl);
                Bitmap myBitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
                return myBitmap;
            } catch (IOException e) {
                return null;
            }
        }

        protected void onPostExecute(Bitmap ret) {
            if (ret != null) {
                image = ret;
                if (lta != null) {
                    lta.notifyDataSetChanged();
                }
            }
        }
    }

}
