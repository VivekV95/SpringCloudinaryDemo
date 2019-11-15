package com.example.imageuploaddemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ImageuploaddemoApplication {

    public static void main(String[] args) {
        // Creates directory at the root of the project directory to store uploaded filed
        // NOT REQUIRED for Cloudinary image hosting
        /*
        new File(FileController.uploadDirectory).mkdir();
         */
        SpringApplication.run(ImageuploaddemoApplication.class, args);
    }
}
