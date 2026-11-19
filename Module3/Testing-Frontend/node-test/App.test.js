
import { greet, greetInHindi } from './App.js';
import { describe, test } from 'node:test';
import assert from 'node:assert';

describe('greet function', () => {

    // Test Case 1: English greeting
    test('greet function should return a greeting message', () => {
        const name = 'John';
        const expectedGreeting = `Hello, ${name}!`;
        const actualGreeting = greet(name);

        assert.strictEqual(actualGreeting, expectedGreeting);
    });

    // Test Case 2: Hindi greeting
    test('greet return in hindi', () => {
        const name = 'John';
        const expectedGreeting = `नमस्ते, ${name}!`;
        const actualGreeting = greetInHindi(name);

        assert.strictEqual(actualGreeting, expectedGreeting);
    });

});
