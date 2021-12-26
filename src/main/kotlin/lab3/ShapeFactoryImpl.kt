import kotlin.random.Random

class ShapeFactoryImpl : ShapeFactory {
	private val maxSize = 1000.0
	private val minSize = 10.0
	
	override fun createCircle(radius: Double): Circle {
		if (radius > 0.0)
			return Circle(radius)
		else
			throw IllegalArgumentException("Cannot create a circle with a less than 0 radius!")
	}
	
	override fun createSquare(side: Double): Square {
		if (side > 0.0)
			return Square(side)
		else
			throw IllegalArgumentException("Cannot create a square with a less than 0 side!")
	}
	
	override fun createRectangle(width: Double, height: Double): Rectangle {
		if (width > 0.0 && height > 0.0)
			return Rectangle(width, height)
		else
			throw IllegalArgumentException("Cannot create a rectangle with sides less than 0!")
	}
	
	override fun createTriangle(left: Double, right: Double, base: Double): Triangle {
		if (left > 0.0 && right > 0.0 && base > 0.0)
			return Triangle(left, right, base)
		else
			throw IllegalArgumentException("Cannot create a triangle with sides less than 0!")
	}
	
	
	override fun createRandomCircle(): Circle {
		val randRadius = Random.nextDouble(minSize, maxSize)
		return Circle(randRadius)
	}
	
	override fun createRandomSquare(): Square {
		val randSide = Random.nextDouble(minSize, maxSize)
		return Square(randSide)
	}
	
	override fun createRandomRectangle(): Rectangle {
		val width = Random.nextDouble(minSize, maxSize)
		val height = Random.nextDouble(minSize, maxSize)
		return Rectangle(width, height)
	}
	
	override fun createRandomTriangle(): Triangle {
		val left = Random.nextDouble(minSize, maxSize)
		val right = Random.nextDouble(minSize, maxSize)
		val base = Random.nextDouble(max((left - right),(right - left)), left + right)
		return Triangle(left, right, base)
	}
	
	
	override fun createRandomShape(): Shape {
		return when (Random.nextInt(1, 5)) {
			(1) -> createRandomCircle()
			(2) -> createRandomSquare()
			(3) -> createRandomRectangle()
			(4) -> createRandomTriangle()
			// Not exactly sure what would be the appropriate type of exception here
			else -> throw IllegalStateException("Something went wrong while creating a random number")
		}
	}
}
