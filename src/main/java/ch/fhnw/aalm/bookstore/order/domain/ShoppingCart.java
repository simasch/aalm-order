package ch.fhnw.aalm.bookstore.order.domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class ShoppingCart {

    private final Set<ShoppingCartItem> items = new HashSet<>();

    public void addBook(Book book) {
        var item = items.stream().filter(i -> i.book == book).findAny().orElseGet(() -> new ShoppingCartItem(book));
        item.setQuantity(item.getQuantity() + 1);
        items.add(item);
    }

    public void removeBook(Book book) {
        items.stream().filter(i -> i.book == book).findAny().ifPresent(items::remove);
    }

    public Set<ShoppingCartItem> getItems() {
        return items;
    }

    public static class ShoppingCartItem {
        private Book book;
        private int quantity;

        public ShoppingCartItem(Book book) {
            this.book = book;
        }

        public Book getBook() {
            return book;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ShoppingCartItem that = (ShoppingCartItem) o;
            return Objects.equals(book, that.book);
        }

        @Override
        public int hashCode() {
            return Objects.hash(book);
        }
    }
}
