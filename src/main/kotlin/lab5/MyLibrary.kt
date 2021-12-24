const val maxBooksPerUser = 3

class MyLibrary(
	private val books: MutableMap<Book, Status>,
	private val users: MutableList<User>
) : LibraryService {
	
	
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
		users.add(user)
	}
	
	override fun registerUser(firstName: String, middleName: String, lastName: String) {
		users.add(User(firstName, middleName, lastName))
	}
	
	override fun unregisterUser(user: User) {
		users.remove(user)
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