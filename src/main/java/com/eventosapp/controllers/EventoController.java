package com.eventosapp.controllers;

import com.eventosapp.models.Convidado;
import com.eventosapp.models.Evento;
import com.eventosapp.repository.ConvidadoRepository;
import com.eventosapp.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;


import java.util.List;

@Controller
public class EventoController {
    @Autowired
    private EventoRepository er;
    @Autowired
    private ConvidadoRepository cr;

    @RequestMapping(value="cadastrarEvento", method = RequestMethod.GET)
    public String form(){
        return "evento/formEvento";
    }

    @RequestMapping(value="cadastrarEvento", method = RequestMethod.POST)

    public String form(Evento evento){
        er.save(evento);
        return "redirect:/cadastrarEvento";
    }
    @RequestMapping(value="/eventos")
    public ModelAndView listaEventos(){
        ModelAndView mv = new ModelAndView("index");
        Iterable<Evento> eventos = er.findAll();
        mv.addObject("eventos", eventos);
        return mv;
    }
    @RequestMapping(value="/{codigo}", method = RequestMethod.GET)
    public ModelAndView detalhesEvento(@PathVariable("codigo") Long codigo){

        Evento evento = er.findByCodigo(codigo);
        ModelAndView mv = new ModelAndView("evento/detalhesEvento");
        mv.addObject("evento", evento);

        Iterable<Convidado> convidados = cr.findByEvento(evento);
        mv.addObject("convidados", convidados);

        return mv;
    }

    @RequestMapping(value="/{codigo}", method = RequestMethod.POST)
    public String detalhesEventoPost(@PathVariable("codigo") Long codigo, @Valid Convidado convidado,
                                     BindingResult result, RedirectAttributes attributes){

        if(result.hasErrors()){
            attributes.addFlashAttribute("flashMessage", "Verifique os campos!");
            attributes.addFlashAttribute("flashType", "danger");
            return "redirect:/{codigo}";
        }
        Evento evento = er.findByCodigo(codigo);
        convidado.setEvento(evento);
        cr.save(convidado);
        attributes.addFlashAttribute("flashMessage", "Convidado adicionado com sucesso!");
        attributes.addFlashAttribute("flashType", "success");

        return "redirect:/{codigo}";
    }
}
