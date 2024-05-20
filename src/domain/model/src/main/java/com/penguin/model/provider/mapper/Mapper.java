package com.penguin.model.provider.mapper;

import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.values.copy.*;
import org.springframework.stereotype.Component;

@Component
public class Mapper {

    private final CopyFactory factory;

    public Mapper(CopyFactory factory) {
        this.factory = factory;
    }

    public LiteraryWork toLiteraryWork(Copy copy, String uuid, String type) {
        LiteraryWork literaryWork = new LiteraryWork();
        literaryWork.setId(uuid);
        literaryWork.setType(type);
        literaryWork.setTitle(copy.getTitle());
        literaryWork.setAuthor(copy.getAuthor());
        literaryWork.setCopies(copy.getStock());
        literaryWork.setYearOfPublication(copy.getPublicationYear());
        literaryWork.setPrice(copy.getPrice());
        return literaryWork;
    }

    public Copy toCopy(LiteraryWork literaryWork) {
        return factory.createCopy(
                    new Type(literaryWork.getType()),
                    new Title(literaryWork.getTitle()),
                    new Author(literaryWork.getAuthor()),
                    new Stock( literaryWork.getCopies()),
                    new PublicationYear(literaryWork.getYearOfPublication()),
                    new Price(literaryWork.getPrice())
        );
    }

    public Bill toBill(CalculatedBill calculatedBill) {
        return new Bill(calculatedBill.getRegisteredAt(),calculatedBill.getCopies(), calculatedBill.getPrice());
    }


}
