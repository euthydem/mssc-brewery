package az.micro.msscbrawery.controller;

import az.micro.msscbrawery.model.BeerDto;
import az.micro.msscbrawery.services.BeerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerService beerService;

    public BeerController(BeerService beerService) {
        this.beerService = beerService;
    }

    @GetMapping("/{beerId}")
    public ResponseEntity<BeerDto> getBeer(@PathVariable UUID beerId) {

        return new ResponseEntity<>(beerService.getBeerById(beerId) , HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity saveNewBeer(BeerDto beerDto) {
        BeerDto savedDto = beerService.generateNewBeer(beerDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Location" , "/api/v1/beer/" + savedDto.getId().toString());
        return new ResponseEntity(httpHeaders , HttpStatus.CREATED);
    }
}