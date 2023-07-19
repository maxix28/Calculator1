package calculator
val variableList = mutableMapOf<String, Int>()
var oneSign="[+-]+[\\s]*[\\d]+".toRegex()
var correct = "[\\s]*[\\d]*[\\w]*[\\s]*[+-]+[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*".toRegex()
var digit ="[\\d]+".toRegex()


var variable ="[\\s]*[\\w]+[\\s]*=[\\s]*[+-]?[\\d]+".toRegex()
var OnlyVariable ="[\\s]*[\\w]+[\\s]*".toRegex()
var variable_from_variable ="[\\s]*[\\w]+[\\s]*=[\\s]*[\\w]+".toRegex()


fun SetVariable(start:String){

    var list: List<String> = listOf(*start.split(" ").toTypedArray())
    var list_M = list.toMutableList()
    if("[\\w]+=[\\d]+".toRegex().matches(start)){
        var list1: List<String> = listOf(*start.split("=").toTypedArray())
        variableList.put(list1[0],list1 [1].toInt())
        return
    }

    var a =0
    while(a<list_M.size){
        if(list_M[a]==""||list_M[a]==" "){
            list_M.removeAt(a)
            continue
        }
        a++
    }


    variableList.put(list_M[0],list_M[2].toInt())
    list_M.clear()

}
fun var_from_var(start: String){
    var list: List<String> = listOf(*start.split(" ").toTypedArray())
    var list_M = list.toMutableList()
    var a =0
    while(a<list_M.size){
        if(list_M[a]==""||list_M[a]==" "){
            list_M.removeAt(a)
            continue
        }
        a++
    }
    if(variableList.containsKey(list_M[2])){
        var str = list_M[0]+"="+ variableList.getValue(list_M[2])
        SetVariable(str)
    }
    else{
        println("Unknown variable")
        return
    }
}
var command ="/[\\w]*".toRegex()
fun main() {
    all@  while (true) {

        var start = readln()
        if (start.isNullOrEmpty()){
            continue@all
        }
        else if (start == "/exit") {
            println("Bye!")
            break@all
        }

        else if (start == "/help") {
            println("The program calculates the sum of numbers!")
            continue@all
        }
        else if(command.matches(start)){
            println("Unknown command")
            continue@all
        }
        if(variable.matches(start)){
            SetVariable(start)
            continue@all

        }
        if(OnlyVariable.matches(start)){
            println( variableList.getValue(start))
            continue@all

        }
        if(variable_from_variable.matches(start)){
            var_from_var(start)
            continue@all

        }


        else if(oneSign.matches(start)){
            if (start[0]=='+'){
                for ( s in 1 ..start.length-1){
                    print(start[s])
                    continue@all
                }
            }
            println(start)
            continue@all
        }
        else if(digit.matches(start)){
            println(start)
            continue@all
        }
        else if(!correct.matches(start)){
            println("Invalid expression")
            continue@all
        }

        try {
            var list: List<String> = listOf(*start.split(" ").toTypedArray())

            var mount=0
            var list_M = list.toMutableList()

            var a =0
            while(a<list_M.size){
                if(list_M[a]==""||list_M[a]==" "){
                    list_M.removeAt(a)
                    continue
                }
                if(variableList.containsKey(list_M[a])){
                    list_M[a]=variableList.getValue(list_M[a]).toString()
                    a++
                    continue
                }
                if(list_M[a]=="--"||list_M[a]=="----"){
                    list_M[a]="+"
                    a++
                    continue
                }
                if(list_M[a]=="---"){
                    list_M[a]="-"
                    a++
                    continue
                }
                if("[+]+".toRegex().matches(list_M[a])){
                    list_M[a]="+"
                    a++
                    continue
                }
                a++
            }
            var positive= true
            math@ for(a in 0.. list_M.size-1){
                if (a!=0) {
                    if (list_M[a - 1] == "-") {
                        positive = false
                    } else if(list_M[a - 1] == "+"){
                        positive = true
                    }
                }
                if(list_M[a]!="+"&&list_M[a]!="-"){
                    if(positive==true)   mount+=list_M[a].toInt()
                    else mount-=list_M[a].toInt()
                }
            }
            println(mount)
        }
        catch (e:Exception){
            println(start)
        }
    }
}

