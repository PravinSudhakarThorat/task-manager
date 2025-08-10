import React from "react";
import { QueryClient, QueryClientProvider } from "@tanstack/react-query";
import Login from "./components/Login";
import Dashboard from "./components/Dashboard";

const qc = new QueryClient();

export default function App(){
  return (
    <QueryClientProvider client={qc}>
      <div style={{padding:20}}>
        <h2>Task Manager (Demo)</h2>
        <Login />
        <hr />
        <Dashboard />
      </div>
    </QueryClientProvider>
  );
}
