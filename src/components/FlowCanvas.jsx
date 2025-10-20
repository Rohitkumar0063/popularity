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

      // Declare arrays first
      const newNodes = [];
      const newEdges = [];

      // Build nodes and edges
      users.forEach((user, index) => {
        newNodes.push({
          id: user.stringId,
          type: 'default',
          data: { label: user.username },
          position: { x: 150 * index, y: 100 }, // spacing nodes horizontally
        });

        // Build edges for friends
        if (user.friends && user.friends.length > 0) {
          user.friends.forEach(friend => {
            if (friend.stringId) {
              newEdges.push({
                id: `e-${user.stringId}-${friend.stringId}`,
                source: user.stringId,
                target: friend.stringId,
                animated: true,
                style: { stroke: '#555' },
              });
            }
          });
        }
      });

      setNodes(newNodes);
      setEdges(newEdges);
    };

    fetchData();
  }, []);

  return (
    <div style={{ width: '100%', height: '500px', border: '1px solid #ddd' }}>
      <ReactFlow
        nodes={nodes}
        edges={edges}
        fitView
        nodesDraggable={true}
        nodesConnectable={true}
        elementsSelectable={true}
        snapToGrid={true}
        snapGrid={[15, 15]}
      >
        <MiniMap />
        <Controls />
        <Background />
      </ReactFlow>
    </div>

  );
};

export default FlowCanvas;
