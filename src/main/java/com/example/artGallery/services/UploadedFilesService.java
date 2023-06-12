package com.example.artGallery.services;

import com.example.artGallery.model.UploadedFile;
import com.example.artGallery.repositories.ArtworkRepository;
import com.example.artGallery.repositories.FilesRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

@Service
public class UploadedFilesService {
    private final FilesRepository fileRepository;
    private final ArtworkRepository artworkRepository;

    public UploadedFilesService(FilesRepository filesRepository, ArtworkRepository artworkRepository) {
        this.fileRepository = filesRepository;
        this.artworkRepository = artworkRepository;
    }


    public int saveFile(MultipartFile file) {
        UploadedFile fileToSave = new UploadedFile();
        String originalFileName = file.getOriginalFilename();
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(originalFileName));
        String[] fileNameType = fileName.split("\\.");
        fileToSave.setName(fileNameType[0]);
        fileToSave.setType(fileNameType[fileNameType.length - 1]);
        try {
            fileToSave.setData(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        int newFileId = getNewId();
        fileToSave.setId(newFileId);
        fileRepository.save(fileToSave);

        return newFileId;
    }

    private int getNewId() {
        Optional<Integer> idOpt = fileRepository.getLastId();
        return idOpt.map(integer -> integer + 1).orElse(1);
    }

    public UploadedFile prepareFile(int fileId) {
        return fileRepository.getById(fileId);
    }
}
