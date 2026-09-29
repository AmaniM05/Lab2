package seg3502.lab2.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class CalculatorController {

	@GetMapping("/")
	fun home(): String {
		return "home"
	}

	@PostMapping("/calculate")
	fun calculate(
		@RequestParam first: String,
		@RequestParam second: String,
		@RequestParam operation: String,
		model: Model,
	): String {
		model.addAttribute("first", first)
		model.addAttribute("second", second)

		val firstNumber = first.toDoubleOrNull()
		val secondNumber = second.toDoubleOrNull()

		if (firstNumber == null || secondNumber == null) {
			model.addAttribute("error", "Please enter a valid number in both fields.")
			return "home"
		}

		val result = when (operation) {
			"add" -> firstNumber + secondNumber
			"subtract" -> firstNumber - secondNumber
			"multiply" -> firstNumber * secondNumber
			"divide" -> {
				if (secondNumber == 0.0) {
					model.addAttribute("error", "A number cannot be divided by zero.")
					return "home"
				}

				firstNumber / secondNumber
			}
			else -> {
				model.addAttribute("error", "Please choose one of the available operations.")
				return "home"
			}
		}

		model.addAttribute("result", result)
		return "home"
	}
}
