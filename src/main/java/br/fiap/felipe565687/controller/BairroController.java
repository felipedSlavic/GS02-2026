package br.fiap.felipe565687.controller;

import br.fiap.felipe565687.dto.BairroDTO;
import br.fiap.felipe565687.dto.PainelItemDTO;
import br.fiap.felipe565687.service.BairroService;
import br.fiap.felipe565687.service.PainelService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/bairros")
@AllArgsConstructor
public class BairroController {
    private final BairroService service;
    private final PainelService painel;
    private static final List<String> ZONAS = List.of("Centro", "Norte", "Sul", "Leste", "Oeste");


    @GetMapping
    public String listar(Model model) {
        model.addAttribute("bairros", service.listar());
        return "bairros/lista";
    }

    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("bairro", new BairroDTO(null, null, null, null));
        model.addAttribute("zonas", ZONAS);
        return "bairros/form";
    }

    @GetMapping("/{id}/editar")
    public String editar(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("bairro", service.buscarPorId(id));
            model.addAttribute("zonas", ZONAS);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("bairros", service.listar());
            return "bairros/lista";
        }
        return "bairros/form";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid @ModelAttribute("bairro") BairroDTO dto,
                         BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("zonas", ZONAS);
            return "bairros/form";
        }
        try {
            service.salvar(dto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("zonas", ZONAS);
            return "bairros/form";
        }

        return "redirect:/bairros";

    }

    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id, Model model) {
        try {
            service.excluir(id);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("bairros", service.listar());
            return "bairros/lista";
        }
        return "redirect:/bairros";
    }

    @GetMapping("/{id}/painel")
    public String montarPainel(@PathVariable Long id, Model model) {
        try {
            List<PainelItemDTO> listaItens = painel.montarPainel(id);
            model.addAttribute("bairro", service.buscarPorId(id));
            model.addAttribute("itens", listaItens);
            model.addAttribute("qtdNormal", painel.contadorStatus(listaItens, "NORMAL"));
            model.addAttribute("qtdAtencao", painel.contadorStatus(listaItens, "ATENCAO"));
            model.addAttribute("qtdAlerta", painel.contadorStatus(listaItens, "ALERTA"));
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("bairros", service.listar());
            return "bairros/lista";
        }
        return "bairros/painel";

    }


}
