//삽입정렬

fun insertionSort(input: List<Int>): List<Int> {
    // input 리스트를 변경 가능한 리스트로 변경
    val list = input.toMutableList()
    // 리스트 요소 갯수만큼 반복한다
    for (i in 1..list.size - 1) {
    // 비교할 현재 요소는 인덱스 i
        val current = list[i]
    // 현재요소의 바로 앞 요소를 변수 j 설정
        var j = i - 1
    //  j가 0이상이면서 현재요소보다 이전요소가 작은 값 찾을 때까지 아래 명령 반복
        while (j >= 0 && list[j] < current) {
    // 반복할 때마다, 찾은 요소(list[j])를 오른쪽으로 한 칸 이동
            list[j + 1] = list[j]
    // 옮긴 뒤 후위감소로 j를 하나씩 감소시켜서 다음 이전요소랑 비교
            j--
        }
    // 현재요소를 올바른 위치에 삽입
        list[j + 1] = current
    }
    return list
}

val mylist = listOf(10, 5, 8, 2, 7)
val sortedlist = insertionSort(mylist)
println(sortedlist)
