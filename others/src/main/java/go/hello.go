package main

import (
	"fmt"
)

var (
	a int
	b int;
)
func main()  {
	a, b = 1, 3;
	fmt.Println("Hello, niu")
	fmt.Print("zwt zzz\n")
	var zwt int = 123;
	fmt.Println(zwt);
	//nzh := 123;
	c, d := "a", "b";
	fmt.Print("%d\n", a );
	var niu = c;
	fmt.Println("c" +  c, "niu"  + niu);
	niu = "e";
	fmt.Println("c" +  c, "niu"  + niu);

	fmt.Println(c, d);
}