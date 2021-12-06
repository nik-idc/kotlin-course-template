import org.junit.Assert
import org.junit.Test

class MutableMatrixTest {
	@Test
	fun testSet() {
		val mat = MutableMatrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		
		mat[1, 1] = 123.0
		Assert.assertEquals(123.0, mat[1, 1], 0.00001)
	}
	
	@Test
	fun testPlusAssign() {
		val mat = MutableMatrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		val matAdd = Matrix(
			mutableListOf(
				mutableListOf(1.0, -2.0, 3.0),
				mutableListOf(4.0, -7.0, 5.0)
			)
		)
		val plusRes = Matrix(
			mutableListOf(
				mutableListOf(3.0, 2.0, 2.0),
				mutableListOf(7.0, -2.0, 7.0)
			)
		)
		
		mat += matAdd
		Assert.assertEquals(plusRes, mat)
	}
	
	@Test
	fun testMinusAssign() {
		val mat = MutableMatrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		val matSub = Matrix(
			mutableListOf(
				mutableListOf(1.0, -2.0, 3.0),
				mutableListOf(4.0, -7.0, 5.0)
			)
		)
		val minusRes = Matrix(
			mutableListOf(
				mutableListOf(1.0, 6.0, -4.0),
				mutableListOf(-1.0, 12.0, -3.0)
			)
		)
		
		mat -= matSub
		Assert.assertEquals(minusRes, mat)
	}
	
	@Test
	fun testScalarTimesAssign() {
		val mat = MutableMatrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		val scalar = 3.0
		val scalarTimesRes = Matrix(
			mutableListOf(
				mutableListOf(2.0 * scalar, 4.0 * scalar, -1.0 * scalar),
				mutableListOf(3.0 * scalar, 5.0 * scalar, 2.0 * scalar)
			)
		)
		
		mat *= scalar
		Assert.assertEquals(scalarTimesRes, mat)
	}
	
	@Test
	fun testTimesAssign() {
		val mat1 = MutableMatrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		val mat2 = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0),
				mutableListOf(3.0, 5.0),
				mutableListOf(1.0, 7.0)
			)
		)
		val matTimesRes = Matrix(
			mutableListOf(
				mutableListOf(15.0, 21.0),
				mutableListOf(23.0, 51.0)
			)
		)
		
		mat1 *= mat2
		Assert.assertEquals(matTimesRes, mat1)
	}
}