package com.penguin.model.provider.factory;

import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.values.copy.*;

public interface CopyFactory {
    Copy createCopy(
            Type type,
            Title title,
            Author author,
            Stock stock,
            PublicationYear publicationYear,
            Price price
    );
}
