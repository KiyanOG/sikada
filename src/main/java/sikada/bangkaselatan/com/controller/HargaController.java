package sikada.bangkaselatan.com.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sikada.bangkaselatan.com.entity.Harga;
import sikada.bangkaselatan.com.repository.HargaRepository;
import sikada.bangkaselatan.com.repository.KomoditasRepository;
import sikada.bangkaselatan.com.repository.PasarRepository;

@Controller
@RequestMapping("/admin/harga")
public class HargaController {

    private final HargaRepository hargaRepository;
    private final KomoditasRepository komoditasRepository;
    private final PasarRepository pasarRepository;

    public HargaController(
            HargaRepository hargaRepository,
            KomoditasRepository komoditasRepository,
            PasarRepository pasarRepository) {

        this.hargaRepository = hargaRepository;
        this.komoditasRepository = komoditasRepository;
        this.pasarRepository = pasarRepository;
    }

    @GetMapping
public String index(
        @RequestParam(required = false) Long pasarId,
        @RequestParam(required = false) Long komoditasId,
        Model model) {

    List<Harga> hargaList;

    if (pasarId != null && komoditasId != null) {

        hargaList = hargaRepository
                .findByPasarIdAndKomoditasId(
                        pasarId,
                        komoditasId);

    } else if (pasarId != null) {

        hargaList = hargaRepository
                .findByPasarId(pasarId);

    } else if (komoditasId != null) {

        hargaList = hargaRepository
                .findByKomoditasId(komoditasId);

    } else {

        hargaList = hargaRepository.findAll();
    }

    model.addAttribute("hargaList", hargaList);

    model.addAttribute("pasarList",
            pasarRepository.findAll());

    model.addAttribute("komoditasList",
            komoditasRepository.findAll());

    model.addAttribute("selectedPasarId",
            pasarId);

    model.addAttribute("selectedKomoditasId",
            komoditasId);

    return "admin/harga";
}

    @GetMapping("/tambah")
    public String tambah(Model model) {

        model.addAttribute("harga",
                new Harga());

        model.addAttribute("komoditasList",
                komoditasRepository.findAll());

        model.addAttribute("pasarList",
                pasarRepository.findAll());

        return "admin/tambah-harga";
    }

    @PostMapping("/simpan")
    public String simpan(@ModelAttribute Harga harga) {

        hargaRepository.save(harga);

        return "redirect:/admin/harga";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id,
                       Model model) {

        model.addAttribute("harga",
                hargaRepository.findById(id).orElseThrow());

        model.addAttribute("komoditasList",
                komoditasRepository.findAll());

        model.addAttribute("pasarList",
                pasarRepository.findAll());

        return "admin/edit-harga";
    }

    @PostMapping("/update")
public String update(
        @RequestParam Long id,
        @RequestParam Double harga,
        @RequestParam Long komoditasId,
        @RequestParam Long pasarId) {

    Harga data = hargaRepository.findById(id).orElseThrow();

    data.setHarga(harga);

    data.setKomoditas(
            komoditasRepository.findById(komoditasId)
                    .orElseThrow());

    data.setPasar(
            pasarRepository.findById(pasarId)
                    .orElseThrow());

    hargaRepository.save(data);

    return "redirect:/admin/harga";
}

    @GetMapping("/hapus/{id}")
    public String hapus(@PathVariable Long id) {

        hargaRepository.deleteById(id);

        return "redirect:/admin/harga";
    }
}