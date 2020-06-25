package com.bcp.tipocambio.util;

import com.bcp.tipocambio.dto.response.TipoCambioResponse;
import com.bcp.tipocambio.models.TipoCambio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EntityDtoConverter {

    @Autowired
    private ModelMapper modelMapper;

    public TipoCambioResponse convertEntityToDto(TipoCambio tipoCambio){
        return modelMapper.map(tipoCambio, TipoCambioResponse.class);
    }


}
