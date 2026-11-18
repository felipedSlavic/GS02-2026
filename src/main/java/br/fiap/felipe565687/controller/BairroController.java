package br.fiap.felipe565687.controller;

import br.fiap.felipe565687.service.BairroService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bairros")
@AllArgsConstructor
public class BairroController {
    private final BairroService service;


}
