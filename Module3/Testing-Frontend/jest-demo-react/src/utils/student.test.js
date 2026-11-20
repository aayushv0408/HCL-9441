import { isEligible } from "./student";
describe("isEligible", () => {
  test("returns true for attendance >= 75", () => {
    expect(isEligible(80)).toBe(true);
  });
  test("returns false for attendance < 75", () => {
    expect(isEligible(70)).toBe(false);
  });
}); 