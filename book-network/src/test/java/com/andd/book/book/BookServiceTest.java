package com.andd.book.book;

import com.andd.book.common.PageResponse;
import com.andd.book.file.FileStorageService;
import com.andd.book.history.BookTransactionHistoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

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
    @Mock
    Authentication connectedUser;

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
        when(bookMapper.toBook(bookRequest))
                .thenReturn(book);
        when(bookRepository.save(book))
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

        //Đoạn code dưới để verify xem là bookMapper nó gọi hàm .toBook(bookRequest) bao nhiêu lần
        //Ở đây đang mong muốn là bookMapper chỉ gọi .toBook(bookRequest) duy nhất 1 lần
        //Nếu trong bookService.save  mà gọi bookMapper.toBook(bookRequest) gọi nhiều lần thì test case sẽ FAILED
        verify(bookMapper, times(1))
                .toBook(bookRequest);
        verify(bookRepository, times(1))
                .save(book);
    }

    @Test
    public void should_return_all_books() {
        // Given
        Book book1 = Book.builder()
                .id(1)
                .title("Harry Potter tap 1")
                .authorName("Jk. Rowling")
                .isbn("harrypotter1")
                .synopsis("Day la tap dau tien cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book2 = Book.builder()
                .id(2)
                .title("Harry Potter tap 2")
                .authorName("Jk. Rowling")
                .isbn("harrypotter2")
                .synopsis("Day la tap thứ 2 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book3 = Book.builder()
                .id(3)
                .title("Harry Potter tap 3")
                .authorName("Jk. Rowling")
                .isbn("harrypotter3")
                .synopsis("Day la tap thứ 3 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book4 = Book.builder()
                .id(4)
                .title("Harry Potter tap 4")
                .authorName("Jk. Rowling")
                .isbn("harrypotter4")
                .synopsis("Day la tap thứ 4 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book5 = Book.builder()
                .id(5)
                .title("Harry Potter tap 5")
                .authorName("Jk. Rowling")
                .isbn("harrypotter5")
                .synopsis("Day la tap thứ 5 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book6 = Book.builder()
                .id(6)
                .title("Harry Potter tap 6")
                .authorName("Jk. Rowling")
                .isbn("harrypotter6")
                .synopsis("Day la tap thứ 6 cua loat truyen harry potter")
                .shareable(true)
                .build();
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        List<BookResponse> bookResponseList = null;

        Page<Book> pageBooks = new PageImpl<>(books);

        BookResponse bookResponse1 = new BookResponse();
        bookResponse1.setId(1);
        bookResponse1.setTitle("Harry Potter tap 1");
        bookResponse1.setAuthorName("Jk. Rowling");
        bookResponse1.setIsbn("harrypotter1");
        bookResponse1.setSynopsis("Day la tap dau tien cua loat truyen harry potter");
        bookResponse1.setShareable(true);

        BookResponse bookResponse2 = new BookResponse();
        bookResponse2.setId(2);
        bookResponse2.setTitle("Harry Potter tap 2");
        bookResponse2.setAuthorName("Jk. Rowling");
        bookResponse2.setIsbn("harrypotter2");
        bookResponse2.setSynopsis("Day la tap thứ 2 cua loat truyen harry potter");
        bookResponse2.setShareable(true);

        BookResponse bookResponse3 = new BookResponse();
        bookResponse3.setId(3);
        bookResponse3.setTitle("Harry Potter tap 3");
        bookResponse3.setAuthorName("Jk. Rowling");
        bookResponse3.setIsbn("harrypotter3");
        bookResponse3.setSynopsis("Day la tap thứ 3 cua loat truyen harry potter");
        bookResponse3.setShareable(true);

        BookResponse bookResponse4 = new BookResponse();
        bookResponse4.setId(4);
        bookResponse4.setTitle("Harry Potter tap 4");
        bookResponse4.setAuthorName("Jk. Rowling");
        bookResponse4.setIsbn("harrypotter4");
        bookResponse4.setSynopsis("Day la tap thứ 4 cua loat truyen harry potter");
        bookResponse4.setShareable(true);

        BookResponse bookResponse5 = new BookResponse();
        bookResponse5.setId(5);
        bookResponse5.setTitle("Harry Potter tap 5");
        bookResponse5.setAuthorName("Jk. Rowling");
        bookResponse5.setIsbn("harrypotter5");
        bookResponse5.setSynopsis("Day la tap thứ 5 cua loat truyen harry potter");
        bookResponse5.setShareable(true);

        BookResponse bookResponse6 = new BookResponse();
        bookResponse6.setId(6);
        bookResponse6.setTitle("Harry Potter tap 6");
        bookResponse6.setAuthorName("Jk. Rowling");
        bookResponse6.setIsbn("harrypotter6");
        bookResponse6.setSynopsis("Day la tap thứ 6 cua loat truyen harry potter");
        bookResponse6.setShareable(true);

        // Mock the calls
        when(connectedUser.getName()).thenReturn("hdfgd-3453-fgd345-dt34-34t3-3345ds");
        when(bookRepository.findAllDisplayableBooks(any(Pageable.class), any())).thenReturn(pageBooks);
        when(bookMapper.toBookResponse(book1)).thenReturn(bookResponse1);
        when(bookMapper.toBookResponse(book2)).thenReturn(bookResponse2);
        when(bookMapper.toBookResponse(book3)).thenReturn(bookResponse3);
        when(bookMapper.toBookResponse(book4)).thenReturn(bookResponse4);
        when(bookMapper.toBookResponse(book5)).thenReturn(bookResponse5);
        when(bookMapper.toBookResponse(book6)).thenReturn(bookResponse6);

        // When
        PageResponse<BookResponse> pageBookResponse = bookService.findAllBooks(0, 10, connectedUser);

        pageBookResponse.getSize(); //Số lượng phần tử trong mỗi trang.
        pageBookResponse.getNumber(); //Số thứ tự của trang hiện tại (bắt đầu từ 0).
        pageBookResponse.getContent(); //Danh sách các đối tượng chứa trong trang hiện tại.
        pageBookResponse.getTotalElements(); //Tổng số lượng phần tử trong toàn bộ kết quả.
        pageBookResponse.getTotalPages(); //Tổng số trang có thể có dựa trên size và totalElements.
        pageBookResponse.isFirst(); //Biến boolean cho biết liệu trang hiện tại có phải là trang đầu tiên hay không.
        pageBookResponse.isLast(); //Biến boolean cho biết liệu trang hiện tại có phải là trang cuối cùng hay không.

        // Then
        // Verify the results
        assertEquals(books.size(), pageBookResponse.getSize());
        assertNotNull(pageBookResponse);
        assertNotNull(pageBookResponse.getContent());
        assertEquals(pageBookResponse.getContent().size(), pageBooks.getSize());
        //assertEquals(5, pageBookResponse.getContent().size());

        assertEquals(bookResponse1, pageBookResponse.getContent().get(0));
        assertEquals(bookResponse2, pageBookResponse.getContent().get(1));
        assertEquals(bookResponse3, pageBookResponse.getContent().get(2));
        assertEquals(bookResponse4, pageBookResponse.getContent().get(3));
        assertEquals(bookResponse5, pageBookResponse.getContent().get(4));
        assertEquals(bookResponse6, pageBookResponse.getContent().get(5));

        assertEquals(0, pageBookResponse.getNumber());
        assertEquals(6, pageBookResponse.getTotalElements());
        assertEquals(1, pageBookResponse.getTotalPages());
        assertTrue(pageBookResponse.isFirst());
        assertTrue(pageBookResponse.isLast());
    }

    @Test
    public void should_return_all_books_2() {
        // Given
        Book book1 = Book.builder()
                .id(1)
                .title("Harry Potter tap 1")
                .authorName("Jk. Rowling")
                .isbn("harrypotter1")
                .synopsis("Day la tap dau tien cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book2 = Book.builder()
                .id(2)
                .title("Harry Potter tap 2")
                .authorName("Jk. Rowling")
                .isbn("harrypotter2")
                .synopsis("Day la tap thứ 2 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book3 = Book.builder()
                .id(3)
                .title("Harry Potter tap 3")
                .authorName("Jk. Rowling")
                .isbn("harrypotter3")
                .synopsis("Day la tap thứ 3 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book4 = Book.builder()
                .id(4)
                .title("Harry Potter tap 4")
                .authorName("Jk. Rowling")
                .isbn("harrypotter4")
                .synopsis("Day la tap thứ 4 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book5 = Book.builder()
                .id(5)
                .title("Harry Potter tap 5")
                .authorName("Jk. Rowling")
                .isbn("harrypotter5")
                .synopsis("Day la tap thứ 5 cua loat truyen harry potter")
                .shareable(true)
                .build();
        Book book6 = Book.builder()
                .id(6)
                .title("Harry Potter tap 6")
                .authorName("Jk. Rowling")
                .isbn("harrypotter6")
                .synopsis("Day la tap thứ 6 cua loat truyen harry potter")
                .shareable(true)
                .build();
        List<Book> books = new ArrayList<>();
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        books.add(book5);
        books.add(book6);

        Page<Book> pageBooks = new PageImpl<>(books);

        BookResponse bookResponse1 = new BookResponse();
        bookResponse1.setId(1);
        bookResponse1.setTitle("Harry Potter tap 1");
        bookResponse1.setAuthorName("Jk. Rowling");
        bookResponse1.setIsbn("harrypotter1");
        bookResponse1.setSynopsis("Day la tap dau tien cua loat truyen harry potter");
        bookResponse1.setShareable(true);

        BookResponse bookResponse2 = new BookResponse();
        bookResponse2.setId(2);
        bookResponse2.setTitle("Harry Potter tap 2");
        bookResponse2.setAuthorName("Jk. Rowling");
        bookResponse2.setIsbn("harrypotter2");
        bookResponse2.setSynopsis("Day la tap thứ 2 cua loat truyen harry potter");
        bookResponse2.setShareable(true);

        BookResponse bookResponse3 = new BookResponse();
        bookResponse3.setId(3);
        bookResponse3.setTitle("Harry Potter tap 3");
        bookResponse3.setAuthorName("Jk. Rowling");
        bookResponse3.setIsbn("harrypotter3");
        bookResponse3.setSynopsis("Day la tap thứ 3 cua loat truyen harry potter");
        bookResponse3.setShareable(true);

        BookResponse bookResponse4 = new BookResponse();
        bookResponse4.setId(4);
        bookResponse4.setTitle("Harry Potter tap 4");
        bookResponse4.setAuthorName("Jk. Rowling");
        bookResponse4.setIsbn("harrypotter4");
        bookResponse4.setSynopsis("Day la tap thứ 4 cua loat truyen harry potter");
        bookResponse4.setShareable(true);

        BookResponse bookResponse5 = new BookResponse();
        bookResponse5.setId(5);
        bookResponse5.setTitle("Harry Potter tap 5");
        bookResponse5.setAuthorName("Jk. Rowling");
        bookResponse5.setIsbn("harrypotter5");
        bookResponse5.setSynopsis("Day la tap thứ 5 cua loat truyen harry potter");
        bookResponse5.setShareable(true);

        BookResponse bookResponse6 = new BookResponse();
        bookResponse6.setId(6);
        bookResponse6.setTitle("Harry Potter tap 6");
        bookResponse6.setAuthorName("Jk. Rowling");
        bookResponse6.setIsbn("harrypotter6");
        bookResponse6.setSynopsis("Day la tap thứ 6 cua loat truyen harry potter");
        bookResponse6.setShareable(true);

        // Mock the calls
        when(connectedUser.getName()).thenReturn("hdfgd-3453-fgd345-dt34-34t3-3345ds");
        when(bookRepository.findAllDisplayableBooks(any(Pageable.class), any())).thenReturn(pageBooks);
        when(bookMapper.toBookResponse(book1)).thenReturn(bookResponse1);
        when(bookMapper.toBookResponse(book2)).thenReturn(bookResponse2);
        when(bookMapper.toBookResponse(book3)).thenReturn(bookResponse3);
        when(bookMapper.toBookResponse(book4)).thenReturn(bookResponse4);
        when(bookMapper.toBookResponse(book5)).thenReturn(bookResponse5);
        when(bookMapper.toBookResponse(book6)).thenReturn(bookResponse6);

        // When
        PageResponse<BookResponse> pageBookResponse = bookService.findAllBooks(0, 5, connectedUser);

        pageBookResponse.getSize(); //Số lượng phần tử trong mỗi trang.
        pageBookResponse.getNumber(); //Số thứ tự của trang hiện tại (bắt đầu từ 0).
        pageBookResponse.getContent(); //Danh sách các đối tượng chứa trong trang hiện tại.
        pageBookResponse.getTotalElements(); //Tổng số lượng phần tử trong toàn bộ kết quả.
        pageBookResponse.getTotalPages(); //Tổng số trang có thể có dựa trên size và totalElements.
        pageBookResponse.isFirst(); //Biến boolean cho biết liệu trang hiện tại có phải là trang đầu tiên hay không.
        pageBookResponse.isLast(); //Biến boolean cho biết liệu trang hiện tại có phải là trang cuối cùng hay không.

        // Then
        // Verify the results
        assertEquals(books.size(), pageBookResponse.getSize());
        assertNotNull(pageBookResponse);
        assertNotNull(pageBookResponse.getContent());
        assertEquals(pageBookResponse.getContent().size(), pageBooks.getSize());
        //assertEquals(5, pageBookResponse.getContent().size());

        assertEquals(bookResponse1, pageBookResponse.getContent().get(0));
        assertEquals(bookResponse2, pageBookResponse.getContent().get(1));
        assertEquals(bookResponse3, pageBookResponse.getContent().get(2));
        assertEquals(bookResponse4, pageBookResponse.getContent().get(3));
        assertEquals(bookResponse5, pageBookResponse.getContent().get(4));
        assertEquals(bookResponse6, pageBookResponse.getContent().get(5));

        assertEquals(0, pageBookResponse.getNumber());
        assertEquals(6, pageBookResponse.getTotalElements());
        assertEquals(1, pageBookResponse.getTotalPages());
        assertTrue(pageBookResponse.isFirst());
        assertTrue(pageBookResponse.isLast());
    }
}