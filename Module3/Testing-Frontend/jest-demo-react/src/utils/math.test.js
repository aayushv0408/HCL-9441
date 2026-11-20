import {add, subtract, multiply, divide} from './math'
describe('math utils', () => {
  test('add two numbers', () => {
    expect(add(2, 3)).toBe(5);
  });

  test('subtract two numbers', () => {
    expect(subtract(5, 3)).toBe(2);
  });
  
  test('multiply two numbers', () => {
    expect(multiply(2, 3)).toBe(6);
  });
});