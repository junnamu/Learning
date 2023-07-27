//        [조건문+함수]
//세개의 Int a,b,c가 주어졌을 때 두번째로 큰 수를 Int로 반환하는 함수
//ex) 12, 51, 3 -> 12

// Int a, b, c가 주어진다.
// 함수 second가 두번째로 큰 수를 int로 반환한다.
// 함수 second를 출력한다.

val a = 12
val b = 51
val c = 3


fun second() : Int {
    if (a < b) {
        if (b < c) {
            return b
        } else if (a < c) {
            return c
        } else 
            return a
    } else {
        if (b > c) {
            return b
        } else if (a < c) {
            return a
        } else 
            return c
    }
}

println(second())
