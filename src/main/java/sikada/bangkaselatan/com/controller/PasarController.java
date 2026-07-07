package sikada.bangkaselatan.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sikada.bangkaselatan.com.entity.Pasar;
import sikada.bangkaselatan.com.repository.PasarRepository;

@Controller
@RequestMapping("/admin/pasar")
public class PasarController {

    @Autowired
    private PasarRepository pasarRepository;

    public PasarController(PasarRepository pasarRepository) {
        this.pasarRepository = pasarRepository;
    }

    @GetMapping
    public String index(Model model){

        model.addAttribute(
                "pasarList",
                pasarRepository.findAll());

        return "admin/pasar";
    }

    @GetMapping("/tambah")
    public String tambah(Model model){

        model.addAttribute(
                "pasar",
                new Pasar());

        return "admin/tambah-pasar";
    }

    @PostMapping("/simpan")
    public String simpan(
            @ModelAttribute Pasar pasar){

        pasarRepository.save(pasar);

        return "redirect:/admin/pasar";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable Long id,
            Model model){

        Pasar pasar =
                pasarRepository.findById(id)
                        .orElseThrow();

        model.addAttribute(
                "pasar",
                pasar);

        return "admin/edit-pasar";
    }

    @PostMapping("/update")
    public String update(
            @ModelAttribute Pasar pasar){

        pasarRepository.save(pasar);

        return "redirect:/admin/pasar";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(
            @PathVariable Long id){

        pasarRepository.deleteById(id);

        return "redirect:/admin/pasar";
    }

    public PasarRepository getPasarRepository() {
        return pasarRepository;
    }

    public void setPasarRepository(PasarRepository pasarRepository) {
        this.pasarRepository = pasarRepository;
    }
}