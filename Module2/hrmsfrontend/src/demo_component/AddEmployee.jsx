
function AddEmployee() {

  async function addEmployee(e) {
    e.preventDefault();

    let employee = {
      emp_id: e.target.emp_id.value,
      emp_name: e.target.emp_name.value,
      stack: e.target.stack.value,
      designation: e.target.designation.value
    };

    let response = await fetch('http://localhost:8080/addNewEmployee', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(employee)
    });

    if (response.ok) {
      alert('Employee added successfully');
    } else {
      alert('Failed to add employee');
    }
  }  

  return (
    <div>
      <h2>Add Employee</h2>

      <form onSubmit={addEmployee}>
        <input name="emp_id" type="number" placeholder="Employee ID" required />
        <br /><br />

        <input name="emp_name" type="text" placeholder="Employee Name" required />
        <br /><br />

        <input name="stack" type="text" placeholder="Stack" required />
        <br /><br />

        <input name="designation" type="text" placeholder="Designation" required />
        <br /><br />

        <button type="submit">Add Employee</button>
      </form>
    </div>
  );
}

export default AddEmployee;

// private long emp_id;
//     private String emp_name;
//     private String stack;
//     private String designation;