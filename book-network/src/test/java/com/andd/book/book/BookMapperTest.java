package com.andd.book.book;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookMapperTest {

    private BookMapper bookMapper;

    @BeforeEach
    void setUp() {
        bookMapper = new BookMapper();
    }

    @Test
    public void shouldMapBookRequestToBook() {
        BookRequest bookRequest = new BookRequest(
                1,
                "Harry Potter tap 1",
                "Jk. Rowling",
                "harrypotter123",
                "Day la tap dau tien cua loat truyen harry potter",
                true);

        Book book = bookMapper.toBook(bookRequest);

        Assertions.assertEquals(bookRequest.id(), book.getId());
        Assertions.assertEquals(bookRequest.title(), book.getTitle());
        Assertions.assertEquals(bookRequest.authorName(), book.getAuthorName());
        Assertions.assertEquals(bookRequest.isbn(), book.getIsbn());
        Assertions.assertEquals(bookRequest.synopsis(), book.getSynopsis());
        Assertions.assertEquals(bookRequest.shareable(), book.isShareable());
    }

    @Test
    public void should_map_bookrequest_to_book_when_bookrequest_is_null() {
        var exp = assertThrows(NullPointerException.class, () -> bookMapper.toBook(null));
        Assertions.assertEquals("The book request should not be null", exp.getMessage());
    }

    @Test
    public void shouldMapBookToBookResponse() {
        Book book = Book.builder()
                .id(123)
                .title("Harry Potter tap 2")
                .authorName("JK. Rowling")
                .isbn("hptap2")
                .synopsis("Day la HP tap 2 cua loat truyen HP")
                .archived(true)
                .shareable(false)
                .build();
        BookResponse bookResponse = bookMapper.toBookResponse(book);

        Assertions.assertEquals(book.getId(), bookResponse.getId());
        Assertions.assertEquals(book.getTitle(), bookResponse.getTitle());
        Assertions.assertEquals(book.getAuthorName(), bookResponse.getAuthorName());
        Assertions.assertEquals(book.getIsbn(), bookResponse.getIsbn());
        Assertions.assertEquals(book.getSynopsis(), bookResponse.getSynopsis());
        Assertions.assertEquals(book.isArchived(), bookResponse.isArchived());
        Assertions.assertEquals(book.isShareable(), bookResponse.isShareable());
        Assertions.assertEquals(book.getRate(), bookResponse.getRate());
        Assertions.assertEquals(0.0, book.getRate());
    }
}