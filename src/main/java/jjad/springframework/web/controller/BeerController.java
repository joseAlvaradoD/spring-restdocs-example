package jjad.springframework.web.controller;

import jjad.springframework.repository.BeerRepository;
import jjad.springframework.web.mappers.BeerMapper;
import jjad.springframework.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    @ResponseStatus(HttpStatus.OK)
    public BeerDto getBeerById(@PathVariable("beerId") UUID beerId){


        return beerMapper.beerToBeerDto(beerRepository.findById(beerId).get());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void saveNewBeer(@RequestBody @Validated BeerDto beerDto){

        beerRepository.save(beerMapper.beerDtoToBeer(beerDto));
;
    }

    @PutMapping("/{beerId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());

            beerRepository.save(beer);
        });
    }

}
