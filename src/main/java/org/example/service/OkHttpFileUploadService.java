package org.example.service;

import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class OkHttpFileUploadService {

    private final OkHttpClient client = new OkHttpClient();
    private static final String URL = "http://localhost:8080/api/upload";

    public String uploadFile(File file, String description) throws IOException {
        RequestBody fileBody = RequestBody.create(file, MediaType.parse("text/plain"));

        MultipartBody multipartBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", file.getName(), fileBody)
                .addFormDataPart("description", description)
                .build();

        Request request = new Request.Builder()
                .url(URL)
                .post(multipartBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Unexpected response code: " + response);
            }
            return response.body().string();
        }
    }
}
