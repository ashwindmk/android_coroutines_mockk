package com.ashwin.coroutinesmockk

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.*
import kotlinx.coroutines.*
import org.json.JSONObject
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = Dispatchers.Unconfined

    @Before
    fun setup() {
        MockKAnnotations.init(this)
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun addOneTest() {
        val initialValue = 10

        val viewModel = TestableViewModel(dispatcher)

        mockkStatic("kotlinx.coroutines.DelayKt")
        coEvery { delay(5000) } returns Unit

        viewModel._count.value = initialValue
        viewModel.addOne()

        var expected = initialValue + 1
        var actual = viewModel.count.value
        assertEquals(expected, actual)
    }

    @Test
    fun subtractOneTest() {
        val initialValue = 10

        var viewModel = TestableViewModel(dispatcher)
        viewModel = spyk(viewModel)

        coEvery { viewModel.subtract(any(), 1) } coAnswers {
            (this.arg(0) as Int) - 1
        }

        viewModel._count.value = initialValue
        viewModel.subtractOne()

        coVerify { viewModel.subtract(any(), 1) }

        val expected = initialValue - 1
        val actual = viewModel.count.value
        assertEquals(expected, actual)
    }

    @Test
    fun networkResponseTest() {
        val viewModel = NetworkViewModel(dispatcher)

        viewModel.requestResponse()

        val res = viewModel.response.value
        val json = JSONObject(res)  // Note: Using JSON requires org.json:json lib as testImplementation
        val actual = json.getString("username")
        val expected = "Bret"

        assertEquals(expected, actual)
    }

    @Test
    fun mockNetworkResponseTest() {
        val username = "someone"

        val viewModel = NetworkViewModel(dispatcher)

        mockkStatic("com.ashwin.coroutinesmockk.NetworkUtilKt")
        every { getResponse(any()) } returns "{\"username\": \"$username\"}"

        viewModel.requestResponse()

        val res = viewModel.response.value
        val json = JSONObject(res)
        val actual = json.getString("username")
        val expected = username

        assertEquals(expected, actual)
    }

    @After
    fun clean() {
        clearAllMocks()
    }
}