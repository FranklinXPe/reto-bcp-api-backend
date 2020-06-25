package com.bcp.tipocambio.services;

import com.bcp.tipocambio.dto.request.CambioMonedaResponse;
import com.bcp.tipocambio.dto.request.TipoCambioRequest;
import com.bcp.tipocambio.exception.TipoCambioNotFoundException;
import com.bcp.tipocambio.models.TipoCambio;
import com.bcp.tipocambio.repository.TipoCambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class TipoCambioService {

    @Autowired
    private TipoCambioRepository tipoCambioRepository;

    public TipoCambioService(TipoCambioRepository tipoCambioRepository) {
        this.tipoCambioRepository = tipoCambioRepository;
    }

    public List<TipoCambio> listarTipoCambio(){
        return tipoCambioRepository.findAll();
    }


    public CambioMonedaResponse calcularTipoCambio(String monedaOrigen, String monedaDestino , Double monto){

           Optional<TipoCambio> tipoCambioOptional = tipoCambioRepository.findByOrigenAndDestino(monedaOrigen, monedaDestino);

           if(tipoCambioOptional.isPresent()){

               TipoCambio tipoCambio=tipoCambioOptional.get();

               return new CambioMonedaResponse(monedaOrigen,monedaDestino,
                                                monto,
                                            tipoCambio.getValor()* monto,
                                                tipoCambio.getValor());
           }else{
               throw new TipoCambioNotFoundException(String.format("No existe el tipo de cambio de %s a %s",monedaOrigen,monedaDestino) );
           }
    }


    public TipoCambio actualizarTipoCambio( TipoCambioRequest tipoCambioRequest){

            Optional<TipoCambio> tipoCambioOptional = tipoCambioRepository.findByOrigenAndDestino(tipoCambioRequest.getOrigen(), tipoCambioRequest.getDestino());

            if(tipoCambioOptional.isPresent() ){

                TipoCambio tipoCambioUpdate= tipoCambioOptional.get();

                tipoCambioUpdate.setOrigen(tipoCambioRequest.getOrigen());
                tipoCambioUpdate.setDestino(tipoCambioRequest.getDestino());
                tipoCambioUpdate.setValor(tipoCambioRequest.getValor());

                tipoCambioUpdate= tipoCambioRepository.save(tipoCambioUpdate);

                return tipoCambioUpdate;
            }
            else{
                throw new TipoCambioNotFoundException(String.format("No existe el tipo de cambio de %s a %s",tipoCambioRequest.getOrigen(),tipoCambioRequest.getDestino()) );
            }

    }

}
