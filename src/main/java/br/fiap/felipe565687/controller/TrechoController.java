package br.fiap.felipe565687.controller;

import br.fiap.felipe565687.dto.BairroDTO;
import br.fiap.felipe565687.dto.TrechoDTO;
import br.fiap.felipe565687.service.BairroService;
import br.fiap.felipe565687.service.TrechoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/trechos")
@AllArgsConstructor
public class TrechoController {
    private final TrechoService service;
    private final BairroService bairroService;
    private static final List<String> TIPOSCALCADA = List.of("Regular", "Irregular", "Escadaria", "Sem iluminação");

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("trechos", service.listar());
        return "trechos/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("trecho", new TrechoDTO(null, null, null, null, null, null));
        model.addAttribute("tiposCalcada", TIPOSCALCADA);
        model.addAttribute("bairros", bairroService.listar());
        return "trechos/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("trecho", service.buscarPorId(id));
            model.addAttribute("bairros", bairroService.listar());
            model.addAttribute("tiposCalcada", TIPOSCALCADA);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("trechos", service.listar());
            return "trechos/lista";
        }
        return "trechos/form";
    }
@PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("trecho") TrechoDTO dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("bairros", bairroService.listar());
            model.addAttribute("tiposCalcada", TIPOSCALCADA);
            return "trechos/form";
        }
        try {
            service.salvar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("bairros", bairroService.listar());
            model.addAttribute("tiposCalcada", TIPOSCALCADA);
            return "trechos/form";
        }

        return "redirect:/trechos";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            service.excluir(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("trechos", service.listar());
            return "trechos/lista";
        }
        return "redirect:/trechos";
    }

}

