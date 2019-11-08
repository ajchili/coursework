from csp import AC3, MapColoringCSP, backtracking_search

kirin_csp = MapColoringCSP(
    list('RGB'),
    "A: B C; B: C D E; C: F; D: E H G; E: F; F: G; G: H; H: "
)

print(AC3(kirin_csp))
print(backtracking_search(kirin_csp))