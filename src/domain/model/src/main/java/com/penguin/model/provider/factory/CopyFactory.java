package com.penguin.model.provider.factory;

import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.values.copy.*;

public interface CopyFactory {
    Copy createCopy(
            BookType bookType,
            Title title,
            Author author,
            AreaOfKnowledge areaOfKnowledge,
            NumOfPages numOfPages,
            CopiesOfTheBook copiesOfTheBook,
            Price price
    );
}
