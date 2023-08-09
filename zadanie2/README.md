T -> F{"+" | "-" | "⊕"F}
F -> P{"*" | "xxx"P}
P -> S{"/"S}
S -> ["-"]A
A -> B["^"A]
B -> number | "(" T ")"
