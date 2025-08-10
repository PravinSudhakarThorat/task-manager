import React from "react";
import { useForm } from "react-hook-form";
import { login as apiLogin } from "../api/auth";

export default function Login(){
  const { register, handleSubmit } = useForm();
  const onSubmit = async (d:any) => {
    try {
      const res = await apiLogin(d.username, d.password);
      localStorage.setItem("token", res.token);
      localStorage.setItem("username", res.username);
      alert("Logged in as " + res.username);
    } catch (e) { alert("Login failed"); }
  };
  return (
    <form onSubmit={handleSubmit(onSubmit)}>
      <input {...register("username")} placeholder="username" />
      <input {...register("password")} type="password" placeholder="password" />
      <button type="submit">Login</button>
    </form>
  );
}
