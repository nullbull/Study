package main

import "fmt"

func main()  {
	fmt.Println(getSumAndSub(1, 2))
	a := 1;
	test3(&a);

	fmt.Println(a);
	fmt.Println("a的地址", &a)

	fmt.Println(fff(sum, 2, 3))
	fmt.Println(mySum(1, 2))

}
func getSumAndSub(n1 int , n2 int) (int, int) {
	sum := n1 + n2;
	cheng := n1 * n2;
	return sum, cheng
}

func tx(n int, m int)(sum int, cheng int)  {
	sum = n + m;
	cheng = n * m;
	return
}
func sum(n int, m int) int {
	return n + m;
}

func solutuin(day int) int {
	if day == 10{
		return 1
	}
	return (solutuin(day + 1) + 1) * 2
}
func test3(n *int) {
	*n = *n + 1
}
func fff(funcvar func(int, int) int, m int, n int ) int {
	return funcvar(m, n)
}

func mySum(m, n float32) float32 {
	return m + n;
}
