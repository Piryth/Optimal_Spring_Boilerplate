package fr.optimal.optimalshop.images;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/optimal-shop/api/images")
public class ImageController {
    private final ImageService imageService;

    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> addImage(
            @RequestParam String title,
            @RequestParam MultipartFile image
    ) throws IOException {
        String imgId = imageService.addImage(title, image);
        return ResponseEntity.ok(imgId);
    }

    @GetMapping("/details/{title}")
    public ResponseEntity<Image> getImaageDetails(@PathVariable String title) {
        Image image = imageService.getImageByTitle(title);
        return ResponseEntity.ok(image);
    }

}
