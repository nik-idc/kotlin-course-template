interface LibraryService {
	fun findBooks(
		substring: String? = null,
		author: Author? = null,
		year: Int? = null,
		genre: Genre? = null
	): List<Book>
	
	fun getAllBooks(): List<Book>
	fun getAllAvailableBooks(): List<Book>
	
	fun getBookStatus(book: Book): Status
	fun getAllBookStatuses(): Map<Book, Status>
	
	fun setBookStatus(book: Book, status: Status)
	
	fun addBook(book: Book, status: Status = Status.Available)
	
	fun registerUser(user: User)
	fun registerUser(firstName: String, middleName: String, lastName: String)
	fun unregisterUser(user: User)
	
	fun takeBook(user: User, book: Book)
	fun returnBook(book: Book)
}