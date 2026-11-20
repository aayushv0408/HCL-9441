# Testing

1. unit testing -> isolation
2. Integrated Testing
3. End to end Testing

NodeJs bydefault support inbuild testing framework from node >=23 version

> node-test > create simple node app

> npm init -y

update package.json

create app.js in node-test

```
export const greet = (name) => {
  return `Hello, ${name}`;
};
```

export here signify that the method should be used by other file