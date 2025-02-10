package com.archisacademy.morent.ModelMappper;

import com.archisacademy.morent.dtos.requests.BookingRequest;
import com.archisacademy.morent.entities.Booking;
import lombok.RequiredArgsConstructor;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Service
public class ModelMapperServiceImpl implements ModelMapperService {

    private  ModelMapper modelMapper;

    public ModelMapperServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        modelMapper.addConverter(uuidToLongConverter);
    }

    @Override
    public ModelMapper request() {
        this.modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STANDARD)
                .setAmbiguityIgnored(true);

        return modelMapper;
    }

    @Override
    public ModelMapper response() {
        this.modelMapper.getConfiguration().
                setMatchingStrategy(MatchingStrategies.LOOSE)
                .setAmbiguityIgnored(true);
        return modelMapper;
    }


    Converter<UUID, Long> uuidToLongConverter = new Converter<UUID, Long>() {
        @Override
        public Long convert(MappingContext<UUID, Long> context) {
            return context.getSource() != null ? context.getSource().getMostSignificantBits() : null;
        }
    };


}
