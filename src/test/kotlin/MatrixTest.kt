import org.junit.Assert
import org.junit.Test

class MatrixTest {
	@Test
	fun testEquals() {
		val mat1 = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		
		val compareMat = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		Assert.assertEquals(true, mat1 == compareMat)
	}
	
	@Test
	fun testPlus() {
		// Test valid plus
		val mat1 = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		
		val mat2 = Matrix(
			mutableListOf(
				mutableListOf(1.0, 2.0, 3.0),
				mutableListOf(2.0, 3.0, 1.0)
			)
		)
		val plusRes = Matrix(
			mutableListOf(
				mutableListOf(3.0, 6.0, 2.0),
				mutableListOf(5.0, 8.0, 3.0)
			)
		)
		Assert.assertEquals(plusRes, mat1 + mat2)
		
		// Test invalid plus
		val invalidMat1 = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0, 2.0),
				mutableListOf(3.0, 5.0, 2.0, -3.0)
			)
		)
		val invalidMat2 = Matrix(
			mutableListOf(
				mutableListOf(1.0, 2.0, 3.0),
				mutableListOf(2.0, 3.0, 1.0)
			)
		)
		
		val caught = try {
			invalidMat1 + invalidMat2
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testMinus() {
		// Test valid minus
		val mat1 = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		
		val mat2 = Matrix(
			mutableListOf(
				mutableListOf(1.0, 2.0, 3.0),
				mutableListOf(2.0, 3.0, 1.0)
			)
		)
		val minusRes = Matrix(
			mutableListOf(
				mutableListOf(1.0, 2.0, -4.0),
				mutableListOf(1.0, 2.0, 1.0)
			)
		)
		Assert.assertEquals(minusRes, mat1 - mat2)
		
		// Test invalid minus
		val invalidMat1 = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0, 2.0),
				mutableListOf(3.0, 5.0, 2.0, -3.0)
			)
		)
		val invalidMat2 = Matrix(
			mutableListOf(
				mutableListOf(1.0, 2.0, 3.0),
				mutableListOf(2.0, 3.0, 1.0)
			)
		)
		
		val caught = try {
			invalidMat1 - invalidMat2
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testUnaryMinus() {
		val mat = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		val negatedMat = Matrix(
			mutableListOf(
				mutableListOf(-2.0, -4.0, 1.0),
				mutableListOf(-3.0, -5.0, -2.0)
			)
		)
		Assert.assertEquals(negatedMat, -mat)
	}
	
	@Test
	fun testScalarTimes() {
		val mat = Matrix(
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
		Assert.assertEquals(scalarTimesRes, mat * scalar)
	}
	
	@Test
	fun testScalarDiv() {
		// Test valid scalar div
		val mat = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		val scalar = 3.0
		val scalarTimesRes = Matrix(
			mutableListOf(
				mutableListOf(2.0 / scalar, 4.0 / scalar, -1.0 / scalar),
				mutableListOf(3.0 / scalar, 5.0 / scalar, 2.0 / scalar)
			)
		)
		Assert.assertEquals(scalarTimesRes, mat / scalar)
		
		// Test invalid scalar div
		val caught = try {
			mat / 0.0
			false
		} catch (exc: IllegalArgumentException) {
			true
		}
		Assert.assertEquals(true, caught)
	}
	
	@Test
	fun testTimes() {
		val mat1 = Matrix(
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
		Assert.assertEquals(matTimesRes, mat1 * mat2)
	}
	
	@Test
	fun testGet() {
		val mat = Matrix(
			mutableListOf(
				mutableListOf(2.0, 4.0, -1.0),
				mutableListOf(3.0, 5.0, 2.0)
			)
		)
		
		val elem = 5.0
		Assert.assertEquals(elem, mat[1, 1], 0.00001)
	}
}