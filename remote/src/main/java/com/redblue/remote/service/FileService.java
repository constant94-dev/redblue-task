package com.redblue.remote.service;

import com.redblue.remote.config.StorageProperties;
import com.redblue.remote.controller.FileController;
import com.redblue.remote.vo.FileRecord;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
    private final FileRecord record;

    @Autowired
    public FileService(StorageProperties properties) {
        this.record = new FileRecord(Paths.get(properties.getLocation()));
    }

    public Stream<Path> loadAll() {
        try {
            return Files.walk(this.record.getRootLocation(), 1)
                    .filter(path -> !path.equals(this.record.getRootLocation()))
                    .map(this.record.getRootLocation()::relativize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Stream<Path> loadOne(String filename) {
        try {
            return Files.walk(this.record.getRootLocation(), 1)
                    .filter(path -> !path.equals(this.record.getRootLocation()))
                    .filter(path -> path.getFileName().toString().equals(filename))
                    .map(this.record.getRootLocation()::relativize);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Path load(String filename) {
        return this.record.getRootLocation().resolve(filename);
    }

    public void uploadFile(MultipartFile multipartFile) {
        LOGGER.info("파일 저장 경로: " + this.record.getRootLocation());

        Path copyOfLocation = Paths.get(
                this.record.getRootLocation()
                        + File.separator
                        + StringUtils.cleanPath(multipartFile.getOriginalFilename())
        );

        try {
            Files.copy(multipartFile.getInputStream(), copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Resource loadAsResource(String fileName) {
        LOGGER.info("가져올 파일 이름: " + fileName);

        try {
            Path file = load(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new MalformedURLException();
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
}
