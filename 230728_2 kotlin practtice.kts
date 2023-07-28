//  변수 리스트 함수 개념

//Kotlin 연습
//        [변수]
//아래 동작 구현에 코드를 추가하여 원하는 결과물을 출력하세요.
var first = "Park"
var second = "Junho"
var save = ""
save = first
first = second
second = save
println("결과물: $first $second") // "결과물: Junho Park"

//[리스트 + 변수]
//아래 동작 구현에 코드를 추가하여 원하는 결과물을 출력하세요.
val list1 = mutableListOf(1, 2)
val save1 = list1[0]
list1[0] = list1[1]
list1[1] = save1
println("결과물: $list1") //

//[조건문 + 반복문]
//전달받은 list에서 n보다 큰 수를 리스트로 출력하세요.
val list = listOf(5, 1, 7, 3, 8)
val n = 6
val newList = mutableListOf<Int>()
for (i in list) {
    if (i > n) {
        newList.add(i)
    }
}
println("결과물: $newList")



// 3가지를 각각 함수로 구현하세요.
fun name(first: String, second: String ) : String {
    return second + " " + first
}

println(name("Park", "Junho"))

---------------------------------

fun listswap(a: Int, b: Int): String {
    val list1 = mutableListOf(b, a)
    return "결과물: $list1"
}
println(listswap(1,2))

--------------------------------

fun nlist(n: Int, l: List<Int> ):String {
    val newList = mutableListOf<Int>()
    for (i in l) {
        if (i > n) {
            newList.add(i)
        }
    }
    return "결과물: $newList"
}

val list = listOf(5, 1, 7, 3, 8)
println(nlist(6,list))


