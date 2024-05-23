package com.penguin.model.provider.mapper;

import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.dto.BillGroup;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.events.CalculatedBillGroup;
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
        literaryWork.setBookType(type);
        literaryWork.setTitle(copy.getTitle());
        literaryWork.setAuthor(copy.getAuthor());
        literaryWork.setAreaOfKnowledge(copy.getAreaOfKnowledge());
        literaryWork.setNumOfPages(copy.getNumOfPages());
        literaryWork.setCopiesOfTheBook(copy.getCopiesOfTheBook());
        literaryWork.setPrice(copy.getPrice());
        return literaryWork;
    }


    public Copy toCopy(LiteraryWork literaryWork) {

        return factory.createCopy(
                new BookType(literaryWork.getBookType()),
                new Title(literaryWork.getTitle()),
                new Author(literaryWork.getAuthor()),
                new AreaOfKnowledge(literaryWork.getAreaOfKnowledge()),
                new NumOfPages(literaryWork.getNumOfPages()),
                new CopiesOfTheBook(literaryWork.getCopiesOfTheBook()),
                new Price(literaryWork.getPrice())
        );
    }

    public Bill toBill(CalculatedBill calculatedBill) {
        return new Bill(calculatedBill.getRegisteredAt(),calculatedBill.getCopies(), calculatedBill.getPrice());
    }

    public BillGroup toBillGroup(CalculatedBillGroup calculatedBillGroup) {
        return new BillGroup(calculatedBillGroup.getFinalPrice(), calculatedBillGroup.getBillGroups());
    }


}
