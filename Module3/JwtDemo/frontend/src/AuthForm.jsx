import api from "./api";
import SubmitButton from "./SubmitButton";
import { useState, useActionState } from "react";

const AuthForm = ({ onLogin }) => {
  const [mode, setMode] = useState("login");
  const submitAction = async (prevState, formData) => {
    const username = formData.get("username")?.trim();
    const password = formData.get("password")?.trim();

    if (!username || !password) {
      return {
        error: "username and password is required",
        msg: "",
        username: username || "",
      };
    }
    try {
      if (mode == "register") {
        await api.post("/auth/register", { username, password });
        setMode("login");
        return {
          error: null,
          msg: "user registered successfully",
          username,
        };
      }
      // login mode
      const { data } = await api.post("/auth/login", { username, password });
      localStorage.setItem("token", data.token);
      localStorage.setItem("username", username);
      onLogin(username);
      return {
        error: null,
        msg: "",
        username: data.username,
      };
    } catch (error) {
      return {
        error:
          error.response?.data?.error ||
          error.response?.dta?.message ||
          "Something went wrong. Please try again.",
        msg: "",
        username,
      };
    }
  };
  const [state, formAction, isPending] = useActionState(submitAction, {
    error: null,
    msg: "",
    username: "",
  });
  return (
    <form action={formAction}>
      <h3>{mode === "login" ? "Login" : "Register"}</h3>
      <input
        type="text"
        name="username"
        id="username"
        placeholder="Username"
        defaultValue={state.username}
        required
        disabled={isPending}
      />
      <input
        type="password"
        name="password"
        id="password"
        placeholder="Password"
        required
        disabled={isPending}
      />
      <SubmitButton />
      <button
        type="button"
        disabled={isPending}
        onClick={() => {
          setMode(mode === "login" ? "register" : "login");
        }}
      >
        Switch to {mode === "login" ? "Register" : "Login"}
      </button>
      {state.msg && <p role="status">{state.msg}</p>}
      {state.error && <p role="status">{state.error}</p>}
    </form>
  );
};
export default AuthForm;
