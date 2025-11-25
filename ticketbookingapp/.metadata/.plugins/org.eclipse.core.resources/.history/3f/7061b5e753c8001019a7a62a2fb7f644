package com.moviebooking.controller;

import com.moviebooking.entity.Theatre;
import com.moviebooking.entity.User;
import com.moviebooking.service.TheatreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/theatres")
public class AdminTheatreController {

    private final TheatreService theatreService;

    public AdminTheatreController(TheatreService theatreService) {
        this.theatreService = theatreService;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null && "ADMIN".equalsIgnoreCase(user.getRole());
    }

    @GetMapping
    public String listTheatres(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        List<Theatre> theatres = theatreService.getAllTheatres();
        model.addAttribute("theatres", theatres);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_theatre_list";
    }

    @GetMapping("/new")
    public String showNewTheatreForm(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        model.addAttribute("theatre", new Theatre());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_theatre_form";
    }

    @PostMapping
    public String saveTheatre(@ModelAttribute("theatre") Theatre theatre,
                              HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        theatreService.saveTheatre(theatre);
        return "redirect:/admin/theatres";
    }

    @GetMapping("/edit/{id}")
    public String editTheatre(@PathVariable Long id,
                              Model model,
                              HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        Theatre theatre = theatreService.getTheatreById(id);
        if (theatre == null) return "redirect:/admin/theatres";

        model.addAttribute("theatre", theatre);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_theatre_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteTheatre(@PathVariable Long id,
                                HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        theatreService.deleteTheatreById(id);
        return "redirect:/admin/theatres";
    }
}

