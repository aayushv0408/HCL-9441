import { render, screen } from "@testing-library/react";

import userEvent from "@testing-library/user-event";

import StudentForm from "./StudentForm";

describe("Student Registration Testing", () => {
  test("should render registration form", () => {
    render(<StudentForm />);

    expect(
      screen.getByRole("heading", {
        name: "Student Registration",
      }),
    ).toBeInTheDocument();

    expect(
      screen.getByRole("button", {
        name: "Register Student",
      }),
    ).toBeInTheDocument();
  });

  test("should accept student name", async () => {
    const user = userEvent.setup();

    render(<StudentForm />);

    const input = screen.getByLabelText("Student Name");

    await user.type(input, "Rahul");

    expect(input).toHaveValue("Rahul");
  });

  test("should register student successfully", async () => {
    const user = userEvent.setup();

    render(<StudentForm />);

    await user.type(screen.getByLabelText("Student Name"), "Rahul");

    await user.click(
      screen.getByRole("button", {
        name: "Register Student",
      }),
    );

    expect(
      screen.getByText("Student Rahul added successfully"),
    ).toBeInTheDocument();
  });

  test("should display validation message for empty name", async () => {
    const user = userEvent.setup();

    render(<StudentForm />);

    await user.click(
      screen.getByRole("button", {
        name: "Register Student",
      }),
    );

    expect(screen.getByText("Please enter student name")).toBeInTheDocument();
  });
});