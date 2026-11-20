export const greet = (name) => {
  return `Hello, ${name}!`;
};

export const greetInHindi = (name) => {
  return `Namaste, ${name}!`;
};

export const processOrder = (data, dependecy) => {
  // apply logic
  // call payment gateway
  const paymentInfo = dependecy.processPayment(data.amount);
  return paymentInfo;
};

// implementation
const processPayment = (amount) => {
  // API Call to external payment gateway
  console.log("i am original processPayment");
  return { id: 123, amount };
};