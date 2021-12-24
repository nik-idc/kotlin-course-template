import org.junit.Assert
import org.junit.Test

class ShapeSerializationTest {
	private val sf = ShapeFactoryImpl()
	private val shapesSerializer = ShapesSerializer()
	
	@Test
	fun testEncode() {
		val circle = sf.createCircle(1.0)
		val expectedEncodedCircle = """
			{
			    "type": "Circle",
			    "r": 1.0
			}
		""".trimIndent()
		Assert.assertEquals(expectedEncodedCircle, shapesSerializer.encodeShape(circle))
		
		val square = sf.createSquare(1.0)
		val expectedEncodedSquare = """
			{
			    "type": "Square",
			    "side": 1.0
			}
		""".trimIndent()
		Assert.assertEquals(expectedEncodedSquare, shapesSerializer.encodeShape(square))
		
		val rectangle = sf.createRectangle(2.0, 3.0)
		val expectedEncodedRectangle = """
			{
			    "type": "Rectangle",
			    "width": 2.0,
			    "height": 3.0
			}
		""".trimIndent()
		Assert.assertEquals(expectedEncodedRectangle, shapesSerializer.encodeShape(rectangle))
		
		val triangle = sf.createTriangle(3.0, 4.0, 5.0)
		val expectedEncodedTriangle = """
			{
			    "type": "Triangle",
			    "left": 3.0,
			    "right": 4.0,
			    "base": 5.0
			}
		""".trimIndent()
		Assert.assertEquals(expectedEncodedTriangle, shapesSerializer.encodeShape(triangle))
	}
	
	@Test
	fun testDecode() {
		val encodedCircle = """
			{
			    "type": "Circle",
			    "r": 1.0
			}
		""".trimIndent()
		val expectedCircle = sf.createCircle(1.0)
		Assert.assertEquals(shapesSerializer.decodeShape(encodedCircle), expectedCircle)
		
		val encodedSquare = """
			{
			    "type": "Square",
			    "side": 1.0
			}
		""".trimIndent()
		val expectedSquare = sf.createSquare(1.0)
		Assert.assertEquals(shapesSerializer.decodeShape(encodedSquare), expectedSquare)
		
		val encodedRectangle = """
			{
			    "type": "Rectangle",
			    "width": 2.0,
			    "height": 3.0
			}
		""".trimIndent()
		val expectedRectangle = sf.createRectangle(2.0, 3.0)
		Assert.assertEquals(shapesSerializer.decodeShape(encodedRectangle), expectedRectangle)
		
		val encodedTriangle = """
			{
			    "type": "Triangle",
			    "left": 3.0,
			    "right": 4.0,
			    "base": 5.0
			}
		""".trimIndent()
		val expectedTriangle = sf.createTriangle(3.0, 4.0, 5.0)
		Assert.assertEquals(shapesSerializer.decodeShape(encodedTriangle), expectedTriangle)
	}
}