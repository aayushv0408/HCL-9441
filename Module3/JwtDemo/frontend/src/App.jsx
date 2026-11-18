import { useEffect, useState } from "react";
import api from "./api";
import AuthForm from "./AuthForm";
import PostForm from "./PostForm";

const App = () => {
  const [user, setUser] = useState(localStorage.getItem("username"));
  const [posts, setPosts] = useState([]);

  const loadPosts = async () => {
    const { data } = await api.get("/posts");
    setPosts(data);
  };
  useEffect(() => {
    loadPosts();
  }, []);
  const logout = () => {
    localStorage.removeItem("token");
    localStorage.removeItem("username");
    setUser(null);
  };

  return (
    <div>
      {/* Header */}
      <header>
        <h1>JWT Blog Demo</h1>

        {user && <button onClick={logout}>Logout</button>}
      </header>

      {/* Authentication and Post Form */}
      <section>
        {user ? (
          <>
            <p>Logged in as {user}</p>

            <PostForm onCreated={loadPosts} />
          </>
        ) : (
          <AuthForm onLogin={setUser} />
        )}
      </section>

      {/* All Posts */}
      <section>
        <div>
          <h2>All Posts</h2>

          <span>
            {posts.length} {posts.length === 1 ? "Post" : "Posts"}
          </span>
        </div>

        {posts.length === 0 && (
          <div>
            <p>No posts yet. Be the first to publish one!</p>
          </div>
        )}

        <div>
          {posts.map((p) => (
            <article key={p.id}>
              <h2>{p.title}</h2>

              <p>{p.content}</p>

              <div>
                <span>
                  By <span>{p.author}</span>
                </span>

                <time dateTime={p.createdAt}>
                  {new Date(p.createdAt).toLocaleString()}
                </time>
              </div>
            </article>
          ))}
        </div>
      </section>
    </div>
  );
};
export default App;
