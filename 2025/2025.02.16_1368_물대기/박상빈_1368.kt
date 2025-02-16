import java.io.*
import java.math.BigInteger
import java.util.*
import kotlin.math.*

val reader = BufferedReader(InputStreamReader(System.`in`))
val writer = BufferedWriter(OutputStreamWriter(System.out))

data class Data (
    val from: Int,
    val to: Int,
    val cost: Int
) : Comparable<Data> {
    override fun compareTo(other: Data) : Int {
        return this.cost.compareTo(other.cost)
    }
}

val unf = IntArray(301)
val umul = IntArray(301)

var connected = 0
var ans = BigInteger.ZERO

fun init(n: Int)
{
    for(i in 0 .. n) unf[i] = i
}

fun find(tar: Int) : Int
{
    if (unf[tar] == tar) return unf[tar]
    unf[tar] = find(unf[tar])
    return unf[tar]
}

fun union(A: Int, B:Int, cost: Int)
{
    val a = find(A)
    val b = find(B)

    if (a == b) return

    if (a < b) unf[b] = a
    else unf[a] = b

    connected++
    ans += cost.toBigInteger()
}

fun main()
{
    val pq = PriorityQueue<Data>()

    val n = reader.readLine()!!.toInt()

    for(i in 1 .. n)
    {
        val umulcost = reader.readLine()!!.toInt()
        umul[i] = umulcost
        pq.add(Data(0, i, umulcost))
    }

    for(i in 1 .. n)
    {
        val arr = reader.readLine()!!.split(" ").map { it.toInt() }
        for(j in 1 .. n)
        {
            if (i == j) continue
            pq.add(Data(i, j, arr[j - 1]))
        }
    }

    init(n)

    while(!pq.isEmpty())
    {
        if (connected == n) break

        val now = pq.poll()

        union(now.from, now.to, now.cost)
    }

    println(ans)
}