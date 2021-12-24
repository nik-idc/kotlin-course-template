import org.junit.Assert
import org.junit.Test

class ShapeCollectorTest {
	private val sf = ShapeFactoryImpl()
	private val shapes = ShapeCollector(
		listOf(
			sf.createSquare(1.0),
			sf.createRectangle(1.0, 2.0),
			sf.createTriangle(3.0, 4.0, 5.0),
			sf.createCircle(1.0)
		)
	)
	
	@Test
	fun testGetAllSortedByArea() {
		// Test ascending sort
		var orderToExpect = ShapeCollector(
			listOf(
				sf.createSquare(1.0),
				sf.createRectangle(1.0, 2.0),
				sf.createCircle(1.0),
				sf.createTriangle(3.0, 4.0, 5.0)
			)
		)
		var orderActual = shapes.getAllSorted(ShapeComparators.CompareByAreaAsc)
		Assert.assertEquals(orderToExpect.getAll(), orderActual)
		
		// Test descending sort
		orderToExpect = ShapeCollector(
			listOf(
				sf.createTriangle(3.0, 4.0, 5.0),
				sf.createCircle(1.0),
				sf.createRectangle(1.0, 2.0),
				sf.createSquare(1.0)
			)
		)
		orderActual = shapes.getAllSorted(ShapeComparators.CompareByAreaDesc)
		Assert.assertEquals(orderToExpect.getAll(), orderActual)
	}
	
	@Test
	fun testGetAllSortedByPerimeter() {
		// Test ascending sort
		var orderToExpect = ShapeCollector(
			listOf(
				sf.createSquare(1.0),
				sf.createRectangle(1.0, 2.0),
				sf.createCircle(1.0),
				sf.createTriangle(3.0, 4.0, 5.0)
			)
		)
		var orderActual = shapes.getAllSorted(ShapeComparators.CompareByPerimeterAsc)
		Assert.assertEquals(orderToExpect.getAll(), orderActual)
		
		// Test descending sort
		orderToExpect = ShapeCollector(
			listOf(
				sf.createTriangle(3.0, 4.0, 5.0),
				sf.createCircle(1.0),
				sf.createRectangle(1.0, 2.0),
				sf.createSquare(1.0)
			)
		)
		orderActual = shapes.getAllSorted(ShapeComparators.CompareByPerimeterDesc)
		Assert.assertEquals(orderToExpect.getAll(), orderActual)
	}
	
	@Test
	fun testGetAllSortedByRadius() {
		val circles = ShapeCollector(
			listOf(
				sf.createCircle(3.0),
				sf.createCircle(1.0),
				sf.createCircle(4.0),
				sf.createCircle(2.0),
			)
		)
		
		// Test ascending sort
		var orderToExpect = ShapeCollector(
			listOf(
				sf.createCircle(1.0),
				sf.createCircle(2.0),
				sf.createCircle(3.0),
				sf.createCircle(4.0),
			)
		)
		var orderActual = circles.getAllSorted(ShapeComparators.CompareByRadiusAsc)
		Assert.assertEquals(orderToExpect.getAll(), orderActual)
		
		// Test descending sort
		orderToExpect = ShapeCollector(
			listOf(
				sf.createCircle(4.0),
				sf.createCircle(3.0),
				sf.createCircle(2.0),
				sf.createCircle(1.0),
			)
		)
		orderActual = circles.getAllSorted(ShapeComparators.CompareByRadiusDesc)
		Assert.assertEquals(orderToExpect.getAll(), orderActual)
	}
	
	@Test
	fun testGetAllByClass() {
		val square = shapes.getAllByClass(Square::class.java)
		val rectangle = shapes.getAllByClass(Rectangle::class.java)
		val triangle = shapes.getAllByClass(Triangle::class.java)
		val circle = shapes.getAllByClass(Circle::class.java)
		
		Assert.assertEquals(shapes.getAllByClass(Square::class.java), square)
		Assert.assertEquals(shapes.getAllByClass(Rectangle::class.java), rectangle)
		Assert.assertEquals(shapes.getAllByClass(Triangle::class.java), triangle)
		Assert.assertEquals(shapes.getAllByClass(Circle::class.java), circle)
	}
}