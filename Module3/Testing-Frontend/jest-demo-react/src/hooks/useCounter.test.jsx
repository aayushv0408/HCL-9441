import { renderHook, act } from "@testing-library/react";
import useCounter from "./useCounter";

describe("useCounter Hook Testing", () => {
  test("initial count should be zero", () => {
    const { result } = renderHook(() => useCounter());
    expect(result.current.count).toBe(0);
  });
  test("should increment counter", () => {
    const { result } = renderHook(() => useCounter());
    act(() => {
      result.current.increment();
    });
    expect(result.current.count).toBe(1);
  });
  test("should decrement counter", () => {
    const { result } = renderHook(() => useCounter());
    act(() => {
      result.current.decrement();
    });
    expect(result.current.count).toBe(-1);
  });
  test("should reset counter", () => {
    const { result } = renderHook(() => useCounter());
    act(() => {
      result.current.increment();
      result.current.increment();
      result.current.reset();
    });
    expect(result.current.count).toBe(0);
  });
});