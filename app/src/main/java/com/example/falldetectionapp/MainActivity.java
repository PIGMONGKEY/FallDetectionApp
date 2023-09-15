package com.example.falldetectionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;

public class MainActivity extends AppCompatActivity {
    private WebSocket socket;
    private VideoWebSocketListener videoWebSocketListener = new VideoWebSocketListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            socketTest();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void socketTest() throws InterruptedException {
        OkHttpClient client = new OkHttpClient();
        VideoWebSocketListener videoWebSocketListener = new VideoWebSocketListener();
        Request request = new Request.Builder().url("ws://10.0.2.2:10000/video").build();

        client.newWebSocket(request, videoWebSocketListener).send("receiver");
        Thread.sleep(5000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        socket.close(1000, "close");
    }
}