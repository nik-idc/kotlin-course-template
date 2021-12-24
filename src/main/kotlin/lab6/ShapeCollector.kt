object ShapeComparators {
	// Perimeter comparators
	val CompareByPerimeterAsc = ShapePerimeterComparator()
	val CompareByPerimeterDesc: Comparator<Shape> = ShapePerimeterComparator().reversed()
	
	// Area comparators
	val CompareByAreaAsc = ShapeAreaComparator()
	val CompareByAreaDesc: Comparator<Shape> = ShapeAreaComparator().reversed()
	
	// Circle radius comparators
	val CompareByRadiusAsc = CircleRadiusComparator()
	val CompareByRadiusDesc: Comparator<Circle> = CircleRadiusComparator().reversed()
}

class ShapeCollector<T : Shape>() {
	private val shapes = mutableListOf<T>()
	
	constructor (initialShapes: List<T>) : this() {
		if (initialShapes.isNotEmpty()) {
			for (shape in initialShapes)
				shapes.add(shape)
		}
	}
	
	fun add(new: T) {
		shapes.add(new)
	}
	
	fun addAll(new: List<T>) {
		for (shape in new)
			shapes.add(shape)
	}
	
	fun getAll(): List<Shape> {
		return shapes
	}
	
	fun getAllSorted(cmp: Comparator<in T>): List<Shape> {
		return shapes.sortedWith(cmp)
	}
	
	fun getAllByClass(className: Class<out T>): List<T> {
		return shapes.filter { shape -> (shape.javaClass == className) }
	}
}