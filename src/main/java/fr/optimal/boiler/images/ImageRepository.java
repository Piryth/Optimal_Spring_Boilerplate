package fr.optimal.optimalshop.images;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepository extends MongoRepository<Image, String> {
    public Image getImageByTitle(String title);
}
