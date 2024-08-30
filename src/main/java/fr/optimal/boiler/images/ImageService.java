package fr.optimal.optimalshop.images;

import lombok.RequiredArgsConstructor;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public String addImage(String title, MultipartFile image) throws IOException {
        Image newImage = new Image(title);
        newImage.setFile(new Binary(BsonBinarySubType.BINARY, image.getBytes()));
        imageRepository.save(newImage);
        return newImage.getId();
    }

    public Image getImageByTitle(String title) {
        return imageRepository.getImageByTitle(title);
    }
}
