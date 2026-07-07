package sikada.bangkaselatan.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import sikada.bangkaselatan.com.entity.Komoditas;
import sikada.bangkaselatan.com.repository.KomoditasRepository;

    
    @Controller
@RequestMapping("/admin/komoditas")
public class KomoditasController {

    @Autowired
    private KomoditasRepository komoditasRepository;

    public KomoditasController(KomoditasRepository komoditasRepository) {
        this.komoditasRepository = komoditasRepository;
    }

    @GetMapping
    public String index(Model model){

        model.addAttribute(
                "komoditasList",
                komoditasRepository.findAll());

        return "admin/komoditas";
    }

    @GetMapping("/tambah")
    public String tambahForm(Model model){

        model.addAttribute(
                "komoditas",
                new Komoditas());

        return "admin/tambah-komoditas";
    }

    @PostMapping("/simpan")
    public String simpan(
            @ModelAttribute Komoditas komoditas){

        komoditasRepository.save(komoditas);

        return "redirect:/admin/komoditas";
    }

    @GetMapping("/hapus/{id}")
    public String hapus(
            @PathVariable Long id){

        komoditasRepository.deleteById(id);

        return "redirect:/admin/komoditas";
    }

    @GetMapping("/edit/{id}")
public String edit(
        @PathVariable Long id,
        Model model){

    Komoditas komoditas =
            komoditasRepository.findById(id)
                    .orElseThrow();

    model.addAttribute(
            "komoditas",
            komoditas);

    return "admin/edit-komoditas";
}


    @PostMapping("/update")
public String update(
        @ModelAttribute Komoditas komoditas){

    komoditasRepository.save(komoditas);

    return "redirect:/admin/komoditas";
}

    public KomoditasRepository getKomoditasRepository() {
        return komoditasRepository;
    }

    public void setKomoditasRepository(KomoditasRepository komoditasRepository) {
        this.komoditasRepository = komoditasRepository;
    }

}

