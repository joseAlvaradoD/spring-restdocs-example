package jjad.springframework.web.mappers;

import jjad.springframework.domain.Beer;
import jjad.springframework.web.model.BeerDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(uses = DateMapper.class)
public interface BeerMapper {

    BeerDto beerToBeerDto(Beer beer);
    Beer beerDtoToBeer(BeerDto dto);

}
