import { render, screen } from "@testing-library/react";

import Student from "./Student";

describe("Student Component Testing", () => {
  test("should render student heading", () => {
    render(<Student />);

    const heading = screen.getByText("Student Management");

    expect(heading).toBeInTheDocument();
  });

  test("should render welcome message", () => {
    render(<Student />);

    expect(screen.getByText("Welcome to Student Portal")).toBeInTheDocument();
  });

  test("should render Add Student button", () => {
    render(<Student />);

    expect(
      screen.getByRole("button", {
        name: "Add Student",
      }),
    ).toBeInTheDocument();
  });
});