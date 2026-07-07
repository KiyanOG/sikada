package sikada.bangkaselatan.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import sikada.bangkaselatan.com.repository.HargaRepository;
import sikada.bangkaselatan.com.repository.PasarRepository;

@Controller
public class HomeController {

    @Autowired  
    private HargaRepository hargaRepository;
    @Autowired
    private PasarRepository pasarRepository;

    public HomeController(HargaRepository hargaRepository, PasarRepository pasarRepository) {
        this.hargaRepository = hargaRepository;
        this.pasarRepository = pasarRepository;
    }

    @GetMapping("/")
public String home(
        @RequestParam(required = false) Long pasarId,
        Model model) {

    model.addAttribute("pasarList",
            pasarRepository.findAll());

    if (pasarId != null) {

        model.addAttribute(
                "hargaList",
                hargaRepository.findByPasarId(pasarId)
        );

        System.out.println("Filter Pasar = " + pasarId);

    } else {

        model.addAttribute(
                "hargaList",
                hargaRepository.findAll()
        );
    }

    return "index";
}

    public PasarRepository getPasarRepository() {
        return pasarRepository;
    }

    public HargaRepository getHargaRepository() {
        return hargaRepository;
    }

    public void setHargaRepository(HargaRepository hargaRepository) {
        this.hargaRepository = hargaRepository;
    }

    public void setPasarRepository(PasarRepository pasarRepository) {
        this.pasarRepository = pasarRepository;
    }
}