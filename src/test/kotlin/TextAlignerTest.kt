import org.junit.Test
import org.junit.Assert

class TextAlignerTest {
	// Test string
	private val testStr = "\tWell, what can I tell you?\n" +
			"\n" +
			"\tLife in the wide world\n" +
			"goes on much as it has this past Age, full of its own comings\n" +
			"and goings, scarcely aware of the existence of Hobbits,\n" +
			"for which I am very thankful.\n"

	private val ta = TextAlignerLab1(testStr) // Text aligner object

	private val lineLength = 50 // Length of a modified string

	@Test
	fun testAlignLeft(){
		ta.alignText(lineLength, Alignment.LEFT)

		var bufferStr = ""
		var lastChar = ' '
		for(i in ta.text.indices) {
			if (bufferStr.isNotEmpty() && ta.text[i] == '\n' && lastChar != '\n') {
				Assert.assertTrue(bufferStr.length <= lineLength && bufferStr[0] != ' ')
				bufferStr = ""
			}
			else if(ta.text[i] != '\n'){
				bufferStr += ta.text[i]
			}
			lastChar = ta.text[i]
		}
	}

	@Test
	fun testAlignRight(){
		ta.alignText(lineLength, Alignment.RIGHT)

		var bufferStr = ""
		var lastChar = ' '
		for(i in ta.text.indices) {
			if (bufferStr.isNotEmpty() && ta.text[i] == '\n' && lastChar != '\n') {
				Assert.assertTrue(bufferStr.length == lineLength && bufferStr.last() != ' ')
				bufferStr = ""
			} else if(ta.text[i] != '\n') { //if(ta.text[i] != '\n' && lastChar != '\n')
				bufferStr += ta.text[i]
			}
			lastChar = ta.text[i]
		}
	}

	@Test
	fun testAlignCenter(){
		ta.alignText(lineLength, Alignment.CENTER)

		var bufferStr = ""
		var lastChar = ' '
		for(i in ta.text.indices) {
			if (bufferStr.isNotEmpty() && ta.text[i] == '\n' && lastChar != '\n') {
				var leftSpacesCount = 0
				var rightSpacesCount = 0
				for(j in bufferStr.indices){
					if(!bufferStr[j].isLetterOrDigitOrPM())
						leftSpacesCount++
					else
						break
				}
				for(j in bufferStr.indices.reversed()){
					if(!bufferStr[j].isLetterOrDigitOrPM())
						rightSpacesCount++
					else
						break
				}
				Assert.assertTrue(bufferStr.length == lineLength && (leftSpacesCount - rightSpacesCount) < 2)
				bufferStr = ""
			} else if(ta.text[i] != '\n') {
				bufferStr += ta.text[i]
			}
			lastChar = ta.text[i]
		}
	}

	@Test
	fun testAlignJustified(){
		ta.alignText(lineLength, Alignment.JUSTIFIED)

		var bufferStr = ""
		var lastChar = ' '
		for(i in ta.text.indices) {
			if (bufferStr.isNotEmpty() && ta.text[i] == '\n' && lastChar != '\n') {
				var curSpacesPerEmptyCount = 0
				var firstSpacesPerEmptyCount = 0
				var lastSpacesPerEmptyCount = 0
				for(j in bufferStr.indices) {
					if (bufferStr[j] == ' ')
						curSpacesPerEmptyCount++
					else {
						if(firstSpacesPerEmptyCount == 0)
							firstSpacesPerEmptyCount = curSpacesPerEmptyCount
						else if(curSpacesPerEmptyCount > 0)
							lastSpacesPerEmptyCount = curSpacesPerEmptyCount
						curSpacesPerEmptyCount = 0
					}
				}
				Assert.assertTrue(bufferStr.length == lineLength &&
						firstSpacesPerEmptyCount - lastSpacesPerEmptyCount < 2
						&& bufferStr.first() != ' ' && bufferStr.last() != ' ')
				bufferStr = ""
			} else if(ta.text[i] != '\n') {
				bufferStr += ta.text[i]
			}
			lastChar = ta.text[i]
		}
	}
}