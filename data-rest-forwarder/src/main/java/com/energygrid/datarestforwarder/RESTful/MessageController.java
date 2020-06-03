package com.energygrid.datarestforwarder.RESTful;

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
        File jsonFile = new File("data-rest-forwarder/src/main/java/com/energygrid/datarestforwarder/RESTful/JSONfiles/regional.json");
        String message = gson.fromJson(new FileReader(jsonFile), String.class);
        return message;
    }
}
