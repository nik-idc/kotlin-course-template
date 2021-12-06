import org.junit.Assert
import org.junit.Test

class ShapesFactoryImplTest {
	private val shapeFactory = ShapeFactoryImpl()
	
	@Test
	fun testCreateCircle() {
		// Testing response to a negative radius
		val radius = -3.0
		val caught = try {
			shapeFactory.createCircle(radius)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testCreateSquare() {
		// Testing response to a negative side
		val side = -3.0
		val caught = try {
			shapeFactory.createSquare(side)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testCreateRectangle() {
		// Testing response to negative sides
		val width = -3.0
		val height = 3.0
		
		val caught = try {
			shapeFactory.createRectangle(width, height)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testCreateTriangle() {
		// Testing response to negative sides
		var left = -3.0
		var right = 4.0
		var base = 5.0
		var caught = try {
			shapeFactory.createTriangle(left, right, base)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
		
		// Testing response to invalid triangle sides
		left = 2.0
		right = 5.0
		base = 1.0
		caught = try {
			shapeFactory.createTriangle(left, right, base)
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
}
