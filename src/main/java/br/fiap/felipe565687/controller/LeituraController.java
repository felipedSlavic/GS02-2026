package br.fiap.felipe565687.controller;

import br.fiap.felipe565687.dto.LeituraDTO;
import br.fiap.felipe565687.dto.TrechoDTO;
import br.fiap.felipe565687.service.LeituraService;
import br.fiap.felipe565687.service.TrechoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/leituras")
@AllArgsConstructor
public class LeituraController {
    private final LeituraService service;
    private final TrechoService trechoService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("leituras", service.listar());
        return "leituras/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("leitura", new LeituraDTO(null, null, null, null, null, null));
        model.addAttribute("trechos", trechoService.listar());
        return "leituras/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("leitura", service.buscarPorId(id));
            model.addAttribute("trechos", trechoService.listar());
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("leituras", service.listar());
            return "leituras/lista";
        }
        return "leituras/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("leitura") LeituraDTO dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("trechos", trechoService.listar());
            return "leituras/form";
        }
        try {
            service.salvar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("trechos", trechoService.listar());
            return "leituras/form";
        }

        return "redirect:/leituras";
    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            service.excluir(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("leituras", service.listar());
            return "leituras/lista";
        }
        return "redirect:/leituras";
    }

}
