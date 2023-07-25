// [변수] 입력 받은 A, B의 합 결과 변수에 저장해서 출력

    val a = 1
    val b = 2
    var sum : Int = a + b
    println(sum)

    val sub : Int = a - b
    println(sub)

    val multiply : Int = a*b
    println(multiply)


    val divide : Int = a/b
    println(divide)

    val remain : Int = a%b
    println(remain)


// [반복문] 1부터 10까지의 값 모두 출력

var count: Int = 0
for (i in 1..10) {
    count = count + i
}
println(count)

//[반복문 + 조건문] 1부터 10까지 짝수 출력
var even: Int = 0
for (i in 1..10) {
    if (i % 2 == 0) {
        println(i)
    }
}


//        [반복문 + 조건문] 1부터 10까지 홀수 출력
var odd: Int = 0
for (i in 1..10) {
    if (i % 2 == 1) {
        println(i)
    }
}


//        [반복문 + 리스트] 3의 배수, 3부터 30까지 10개 리스트에 저장해서 리스트 출력

// 빈 리스트를 만든다
// 그 다음에 반복문으로 범위 설정 / if문으로 또 범위 설정
// 리스트 추가


var list1 = mutableListOf<Int>()

for (i in 3..30) {
    if (i % 3 == 0) {
        list1.add(i)
    }
}

println(list1)


//        [반복문 + 리스트] 2의 배수, 2부터 20까지 10개 리스트에 저장해서 리스트 출력


var list2 = mutableListOf<Int>()

for (i in 2..20) {
    if (i % 2 == 0) {
        list2.add(i)
    }
}
println(list2)


