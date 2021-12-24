import org.junit.Assert
import org.junit.Test

class LibraryTest {
	private val john = User("John", "", "Smith")
	private val cathy = User("Cathy", "", "Jackson")
	private val david = User("David", "Michael", "Philips")
	private val dan = User("Daniel", "George", "Williams")
	
	private val initialUsers = mutableListOf(john, cathy, david, dan)
	
	private val initialBooks = mutableMapOf(
		Book(
			"Harry Potter & The Philosopher's Stone",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 1997
		) to Status.Available,
		Book(
			"Harry Potter & The Chamber of Secrets",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 1998
		) to Status.UsedBy(cathy),
		Book(
			"Harry Potter & The Prisoner of Azkaban",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 1999
		) to Status.Available,
		Book(
			"Harry Potter & The Goblet of Fire",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 2000
		) to Status.Restoration,
		Book(
			"Harry Potter & The Order of the Phoenix",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 2003
		) to Status.ComingSoon,
		Book(
			"Harry Potter & The Half-Blood Prince",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 2005
		) to Status.UsedBy(john),
		Book(
			"Harry Potter & The Deathly Hallows",
			listOf(Author("Joanne", "Kathleen ", "Rowling")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 2007
		) to Status.Available,
		Book(
			"The Lord of the Rings: The Fellowship of the Ring",
			listOf(Author("John", "Ronald Reuel", "Tolkien")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1954
		) to Status.UsedBy(david),
		Book(
			"The Lord of the Rings: The Two Towers",
			listOf(Author("John", "Ronald Reuel", "Tolkien")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1954
		) to Status.Available,
		Book(
			"The Lord of the Rings: The Return of the King",
			listOf(Author("John", "Ronald Reuel", "Tolkien")),
			listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1955
		) to Status.UsedBy(dan)
	)
	
	private val library = MyLibrary(initialBooks, initialUsers)
	
	private val bookInLibrary = Book(
		"Harry Potter & The Philosopher's Stone",
		listOf(Author("Joanne", "Kathleen ", "Rowling")),
		listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 1997
	)
	
	private val bookNotInLibrary = Book(
		"Good book",
		listOf(Author("Some", "random ", "person")),
		listOf(Genre.BIOGRAPHY), 2015
	)
	
	@Test
	fun testFindBooks() {
		val lotrBooksToExpect = listOf(
			Book(
				"The Lord of the Rings: The Fellowship of the Ring",
				listOf(Author("John", "Ronald Reuel", "Tolkien")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1954
			),
			Book(
				"The Lord of the Rings: The Two Towers",
				listOf(Author("John", "Ronald Reuel", "Tolkien")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1954
			),
			Book(
				"The Lord of the Rings: The Return of the King",
				listOf(Author("John", "Ronald Reuel", "Tolkien")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1955
			)
		)
		
		val lotrBooksFound = library.findBooks("The Lord of the Rings")
		Assert.assertEquals(lotrBooksToExpect, lotrBooksFound)
	}
	
	@Test
	fun testGetAllAvailableBooks() {
		val availableBooksToExpect = listOf(
			Book(
				"Harry Potter & The Philosopher's Stone",
				listOf(Author("Joanne", "Kathleen ", "Rowling")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 1997
			),
			Book(
				"Harry Potter & The Prisoner of Azkaban",
				listOf(Author("Joanne", "Kathleen ", "Rowling")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 1999
			),
			Book(
				"Harry Potter & The Deathly Hallows",
				listOf(Author("Joanne", "Kathleen ", "Rowling")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FOR_KIDS, Genre.FANTASY), 2007
			),
			Book(
				"The Lord of the Rings: The Two Towers",
				listOf(Author("John", "Ronald Reuel", "Tolkien")),
				listOf(Genre.ACTION, Genre.ADVENTURE, Genre.FANTASY), 1954
			)
		)
		
		val availableBooksFound = library.getAllAvailableBooks()
		Assert.assertEquals(availableBooksToExpect, availableBooksFound)
	}
	
	@Test
	fun testGetBookStatus() {
		// Testing when book is in the library
		val statusFound = library.getBookStatus(bookInLibrary)
		Assert.assertEquals(Status.Available, statusFound)
		
		// Testing when book is NOT in the library
		val bookNotInLibrary = Book(
			"Good book",
			listOf(Author("Some", "random ", "person")),
			listOf(Genre.BIOGRAPHY), 2015
		)
		val caught = try {
			library.getBookStatus(bookNotInLibrary)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testSetBookStatus() {
		// Testing when book is in the library
		library.setBookStatus(bookInLibrary, Status.ComingSoon)
		Assert.assertEquals(Status.ComingSoon, library.getBookStatus(bookInLibrary))
		
		// Testing when book is NOT in the library
		val caught = try {
			library.setBookStatus(bookNotInLibrary, Status.ComingSoon)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testTakeBook() {
		// Testing when book is in the library
		library.takeBook(john, bookInLibrary)
		Assert.assertEquals(library.getBookStatus(bookInLibrary), Status.UsedBy(john))
		
		// Testing when book is NOT in the library
		val caught = try {
			library.takeBook(john, bookNotInLibrary)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
}