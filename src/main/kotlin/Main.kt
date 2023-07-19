package calculator

var oneSign="[+-]+[\\s]*[\\d]+".toRegex()
var correct = "[\\d]*[\\s]*[+-]+[\\s]*[\\d]+[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*[\\s]*[+-]*[\\s]*[\\d]*".toRegex()
var digit ="[\\d]+".toRegex()

var command ="/[\\w]*".toRegex()
fun main() {
    all@  while (true) {

        var start = readln()
        if (start.isNullOrEmpty()){
            continue@all
        }
        if (start == "/exit") {
            println("Bye!")
            break@all
        }

        if (start == "/help") {
            println("The program calculates the sum of numbers!")
            continue@all
        }

        if(command.matches(start)){
            println("Unknown command")
            continue@all
        }

        if(oneSign.matches(start)){
            if (start[0]=='+'){
                for ( s in 1 ..start.length-1){
                    print(start[s])
                    continue@all
                }
            }
            println(start)
            continue@all
        }
        if(digit.matches(start)){
            println(start)
            continue@all
        }
        if(!correct.matches(start)){
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

