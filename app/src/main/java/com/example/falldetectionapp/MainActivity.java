package com.example.falldetectionapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class MainActivity extends AppCompatActivity {
    private WebSocket socket;
    private ImageView imageView;
    private final JSONObject CONNECTION_INFO = new JSONObject();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.testImage);

        try {
            socketTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void socketTest() throws InterruptedException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("ws://10.0.2.2:10000/video").build();

        socket = client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                Log.d("video", "closed");
            }

            @Override
            public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
                Log.d("video", "closing");
            }

            @Override
            public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
                Log.d("video", "failure : " + t.getCause() + " / " + t.getMessage() + " / " + t);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
                Log.d("video", "get message : " + text);
            }

            @Override
            public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showImage(BitmapFactory.decodeByteArray(bytes.toByteArray(), 0, bytes.toByteArray().length));
                    }
                });
            }

            @Override
            public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
                Log.d("video", "connected");
                try {
                    CONNECTION_INFO.put("identifier", "receiver");
                    CONNECTION_INFO.put("camera_id", "cam01");
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
                webSocket.send(CONNECTION_INFO.toString());
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.close(1000, "close");
    }

    private void showImage(Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);
    }
}