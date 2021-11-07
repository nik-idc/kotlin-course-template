import org.junit.Test
import org.junit.Assert
import java.util.*

class TextAlignerTest {
	// Test string
	private val testStr = "\tWell, what can I tell you?\n" +
			"\n" +
			"\tLife in the wide world\n" +
			"goes on much as it has this past Age, full of its own comings\n" +
			"and goings, scarcely aware of the existence of Hobbits,\n" +
			"for which I am very thankful.\n"
	
	private val testStrAlignedLeft = "Well, what can I tell you?\n" +
			"\n" +
			"Life in the wide world goes on much as it has this\n" +
			"past Age, full of its own comings and goings,\n" +
			"scarcely aware of the existence of Hobbits, for\n" +
			"which I am very thankful."
	
	
	private val testStrAlignedRight = "                        Well, what can I tell you?\n" +
			"\n" +
			"Life in the wide world goes on much as it has this\n" +
			"     past Age, full of its own comings and goings,\n" +
			"   scarcely aware of the existence of Hobbits, for\n" +
			"                         which I am very thankful."
	
	private val testStrAlignedCenter = "            Well, what can I tell you?            \n" +
			"\n" +
			"Life in the wide world goes on much as it has this\n" +
			"   past Age, full of its own comings and goings,  \n" +
			"  scarcely aware of the existence of Hobbits, for \n" +
			"             which I am very thankful.            "
	
	private val testStrAlignedJustified = "Well,      what      can      I      tell     you?\n" +
			"\n" +
			"Life in the wide world goes on much as it has this\n" +
			"past  Age,  full  of  its  own comings and goings,\n" +
			"scarcely  aware  of  the existence of Hobbits, for\n" +
			"which        I       am       very       thankful."
	
	private val ta = TextAligner(testStr) // Text aligner object
	
	private val lineLength = 50 // Length of a modified string
	
	@Test
	fun testAlignLeft() {
		val alignedStr = ta.alignText(lineLength, Alignment.LEFT)
		Assert.assertEquals(testStrAlignedLeft, alignedStr)
	}
	
	@Test
	fun testAlignRight() {
		val alignedStr = ta.alignText(lineLength, Alignment.RIGHT)
		Assert.assertEquals(testStrAlignedRight, alignedStr)
	}
	
	@Test
	fun testAlignCenter() {
		val alignedStr = ta.alignText(lineLength, Alignment.CENTER)
		Assert.assertEquals(testStrAlignedCenter, alignedStr)
	}
	
	@Test
	fun testAlignJustified() {
		val alignedStr = ta.alignText(lineLength, Alignment.JUSTIFIED)
		Assert.assertEquals(testStrAlignedJustified, alignedStr)
	}
	
	@Test
	fun testExceptionThrow() {
		val excStr: String? = try {
			ta.alignText(-2, Alignment.LEFT)
		} catch (e: InputMismatchException) {
			null
		}
		Assert.assertEquals(null, excStr)
	}
}
