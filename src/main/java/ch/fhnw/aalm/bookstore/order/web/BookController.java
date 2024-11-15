package ch.fhnw.aalm.bookstore.order.web;

import ch.fhnw.aalm.bookstore.order.integration.CatalogClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BookController {

    private final CatalogClient catalogClient;

    public BookController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping
    public String catalog() {
        return "index";
    }

    @GetMapping("/search")
    public String search(Model model, String keywords) {
        model.addAttribute("books", catalogClient.findBooks(keywords));
        return "index";
    }
}
