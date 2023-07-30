// 주어진 문자열 뒤집기 (stack 활용)
// 문자열 input의 각 문자를 뒤집어서 반환하는 함수를 완성하세요. (스택 활용)
// ex1) reverseWord(“hi”) == “ih”
// ex2) reverseWord(“easy”) == “ysae”


//일단 빈 stack 만들고
//stack에 char 형태로 반복해서 push로 넣고
//reverse 라는 변수 빈 값 만들고
//reverse에 stack에서 pop으로 뺀거 다시 넣는다

import java.util.Stack

fun reverseWord(input: String): String {
    val stack: Stack<Char> = Stack()
    for (char in input) {
        stack.push(char)
    }
    var reverse = ""
    for (i in 0..stack.size-1) {      //reverse에 stack요소 갯수만큼 stack에서 pop으로 반복해서 빼서 넣는다
        reverse += stack.pop()
    }
    return reverse
}

println(reverseWord("hi"))   // ih
println(reverseWord("easy"))  // ysae

// stack 개념 정리 (후입선출)
// stack.push : 스택 맨 위 요소 넣는다
// stack.pop : 스택 맨 위 요소 제거
// stack.peek : 스택 맨 위 요소 제거하지 않고 반환 (조회 개념)
