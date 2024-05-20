package com.penguin;

import com.penguin.calculateBillUseCase.CalculateBillUseCase;
import com.penguin.model.provider.commands.CalculateBillCommand;
import com.penguin.model.provider.commands.SaveBookCommand;
import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.mapper.Mapper;
import com.penguin.model.provider.values.copy.*;
import com.penguin.model.generic.DomainEvent;

import com.penguin.saveCopyUseCase.SaveCopyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/book")
public class RouterRest {

    private final SaveCopyUseCase saveCopyUseCase;
    private final CalculateBillUseCase calculateBillUseCase;
    private final Mapper mapper;
    private final CopyFactory copyFactory;

    public RouterRest(SaveCopyUseCase saveCopyUseCase, CalculateBillUseCase calculateBillUseCase, Mapper mapper, CopyFactory copyFactory) {
        this.saveCopyUseCase = saveCopyUseCase;
        this.calculateBillUseCase = calculateBillUseCase;
        this.mapper = mapper;
        this.copyFactory = copyFactory;
    }

    @PostMapping("/save")
    public Mono<ResponseEntity<LiteraryWork>> saveCopy(@RequestBody SaveBookCommand command){
        return saveCopyUseCase.apply(Mono.just(command))
                .map(domain -> {
                    BookSaved bookSaved = (BookSaved) domain;
                    Copy copy = copyFactory.createCopy(new Type(command.getType()),new Title(bookSaved.getTitle()),new Author(bookSaved.getAuthor()),new Stock( bookSaved.getStock()),new PublicationYear(bookSaved.getPublicationYear()),new Price(bookSaved.getPrice()));
                    return mapper.toLiteraryWork(copy,domain.uuid.toString(),command.getType());
                })
                .singleOrEmpty()
                .map(literaryWork -> new ResponseEntity<>(literaryWork,HttpStatus.CREATED));

    }
    @PostMapping("/bill")
    public Mono<ResponseEntity<Bill>> calculateBill(@RequestBody CalculateBillCommand command) {
        return calculateBillUseCase.apply(Mono.just(command))
                .map(domain -> {
                    CalculatedBill calculatedBill = (CalculatedBill) domain;
                    return mapper.toBill(calculatedBill);
                })
                .singleOrEmpty()
                .map(ResponseEntity::ok);

    }

}
