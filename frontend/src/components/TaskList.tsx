import React from "react";
import { useQuery } from "@tanstack/react-query";
import { fetchTasks } from "../api/tasks";
import TaskCard from "./TaskCard";

export default function TaskList(){
  const { data, isLoading } = useQuery(["tasks"], fetchTasks);
  if(isLoading) return <div>Loading...</div>;
  return <div>{Array.isArray(data) ? data.map((t:any)=>(<TaskCard key={t.id} task={t} />)) : null}</div>;
}
