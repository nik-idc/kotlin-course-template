import kotlin.math.PI
import kotlin.math.sqrt

class Circle(val r: Double) : Shape {
	
	init {
		if (r <= 0.0)
			throw IllegalArgumentException("Circle radius cannot be less than 0!")
	}
	
	override fun calcArea(): Double {
		return PI * r * r
	}
	
	override fun calcPerimeter(): Double {
		return 2.0 * PI * r
	}
}

class Square(val side: Double) : Shape {
	init {
		if (side <= 0.0)
			throw IllegalArgumentException("Square side cannot be less than 0!")
	}
	
	override fun calcArea(): Double {
		return side * side
	}
	
	override fun calcPerimeter(): Double {
		return 4.0 * side
	}
}

class Rectangle(val width: Double, val height: Double) : Shape {
	init {
		if (width <= 0.0 || height <= 0.0)
			throw IllegalArgumentException("Rectangle sides cannot be less than 0!")
	}
	
	override fun calcArea(): Double {
		return width * height
	}
	
	override fun calcPerimeter(): Double {
		return 2.0 * (width + height)
	}
}

class Triangle(val left: Double, val right: Double, val base: Double) : Shape {
	init {
		if (left <= 0.0 || right <= 0.0 || base <= 0.0)
			throw IllegalArgumentException("Triangle sides cannot be less than 0!")
		else if (left >= right + base ||
			right >= left + base ||
			base >= right + left
		)
			throw IllegalArgumentException("Each side of a triangle must be less than the sum of two other sides!")
	}
	
	override fun calcArea(): Double {
		val s = (left + right + base) / 2.0 // Semi-perimeter
		return sqrt(s * (s - left) * (s - right) * (s - base)) // Heron's formula
	}
	
	override fun calcPerimeter(): Double {
		return left + right + base
	}
}

