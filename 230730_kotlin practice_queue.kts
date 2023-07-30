// 단어 카드 섞기
// 알파벳 순으로 단어 카드가 순서대로 있다. 단어카드를 섞을때마다 제일 앞에 있는 단어 카드를 버리고 그다음에 있는 카드를 제일 마지막으로 옮긴다. (큐 활용)
// N이 주어졌을 때 제일 앞의 카드의 알파벳을 반환하는 함수를 완성하세요. (1 <= N <= 26)
// ex1) mixAlphabet(1) == “c”
// ex2) mixAlphabet(3) == “g”

import java.util.*

//queue 라는 빈 linkedlist를 만든다
//a~z까지 queue에 반복해서 큐로 넣는다
//1부터 input만큼 반복해서 동작시키고, n은 1~26까지 범위 (a~z가 26개여서 굳이 설정x)
//queue의 첫번째 순서 알파벳 버린다
//queue의 다음 두번째 순서 알파벳은 마지막 순서에 넣는다
//동작 이후 queue의 제일 앞 알파벳 반환

fun mixAlphabet(input: Int): Char {
    val queue: LinkedList<Char> = LinkedList()
    for (char in 'a'..'z') {
        queue.add(char)
    }

    for (i in 1..input) {
        queue.poll()
        val second = queue.poll()
        queue.offer(second)
    }
    return queue.peek()
}

println(mixAlphabet(1))
println(mixAlphabet(2))
println(mixAlphabet(3))
//    ...
println(mixAlphabet(25))
println(mixAlphabet(26)) //queue 요소가 이제 없어서 출력x
