package guru.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{

    private final AuthorRepository authorRepository;    
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;



    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository,
            PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }



    @Override
    public void run(String... args) throws Exception {
        
        Publisher pub1 = new Publisher("Editorial 1", "Madrid");
        publisherRepository.save(pub1);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driver Design", "12121212");
        
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);
        ddd.setPublisher(pub1);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        pub1.getBooks().add(ddd);
        publisherRepository.save(pub1);
        
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Dev without EJB", "99887766");
        
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);
        noEJB.setPublisher(pub1);
        pub1.getBooks().add(noEJB);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(pub1);
                
        System.out.println("Number of Books:" + bookRepository.count());
        System.out.println("Number of Authors:" + authorRepository.count());
        System.out.println("Number of Publishers:" + publisherRepository.count());

        for (Book book:bookRepository.findAll())
            System.out.println("*B* " + book.getTitle());

        for (Author author:authorRepository.findAll())
            System.out.println("*A* " + author.getFirstName());

        for (Publisher publisher:publisherRepository.findAll())
            System.out.println("*P* " + publisher.getName());

               
    }

   
}
