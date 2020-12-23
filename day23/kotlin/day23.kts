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
    var currentCup:Cup? = null
    var destinationCup:Cup? = null

    fun add(value: Int) {
        var newCup = Cup(value)
        if (destinationCup == null){
            newCup.previous = newCup
            newCup.next = newCup
            currentCup = newCup
            destinationCup = newCup
        }
        else {
            newCup.previous = destinationCup
            newCup.next = destinationCup.next

            destinationCup.next.previous = newCup
            destinationCup.next = newCup
            

            destinationCup = newCup
        }
    }


}

var input = File("./day23_input.txt").readText()

// init the clockwiseCircle

input.forEach {clockwiseCircle.add(it)}


println(clockwiseCircle)