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

fun lab4Demo() {
	val mat1 = MutableMatrix(
		mutableListOf(
			mutableListOf(2.0, 4.0, -1.0, 2.0),
			mutableListOf(3.0, 5.0, 2.0, -3.0),
			mutableListOf(1.0, 0.0, 1.0, -1.0)
		)
	)
	
	val mat2 = Matrix(
		mutableListOf(
			mutableListOf(1.0, 36.0, -1.0, 21.0),
			mutableListOf(0.0, -25.0, 3.0, 8.0),
			mutableListOf(12.0, 11.0, 7.0, 5.0)
		)
	)
	
	val mat3 = Matrix(
		mutableListOf(
			mutableListOf(-7.0, 2.0, 14.0),
			mutableListOf(3.0, 5.0, 12.0),
			mutableListOf(12.0, 6.0, -9.0),
			mutableListOf(-5.0, 2.0, -10.0)
		)
	)
	
	println("First matrix:\n$mat1")
	println("Second matrix:\n$mat2")
	println("Sum result:\n" + (mat1 + mat2).toString())
	println()
	
	println("Matrix:\n$mat1")
	println("Multiply by scalar 5 result:\n" + (mat1 * 5.0).toString())
	println()
	
	println("First matrix:\n$mat1")
	println("Second matrix:\n$mat3")
	println("Multiplication result:\n" + (mat1 * mat3).toString())
	println()
	
	println("Matrix:\n$mat1")
	println("Third element in second row: " + mat1[1, 2])
	mat1[1,2] = 321.0
	println("Matrix after change:\n$mat1")
	println("Third element in second row after change: " + mat1[1, 2])
	println()
}

fun main() {
	lab4Demo()
}
