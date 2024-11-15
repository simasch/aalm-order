package ch.fhnw.aalm.bookstore.order.web;

import ch.fhnw.aalm.bookstore.order.domain.Book;
import ch.fhnw.aalm.bookstore.order.domain.ShoppingCart;
import ch.fhnw.aalm.bookstore.order.integration.CatalogClient;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.annotation.SessionScope;

@SessionScope
@Controller
public class ShoppingCartController {

    private final ShoppingCart shoppingCart = new ShoppingCart();

    private final CatalogClient catalogClient;

    public ShoppingCartController(CatalogClient catalogClient) {
        this.catalogClient = catalogClient;
    }

    @GetMapping("/cart")
    public String catalog(Model model) {
        var items = shoppingCart.getItems();
        model.addAttribute("items", items);
        return "cart";
    }

    @GetMapping("/cart/add/{isbn}")
    public String add(Model model, @PathVariable String isbn) {
        Book book = catalogClient.getBook(isbn);

        if (book != null) {
            shoppingCart.addBook(book);
        }

        return catalog(model);
    }

}
