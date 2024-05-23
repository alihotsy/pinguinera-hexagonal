package com.penguin;

import com.penguin.calculateBillGroupUseCase.CalculateBillGroupUseCase;
import com.penguin.calculateBillUseCase.CalculateBillUseCase;
import com.penguin.calculateMaxDiscountUseCase.CalculateMaxWholeDiscountUseCase;
import com.penguin.getAllBooksUseCase.GetAllBooksUseCase;
import com.penguin.model.generic.DomainEvent;
import com.penguin.model.provider.commands.*;
import com.penguin.model.provider.dto.Bill;
import com.penguin.model.provider.dto.BillGroup;
import com.penguin.model.provider.dto.LiteraryWork;
import com.penguin.model.provider.dto.MaxWholeDiscount;
import com.penguin.model.provider.entity.Copy;
import com.penguin.model.provider.events.BookSaved;
import com.penguin.model.provider.events.CalculatedBill;
import com.penguin.model.provider.events.CalculatedBillGroup;
import com.penguin.model.provider.events.CalculatedMaxWholeDiscount;
import com.penguin.model.provider.factory.CopyFactory;
import com.penguin.model.provider.mapper.Mapper;
import com.penguin.model.provider.values.copy.*;

import com.penguin.saveCopyUseCase.SaveCopyUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/book")
public class RouterRest {

    private final SaveCopyUseCase saveCopyUseCase;
    private final CalculateBillUseCase calculateBillUseCase;
    private final CalculateMaxWholeDiscountUseCase calculateMaxWholeDiscountUseCase;
    private final CalculateBillGroupUseCase calculateBillGroupUseCase;
    private final GetAllBooksUseCase getAllBooksUseCase;
    private final Mapper mapper;
    private final CopyFactory copyFactory;

    public RouterRest(SaveCopyUseCase saveCopyUseCase, CalculateBillUseCase calculateBillUseCase, CalculateMaxWholeDiscountUseCase calculateMaxWholeDiscountUseCase, CalculateBillGroupUseCase calculateBillGroupUseCase, GetAllBooksUseCase getAllBooksUseCase, Mapper mapper, CopyFactory copyFactory) {
        this.saveCopyUseCase = saveCopyUseCase;
        this.calculateBillUseCase = calculateBillUseCase;
        this.calculateMaxWholeDiscountUseCase = calculateMaxWholeDiscountUseCase;
        this.calculateBillGroupUseCase = calculateBillGroupUseCase;
        this.getAllBooksUseCase = getAllBooksUseCase;
        this.mapper = mapper;
        this.copyFactory = copyFactory;
    }

    @GetMapping("/get-all")
    public Mono<ResponseEntity<List<LiteraryWork>>> getAllBooks(@RequestBody GetAllBooksCommand getAllBooksCommand) {
        return getAllBooksUseCase.apply(Mono.just(getAllBooksCommand))
                .map(domain -> {
                    String uuid = domain.uuid.toString();
                    BookSaved bookSaved = (BookSaved) domain;
                    Copy copy = copyFactory.createCopy(new BookType(bookSaved.getType()),new Title(bookSaved.getTitle()),new Author(bookSaved.getAuthor()),new AreaOfKnowledge(bookSaved.getAreaOfKnowledge()),new NumOfPages(bookSaved.getNumOfPages()),new CopiesOfTheBook( bookSaved.getCopiesOfTheBook()),new Price(bookSaved.getPrice()));
                    return mapper.toLiteraryWork(copy,uuid,((BookSaved) domain).getType());
                }).collectList()
                .map(ResponseEntity::ok);

    }
    @PostMapping("/save")
    public Mono<ResponseEntity<LiteraryWork>> saveCopy(@RequestBody SaveBookCommand command){
        return saveCopyUseCase.apply(Mono.just(command))
                .map(domain -> {
                    BookSaved bookSaved = (BookSaved) domain;
                    Copy copy = copyFactory.createCopy(new BookType(command.getBookType()),new Title(bookSaved.getTitle()),new Author(bookSaved.getAuthor()),new AreaOfKnowledge(bookSaved.getAreaOfKnowledge()),new NumOfPages(bookSaved.getNumOfPages()),new CopiesOfTheBook( bookSaved.getCopiesOfTheBook()),new Price(bookSaved.getPrice()));
                    return mapper.toLiteraryWork(copy,domain.uuid.toString(),command.getBookType());
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

    @PostMapping("/bill-group")
    public Mono<ResponseEntity<BillGroup>> calculateBillGroup(@RequestBody CalculateBillGroupCommand command) {
        return calculateBillGroupUseCase.apply(Mono.just(command))
                .singleOrEmpty()
                .map(domainEvent ->  {
                    CalculatedBillGroup event = (CalculatedBillGroup) domainEvent;
                    return mapper.toBillGroup(event);
                }).map(billGroup -> new ResponseEntity<>(billGroup,HttpStatus.CREATED));
    }

    @PostMapping("/max-discount")
    public Mono<ResponseEntity<MaxWholeDiscount>> calculateMaxDiscount(@RequestBody CalculateMaxWholeDiscountCommand command) {
        return calculateMaxWholeDiscountUseCase.apply(Mono.just(command))
                .map(domain -> {
                    CalculatedMaxWholeDiscount maxWholeDiscount = (CalculatedMaxWholeDiscount) domain;
                    return new MaxWholeDiscount(maxWholeDiscount.getBudget(),maxWholeDiscount.getBooks(), maxWholeDiscount.getTotalCopies());
                })
                .singleOrEmpty()
                .map(maxWholeDiscount -> new ResponseEntity<>(maxWholeDiscount,HttpStatus.CREATED));
    }


}
