package com.workshop_microservicios.workshop_microservicios.Controller;

import com.workshop_microservicios.workshop_microservicios.Model.Registro;
import com.workshop_microservicios.workshop_microservicios.Service.ValidacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/validar")
public class ValidacionController {
    @Autowired
    private ValidacionService validacionService;

    @PostMapping("/csv")
    public boolean validarCSV(@RequestBody Registro registro) {

        return validacionService.validarRegistro(registro);
    }
}

