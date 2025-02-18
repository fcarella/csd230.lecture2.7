package csd230.lecture27;

import com.github.javafaker.Faker;
import csd230.lecture27.entities.Cart;
import csd230.lecture27.respositories.BookRepository;
import csd230.lecture27.respositories.CartItemRepository;
import csd230.lecture27.respositories.CartRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
	CartItemRepository cartItemRepository;

	@Autowired
	CartRepository cartRepository;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	@Bean
	public CommandLineRunner demo(BookRepository repository) {
		return (args) -> {
			Cart cart=new Cart();
			cartRepository.save(cart);

			Faker faker = new Faker();
			com.github.javafaker.Book fakeBook = faker.book();
			com.github.javafaker.Number number = faker.number();
			com.github.javafaker.Code code=faker.code();

			// save a few customers
			for(int i=0;i<5;i++) {
				String title=fakeBook.title();
				double price=number.randomDouble(2, 10, 100);
				int copies=number.numberBetween(5, 20);
				int quantity=number.numberBetween(1, 50);
				String author=fakeBook.author();
				String isbn=code.isbn10();
				String description="Book: "+title;
				csd230.lecture27.entities.Book book = new csd230.lecture27.entities.Book(
						price,
						quantity,
						description,
						title,
						copies,
						author,
						isbn
				);
				cart.addItem(book);
				repository.save(book);
				cartRepository.save(cart);
			}


			// fetch all books
			log.info("BookEntitys found with findAll():");
			log.info("-------------------------------");
			repository.findAll().forEach(book -> {
				log.info(book.toString());
			});
			log.info("");

			// fetch an individual customer by ID
			csd230.lecture27.entities.Book book = repository.findById(1L);
			log.info("BookEntity found with findById(1L):");
			log.info("--------------------------------");
			log.info(book.toString());
			log.info("");


			// fetch book by ISBN_10
			log.info("BookEntity found with findByIsbn('"+book.getIsbn()+"'):");
			log.info("--------------------------------------------");
			repository.findByIsbn( book.getIsbn()).forEach(isbn -> {
				log.info(isbn.toString());
			});

		};
	}
}
