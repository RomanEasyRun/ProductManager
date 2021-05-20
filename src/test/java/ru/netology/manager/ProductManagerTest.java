package ru.netology.manager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.repository.Repository;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

public class ProductManagerTest {
    private Repository repository = new Repository();
    private ProductManager manager = new ProductManager(repository);
    private Book first = new Book(1, "Book1", 300, "Author1");
    private Book second = new Book(2, "Book2", 500, "Author2");
    private Smartphone third = new Smartphone(3, "Phone1", 15000, "Brand1");
    private Smartphone fourth = new Smartphone(4, "Phone2", 20000, "Brand2");
    private Product fifth = new Product(5, "Apple", 40);

    @BeforeEach
    public void setUp() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.save(fifth);
    }

    @Test
    public void GetAll() {
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth, fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchByBooksName() {
        Product[] actual = manager.searchBy("Book1");
        Product[] expected = new Product[]{first};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchByBooksAuthor() {
        Product[] actual = manager.searchBy("Author2");
        Product[] expected = new Product[]{second};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchBySmartphonesName() {
        Product[] actual = manager.searchBy("Phone1");
        Product[] expected = new Product[]{third};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchBySmartphonesBrand() {
        Product[] actual = manager.searchBy("Brand2");
        Product[] expected = new Product[]{fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void SearchByProducts() {
        Product[] actual = manager.searchBy("Apple");
        Product[] expected = new Product[]{fifth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void NoSearchName() {
        Product[] actual = manager.searchBy("Something");
        Product[] expected = new Product[]{};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void AddNewProduct() {
        Product sixth = new Product(6, "Radio", 1000);
        manager.add(sixth);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth, fifth, sixth};
        assertArrayEquals(expected, actual);
    }

    @Test
    public void RemoveById() {
        repository.removeById(5);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, second, third, fourth};
        assertArrayEquals(expected, actual);
    }
}