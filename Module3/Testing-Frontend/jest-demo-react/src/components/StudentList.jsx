import { useEffect, useState } from "react";

import { getStudents } from "../service/studentService";

const StudentList = () => {
  const [students, setStudents] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadStudents = async () => {
    const { data } = await getStudents();

    setStudents(data);
    setLoading(false);
  };

  useEffect(() => {
    loadStudents();
  }, []);
  if (loading) {
    return <p>Loading students...</p>;
  }

  return (
    <div>
      <h2>Student List</h2>

      <ul>
        {students.map((student) => (
          <li key={student.id}>{student.name}</li>
        ))}
      </ul>
    </div>
  );
};

export default StudentList;