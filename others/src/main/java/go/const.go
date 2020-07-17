package main

import (
	"fmt"
	"unsafe"
)

//const (
//	a = "abc";
//	b = len(a);
//	c = unsafe.Sizeof(a);
//)
const (
	a = iota
	b
	c
	d = "hha"
	e
	f = 100
	g
	h = iota
	i
)
func main()  {
	const LENGTH int = 10;
	const WIDTH int = 20;
	area := LENGTH * WIDTH;
	fmt.Println(area);
	fmt.Print(LENGTH);
	fmt.Println(a, b, c, d, e, f, g, h, i);
	numbers :=[6]int{1, 2, 3, 5}
	for a := 0; a < len(numbers); a++ {
		fmt.Println(numbers[a]);
	}

	//for i, x := range numbers  {
	//	fmt.Print("第 %d x's count = %d \n",i ,x);
	//}
	//
	var i, j int
	for i = 2; i < 100; i++ {
		for j = 2; j <= (i /j); j++ {
			if(i %j == 0) {
				break;
			}
		}
		if (j > (i / j)) {
			fmt.Printf("%d \n", i)
		}
	}

	var str string = "zwt bu niao wo"
	for index, val := range str {
		fmt.Printf("index= %d value = %c\n", index, val);
	}
	var str2  string = "猪炜婷不鸟我"
	//str3 := []rune(str2)
	//for k := 0; k < len(str3) ; k++ {
	//	fmt.Printf("%c \n", str3[i])
	//}
	for index, val := range str2 {
		fmt.Printf("index = %d value = %c\n", index, val, unsafe.Sizeof(val))
	}
	var name string
	fmt.Scanf("%s", &name);
	fmt.Printf("%s", name)



}
