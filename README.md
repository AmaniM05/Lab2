# Lab 2

This project is a basic calculator made with Kotlin, Spring MVC, and Thymeleaf.

## Shared page contract

The main page uses the route `/`.

The calculator form sends a POST request to `/calculate`.

The first number field is named `first`.

The second number field is named `second`.

The operation field is named `operation`. Its possible values are `add`, `subtract`, `multiply`, and `divide`.

The controller gives the page the values `first`, `second`, `result`, and `error` through the model.

When the calculation works, the answer is stored in `result`. When the input is not valid, the message is stored in `error`.

## Running the project

1. Make sure Java 17 is selected.

2. Open a terminal in the project folder.

3. Run `./gradlew bootRun`.

4. Open `http://localhost:8080` in a browser.

## Running the tests

Run `./gradlew test` from the project folder.
