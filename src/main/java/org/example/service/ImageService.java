package org.example.service;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Optional;

@Service
public class ImageService {

    @Value("${app.image.bucket}")
    private String bucket;

    @SneakyThrows
    public void upload(String image, InputStream content, String path) throws IOException {
        Path fullImagePath = Path.of(bucket + path, image);

        try(content) {
            Files.createDirectories(fullImagePath.getParent());
            Files.write(fullImagePath, content.readAllBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        }
    }

    @SneakyThrows
    public Optional<byte[]> get(String image){
        Path fullImagePath = Path.of(bucket, image);

        return Files.exists(fullImagePath)
                ? Optional.of(Files.readAllBytes(fullImagePath))
                : Optional.empty();
    }
}
