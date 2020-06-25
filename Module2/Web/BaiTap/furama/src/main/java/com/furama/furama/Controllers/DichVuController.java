package com.furama.furama.Controllers;

import com.furama.furama.Models.DichVu;
import com.furama.furama.Services.DichVuServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class DichVuController {
    @Autowired
    DichVuServices dichVuServices;

    @GetMapping("/create-services")
    public String getCreateServices(Model model){
        model.addAttribute("DichVu", new DichVu());
        return "";
    }

    @PostMapping("/create-services")
    public String postCreateServices(@Valid @ModelAttribute(value = "DichVu") DichVu dichVu, BindingResult result,
                                     Model model){
        new DichVu().validate(dichVu,result);
        if (result.hasFieldErrors()){
            return "";
        }else {
            dichVuServices.save(dichVu);
            model.addAttribute("message","Tao Dich Vu Moi Thanh Cong!");
            return "";
        }
    }

    @GetMapping("/services")
    public String getServicesList(@PageableDefault(size = 5)Pageable pageable,Model model){
        model.addAttribute("ListDichVu",dichVuServices.findAll(pageable));
        return "";
    }
}