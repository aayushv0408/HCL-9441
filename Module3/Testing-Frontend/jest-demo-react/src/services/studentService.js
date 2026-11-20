export const getStudents = async () => {
  const response = await fetch("http://localhost:8080/api/students");

  if (!response.ok) {
    throw new Error("Failed to fetch students");
  }

  return response.json();
};

export const deleteStudent = async (id) => {
  return fetch(`http://localhost:8080/api/students/${id}`, {
    method: "DELETE",
  });
};
