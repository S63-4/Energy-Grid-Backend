package com.energygrid.datarestforwarder.RESTful;

import com.energygrid.datarestforwarder.models.SharedJSON;
import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class MessageController {
    private Gson gson = new Gson();

    @GetMapping("/regional")
    public String regionalMessage() throws IOException {
        File jsonFile = new File(
                getClass().getClassLoader().getResource("regional.json").getFile()
        );
        SharedJSON message = gson.fromJson(new FileReader(jsonFile), SharedJSON.class);
        return gson.toJson(message);
    }
}
