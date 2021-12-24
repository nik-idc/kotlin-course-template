import java.util.*

/**
 * Enum class of types of alignments
 */
enum class Alignment {
	LEFT,
	RIGHT,
	CENTER,
	JUSTIFIED
}

class TextAligner(private val text: String) {
	/**
	 * Aligns string [str] to the right and makes the
	 * result [lineLength] characters long
	 */
	private fun alignRight(str: String, lineLength: Int): String {
		return if (str.length < lineLength)
			" ".padEnd(lineLength - str.length) + str
		else
			str
	}
	
	/**
	 * Aligns string [str] to the center and makes the
	 * result [lineLength] characters long
	 */
	private fun alignCenter(str: String, lineLength: Int): String {
		var result = ""
		
		if (str.length < lineLength) {
			val spaces = "".padEnd((lineLength - str.length) / 2, ' ')
			result += spaces + str + spaces
			if ((lineLength - str.length) % 2 != 0)
				result = " $result"
		} else
			result = str
		return result
	}
	
	/**
	 * 'Justifies' string [str] and makes the
	 * result [lineLength] characters long
	 */
	private fun alignJustified(str: String, lineLength: Int): String {
		val result = StringBuilder(str)
		if (str.length < lineLength) {
			
			val spacesIndices = mutableListOf(0)
			var curIndex = 0
			while (curIndex != -1) {
				curIndex = result.indexOf(' ', spacesIndices.last() + 1)
				spacesIndices += curIndex
			}
			if (spacesIndices.size > 1) {
				spacesIndices.removeAt(0) // Removing first because index 0 is no longer relevant
				spacesIndices.removeAt(spacesIndices.lastIndex) // Deleting the last element which is always -1
				
				val spacesCount = spacesIndices.size
				val extraSpaces = lineLength - str.length
				val spacesPerEmpty: Int = extraSpaces / spacesCount
				val spaces = "".padEnd(spacesPerEmpty)
				
				for (i in spacesIndices.indices.reversed()) {
					result.insert(spacesIndices[i], spaces)
					spacesIndices[i] += (spacesPerEmpty + 1) * i
				}
				
				while (result.length < lineLength) {
					result.insert(spacesIndices.first(), ' ')
					spacesIndices.removeAt(0)
				}
			}
		}
		return result.toString()
	}
	
	/**
	 * Aligns string [str], according to alignment
	 * parameter [alignment] to length of [lineLength] characters
	 */
	private fun alignStr(str: String, lineLength: Int, alignment: Alignment): String {
		return when (alignment) {
			(Alignment.RIGHT) -> alignRight(str, lineLength)
			(Alignment.CENTER) -> alignCenter(str, lineLength)
			(Alignment.JUSTIFIED) -> alignJustified(str, lineLength)
			else -> str
		}
	}
	
	/**
	 * Aligns each string of [text], according to alignment
	 * parameter [alignment] to length of [lineLength] characters
	 */
	fun alignText(lineLength: Int, alignment: Alignment): String {
		if (lineLength <= 0)
			throw InputMismatchException("No such thing as a line with length less than or equal to zero!")
		
		var finStr = "" // String that we will return at the end of the function
		var tempStr = "" // Buffer string to create strings of length 'lineLength'
		
		var wordStart = 0 // Starting index of a word in a string
		var isWord = false // Indicates whether the iterator is inside a word
		var hasSpaces = false // Indicates whether the string is just one big word or a collection of words
		var spacesCounter = false // Keeps track of if we have too many spaces in between sentences
		
		// Going through the initial text in this loop
		for (i in 0 until text.length - 1) {
			
			// If character is a new line symbol
			if (text[i] == '\n' && tempStr.isNotEmpty()) {
				if (text[i + 1] != '\n') {
					// If the new line symbol just marks the end of a string, replace it with a space
					tempStr += ' '
					spacesCounter = true
				} else {
					// If the next character is also a new line symbol, add an empty new line to the resulting string
					tempStr = alignStr(tempStr, lineLength, alignment)
					finStr += if (finStr.isNotEmpty())
						'\n' + tempStr + '\n'
					else
						tempStr + '\n'
					tempStr = ""
					hasSpaces = false
					spacesCounter = false
				}
			} else if (text[i] == ' ' && tempStr.isNotEmpty() && !spacesCounter) {
				// If character is a space and the buffer string is not empty, add it to the buffer string
				tempStr += text[i]
				spacesCounter = true
			} else if (text[i].isLetterOrDigitOrPM()) {
				// If character is a letter or a punctuation mark
				// add it to the buffer string
				tempStr += text[i]
				spacesCounter = false
			}
			
			if (tempStr.isNotEmpty()) {
				// If the buffer string is not empty, process it
				if (tempStr.last() == ' ' || tempStr.last() == '\n') {
					// If current character is a space
					// then last word successfully filled in
					// our string
					isWord = false
					hasSpaces = true
				} else if (tempStr.last().isLetterOrDigit() && !isWord) {
					// Remember the starting index of the word,
					// so that we can discard it from the string if need be
					isWord = true
					wordStart = tempStr.lastIndex
				}
				
				if (tempStr.length == lineLength || i == text.length - 2) {
					// If the buffer string is full, or we've reached the end of
					// input text, decide how to deal with the buffer string
					val len = tempStr.length // Length of the string (creating a value for readability reasons)
					
					if (finStr.isNotEmpty())
						finStr += '\n' // Adding a new line symbol if the final string is not empty
					
					var res = ""
					if (text[i + 1].isLetterOrDigitOrPM() && tempStr.last().isLetterOrDigit() && hasSpaces) {
						// If the last word in the string does not fit, discard it
						res += tempStr.dropLast(len - wordStart + 1) // Add the 'good' part of the string
						tempStr =
								tempStr.substring(wordStart, len) // Leave the discarded part in the buffer string for later
					} else {
						// Otherwise, we're clear to add the string to the final string
						if (tempStr.last() == ' ')
							tempStr = tempStr.dropLast(1) // Deleting the last space if the string ends with one
						res += tempStr // Adding the result to the final string
						tempStr = "" // Clearing the buffer string
					}
					
					res = alignStr(res, lineLength, alignment)
					finStr += res
					// Reassigning to false since we're about to begin making a new string
					hasSpaces = false
					// Reset number of spaces to zero
					spacesCounter = false
				}
			}
		}
		return finStr
	}
}