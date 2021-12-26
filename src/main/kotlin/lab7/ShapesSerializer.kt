package lab7

import Circle
import Rectangle
import Shape
import Square
import Triangle
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.*
import java.io.File

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
	
	fun encodeShape(shape: Shape): String = json.encodeToString(shape)
	fun encodeShapes(shapes: List<Shape>): String = json.encodeToString(shapes)
	
	fun decodeShape(shapeStr: String): Shape = json.decodeFromString(shapeStr)
	fun decodeShapes(shapesStr: String): List<Shape> = json.decodeFromString(shapesStr)
}

class ShapeFiles {
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
	
	fun encodeShapesToFile(encodedShapes: String, path: String) {
		File(path).writeText(encodedShapes)
	}
	
	fun decodeShapeFromFile(path: String): Shape {
		val text = File(path).readText()
		return json.decodeFromString(text)
	}
	
	fun decodeShapesFromFile(path: String): List<Shape> {
		val text = File(path).readText()
		return json.decodeFromString(text)
	}
}
