/**
 * Extension function to the 'Char' class
 * that checks whether the character is a
 * letter, digit or a punctuation mark
 */
fun Char.isLetterOrDigitOrPM(): Boolean {
	return this.isLetterOrDigit() || "!?,.:;'".contains(this)
}

/**
 * Extension function to the 'Char' class
 * that checks whether the character is a
 * math operator
 */
fun Char.isOperator(): Boolean {
	return ("+-*/^".contains(this))
}

/**
 * Extension function to the 'String' class
 * that checks whether the string is a
 * math operator
 */
fun String.isOperator(): Boolean {
	return ("+-*/^".contains(this))
}

/**
 * Extension function to the 'String' class
 * that checks whether the string is a
 * number
 */
fun String.isNumber(): Boolean {
	//return !this.any { !it.isDigit() } && (this.count {it == '.'} <= 1)
	return this.all { it.isDigit() || it == '.' } && (this.count { it == '.' } <= 1) && this.last() != '.'
}

/**
 * Extension function to the 'String' class
 * that checks whether the string is a
 * math function
 */
fun String.isMathFunction(): Boolean {
	val functions = arrayOf("cos", "sin", "tan", "log", "ln")
	return functions.contains(this)
}

/**
 * Extension function to the 'String' class
 * that checks whether the string is a
 * math constant (PI or E)
 */
fun String.isMathConstant(): Boolean {
	return this == "pi" || this == "e"
}