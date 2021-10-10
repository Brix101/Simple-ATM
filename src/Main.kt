import java.text.DecimalFormat

var isNum = false
var pin:Int = 0
val df = DecimalFormat("#.##")

data class Customer(var pin: Int = 0, var name: String= "", var balance: Double =0.00)

object CustomerList {
    var customerList = listOf(
        Customer(1010,"Brixter Porras",5000.00),
        Customer(2020,"Jane Doe" ,76000.00),
        Customer(3030,"John Doe" ,24000.00),
    )
}

fun List<Customer>.filterByPin(pin: Int) = this.filter { it.pin == pin }

fun main(args: Array<String>) {
    println("Simple ATM")
    while (!isNum){
        try{
            if(pin==0){
                println("Enter User Pin :")
                pin = Integer.valueOf(readLine())
            }else {
                val user = CustomerList.customerList.filterByPin(pin)
                if(user.last()!=null){
                    toPrint()
                    val operation = Integer.valueOf(readLine())
                    performOp(operation,user.last())
                }
            }
        }catch(e: Exception){
            pin = 0
            println("User Not Found")
        }
    }
}

fun toPrint(){
    println("Choose the operation you want to perform:")
    println(" 1: Withdraw | 2: Deposit | 3: Balance | 4: Send | 5: Exit ")
}

fun performOp(op: Any,user: Customer){
    when(op){
        1 -> withdraw(user)
        2 -> deposit(user)
        3 -> println("Your Balance : ${checkBalance(user)}")
        4 -> println("sent money")
        5 -> {
            println("Exiting...")
            if(pin==0){
                isNum = true
            }else{
                pin = 0
            }
        }
    }
}

fun checkBalance(user: Customer): Any {
    return df.format(user.balance).toDouble()
}

fun withdraw(user: Customer) {
    println("Input Amount to Withdraw :")
    var isAmount = false
    while (!isAmount) {
        try {
            val amount = readLine()
            val money = user.balance
            if(money >= (amount?.toDouble()!!)){
                val balance = money - (amount.toDouble())
                user.balance = balance
                println("Withdraw Successfully")
                println("Your Balance :${balance}")
                isAmount = true
            }else{
                println("Please Input Amount Lower Than Your Balance :")
            }

        } catch (e: Exception) {
            println("Please Input Amount :")
        }
    }
}

fun deposit(user: Customer){
    println("Input Amount to Deposit :")
    var isAmount = false
    while (!isAmount) {
        try {
            val amount = readLine()
            val money = user.balance
            val balance = money + (amount?.toDouble()!!)
            user.balance = balance
            println("Deposit Successfully")
            println("Your Balance :${balance}")
            isAmount = true
        }catch (e: Exception){
            println("Please Input Amount :")
        }
    }
}

fun sendMoney(user: Customer){

}




