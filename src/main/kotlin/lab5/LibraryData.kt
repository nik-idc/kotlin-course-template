enum class Genre {
	ADVENTURE,
	ACTION,
	FOR_KIDS,
	BIOGRAPHY,
	FANTASY
}


data class Book(val title: String, val authors: List<Author>, val genres: List<Genre>, val year: Int) {
	init {
		if (title.isEmpty())
			throw IllegalArgumentException("A book can't have an empty title!")
		if (genres.isEmpty())
			throw IllegalArgumentException("A book can't have no genre!")
	}
}


sealed class Status {
	object Available : Status()
	data class UsedBy(val user: User) : Status()
	object ComingSoon : Status()
	object Restoration : Status()
}


data class Author(val firstName: String, val lastName: String = "", val middleName: String = "") {
	init {
		if (firstName.isEmpty())
			throw IllegalArgumentException("An author must have a first name!")
	}
	
	override fun toString(): String {
		return "$firstName $middleName $lastName"
	}
}


data class User(val firstName: String, val lastName: String = "", val middleName: String = "") {
	init {
		if (firstName.isEmpty())
			throw IllegalArgumentException("An author must have a first name!")
	}
	
	override fun toString(): String {
		return "$firstName $middleName $lastName"
	}
}
