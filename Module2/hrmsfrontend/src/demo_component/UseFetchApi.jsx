import { useEffect, useState } from 'react'

function UseFetchApi() {
  const [serverData, setServerData] = useState('')

  useEffect(() => {
    async function getData() {
      try {
        const response = await fetch('http://localhost:8080/')
        const jsonData = await response.text()
        setServerData(jsonData)
      } catch (error) {
        console.error(error)
        setServerData('Not found')
      }
    }

    getData()
  }, [])

  return <div>{JSON.stringify(serverData)}</div>
}

export default UseFetchApi