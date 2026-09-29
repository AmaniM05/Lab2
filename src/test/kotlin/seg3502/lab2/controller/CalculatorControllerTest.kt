package seg3502.lab2.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.model
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.view
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class CalculatorControllerTest {

	private lateinit var mockMvc: MockMvc

	@BeforeEach
	fun setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(CalculatorController()).build()
	}

	@Test
	fun `opens the calculator page`() {
		mockMvc.perform(get("/"))
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
	}

	@Test
	fun `adds two numbers`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "2")
				.param("second", "3")
				.param("operation", "add"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("first", "2"))
			.andExpect(model().attribute("second", "3"))
			.andExpect(model().attribute("result", 5.0))
	}

	@Test
	fun `subtracts two numbers`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "10")
				.param("second", "4")
				.param("operation", "subtract"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("result", 6.0))
	}

	@Test
	fun `multiplies two numbers`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "3")
				.param("second", "5")
				.param("operation", "multiply"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("result", 15.0))
	}

	@Test
	fun `divides two numbers`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "10")
				.param("second", "4")
				.param("operation", "divide"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("result", 2.5))
	}

	@Test
	fun `shows an error for invalid input`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "hello")
				.param("second", "3")
				.param("operation", "add"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("first", "hello"))
			.andExpect(model().attribute("second", "3"))
			.andExpect(model().attribute("error", "Please enter a valid number in both fields."))
			.andExpect(model().attributeDoesNotExist("result"))
	}

	@Test
	fun `shows an error when dividing by zero`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "10")
				.param("second", "0")
				.param("operation", "divide"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("error", "A number cannot be divided by zero."))
			.andExpect(model().attributeDoesNotExist("result"))
	}

	@Test
	fun `shows an error for an unknown operation`() {
		mockMvc.perform(
			post("/calculate")
				.param("first", "10")
				.param("second", "2")
				.param("operation", "power"),
		)
			.andExpect(status().isOk)
			.andExpect(view().name("home"))
			.andExpect(model().attribute("error", "Please choose one of the available operations."))
			.andExpect(model().attributeDoesNotExist("result"))
	}
}
