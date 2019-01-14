men([a,b,c,d,e,f]).
women([p1,p2,p3,p4,p5,p6]).

preference(a,[p2,p1,p5,p6,p3,p4]).
preference(b,[p3,p5,p4,p2,p1,p6]).
preference(c,[p2,p5,p6,p3,p1,p4]).
preference(d,[p3,p5,p6,p2,p1,p4]).
preference(e,[p5,p2,p3,p4,p1,p6]).
preference(f,[p4,p1,p3,p5,p2,p6]).

preference(p1,[f,a,b,c,e,d]).
preference(p2,[a,d,e,f,b,c]).
preference(p3,[f,d,b,c,e,a]).    
preference(p4,[f,b,a,e,d,c]).
preference(p5,[c,b,a,d,e,f]).
preference(p6,[c,b,a,d,e,f]).

