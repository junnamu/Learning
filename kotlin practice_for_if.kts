// 저장된 리스트에서 0을 포함해 짝수 index (0, 2, 4...) 값을 모두 출력 3가지 표현

val list1 = listOf ("a", "b", "c", "d")
for (i in 0..list1.size-1) {
    if (i % 2 == 0) {
        println(list1[i])
    }
}

val list2 = listOf ("a", "b", "c", "d")
for (i in 0 until list2.size) {
    if (i % 2 == 0) {
        println(list2[i])
    }
}

val list3 = listOf ("a", "b", "c", "d")
for (i in list3.indices) {
    if (i % 2 == 0) {
        println(list3[i])
    }
}

// 저장된 리스트에서 홀수 index (1, 3, 5...) 값을 모두 출력 3가지 표현
val list1 = listOf ("a", "b", "c", "d")
for (i in 0..list1.size-1) {
    if (i % 2 == 1) {
        println(list1[i])
    }
}

val list2 = listOf ("a", "b", "c", "d")
for (i in 0 until list2.size) {
    if (i % 2 == 1) {
        println(list2[i])
    }
}

val list3 = listOf ("a", "b", "c", "d")
for (i in list3.indices) {
    if (i % 2 == 1) {
        println(list3[i])
    }
}
