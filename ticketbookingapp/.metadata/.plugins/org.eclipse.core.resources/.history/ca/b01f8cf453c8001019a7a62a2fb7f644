package com.moviebooking.controller;

import com.moviebooking.entity.Screen;
import com.moviebooking.entity.Theatre;
import com.moviebooking.entity.User;
import com.moviebooking.service.ScreenService;
import com.moviebooking.service.TheatreService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/screens")
public class AdminScreenController {

    private final ScreenService screenService;
    private final TheatreService theatreService;

    public AdminScreenController(ScreenService screenService, TheatreService theatreService) {
        this.screenService = screenService;
        this.theatreService = theatreService;
    }

    private boolean isAdmin(HttpSession session) {
        User user = (User) session.getAttribute("loggedInUser");
        return user != null && "ADMIN".equalsIgnoreCase(user.getRole());
    }

    @GetMapping
    public String listScreens(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        List<Screen> screens = screenService.getAllScreens();
        model.addAttribute("screens", screens);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_screen_list";
    }

    @GetMapping("/new")
    public String showNewScreenForm(Model model, HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        List<Theatre> theatres = theatreService.getAllTheatres();
        model.addAttribute("screen", new Screen());
        model.addAttribute("theatres", theatres);
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_screen_form";
    }

    @PostMapping
    public String saveScreen(@ModelAttribute("screen") Screen screen,
                             @RequestParam("theatreId") Long theatreId,
                             HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        Theatre theatre = theatreService.getTheatreById(theatreId);
        screen.setTheatre(theatre);
        screenService.saveScreen(screen);
        return "redirect:/admin/screens";
    }

    @GetMapping("/edit/{id}")
    public String editScreen(@PathVariable Long id,
                             Model model,
                             HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        Screen screen = screenService.getScreenById(id);
        if (screen == null) return "redirect:/admin/screens";

        List<Theatre> theatres = theatreService.getAllTheatres();
        model.addAttribute("screen", screen);
        model.addAttribute("theatres", theatres);
        model.addAttribute("selectedTheatreId", screen.getTheatre().getId());
        model.addAttribute("loggedInUser", session.getAttribute("loggedInUser"));
        return "admin_screen_form";
    }

    @GetMapping("/delete/{id}")
    public String deleteScreen(@PathVariable Long id,
                               HttpSession session) {
        if (!isAdmin(session)) return "redirect:/login";

        screenService.deleteScreenById(id);
        return "redirect:/admin/screens";
    }
}
