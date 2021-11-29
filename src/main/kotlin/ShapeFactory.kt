interface ShapeFactory {
	fun createCircle(radius: Double): Circle
	fun createSquare(side: Double): Square
	fun createRectangle(width: Double, height: Double): Rectangle
	fun createTriangle(left: Double, right: Double, base: Double): Triangle
	
	fun createRandomCircle(): Circle
	fun createRandomSquare(): Square
	fun createRandomRectangle(): Rectangle
	fun createRandomTriangle(): Triangle
	
	fun createRandomShape(): Shape
}