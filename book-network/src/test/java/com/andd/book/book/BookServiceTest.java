package com.andd.book.book;

import com.andd.book.file.FileStorageService;
import com.andd.book.history.BookTransactionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class BookServiceTest {

    //which service we want to test
    // Bên trong BookService class có inject 4 objects là BookMapper bookMapper và BookRepository bookRepository
    // BookTransactionHistoryRepository transactionHistoryRepository và FileStorageService fileStorageService
    @InjectMocks
    private BookService bookService;

    // Vậy bây giờ ta sẽ định nghĩa 4 objects này bên dưới
    @Mock
    BookMapper bookMapper;
    @Mock
    BookRepository bookRepository;
    @Mock
    BookTransactionHistoryRepository transactionHistoryRepository;
    @Mock
    FileStorageService fileStorageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_successfully_save_a_student() {
        //Given
        BookRequest bookRequest = new BookRequest(
                null,
                "Harry Potter tap 1",
                "Jk. Rowling",
                "harrypotter123",
                "Day la tap dau tien cua loat truyen harry potter",
                true
        );
        Book book = Book.builder()
                .id(null)
                .title("Harry Potter tap 1")
                .authorName("Jk. Rowling")
                .isbn("harrypotter123")
                .synopsis("Day la tap dau tien cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book savedBook = Book.builder()
                .id(100)
                .title("Harry Potter tap 1")
                .authorName("Jk. Rowling")
                .isbn("harrypotter123")
                .synopsis("Day la tap dau tien cua loat truyen harry potter")
                .shareable(true)
                .build();
        Authentication connectedUser = null;

        // Mock the calls
        Mockito.when(bookMapper.toBook(bookRequest))
                .thenReturn(book);
        Mockito.when(bookRepository.save(book))
                .thenReturn(savedBook);

        //When
        Integer savedId = bookService.save(bookRequest, connectedUser);

        //Then
        assertEquals(savedBook.getId(), savedId);
        //assertEquals(bookRequest.id(), book.getId());
        //assertEquals(bookRequest.title(), book.getTitle());
        //assertEquals(bookRequest.authorName(), book.getAuthorName());
        //assertEquals(bookRequest.isbn(), book.getIsbn());
        //assertEquals(bookRequest.synopsis(), book.getSynopsis());
        //assertEquals(bookRequest.shareable(), book.isShareable());
    }
}