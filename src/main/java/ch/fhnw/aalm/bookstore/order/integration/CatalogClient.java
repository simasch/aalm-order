package ch.fhnw.aalm.bookstore.order.integration;

import ch.fhnw.aalm.bookstore.order.domain.Book;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private final RestClient restClient;

    public CatalogClient(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    public Book[] findBooks(String keywords) {
        return restClient
                .get()
                .uri("http://localhost:8080/api/books?keywords=%s".formatted(keywords))
                .retrieve()
                .body(Book[].class);
    }

    public Book getBook(String isbn) {
        return restClient
                .get()
                .uri("http://localhost:8080/api/books/%s".formatted(isbn))
                .retrieve()
                .body(Book.class);
    }
}
