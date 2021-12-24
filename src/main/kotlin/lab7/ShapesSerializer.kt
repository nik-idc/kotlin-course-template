import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import java.io.File
import java.io.IOException

class ShapesSerializer {
	private val json = Json {
		prettyPrint = true
		serializersModule = SerializersModule {
			polymorphic(Shape::class) {
				subclass(Circle::class)
				subclass(Square::class)
				subclass(Rectangle::class)
				subclass(Triangle::class)
			}
		}
	}
	
	fun encodeShape(shape: Shape) : String = json.encodeToString(shape)
	fun decodeShape(shapeStr: String) : Shape = json.decodeFromString(shapeStr)
}

class ShapeFiles {
	private val serializer = ShapesSerializer()
	
	fun encodeShapesToFile(shapes: List<Shape>, path: String) {
		try {
			var res = ""
			for(shape in shapes)
				res += serializer.encodeShape(shape)
			File(path).writeText(res)
		} catch(exc: IOException) {
			println("Could not write into file: ${exc.message}")
		}
	}
	
	fun decodeShapesFromFile(path: String) : List<Shape> {
		val res = mutableListOf<Shape>()
		try {
			var text = File(path).readText()
			while(text.isNotEmpty()) {
				// Take substring before '}', while filtering out all the '\r\n' etc.
				val curObj = text.substringBefore('}').filter { char -> (!" \r\n".contains(char)) } + '}'
				res.add(serializer.decodeShape(curObj)) // Decode and add
				text = text.substringAfter('}') // Update text from file
			}
		} catch(exc: IllegalArgumentException) {
			println("It appears a JSON object was in incorrect format: ${exc.message}")
		} catch(exc: IOException) {
			println("Could not read from file: ${exc.message}")
		}
		return res
	}
}
