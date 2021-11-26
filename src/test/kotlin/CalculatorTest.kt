import org.junit.Test
import org.junit.Assert
import kotlin.math.*

/**
 * If the absolute value of the difference between
 * any two double values is less or equal than this number
 * then it's safe to say they are equal
 */
const val doubleComparisonEpsilon = 0.0000001

class CalculatorTest {
	private val calculator = Calculator()
	
	@Test
	fun tokensTest() {
		val testStrings = arrayOf(
			"30+-32", "cos()", "san(45)", ")45(", "tan(23))",
			"32.321.+233", "32.321+233+", "(23"
		)
		
		var caught: Boolean
		for (i in testStrings.indices) {
			caught = try {
				calculator.inputStringIntoTokens(testStrings[i])
				false
			} catch (exc: IllegalArgumentException) {
				true
			}
			Assert.assertEquals(true, caught)
		}
		
		val testString = "sin(30)+50-23.5*43.5+tan(46/cos(60)sin(60))"
		val expectedTokens = arrayListOf(
			"sin", "(", "30", ")", "+", "50", "-",
			"23.5", "*", "43.5", "+", "tan", "(", "46", "/", "cos", "(", "60", ")", "*", "sin", "(", "60", ")", ")"
		)
		val tokens = calculator.inputStringIntoTokens(testString)
		Assert.assertEquals(expectedTokens, tokens)
	}
	
	@Test
	fun toPostfixTest() {
		val testStr = arrayListOf(
			"60", "+", "sin", "(", "log", "(", "4500", ")",
			")", "-", "23", "*", "sin", "(", "45", ")"
		) // Tokens list of input: 60+sin(log(4500))-23sin(45)
		val expectedPostfix = arrayListOf("60", "4500", "log", "sin", "+", "23", "45", "sin", "*", "-")
		val postfix = calculator.infixToPostfix(testStr)
		Assert.assertEquals(expectedPostfix, postfix)
	}
	
	@Test
	fun calcTest() {
		var res = calculator.calculate("50*cos(60)-sin(30)*90+48/log(100)+ln(e^2)")
		Assert.assertTrue(abs(res - 6.0) <= doubleComparisonEpsilon)
		
		res = calculator.calculate("25^(cos(60/e^0))-6")
		Assert.assertTrue(abs(res - (-1.0)) <= doubleComparisonEpsilon)
		
		res = calculator.calculate("sin(45)*cos(45)-tan(45)/2+log(100)^ln(e^3)")
		Assert.assertTrue(abs(res - 8.0) <= doubleComparisonEpsilon)
	}
}
