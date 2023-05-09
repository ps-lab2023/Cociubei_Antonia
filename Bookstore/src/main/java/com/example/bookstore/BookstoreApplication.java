package com.example.bookstore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//spring.main.web-application-type=none

@SpringBootApplication
public class BookstoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookstoreApplication.class, args);
    }
    /*

    @Bean
    CommandLineRunner init(BookRepository bookRepository, UserRepository userRepository, CartRepository cartRepository, OrderHistoryRepository orderHistoryRepository) {
        return args -> {

            User user=new User();
            user.setName("User1");
            user.setPassword("user1");
            user.setRol(Rol.ADMIN);

            userRepository.save(user);


            User user2=new User();
            user2.setName("User2");
            user2.setPassword("user2");
            user2.setRol(Rol.CLIENT);

            userRepository.save(user2);

            Book book1=new Book();
            book1.setAuthor("Liviu Rebreanu");
            book1.setCategory(Category.FICTION);
            book1.setPrice(23);
            book1.setQuantity(3);
            book1.setPublisher("Editura Dunarea");
            book1.setTitle("Ion");            

            bookRepository.save(book1);
           // bookRepository.deleteById(1L);

            Book book2=new Book();
            book2.setAuthor("Leo Viktor");
            book2.setCategory(Category.BIOGRAPHY);
            book2.setPrice(30);
            book2.setQuantity(5);
            book2.setPublisher("Editura Rao");
            book2.setTitle("La cules de mere");

            bookRepository.save(book2);


            Rol adminRole = Rol.ADMIN;
            double newPrice= 24.99;
            BookServiceImp bookServiceImp = new BookServiceImp(bookRepository);
            bookServiceImp.updateBook(book2.getId(),newPrice, adminRole);

            CartServiceImp cartServiceImp =new CartServiceImp(cartRepository,userRepository,bookServiceImp);
            AddCartDto addCartDto=new AddCartDto(2L,book1.getId());


            OrderHistory order = new OrderHistory();
            order.setClientId(2L);
            order.setTotal(2);
            List<Book> books = new ArrayList<>();            
            books.add(book1);
            books.add(book2);
            order.setBook(books);

           // OrderHistory savedOrder = orderHistoryRepository.save(order);
            orderHistoryRepository.save(order);

        };
    }*/
}
