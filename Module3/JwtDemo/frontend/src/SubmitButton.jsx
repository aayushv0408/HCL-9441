import { useFormStatus } from "react-dom";

const SubmitButton = () => {
  const { pending } = useFormStatus();

  return (
    <button
      type="submit"
      disabled={pending}
      className={`w-full flex items-center justify-center gap-2
        px-6 py-3 rounded-lg font-semibold text-white
        shadow-md transition-all duration-300
        focus:outline-none focus:ring-4 focus:ring-blue-300
        ${
          pending
            ? "bg-blue-400 cursor-not-allowed opacity-80"
            : "bg-blue-600 hover:bg-blue-700 active:scale-95"
        }`}
    >
      {pending && (
        <svg className="w-5 h-5 animate-spin" viewBox="0 0 24 24" fill="none">
          <circle
            className="opacity-25"
            cx="12"
            cy="12"
            r="10"
            stroke="currentColor"
            strokeWidth="4"
          />
          <path
            className="opacity-75"
            fill="currentColor"
            d="M4 12a8 8 0 018-8v4a4 4 0 00-4 4H4z"
          />
        </svg>
      )}

      {pending ? "Submitting Securely..." : "Submit"}
    </button>
  );
};

export default SubmitButton;
