package main

import "fmt"

var age = test()

func test() int {
	fmt.Println("test call")
	return 90
}
func init()  {
	fmt.Println("init call")
}

func main()  {
	/**二位数组
	 */
	var a[10][10] int;
	for i := 0; i < 10 ; i++ {
		for j := 0; j < 10; j++ {
			a[i][j] = i * j;
		}
	}
	for index, val := range a {
		for j, temp := range val {
			fmt.Printf("%d %d, %d\n", index, j, temp)
		}
	}
	fmt.Println("aaa ", age)
}


