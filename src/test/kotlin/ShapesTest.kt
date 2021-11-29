import org.junit.Assert
import org.junit.Test
import kotlin.math.PI
import kotlin.math.sqrt

class ShapesTest {

	private val doubleCompDelta = 0.00001
	
	@Test
	fun testCircle() {
		val r = 3.0
		val expectedArea = PI * r * r
		val expectedPerimeter = 2 * PI * r
		
		val circle = Circle(r)
		Assert.assertEquals(expectedArea, circle.calcArea(), doubleCompDelta)
		Assert.assertEquals(expectedPerimeter, circle.calcPerimeter(), doubleCompDelta)
	}
	
	@Test
	fun testSquare() {
		val side = 3.0
		val expectedArea = side * side
		val expectedPerimeter = 4 * side
		
		val square = Square(side)
		Assert.assertEquals(expectedArea, square.calcArea(), doubleCompDelta)
		Assert.assertEquals(expectedPerimeter, square.calcPerimeter(), doubleCompDelta)
	}
	
	@Test
	fun testRectangle() {
		val width = 5.0
		val height = 4.0
		val expectedArea = width * height
		val expectedPerimeter = 2.0 * (width + height)
		
		val rect = Rectangle(width, height)
		Assert.assertEquals(expectedArea, rect.calcArea(), doubleCompDelta)
		Assert.assertEquals(expectedPerimeter, rect.calcPerimeter(), doubleCompDelta)
	}
	
	@Test
	fun testTriangle() {
		val left = 5.0
		val right = 4.0
		val base = 4.0
		
		val s = (left + right + base) / 2.0
		val expectedArea = sqrt(s * (s - left) * (s - right) * (s - base))
		val expectedPerimeter = left + right + base
		
		val triangle = Triangle(left, right, base)
		Assert.assertEquals(expectedArea, triangle.calcArea(), doubleCompDelta)
		Assert.assertEquals(expectedPerimeter, triangle.calcPerimeter(), doubleCompDelta)
	}
}