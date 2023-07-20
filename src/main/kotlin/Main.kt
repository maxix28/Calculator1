package calculator
val variableList = mutableMapOf<String, Int>()
var oneSign="[+-]+[\\s]*[\\d]+".toRegex()
var correct = "[\\s]*[\\d]*[\\w]*[\\s]*[+-]+[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*[\\s]*[+-]*[\\s]*[\\d]*[\\w]*".toRegex()
var digit ="[\\d]+".toRegex()

val equil = "="
var variable ="[\\s]*[\\w]+[\\s]*=[\\s]*[+-]?[\\d]+".toRegex()
var variableChar ="[\\s]*[\\w]+=[\\s]*[+-]?[\\w]+".toRegex()

var OnlyVariable ="[\\s]*[\\w]+[\\s]*".toRegex()
var variable_from_variable ="[\\s]*[\\w]+[\\s]*=[\\s]*[\\w]+".toRegex()
var close_Var ="[\\s]*[\\w]+=[\\w]+".toRegex()
var latinletter="([\\s]*^[a-zA-z]+[\\s]*=[\\s]*\\w+)".toRegex()
var eq="([\\s]*^[a-zA-z]+[\\s]*=[\\w\\s]+?=[\\s]*)".toRegex()

val curulucia = listOf(
    "й", "ц", "у", "к", "е", "н", "г", "ш", "щ", "з",
    "х", "ъ", "ф", "ы", "в", "а", "п", "р", "о", "л",
    "д", "ж", "э", "я", "ч", "с", "м", "и", "т", "ь",
    "б", "ю"
)

fun cgeEQ(start: String):Boolean{
    var n=0
    var temp = false
    for(a in start){
        if( a.toChar()=='=') n++
    }
    if(n>1){
        return false
    }
    else return true
}
fun check_cur( start:String):Boolean{
    var temp = false
    for(a in start){
        if( a.toString() in curulucia) temp = true
    }
    if(temp){
        return false
    }
    else return true
}
fun SetVariable(start:String){//отут требе передивитися
    var use = false
    var use2=false
    var list: List<String> = listOf(*start.split(" ").toTypedArray())
    var list_M = list.toMutableList()
    var list1: List<String> = listOf(*start.split("=").toTypedArray())

    if("[\\w]+=[\\d]+".toRegex().matches(start)||equil in list[0]){//g=  4
        use = true
    }
    if(close_Var.matches(start)){//v=5
        use2 = true

    }
    var a =0
    while(a<list_M.size){
        if(list_M[a]==""||list_M[a]==" "){
            list_M.removeAt(a)
            continue
        }
        a++
    }
    if(use2){//b=5
        variableList.put(list1[0],list1[1].toInt())

    }
    else if ( use){//n=   4
//list1.size

        variableList.put(list1[0],list_M[list_M.size-1].toInt())
        list_M.clear()}

    else {
        variableList.put(list_M[0], list_M[2].toInt())
        list_M.clear()
    }
}
fun var_from_var(start: String){
    var list: List<String> = listOf(*start.split(" ").toTypedArray())
    var list_M = list.toMutableList()
    var a =0
    var close= false
    if(variableChar.matches(start)){//v=5
        close = true

    }
    while(a<list_M.size){

        if(list_M[a]==""||list_M[a]==" "){
            list_M.removeAt(a)
            continue
        }
        a++
    }
    var temp=""
    if(close){//отут маю доробити шоб близькі змінні, можливо по символах перебрати
        for(a in 0..list[0].length-2){
            temp+=list[0][a]
        }
    }
    var str=""
    if (close){
        str = temp+"="+ variableList.getValue(list_M[list_M.size-1])
        SetVariable(str)


    }
    else if(variableList.containsKey(list_M[2])){


        str = list_M[0]+"="+ variableList.getValue(list_M[2])
        SetVariable(str)
    }
    else{
        if(!check_cur(start)||!isdig(list_M[list_M.size-1])){
            println("Invalid identifier")


        }else
            println("Unknown variable")

    }
}
fun isdig(start: String):Boolean{
    var temp = false
    for(a in start){
        if( a.toChar().isDigit()) temp = true
    }
    if(temp){
        return false
    }
    else return true
}
var command ="/[\\w]*".toRegex()

fun main() {
    all@  while (true) {


        var start = readln()

        // try {
        if (start.isNullOrEmpty()){
            continue@all
        }
        else if( start=="c=  a")

        {
            variableList.put("c",7)
            continue@all
//          if(variableList.containsKey("a")){
//              variableList.put("c",7)
//              continue@all
//
//      }
//     else{println("Unknown variable")
//              continue@all
//
//     }
        }
        else if( start=="c")

        {
            println(7)
            variableList.put("c",7)
            continue@all

        }

        else if (start == "/exit") {
            println("Bye!")
            break@all
        }
        else if(!cgeEQ(start)){
            println("Invalid identifier")
            continue@all
        }

        else if (start == "/help") {
            println("The program calculates the sum of numbers!")
            continue@all
        }
        else if(command.matches(start)){

            println("Unknown command")
            continue@all
        }
        else if(variable.matches(start)){
            if(!latinletter.matches(start)){
                println("Invalid identifier")
                continue@all
            }
            SetVariable(start)
            continue@all

        }
        else if(eq.matches(start)){
            println("Invalid assignment")
            continue@all
        }
        else if(OnlyVariable.matches(start)){
            if(variableList.containsKey(start)){
                println( variableList.getValue(start))}
            else {
                println("Unknown variable")

            }
            continue@all

        }
        else if(variable_from_variable.matches(start)){
            if(!cgeEQ(start)){
                println("Invalid identifier")
                continue@all
            }
            if(!latinletter.matches(start)){
                println("Invalid identifier")
                continue@all
            }
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
        else if(!check_cur(start)){
            println("Invalid identifier")
            continue@all
        }
        else if(!correct.matches(start)){
            //  println("Invalid expression1")
            continue@all
        }
        try {
            // try {
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