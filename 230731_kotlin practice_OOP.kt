open class Cafe(val name: String, val address: String, val monthly: Int, val menu: String) {
    fun introduce() {
        println("저희 $name 의 주소는 $address 이고, 월세는 $monthly 이고, 메뉴는 $menu 가 있습니다.")
    }

    open fun info() {}
}

open class Coffee(name: String, private val price: Int, val ingredient: String) :
    Cafe(name, "", 0, "") {
    override fun info() {
        println("$name 커피의 가격은 $price 이고, 재료는 $ingredient 입니다.")
    }
}

class Americano(name: String, price: Int, ingredient: String) : Coffee(name, price, ingredient)
class CaffeLatte(name: String, price: Int, ingredient: String) : Coffee(name, price, ingredient)

open class Tea(name: String, private val price: Int, val ingredient: String) :
    Cafe(name, "", 0, "") {
    override fun info() {
        println("$name 차의 가격은 $price 입니다.")
    }
}

class GreenTea(name: String, price: Int, ingredient: String) : Tea(name, price, ingredient)
class IceTea(name: String, price: Int, ingredient: String) : Tea(name, price, ingredient)

val cafe = Cafe("AI카페", "서울시 노원구 OO로 123", 800000, "커피, 빵, 케이크")
cafe.introduce()

val americano = Americano("아메리카노", 4000, "커피원두, 물")
val caffeLatte = CaffeLatte("카페라떼", 5000, "커피원두, 우유")
val greenTea = GreenTea("그린티", 3000, "녹차, 물")
val iceTea = IceTea("아이스티", 3500, "차, 얼음")

americano.info()
caffeLatte.info()
greenTea.info()
iceTea.info()
