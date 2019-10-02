import search


def print_result(search_type, result):
    print(search_type)
    while result.parent:
        print(result)
        result = result.parent


gp = search.GraphProblem("Arad", "Bucharest", search.romania_map)

# BFS results in the least amount of cities visited
print_result("BFS", search.breadth_first_graph_search(gp))
# DFS results in the longest route and most amount of cities visited
print_result("DFS", search.depth_first_graph_search(gp))
# A* results in the shortest path
print_result("A*", search.astar_search(gp))
