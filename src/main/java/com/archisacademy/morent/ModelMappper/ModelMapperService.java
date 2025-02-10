package com.archisacademy.morent.ModelMappper;

import org.modelmapper.ModelMapper;

public interface ModelMapperService {
    ModelMapper request();
    ModelMapper response();

}