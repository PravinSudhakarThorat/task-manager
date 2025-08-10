import React from "react";
export default function TaskCard({task}:{task:any}){
  if(!task) return null;
  return (
    <div style={{border:'1px solid #ddd', padding:8, margin:6}}>
      <strong>{task.title}</strong>
      <div>Status: {task.status}</div>
      <div>Priority: {task.priority}</div>
    </div>
  );
}
