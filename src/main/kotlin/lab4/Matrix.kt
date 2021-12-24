import kotlin.math.abs

open class Matrix(data: MutableList<MutableList<Double>>) {
	var rows: Int
	var columns: Int
	var mat: MutableList<MutableList<Double>>
	
	init {
		// Checking if data is a valid matrix
		// (comparing sizes of each row)
		var prevSize = data[0].size
		for (row: List<Double> in data) {
			if (row.size != prevSize)
				throw IllegalArgumentException("Not a matrix!")
			prevSize = row.size
		}
		
		// Assigning data
		rows = data.size
		columns = data[0].size
		mat = data
	}
	
	operator fun plus(other: Matrix): Matrix {
		if (rows != other.rows || columns != other.columns)
			throw IllegalArgumentException("Can't perform sum operation on matrices of different sizes!")
		
		val data = MutableList(rows) { MutableList(columns) { 0.0 } }
		for (i in 0 until rows)
			for (j in 0 until columns)
				data[i][j] = mat[i][j] + other.mat[i][j]
		return Matrix(data)
	}
	
	operator fun minus(other: Matrix): Matrix {
		if (rows != other.rows || columns != other.columns)
			throw IllegalArgumentException("Can't perform minus operation on matrices of different sizes!")
		
		val data = MutableList(rows) { MutableList(columns) { 0.0 } }
		for (i in 0 until rows)
			for (j in 0 until columns)
				data[i][j] = mat[i][j] - other.mat[i][j]
		return Matrix(data)
	}
	
	operator fun times(scalar: Double): Matrix {
		val data = MutableList(rows) { MutableList(columns) { 0.0 } }
		for (i in 0 until rows)
			for (j in 0 until columns)
				data[i][j] = mat[i][j] * scalar
		return Matrix(data)
	}
	
	operator fun div(scalar: Double): Matrix {
		if (abs(scalar - 0.0) < 0.00000001)
			throw IllegalArgumentException("Can't divide by zero")
		
		val data = MutableList(rows) { MutableList(columns) { 0.0 } }
		for (i in 0 until rows)
			for (j in 0 until columns)
				data[i][j] = mat[i][j] / scalar
		return Matrix(data)
	}
	
	operator fun times(other: Matrix): Matrix {
		// Check for size
		if (columns != other.rows)
			throw IllegalArgumentException("Number of columns in the left matrix must be equal to the number of rows in the right matrix!")
		
		val resultRows = rows
		val resultColumns = other.columns
		val data = MutableList(resultRows) { MutableList(resultColumns) { 0.0 } }
		
		// Matrix multiplication
		for (i in 0 until resultRows)
			for (j in 0 until resultColumns)
				for (k in 0 until columns)
					data[i][j] += mat[i][k] * other.mat[k][j]
		
		return Matrix(data)
	}
	
	operator fun get(i: Int, j: Int): Double {
		if (i < 0 || i > rows || j < 0 || j > columns)
			throw IllegalArgumentException("Invalid access indices!")
		
		return mat[i][j]
	}
	
	operator fun unaryMinus(): Matrix {
		val data = MutableList(rows) { MutableList(columns) { 0.0 } }
		
		for (i in 0 until rows)
			for (j in 0 until columns)
				data[i][j] = -mat[i][j]
		
		return Matrix(data)
	}
	
	operator fun unaryPlus(): Matrix {
		return this
	}
	
	override fun equals(other: Any?): Boolean {
		if (this === other) return true
		if (other !is Matrix) return false
		
		if ((other.rows != rows) || (other.columns != columns)) return false
		else {
			for (i in 0 until rows)
				for (j in 0 until columns)
					if (mat[i][j] != other.mat[i][j]) return false
		}
		
		return true
	}
	
	override fun hashCode(): Int {
		var result = rows
		result = 31 * result + columns
		result = 31 * result + mat.hashCode()
		return result
	}
	
	override fun toString(): String {
		var res = ""
		for (i in 0 until rows)
			res += mat[i].toString() + "\n"
		return res.dropLast(1)
	}
}

