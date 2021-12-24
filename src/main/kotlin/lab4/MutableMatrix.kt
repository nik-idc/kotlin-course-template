class MutableMatrix(data: MutableList<MutableList<Double>>) : Matrix(data) {
	operator fun set(i: Int, j: Int, value: Double) {
		mat[i][j] = value
	}
	
	operator fun plusAssign(other: Matrix) {
		for (i in 0 until rows)
			for (j in 0 until columns)
				mat[i][j] += other.mat[i][j]
	}
	
	operator fun minusAssign(other: Matrix) {
		for (i in 0 until rows)
			for (j in 0 until columns)
				mat[i][j] -= other.mat[i][j]
	}
	
	operator fun timesAssign(scalar: Double) {
		for (i in 0 until rows)
			for (j in 0 until columns)
				mat[i][j] *= scalar
	}
	
	operator fun divAssign(scalar: Double) {
		for (i in 0 until rows)
			for (j in 0 until columns)
				mat[i][j] /= scalar
	}
	
	operator fun timesAssign(other: Matrix) {
		// Check for size
		if (columns != other.rows)
			throw IllegalArgumentException("Number of columns of the left matrix should be equal to the number of rows of the right matrix!")
		
		val resultRows = rows
		val resultColumns = other.columns
		val data = MutableList(resultRows) { MutableList(resultColumns) { 0.0 } }
		
		// Matrix multiplication here
		for (i in 0 until resultRows)
			for (j in 0 until resultColumns)
				for (k in 0 until columns)
					data[i][j] += mat[i][k] * other.mat[k][j]
		
		rows = resultRows
		columns = resultColumns
		mat = data
		
	}
}
