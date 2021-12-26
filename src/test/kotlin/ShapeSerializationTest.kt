import lab7.ShapesSerializer
import org.junit.Assert
import org.junit.Test

class ShapeSerializationTest {
	private val sf = ShapeFactoryImpl()
	private val shapesSerializer = ShapesSerializer()
	
	@Test
	fun testEncode() {
		// Test encode one shape
		val circle = sf.createCircle(1.0)
		val expectedEncodedCircle = """
			{
			    "type": "Circle",
			    "r": 1.0
			}
		""".trimIndent()
		Assert.assertEquals(expectedEncodedCircle, shapesSerializer.encodeShape(circle))
		
		// Test encode multiple shapes
		val shapes = listOf(
			sf.createCircle(1.0),
			sf.createSquare(1.0),
			sf.createRectangle(1.0, 2.0),
			sf.createTriangle(3.0, 4.0, 5.0)
		)
		val expectedEncodedSquare = """
			[
			    {
			        "type": "Circle",
			        "r": 1.0
			    },
			    {
			        "type": "Square",
			        "side": 1.0
			    },
			    {
			        "type": "Rectangle",
			        "width": 1.0,
			        "height": 2.0
			    },
			    {
			        "type": "Triangle",
			        "left": 3.0,
			        "right": 4.0,
			        "base": 5.0
			    }
			]
		""".trimIndent()
		Assert.assertEquals(expectedEncodedSquare, shapesSerializer.encodeShapes(shapes))
	}
	
	@Test
	fun testDecode() {
		// Test decode one shape
		val circle = sf.createCircle(1.0)
		val encodedCircle = """
			{
			    "type": "Circle",
			    "r": 1.0
			}
		""".trimIndent()
		Assert.assertEquals(shapesSerializer.decodeShape(encodedCircle), circle)
		
		// Test decode multiple shapes
		val shapes = listOf(
			sf.createCircle(1.0),
			sf.createSquare(1.0),
			sf.createRectangle(1.0, 2.0),
			sf.createTriangle(3.0, 4.0, 5.0)
		)
		val encodedShapes = """
			[
			    {
			        "type": "Circle",
			        "r": 1.0
			    },
			    {
			        "type": "Square",
			        "side": 1.0
			    },
			    {
			        "type": "Rectangle",
			        "width": 1.0,
			        "height": 2.0
			    },
			    {
			        "type": "Triangle",
			        "left": 3.0,
			        "right": 4.0,
			        "base": 5.0
			    }
			]
		""".trimIndent()
		Assert.assertEquals(shapesSerializer.decodeShapes(encodedShapes), shapes)
	}
}
