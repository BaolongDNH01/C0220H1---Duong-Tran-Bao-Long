package com.furama.furama.Controllers;

import com.furama.furama.Models.HopDong;
import com.furama.furama.Models.KhachHang;
import com.furama.furama.Services.DichVuServices;
import com.furama.furama.Services.HopDongServices;
import com.furama.furama.Services.KhachHangServices;
import com.furama.furama.Services.NhanVienServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@SessionAttributes({"listDichVuLamHopDong", "listNhanVienLamHopDong"})
public class HopDongController {
    @Autowired
    HopDongServices hopDongServices;
    @Autowired
    KhachHangServices khachHangServices;
    @Autowired
    NhanVienServices nhanVienServices;
    @Autowired
    DichVuServices dichVuServices;

    @GetMapping("/create-contract")
    public String getCreateContract(@RequestParam Optional<Integer> id, Model model
            , RedirectAttributes redirectAttributes) {
        if (id.isPresent()) {
            model.addAttribute("listDichVuLamHopDong", dichVuServices.findAll());
            model.addAttribute("listNhanVienLamHopDong", nhanVienServices.findAll());
            HopDong hopDong = new HopDong();
            KhachHang khachHang = khachHangServices.findById(id.get());
            hopDong.setKhachHang(khachHang);
            model.addAttribute("HopDong", hopDong);
            return "main";
        }
        String mark = "create-contract";
        redirectAttributes.addFlashAttribute("mark", mark);
        return "redirect:/search";
    }

    @PostMapping("/create-contract")
    public String postCreateContract(@Valid @ModelAttribute(value = "HopDong") HopDong hopDong, BindingResult result,
                                     RedirectAttributes redirectAttributes, Model model) {
        new HopDong().validate(hopDong, result);
        if (result.hasFieldErrors()) {
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.HopDong", result);
            model.addAttribute("HopDong", hopDong);
            return "main";
        } else {
            hopDongServices.save(hopDong);
            model.addAttribute("message", "Khoi Tao Hop Dong Thanh Cong!");
            return "main";
        }
    }

    @GetMapping("/get-contract")
    public String getContract(@PageableDefault(size = 5)Pageable pageable,@RequestParam("addServices") Optional<Integer> addServices, Model model){
        if ((addServices.isPresent()) && (addServices.get() == 1)){
            model.addAttribute("addServices",1);
        }
        model.addAttribute("listHopDong",hopDongServices.findAll(pageable));
        return "listHopDong";
    }



}
