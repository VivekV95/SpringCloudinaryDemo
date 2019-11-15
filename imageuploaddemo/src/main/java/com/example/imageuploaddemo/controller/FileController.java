package com.example.imageuploaddemo.controller;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.cloudinary.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

@Controller
public class FileController {
    // Path name for directory created in main method to store files locally
    // NOT REQUIRED for Cloudinary image hosting
    public static String uploadDirectory = System.getProperty("user.dir") + "/upload";

    // Creates Cloudinary object to gain access to your Cloudinary account
    public Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "Your cloud name here",
            "api_key", "Your Cloudinary API Key here",
            "api_secret", "Your Cloudinary API Secret here"));

    // Endpoint for uploading an image from a local file
    // Shows basic html form to upload an image
    // Front end will take care of this
    @RequestMapping("/")
    public String UploadPage(Model model) {
        return "uploadview";
    }

    // Endpoint that handles image reception and hosting. The commented out code
    // stores file in the local directory created in the main method
    @PostMapping(value = "/upload", produces = {"application/json"})
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) {
        // Stores file in local directory
        // NOT REQUIRED for Cloudinary image hosting
        /* Path fileNameAndPath = Paths.get(uploadDirectory, file.getOriginalFilename());
        try {
            Files.write(fileNameAndPath, file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } */
        Map uploadResult = null;
        try {
            // uploadResult stores the JSON response that contains info about the hosted image
            // The front end dev will use the url to display the image
            uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
        } catch (IOException e) { e.printStackTrace();
        }
        // returns the JSON response that Cloudinary sends if the image is successfully hosted
        // response contains the url of the hosted image which is universally accessible
        return new ResponseEntity<>(uploadResult, HttpStatus.OK);
    }
}
