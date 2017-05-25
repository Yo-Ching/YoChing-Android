package tech.redroma.yoching

import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).

 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class ExampleUnitTest
{
    @Test
    @Throws(Exception::class)
    fun testAddition()
    {
        assertEquals(4, (2 + 2).toLong())
    }

    @Test
    @Throws(Exception::class)
    fun testSubtract()
    {
        assertThat(3 -1 , equalTo(2))
    }
}