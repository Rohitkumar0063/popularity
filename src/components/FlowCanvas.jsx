import React, { useEffect, useState } from 'react';
import ReactFlow, { MiniMap, Background, Controls } from 'react-flow-renderer';
import { getUsers } from '../api';
import 'react-flow-renderer/dist/style.css';
import 'react-flow-renderer/dist/theme-default.css';


const FlowCanvas = () => {
  const [nodes, setNodes] = useState([]);
  const [edges, setEdges] = useState([]);

  useEffect(() => {
    const fetchData = async () => {
      const users = await getUsers();
      console.log("Fetched users:", users);

      // Create nodes first
      const newNodes = users.map((user, index) => ({
        id: user.stringId,
        data: { label: user.username },
        position: { x: 100 * index, y: 50 },
      }));

      // Create edges
      const newEdges = [];
      users.forEach(user => {
        if (user.friends && user.friends.length > 0) {
          user.friends.forEach(friend => {
            const friendId = friend.stringId;
            newEdges.push({
              id: `e-${user.stringId}-${friendId}`,
              source: user.stringId,
              target: friendId,
              animated: true,
              style: { stroke: '#555' },
            });
          });
        }
      });

      setNodes(newNodes);
      setEdges(newEdges);
    };

    fetchData();
  }, []);



  return (
    <div style={{ height: '500px' }}>
      <ReactFlow nodes={nodes} edges={edges}>
        <MiniMap />
        <Controls />
        <Background />
      </ReactFlow>
    </div>
  );
};

export default FlowCanvas;
