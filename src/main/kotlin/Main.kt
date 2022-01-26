package converter

import java.math.BigInteger

const val FIRST_MSG = "Enter two numbers in format: {source base} {target base} (To quit type /exit)"
const val SECOND_MSG = "Enter number in base %d to convert to base %d (To go back type /back)"

fun main() {

    while (true) {
        println(FIRST_MSG)
        when (val firstAnswer = readLine()!!.lowercase()) {
            "/exit" -> break
            else -> {
                val (source, target) = firstAnswer.split(" ", limit = 2).map { it.toInt() }
                while (true) {
                    println(SECOND_MSG.format(source, target))
                    when (val secondAnswer = readLine()!!.lowercase()) {
                        "/back" -> break
                        else -> println("Conversion result: ${BigInteger(secondAnswer, source).toString(target)}")
                    }
                }
            }
        }
    }
}