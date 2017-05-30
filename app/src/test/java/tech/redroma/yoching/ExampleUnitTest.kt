package tech.redroma.yoching

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.isEmptyString
import org.hamcrest.CoreMatchers.equalTo
import org.junit.Assert.assertEquals
import org.junit.Assert.assertThat
import org.junit.Test
import tech.redroma.yoching.test.isNull

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
        assertEquals(4, 2 + 2)
    }

    @Test
    @Throws(Exception::class)
    fun testSubtract()
    {
        assertThat(3 -1 , equalTo(2))
        assertThat(null, isNull)
        assertThat("", isEmptyString)
    }
}