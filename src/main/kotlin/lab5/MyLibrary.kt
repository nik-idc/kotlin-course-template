package lab5

const val maxBooksPerUser = 3

class MyLibrary(
	initialBooks: Map<Book, Status>,
	initialUsers: Set<User>
) : LibraryService {
	private val books = mutableMapOf<Book, Status>()
	private val users = mutableSetOf<User>()
	
	init {
		books.plusAssign(initialBooks)
		users.plusAssign(initialUsers)
	}
	
	override fun findBooks(
		substring: String?,
		author: Author?,
		year: Int?,
		genre: Genre?
	): List<Book> {
		return books.keys.filter { book ->
			(substring == null || book.title.contains(substring, ignoreCase = true)
					&& (author == null || book.authors.contains(author))
					&& (year == null || book.year == year)
					&& (genre == null || book.genres.contains(genre)))
		}.toList()
	}
	
	override fun getAllBooks(): List<Book> {
		return books.keys.toList()
	}
	
	override fun getAllAvailableBooks(): List<Book> {
		return books.filter { book -> (book.value == Status.Available) }.keys.toList()
	}
	
	override fun getBookStatus(book: Book): Status {
		return books[book] ?: throw IllegalArgumentException("Couldn't find book ${book.title} by ${book.authors}")
	}
	
	override fun getAllBookStatuses(): Map<Book, Status> {
		return books
	}
	
	override fun setBookStatus(book: Book, status: Status) {
		books[book] ?: throw IllegalArgumentException("Couldn't find book ${book.title} by ${book.authors}")
		books[book] = status
	}
	
	override fun addBook(book: Book, status: Status) {
		books[book] = status
	}
	
	override fun registerUser(user: User) {
		if(!users.add(user))
			throw IllegalArgumentException("User $user is already registered!")
	}
	
	override fun registerUser(firstName: String, lastName: String, middleName: String) {
		registerUser(User(firstName, middleName, lastName))
	}
	
	override fun unregisterUser(user: User) {
		if(!users.remove(user))
			throw IllegalArgumentException("User $user is already registered!")
	}
	
	override fun unregisterUser(firstName: String, lastName: String, middleName: String) {
		unregisterUser(User(firstName, lastName, middleName))
	}
	
	override fun takeBook(user: User, book: Book) {
		if (users.contains(user) && books[book] != null) {
			if (books.filter { bookIter -> (bookIter.value == Status.UsedBy(user)) }.size < maxBooksPerUser
				&& books[book] == Status.Available
			)
				books[book] = Status.UsedBy(user)
		} else
			throw IllegalArgumentException("Couldn't give book ${book.title} to user $user")
	}
	
	override fun returnBook(book: Book) {
		books[book] = Status.Available
	}
}
