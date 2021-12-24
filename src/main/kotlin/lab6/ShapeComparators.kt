class ShapeAreaComparator : Comparator<Shape> {
	override fun compare(o1: Shape, o2: Shape): Int {
		return o1.calcArea().compareTo(o2.calcArea())
	}
}

class ShapePerimeterComparator : Comparator<Shape> {
	override fun compare(o1: Shape, o2: Shape): Int {
		return o1.calcArea().compareTo(o2.calcArea())
	}
}

class CircleRadiusComparator : Comparator<Circle> {
	override fun compare(o1: Circle, o2: Circle): Int {
		return o1.r.compareTo(o2.r)
	}
}