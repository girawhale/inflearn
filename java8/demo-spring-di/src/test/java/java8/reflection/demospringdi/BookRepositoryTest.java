package java8.reflection.demospringdi;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookRepositoryTest {

    @Autowired BookService bookService;

    @Test
    public void di(){
        Assert.assertNotNull(bookService); // null이 아니야?! 왜?!
        Assert.assertNotNull(bookService.bookRepository);
    }
}