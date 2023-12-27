import json
import random

def generate_graph(num_nodes):
    nodes = []

    for i in range(1, num_nodes + 1):
        node = {"id": f"Node {i}", "neighbors": []}
        # Ensure at least one neighbor for each node
        num_neighbors = random.randint(1, num_nodes - 1)
        neighbors = random.sample(range(1, num_nodes + 1), num_neighbors)
        
        # Remove self from neighbors if present
        if i in neighbors:
            neighbors.remove(i)

        for neighbor in neighbors:
            weight = random.randint(1, 10)
            node["neighbors"].append({"id": f"Node {neighbor}", "weight": weight})

        nodes.append(node)

    return {"nodes": nodes}

# Generate a graph with 100 nodes
graph = generate_graph(900)

# Save the graph to a JSON file
with open('graph900.json', 'w') as file:
    json.dump(graph, file, indent=2)

"graph.json file has been generated."
