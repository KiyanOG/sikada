package sikada.bangkaselatan.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import sikada.bangkaselatan.com.repository.HargaRepository;
import sikada.bangkaselatan.com.repository.KomoditasRepository;
import sikada.bangkaselatan.com.repository.PasarRepository;

@Controller
public class AdminController {
    @Autowired
    private KomoditasRepository komoditasRepository;

    @Autowired
    private PasarRepository pasarRepository;

    @Autowired
    private HargaRepository hargaRepository;

    public AdminController(HargaRepository hargaRepository, KomoditasRepository komoditasRepository, PasarRepository pasarRepository) {
        this.hargaRepository = hargaRepository;
        this.komoditasRepository = komoditasRepository;
        this.pasarRepository = pasarRepository;
    }

    @GetMapping("/admin/dashboard")
public String dashboard(
        HttpSession session,
        Model model){

    if(session.getAttribute("admin")==null){
        return "redirect:/admin/login";
    }

    model.addAttribute(
            "totalKomoditas",
            komoditasRepository.count());

    model.addAttribute(
            "totalPasar",
            pasarRepository.count());

    model.addAttribute(
            "totalHarga",
            hargaRepository.count());

    return "admin/dashboard";
}

    public KomoditasRepository getKomoditasRepository() {
        return komoditasRepository;
    }

    public void setKomoditasRepository(KomoditasRepository komoditasRepository) {
        this.komoditasRepository = komoditasRepository;
    }

    public PasarRepository getPasarRepository() {
        return pasarRepository;
    }

    public void setPasarRepository(PasarRepository pasarRepository) {
        this.pasarRepository = pasarRepository;
    }

    public HargaRepository getHargaRepository() {
        return hargaRepository;
    }

    public void setHargaRepository(HargaRepository hargaRepository) {
        this.hargaRepository = hargaRepository;
    }
}

