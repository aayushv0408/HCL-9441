import { greet, greetInHindi, processOrder } from "/App.js";
import { describe, mock, test } from "node:test";
import assert from "node:assert";

describe("order feature", () => {
  test("that it process the order corretly", () => {
    // mocked function
    const mockedProcessPayment = mock.fn((amount) => {
      console.log("i am mocked process payment function");
      return { id: "123", amount };
    });
    const expected = { id: "123", amount: 5000 };
    const actual = processOrder(
      { amount: 5000 },
      { processPayment: mockedProcessPayment },
    );
    assert.deepStrictEqual(actual, expected);
  });
});

describe("Greeting functions", () => {
  test("greet returns the correct greeting", () => {
    const expected = `Hello, World!`;
    const actual = greet("World");

    assert.strictEqual(actual, expected);
  });

  test("greet returns in hindi", () => {
    const expected = `Namaste, Duniya!`;
    const actual = greetInHindi("Duniya");
    assert.strictEqual(actual, expected);
  });
});