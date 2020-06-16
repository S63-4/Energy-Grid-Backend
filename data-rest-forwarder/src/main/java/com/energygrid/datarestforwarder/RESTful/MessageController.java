package com.energygrid.datarestforwarder.RESTful;

import com.energygrid.datarestforwarder.models.SharedJSON;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

@RestController
public class MessageController {

    private ResourceLoader resourceLoader;

    private Gson gson = new Gson();

    @Autowired
    public MessageController(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @GetMapping("/regional")
    public String regionalMessage() throws IOException {
        File jsonFile = new File(
                getClass().getClassLoader().getResource("regional.json").getFile()
        );
        SharedJSON message = gson.fromJson(new FileReader(jsonFile), SharedJSON.class);
        return gson.toJson(message);
    }
}
