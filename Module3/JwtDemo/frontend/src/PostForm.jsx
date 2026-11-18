import { useActionState } from "react";
import SubmitButton from "./SubmitButton";
import api from "./api";

const PostForm = ({ onCreated }) => {
  const submitAction = async (prev, formData) => {
    const title = formData.get("title")?.trim();
    const content = formData.get("content")?.trim();
    if (!title || !content) {
      return {
        error: "Title and content is required",
        msg: "",
        title: title || "",
        content: content || "",
      };
    }
    try {
      await api.post("/posts", { title, content });
      await onCreated?.();

      return {
        error: null,
        msg: "post created successfully",
        title: "",
        content: "",
      };
    } catch (error) {
      return {
        error:
          error.response?.data?.error ||
          error.response?.dta?.message ||
          "failed to create post. Please try again.",
        msg: "",
        title,
        content,
      };
    }
  };
  const [state, formAction, isPending] = useActionState(submitAction, {
    error: null,
    msg: "",
    title: "",
    content: "",
  });
  return (
    <form action={formAction}>
      <h3>Write a Post</h3>
      <input
        type="text"
        name="title"
        id="title"
        placeholder="Title"
        defaultValue={state.title}
        required
        disabled={isPending}
      />
      <textarea
        name="content"
        id="content"
        placeholder="Content"
        rows={4}
        defaultValue={state.content}
        required
        disabled={isPending}
      ></textarea>
      <SubmitButton />
      {state.msg && <p role="status">{state.msg}</p>}
      {state.error && <p role="status">{state.error}</p>}
    </form>
  );
};
export default PostForm;
