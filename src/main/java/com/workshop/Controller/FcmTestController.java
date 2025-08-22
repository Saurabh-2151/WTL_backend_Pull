package com.workshop.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.Message;

import java.util.Map;

@RestController
public class FcmTestController {

    @PostMapping(path = "/test/push", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, Object>> sendTestPush(@RequestBody Map<String, String> body) {
        String token = body.get("token");
        String title = body.getOrDefault("title", "\uD83D\uDE97 Test Booking");
        String msgBody = body.getOrDefault("body", "This is a test from /test/push");

        if (token == null || token.isBlank()) {
            return ResponseEntity.badRequest().body(Map.of(
                "ok", false,
                "error", "token is required"
            ));
        }

        try {
            Message message = Message.builder()
                .putData("title", title)
                .putData("body", msgBody)
                .putData("bookingId", "WTL_TEST_" + System.currentTimeMillis())
                .setToken(token)
                .build();

            String messageId = FirebaseMessaging.getInstance().send(message);

            return ResponseEntity.ok(Map.of(
                "ok", true,
                "messageId", messageId
            ));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of(
                "ok", false,
                "error", e.getMessage()
            ));
        }
    }
}
