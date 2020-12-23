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

    fun remove(delCup: Cup) {
        delCup.previous?.next = delCup.next
        delCup.next?.previous = delCup.previous
    }

    override fun toString(): String{
        var str = firstCup?.value.toString()
        var runningPointer = firstCup?.next

        while (runningPointer != firstCup) {
            str += runningPointer?.value.toString()
            runningPointer = runningPointer?.next
        }
        return str
    }
}

var input = File("./day23_input.txt").readText()

// init the clockwiseCircle

var clockwiseCircle = ClockwiseCircle()
input.forEach {clockwiseCircle.add(it.toString().toInt())}

println(clockwiseCircle.toString())


// rotate
 
var currentCup = clockwiseCircle.firstCup

for (i in 1..10)
{
    var selectedCup1 = currentCup?.next
    var selectedCup2 = selectedCup1?.next
    var selectedCup3 = selectedCup2?.next

    clockwiseCircle.remove(selectedCup1!!)
    clockwiseCircle.remove(selectedCup2!!)
    clockwiseCircle.remove(selectedCup3!!)

    var destValue = currentCup?.value
    destValue = destValue?.minus(1)

    while (destValue == selectedCup1?.value || destValue == selectedCup2?.value || destValue == selectedCup3?.value ){
        destValue = destValue?.minus(1)
        println(destValue)
    }

    var runningPointer = clockwiseCircle.firstCup

    runningPointer = runningPointer?.next

    while (runningPointer != clockwiseCircle.firstCup) {
        if (runningPointer?.value == destValue ) {
            clockwiseCircle.destinationCup = runningPointer
        }
        runningPointer = runningPointer?.next
    }

    clockwiseCircle.add(selectedCup1?.value!!)
    clockwiseCircle.destinationCup = clockwiseCircle.destinationCup?.next
    clockwiseCircle.add(selectedCup2?.value!!)
    clockwiseCircle.destinationCup = clockwiseCircle.destinationCup?.next
    clockwiseCircle.add(selectedCup3?.value!!)


    println(clockwiseCircle.toString())

    currentCup = currentCup?.next
}