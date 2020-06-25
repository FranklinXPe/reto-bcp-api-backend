package com.bcp.tipocambio.controllers;

import com.bcp.tipocambio.dto.request.CambioMonedaResponse;
import com.bcp.tipocambio.dto.request.TipoCambioRequest;
import com.bcp.tipocambio.dto.response.TipoCambioResponse;
import com.bcp.tipocambio.models.TipoCambio;
import com.bcp.tipocambio.services.TipoCambioService;
import com.bcp.tipocambio.util.EntityDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/tipocambio/v1")
public class TipoCambioController {

    @Autowired
    private TipoCambioService tipoCambioService;

    @Autowired
    private EntityDtoConverter converter;


    @GetMapping("/")
    public ResponseEntity<List<TipoCambio>> listarTiposCambio(){
        return  new ResponseEntity<>( tipoCambioService.listarTipoCambio(), HttpStatus.OK);
    }

    @GetMapping(value =  "/convierte")
    public ResponseEntity<CambioMonedaResponse> obtenerTipoCambio(@RequestParam("origen") String origen,
                                                                  @RequestParam("destino") String destino,
                                                                  @RequestParam("monto") Double monto){

        CambioMonedaResponse cambioMonedaResponse = tipoCambioService.calcularTipoCambio(origen, destino, monto);

        return new ResponseEntity<>(cambioMonedaResponse,HttpStatus.OK );
    }


    @PutMapping("/")
    public ResponseEntity<TipoCambioResponse> actualizarTipoCambio(@Validated @RequestBody TipoCambioRequest tipoCambioRequest)
    {
        TipoCambio tipoCambio = tipoCambioService.actualizarTipoCambio( tipoCambioRequest);

        return new ResponseEntity<>(converter.convertEntityToDto(tipoCambio), HttpStatus.OK);
    }
}
