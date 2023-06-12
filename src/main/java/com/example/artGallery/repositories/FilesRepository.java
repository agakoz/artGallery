package com.example.artGallery.repositories;

import com.example.artGallery.model.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface FilesRepository extends JpaRepository<UploadedFile, Integer> {

    @Query("SELECT max(id) from UploadedFile")
    Optional<Integer> getLastId();
}
