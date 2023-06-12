package com.example.artGallery.controllers;

import com.example.artGallery.services.UploadedFilesService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/files")
public class FilesController {
    private final UploadedFilesService fileService ;

    public FilesController(UploadedFilesService fileService){
        this.fileService = fileService;
    }

    @PostMapping(value = "/upload", consumes = {"multipart/form-data"})
    @SneakyThrows
    @ResponseBody
    public int uploadFile(@RequestParam("file") MultipartFile file) {
        int fileId = this.fileService.saveFile(file);
        return fileId;
    }

    @GetMapping("/download/{fileId}")
    @ResponseBody
    @SneakyThrows
    public byte[] downloadFromDB(@PathVariable int fileId) {
        return fileService.prepareFile(fileId).getData();
    }
}
