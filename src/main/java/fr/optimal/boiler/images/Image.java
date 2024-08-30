package fr.optimal.optimalshop.images;

import lombok.Getter;
import lombok.Setter;
import org.bson.types.Binary;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document
@Setter
@Getter
public class Image {

    @MongoId
    private String id;
    @Indexed(unique = true)
    private String title;
    private Binary file;

    public Image(String title) {
        this.title = title;
    }

}

