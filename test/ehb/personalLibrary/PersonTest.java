package test.ehb.personalLibrary;

import static org.junit.Assert.*;

import ehb.personalLibrary.Book;
import ehb.personalLibrary.Library;
import ehb.personalLibrary.Person;
import org.junit.Before;
import org.junit.Test;

public class PersonTest {
	
	private Library library;
	private Book book1;
	private Book book2;
	private Book book3;
	private Book book4;
	private Book book5;
	private Person person;

	// En unité
	@Before
	public void setUp() throws Exception {
		library = new Library();
		book1 = new Book("The Selfish Gene", "Richard Dawkins");
		person = new Person("John Doe");
		
		library.addBook(book1);
		library.addPatron(person);
	}

	// Ce que t'auras besoin pour le test
	public void addMoreBookforCertainTests() {
		book2 = new Book("test", "test");
		book3 = new Book("blah", "blah");
		book4 = new Book("testbook","testbook");
		book5 = new Book("testbook2", "testbook2");
		library.addBook(book2);
		library.addBook(book3);
		library.addBook(book4);
		library.addBook(book5);
	}

	// Book available ?
	@Test
	public void testPatronBorrowBookRosy() {
		assertEquals("null result",book1, person.checkoutBook(book1));	
		assertFalse("book still available", book1.isAvailable());
	}

	// Two same book who are borrowed
	@Test
	public void testPatronBorrowBookTwiceTheSame() {
		person.checkoutBook(book1);
		assertEquals(null, person.checkoutBook(book1));
		
	}
	
	@Test
	public void testPatronBorrowBookNotAddedBook() {
		Book notAddedBook = new Book("Homo Deus: A Brief History of Tomorrow", "Yuval Noah Harari");
		assertEquals(true, person.checkoutBook(notAddedBook));

	}
	
	@Test
	public void testDefaultMaxBooksRosy() {
		addMoreBookforCertainTests();
		assertEquals("problem with default value", 3, person.getMaxBooks());
		assertEquals("problem adding first book", book1, person.checkoutBook(book1)); 
		assertEquals("problem adding second book", book2, person.checkoutBook(book2));
		assertEquals("problem adding third book", book3, person.checkoutBook(book3));	
	}
	
	@Test
	public void testDefaultMaxBooksNonRosy() {
		addMoreBookforCertainTests();
		assertEquals("problem with default value", 3, person.getMaxBooks());
		assertEquals("problem adding first book", book1, person.checkoutBook(book1)); 
		assertEquals("problem adding second book", book2, person.checkoutBook(book2));
		assertEquals("problem adding third book", book3, person.checkoutBook(book3));
		assertEquals("adding fourth book succeeded!!", null, person.checkoutBook(book4));
	}
	
	@Test
	public void testCustomMaxBooksRosy() {
		addMoreBookforCertainTests();
		person.setMaxBooks(4);
		
		assertEquals("problem with setting custom value", 4, person.getMaxBooks());
		assertEquals("problem adding first book", book1, person.checkoutBook(book1)); 
		assertEquals("problem adding second book", book2, person.checkoutBook(book2));
		assertEquals("problem adding third book", book3, person.checkoutBook(book3));	
		assertEquals("problem adding fourth book", book4, person.checkoutBook(book4));	

	}
	
	@Test
	public void testCustomMaxBooksNonRosy() {	
		addMoreBookforCertainTests();
		assertEquals("problem with default value", 3, person.getMaxBooks());
		assertEquals("problem adding first book", book1, person.checkoutBook(book1)); 
		assertEquals("problem adding second book", book2, person.checkoutBook(book2));
		assertEquals("problem adding third book", book3, person.checkoutBook(book3));
		assertEquals("problem adding fourth book", book3, person.checkoutBook(book4));
		assertEquals("adding fifth book succeeded!!", null, person.checkoutBook(book5));
	}
	
	@Test
	public void testPatronPrint() {
		person.checkoutBook(book1);
		assertEquals("John Doe (1 out of 3 books)", person.toString());	
	}
	
	@Test
	public void testListBooksPatron() {
		addMoreBookforCertainTests();
		person.checkoutBook(book1);
		person.checkoutBook(book2);
		assertEquals(2,person.getBooks().size());
		assertTrue(person.getBooks().contains(book1));
		assertTrue(person.getBooks().contains(book2));		
	}
	
	@Test
	public void testAddingDuplicateBookToLibrary() {
		book2 = new Book("test", "test");
		assertFalse(library.getBooks().contains(book2));
		library.addBook(book2);
		assertFalse(library.addBook(book2));
	}
	
	@Test
	public void testAddingBookToLibrary() {
		book2 = new Book("test", "test");
		assertFalse(library.getBooks().contains(book2));
		assertTrue(library.addBook(book2));
		assertTrue(library.getBooks().contains(book2));
	}
	
	@Test
	public void testAddingPatron() {
		Person personX = new Person("John Doe");
		assertFalse(library.getPatrons().contains(personX));
		assertTrue(library.addPatron(personX));
		assertTrue(library.getPatrons().contains(personX));
	}
	
	@Test
	public void testAddingPatronTwice() {
		Person personX = new Person("John Doe");
		assertFalse(library.getPatrons().contains(personX));
		assertTrue(library.addPatron(personX));
		assertFalse(library.addPatron(personX));
		
	}
	
	@Test
	public void testRemoveBook() {
		assertTrue(library.getBooks().contains(book1));
		assertTrue(library.removeBook(book1));
		assertFalse(library.getBooks().contains(book1));
	}
	
	@Test
	public void testRemoveNotAddedBook() {
		book2 = new Book("test", "test");
		assertFalse(library.getBooks().contains(book2));
		int nrOfBooks = library.getBooks().size();
		assertFalse(library.removeBook(book2));
		assertEquals(nrOfBooks, library.getBooks().size());
		
	}
	
	@Test
	public void testRemovePatron() {
		assertTrue(library.getPatrons().contains(person));
		assertTrue(library.removePatron(person));
		assertFalse(library.getPatrons().contains(person));
	}
	
	@Test
	public void testRemoveNotAddedPatron() {
		Person person2 = new Person("test");
		assertFalse(library.getPatrons().contains(person2));
		int nrOfPatrons = library.getPatrons().size();
		assertFalse(library.removePatron(person2));
		assertEquals(nrOfPatrons, library.getPatrons().size());	
	}
	
	@Test
	public void testAvailabilityWhenPatronBorrowsBook() {
		assertTrue(library.isBookAvailable(book1));
		person.checkoutBook(book1);
		assertFalse(library.isBookAvailable(book1));
		
	}
	
	@Test
	public void testAvailabilityWhenPatronBorrowsAndReturnsBook() {
		assertTrue(library.isBookAvailable(book1));
		person.checkoutBook(book1);
		assertFalse(library.isBookAvailable(book1));
		person.checkinBook(book1);
		assertTrue(library.isBookAvailable(book1));
	}
	
	@Test
	public void testAvailableBooksList() {
		book2 = new Book("test","test");
		int nrOfBooksAvailable = library.returnAvailableBooks().size();
		library.addBook(book2);
		assertEquals(nrOfBooksAvailable+1, library.returnAvailableBooks().size());
	
	}
}
