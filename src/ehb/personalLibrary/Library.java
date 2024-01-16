package ehb.personalLibrary;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Library {
	private Set<Book> books;
	private Set<Person> patrons;	

	public Library() {
		super();
		this.books = new HashSet<Book>();
		this.patrons = new HashSet<Person>();
	}

	public Set<Book> getBooks() {
		return books;
	}

	public void setBooks(Set<Book> books) {
		this.books = books;
	}

	public Set<Person> getPatrons() {
		return patrons;
	}

	public void setPatrons(Set<Person> patrons) {
		this.patrons = patrons;
	}

	public boolean isBookAvailable(Book book)
    {
        if (books.contains(book) && book.isAvailable())
        	return true;
        else 
        	return false; 	
    }
	
	public boolean addBook(Book book) {
		return books.add(book);
	}
	
	public boolean removeBook(Book book) {
		return books.remove(book);
	}
	
	public boolean addPatron(Person person) {
		return patrons.add(person);
	}
	
	public boolean removePatron(Person person) {
		return patrons.remove(person);
	}
	
	public List<Book> returnAvailableBooks() {
		ArrayList<Book> availableBooks = new ArrayList<Book>();
		for (Book b: books)
			if (b.isAvailable())
				availableBooks.add(b);
		return availableBooks;		
	}
}
