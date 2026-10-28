import { useEffect, useState } from 'react'

function SampleCode() {
    const[counter,setCounter]=useState(0)
    const[pointer,setPointer]=useState(0)
    useEffect(()=>{
        console.log( "counter "+counter)
        console.log("pointer "+pointer)
    },[counter,pointer])

    function changeCounter(){
    setCounter(counter+1)
    }
function changePointer(){
    setPointer(pointer-2)
}

  return (
    <>
    {/* <div >Employee Details</div>
    <div style={{border:'2px solid red'}}>
    <h3>Name:{empdata.ename}</h3>
    <h3>Emp Id:{empdata.empid}</h3>
    </div> */}
    <h1>counter:{counter}</h1>
    <h1>Pointer:{pointer}</h1>
  <button onClick={changeCounter} >Counter:{counter}</button>
   <button onClick={changePointer} >Pointer:{pointer}</button>
    
    </>
  )
}

export default SampleCode
