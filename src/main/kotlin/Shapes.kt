import kotlin.math.PI
import kotlin.math.sqrt

class Circle(newRadius: Double) : Shape {
	private val r: Double
	
	init {
		if (newRadius > 0.0)
			r = newRadius
		else
			throw IllegalArgumentException("Circle radius cannot be less than 0!")
	}
	
	override fun calcArea(): Double {
		return PI * r * r
	}
	
	override fun calcPerimeter(): Double {
		return 2.0 * PI * r
	}
}

class Square(newSide: Double) : Shape {
	private val side: Double
	
	init {
		if (newSide > 0.0)
			side = newSide
		else
			throw IllegalArgumentException("Square side cannot be less than 0!")
	}
	
	override fun calcArea(): Double {
		return side * side
	}
	
	override fun calcPerimeter(): Double {
		return 4.0 * side
	}
}

class Rectangle(newHeight: Double, newWidth: Double) : Shape {
	private val width: Double
	private val height: Double
	
	init {
		if (newWidth > 0.0 && newHeight > 0.0) {
			width = newWidth
			height = newHeight
		} else
			throw IllegalArgumentException("Rectangle sides cannot be less than 0!")
	}
	
	override fun calcArea(): Double {
		return width * height
	}
	
	override fun calcPerimeter(): Double {
		return 2.0 * (width + height)
	}
}

class Triangle(newLeft: Double, newRight: Double, newBase: Double) : Shape {
	private val left: Double
	private val right: Double
	private val base: Double
	
	init {
		if (newLeft >= newRight + newBase ||
			newRight >= newLeft + newBase ||
			newBase >= newRight + newLeft
		) {
			throw IllegalArgumentException("Each side of a triangle must be less than the sum of two other sides!")
		} else if (newLeft <= 0.0 || newRight <= 0.0 || newBase <= 0.0)
			throw IllegalArgumentException("Triangle sides cannot be less than 0!")
		else {
			left = newLeft
			right = newRight
			base = newBase
		}
	}
	
	override fun calcArea(): Double {
		val s = (left + right + base) / 2.0 // Semi-perimeter
		return sqrt(s * (s - left) * (s - right) * (s - base)) // Heron's formula
	}
	
	override fun calcPerimeter(): Double {
		return left + right + base
	}
}
