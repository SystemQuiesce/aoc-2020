import java.io.File

var breakFlag = false
var theAnswer = 0
var array_index = 0
var array_of_numbers = Array<Int>(200){0}

class Cup(value: Int){
    var value:Int = value
    var next: Cup? = null
    var previous:Cup? = null
}

class ClockwiseCircle {
    var firstCup:Cup? = null
    var destinationCup:Cup? = null

    fun add(value: Int) {
        var newCup = Cup(value)
        var destCup = destinationCup
        println(value)
        if (destCup == null){
            newCup.previous = newCup
            newCup.next = newCup
            firstCup = newCup
            destinationCup = newCup
        }
        else {
            newCup.previous = destCup
            newCup.next = destCup.next

            destCup.next?.previous = newCup
            destCup.next = newCup
            

            destinationCup = newCup
        }
    }

    override fun toString(): String{
        var str = firstCup?.value.toString()
        var runningPointer = firstCup?.next

        while (runningPointer != currentCup) {
            str += runningPointer?.value.toString()
            runningPointer = runningPointer?.next
        }
        return str
    }
}

var input = File("./day23_input.txt").readText()

// init the clockwiseCircle

var clockwiseCircle = ClockwiseCircle()
input.forEach {clockwiseCircle.add(it.toInt())}

println(clockwiseCircle.toString())


// rotate

var currentCup = clockwiseCircle!!.firstCup
var selectedCup1 = currentCup?.next
var selectedCup2 = selectedCup1?.next
var selectedCup3 = selectedCup2?.next

var destValue = (currentCup!!.value - 1)

println("hier")
println(currentCup)
println(destValue)
