//[map]
//국가별 수도를 관리하는 맵 구성
//ex) "대한민국" -> "서울"
val capital =mapOf(
    "대한민국"to"서울",
    "미국"to"워싱턴DC",
    "일본"to"도쿄"
)


println(capital["대한민국"])

//[map]
//국가별 수도를 관리하는 맵 구성
//ex) "대한민국" -> "서울"
val capital =mapOf(
    "대한민국"to"서울",
    "미국"to"워싱턴DC",
    "일본"to"도쿄"
)


println(capital["대한민국"])




//[enum class]
//요일 enum class 구현

enum class Week {
    MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY;

//[enum class + 조건문 + 함수]
//요일 enum class 구현하고 전달받은 요일의 내일을 구하는 함수


    fun nextday(): Week =
        // this 로 내부의 Week 를 받는다
        when (this) {
            MONDAY -> TUESDAY
            TUESDAY -> WEDNESDAY
            WEDNESDAY -> THURSDAY
            THURSDAY -> FRIDAY
            FRIDAY -> SATURDAY
            SATURDAY -> SUNDAY
            SUNDAY -> MONDAY
        }
// tomorrow 함수는 nextday 함수를 받는다.
    fun tomorrow() : Week = nextday()
}
val today = Week.MONDAY
val tomorrow = today.tomorrow()
println(tomorrow)


