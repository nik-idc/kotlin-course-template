fun lab3Demo() {
	val shapeFactory = ShapeFactoryImpl()
	
	val shapesList = arrayOf(
		shapeFactory.createRandomCircle(),
		shapeFactory.createRandomCircle(),
		shapeFactory.createRandomSquare(),
		shapeFactory.createRandomSquare(),
		shapeFactory.createRandomRectangle(),
		shapeFactory.createRandomRectangle(),
		shapeFactory.createRandomTriangle(),
		shapeFactory.createRandomTriangle(),
		shapeFactory.createRandomShape(),
		shapeFactory.createRandomShape(),
		shapeFactory.createRandomShape(),
		shapeFactory.createRandomShape(),
		shapeFactory.createRandomShape(),
		shapeFactory.createRandomShape(),
	)
	
	var sumPerimeter = 0.0
	var sumArea = 0.0
	
	for(shape: Shape in shapesList) {
		sumPerimeter += shape.calcPerimeter()
		sumArea += shape.calcArea()
	}
	
	
	val greatestPerimeter = shapesList.maxOf { it.calcPerimeter() }
	val greatestArea = shapesList.maxOf { it.calcArea() }
	
	println("Total perimeter of all shapes in the list is: $sumPerimeter")
	println("Total area of all shapes in the list is: $sumArea")
	println("Greatest perimeter is: $greatestPerimeter")
	println("Greatest area is: $greatestArea")
}

fun main() {
	lab3Demo()
}
