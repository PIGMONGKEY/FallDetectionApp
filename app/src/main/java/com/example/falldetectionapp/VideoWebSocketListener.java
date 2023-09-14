package com.example.falldetectionapp;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

@Data
public class VideoWebSocketListener extends WebSocketListener {
    private final String WEB_SOCKET_URL = "ws://localhost:10000/video";

    @Override
    public void onClosed(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        Log.d("VideoSocket", "closed");
    }

    @Override
    public void onClosing(@NonNull WebSocket webSocket, int code, @NonNull String reason) {
        Log.d("VideoSocket", "closing");
    }

    @Override
    public void onFailure(@NonNull WebSocket webSocket, @NonNull Throwable t, @Nullable Response response) {
        Log.d("VideoSocket", "failure");
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull String text) {
        Log.d("VideoSocket", text);
    }

    @Override
    public void onMessage(@NonNull WebSocket webSocket, @NonNull ByteString bytes) {
        Log.d("VideoSocket", bytes.toString());
    }

    @Override
    public void onOpen(@NonNull WebSocket webSocket, @NonNull Response response) {
        Log.d("VideoSocket", "connected");
    }
}
