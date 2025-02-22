import java.io.*
import java.math.BigInteger
import java.util.*
import kotlin.math.*

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

val prefixsum = Array<BigInteger>(10000001) { BigInteger.ZERO }

fun main() {
    val (n, m) = reader.readLine()!!.split(" ").map { it.toInt() }

    val arr = reader.readLine()!!.split(" ").map { it.toBigInteger() }

    prefixsum[0] = arr[0]
    for(i in 1 until n) prefixsum[i] = prefixsum[i - 1] + arr[i]

    val map = hashMapOf<Int, Int>()
    var ans = BigInteger.ZERO
    for(i in 0 until n)
    {
        val res = prefixsum[i] % m.toBigInteger()
        if (res == BigInteger.ZERO)
        {
            map[0] = map.getOrDefault(0, 0) + 1
            ans += map[0]!!.toBigInteger()
        }
        else
        {
            ans += map.getOrDefault(res.toInt(), 0).toBigInteger()
            map[res.toInt()] = map.getOrDefault(res.toInt(), 0) + 1
        }
    }

    println(ans)
}