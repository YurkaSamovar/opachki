package org.example.http.rest;

import lombok.RequiredArgsConstructor;
import org.example.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/image")
@RequiredArgsConstructor
public class ImageRestController {

    private final ImageService imageService;

    @GetMapping(value = "/{path}/{name}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public byte[] getImage(@PathVariable String name, @PathVariable String path) {
        return imageService.get("/%s/%s".formatted(path,name))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
}
