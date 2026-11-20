import { getStudents } from "./studentService";

describe("Student API Testing", () => {
  afterEach(() => {
    jest.restoreAllMocks();
  });

  test("should fetch students successfully", async () => {
    const mockStudents = [
      { id: 1, name: "Rahul" },
      { id: 2, name: "Amit" },
      { id: 3, name: "Priya" },
    ];

    global.fetch = jest.fn().mockResolvedValue({
      ok: true,
      json: jest.fn().mockResolvedValue(mockStudents),
    });

    const result = await getStudents();

    expect(result).toEqual(mockStudents);

    expect(fetch).toHaveBeenCalledTimes(1);
  });
});
