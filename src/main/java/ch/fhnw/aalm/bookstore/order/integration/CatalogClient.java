package ch.fhnw.aalm.bookstore.order.integration;

import ch.fhnw.aalm.bookstore.order.domain.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class CatalogClient {

    private static final Logger log = LoggerFactory.getLogger(CatalogClient.class);
    private final RestClient restClient;
    private final String catalogUrl;

    public CatalogClient(RestClient.Builder restClientBuilder, @Value("${catalog.url}") String catalogUrl) {
        this.restClient = restClientBuilder.build();
        this.catalogUrl = catalogUrl;
    }

    public Book[] findBooks(String keywords) {
        log.info("findBooks({})", keywords);
        return restClient
                .get()
                .uri("%s/api/books?keywords=%s".formatted(catalogUrl, keywords))
                .retrieve()
                .body(Book[].class);
    }

    public Book getBook(String isbn) {
        return restClient
                .get()
                .uri("%s/api/books/%s".formatted(catalogUrl, isbn))
                .retrieve()
                .body(Book.class);
    }
}
