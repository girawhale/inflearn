package java8.proxy;

public class BookServiceProxy implements BookService {

    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }


    @Override
    public void rent(Book book) {
//        System.out.println("prev");
        bookService.rent(book);
//        System.out.println("post");
    }

    @Override
    public void returnBook(Book book) {
        bookService.returnBook(book);
    }
}
