import java.util.*
import kotlin.IllegalArgumentException
import kotlin.math.*

class Calculator {
	/**
	 * Enum class of all possible characters in an input string
	 * preceding the current character
	 */
	enum class PrChar {
		NOTHING,
		OPERATOR,
		OPEN_BRACKET,
		CLOSED_BRACKET,
		NUMBER,
		SYMBOL
	}
	
	/**
	 * Calculate the value of mathematical expression [input]
	 */
	fun calculate(input: String): Double {
		if (input.isEmpty())
			throw IllegalArgumentException("Empty string!")
		
		val tokens = inputStringIntoTokens(input)
		return if (tokens.size > 1) {
			val postfix = infixToPostfix(tokens)
			eval(postfix)
		} else
			tokens.first().toDouble()
	}
	
	/**
	 * Process input string into tokens and check for input errors
	 */
	fun inputStringIntoTokens(input: String): List<String> {
		//val infixTokens: Queue<String> = LinkedList()
		val infixTokens: MutableList<String> = mutableListOf()
		//val s: MutableList<Char> = mutableListOf()
		var bracketsBalance = 0
		var prevChar = PrChar.NOTHING // No previous characters yet
		var i = 0 // Input string iterator
		while (i < input.length) { // The big checking loop
			if (input[i].isDigit()) { // Add the number token to the token queue
				if (prevChar == PrChar.CLOSED_BRACKET)
					infixTokens.add("*") // Change ..(..)digit to ..(..)*digit
				
				// Read until encounter a character that is not a digit and not a dot,
				// then check if it is a valid number. If not, throw an exception
				val numToken = input.substring(i, input.length).takeWhile { it.isDigit() || it == '.' }
				if (numToken.isNumber()) // Check if number format is correct
					infixTokens.add(numToken)
				else
					throw IllegalArgumentException("Invalid number at character $i!")
				
				prevChar = PrChar.NUMBER // Set the previous character to be numeric
				i += numToken.length // Iterate through the string
			} else if (input[i].isLetter()) { // Add a function/constant token to the tokens queue
				if (prevChar == PrChar.CLOSED_BRACKET || prevChar == PrChar.NUMBER)
					infixTokens.add("*") // Change ..(..)letter/..digitletter to ..(..)*letter/..digit*letter respectively
				
				// Read until next character is not a symbol.
				// After that check if resulting string is a valid math function or constant. If not throw an exception.
				// If it is a constant, add the numeric representation of it to tokens list, i.e.
				// change ...pi... to ...3.14159265359...
				val symbolicToken = input.substring(i, input.length).takeWhile { it.isLetter() }
				prevChar = if (symbolicToken.isMathFunction()) { // If math function add to the token queue
					infixTokens.add(symbolicToken)
					i += symbolicToken.length
					PrChar.SYMBOL // Set the previous character to be symbolic
				} else if (symbolicToken.isMathConstant()) { // If math constant, add the number form of it to the queue
					// Don't like this, could probably be done better
					if (symbolicToken == "pi") {
						infixTokens.add(PI.toString())
						i += 2 // Iterate through the string
					} else {
						infixTokens.add(E.toString())
						i++ // Iterate through the string
					}
					PrChar.NUMBER // Set the previous character to be numeric
				} else // Otherwise, tell user they did a stupid
					throw IllegalArgumentException("Invalid symbolic string at character $i!")
			} else if (input[i].isOperator()) { // Add an operator to the tokens queue
				// Check for all possible errors
				if ((i == 0 && input[i] != '-' && input[i] != '+') || i == input.lastIndex || prevChar == PrChar.OPERATOR)
					throw IllegalArgumentException("Operator error at character $i!")
				
				if ((input[i] == '-' || input[i] == '+') && (prevChar == PrChar.OPEN_BRACKET || i == 0))
					infixTokens.add("0") // Change ...(-number.../-number... to ...(0-number.../0-number...
				infixTokens.add(input[i].toString())
				
				prevChar = PrChar.OPERATOR // Set the previous character to an operator
				i++ // Iterate through the string
			} else if (input[i] == '(') { // do stuff with the opening bracket
				if (prevChar == PrChar.CLOSED_BRACKET)
					throw IllegalArgumentException("Opening bracket followed by a closing bracket at character $i!")
				
				infixTokens.add("(")
				bracketsBalance++
				
				prevChar = PrChar.OPEN_BRACKET // Set the previous character to an open bracket
				i++ // Iterate through the string
			} else if (input[i] == ')') { // do stuff with the closing bracket
				if (bracketsBalance == 0 || prevChar == PrChar.OPEN_BRACKET || prevChar == PrChar.OPERATOR || prevChar == PrChar.SYMBOL)
					throw IllegalArgumentException("Opening bracket error at character $i!")
				else
					bracketsBalance--
				infixTokens.add(")")
				prevChar = PrChar.CLOSED_BRACKET // Set the previous character to a closed bracket
				i++ // Iterate through the string
			} else if (input[i] == ' ')
				i++ // Iterate through the string
			else
				throw IllegalArgumentException("Unrecognised symbol at character $i!")
		}
		
		if (bracketsBalance != 0) // Check if brackets are balanced
			throw IllegalArgumentException("Unbalanced brackets!")
		
		//return infixTokens.toList()
		return infixTokens
	}
	
	/**
	 * Get precedence of specified operator in a math expression
	 */
	private fun precedence(str: String): Int {
		return when (str) {
			("+"), ("-") -> 1
			("*"), ("/") -> 2
			("^") -> 3
			else -> -1
		}
	}
	
	/**
	 * Process closing bracket when converting from infix to prefix
	 */
	private fun processClosingBracketToPostfix(s: MutableList<String>, postfix: MutableList<String>) {
		// Add the top element of the stack to the postfix string until stack
		// is empty or top element of the stack is an opening bracket
		while (s.last() != "(")
			postfix.add(s.removeLast())
		s.removeLast() // Remove opening bracket
		// If there is a function in the expression, add it to postfix string
		if (s.isNotEmpty() && s.last().isMathFunction())
			postfix.add(s.removeLast())
	}
	
	/**
	 * Process operator when converting from infix to prefix
	 */
	private fun processOperatorToPostfix(s: MutableList<String>, postfix: MutableList<String>, curToken: String) {
		if (s.isEmpty()) // If stack is empty, just add the token to it
			s.add(curToken)
		else { // Otherwise, if stack is not empty
			if (precedence(curToken) > precedence(s.last()))
				s.add(curToken)
			else if (precedence(curToken) == precedence(s.last()) && curToken == "^")
				s.add(curToken)
			else {
				while (s.isNotEmpty() && precedence(curToken) <= precedence(s.last()))
					postfix.add(s.removeLast())
				s.add(curToken)
			}
		}
	}
	
	/**
	 * Convert infix to postfix notation
	 */
	fun infixToPostfix(infix: List<String>): List<String> {
		val postfix: MutableList<String> = mutableListOf()
		val s: MutableList<String> =
				mutableListOf() // List (will be used and referred to as stack) to keep track of operators, brackets and functions
		
		for (i in infix.indices) {
			if (infix[i].isNumber()) // If current token is a number, just add it to the postfix
				postfix.add(infix[i])
			else if (infix[i] == "(") // If opening bracket, push it onto stack
				s.add(infix[i])
			else if (infix[i] == ")") { // If closing bracket
				processClosingBracketToPostfix(s, postfix)
			} else if (infix[i].isOperator()) { // If current token is operator
				processOperatorToPostfix(s, postfix, infix[i])
			} else if (infix[i].isMathFunction()) {
				s.add(infix[i]) // Add math function to the stack
			}
		}
		
		while (s.isNotEmpty()) // Add all remaining operators to postfix
			postfix.add(s.removeLast())
		
		return postfix
	}
	
	/**
	 * Perform binary operation [operator] on two numbers [leftStr] and [rightStr]
	 */
	private fun binOperation(leftStr: String, rightStr: String, operator: String): Double? {
		val left = leftStr.toDouble()
		val right = rightStr.toDouble()
		
		if (operator == "/" && right == 0.0)
			throw InputMismatchException(
					"Attempted division by zero!" +
							"\nPlease take a look at supported patterns of input again!"
			)
		
		return when (operator) {
			("+") -> left + right
			("-") -> left - right
			("*") -> left * right
			("/") -> left / right
			("^") -> left.pow(right)
			else -> null
		}
	}
	
	/**
	 * Calculate function [funcStr] of [numStr]
	 */
	private fun funcOperation(funcStr: String, numStr: String): Double? {
		val num = numStr.toDouble()
		
		if ((funcStr == "log" || funcStr == "log") && num <= 0 || funcStr == "tan" && num % 90 == 0.0)
			throw InputMismatchException(
					"At least one of the functions in your expression has invalid input!" +
							"\nPlease take a look at supported patterns of input again!"
			)
		
		return when (funcStr) {
			("cos") -> cos(Math.toRadians(num))
			("sin") -> sin(Math.toRadians(num))
			("tan") -> tan(Math.toRadians(num))
			("log") -> log(num, 10.0)
			("ln") -> ln(num)
			else -> null
		}
	}
	
	/**
	 * Evaluate postfix expression
	 */
	private fun eval(postfix: List<String>): Double {
		var operationResult: Double?
		val calc: MutableList<String> =
				mutableListOf() // List (will be used and referred to as stack) for calculating the result
		
		for (i in postfix.indices) {
			if (postfix[i].isNumber()) {
				calc.add(postfix[i]) // Push a number to stack
			} else if (postfix[i].isOperator() || postfix[i].isMathFunction()) {
				if (postfix[i].isOperator()) { // Calculate binary operation
					val num2 = calc.removeLast()
					val num1 = calc.removeLast()
					operationResult = binOperation(num1, num2, postfix[i])
					calc.add(operationResult.toString())
				} else { // Calculate unary operation (function)
					val num = calc.removeLast()
					operationResult = funcOperation(postfix[i], num)
					calc.add(operationResult.toString())
				}
			}
		}
		return calc.last().toDouble()
	}
}