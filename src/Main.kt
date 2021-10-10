import java.text.DecimalFormat

var isNum = false
var pin:Int = 0
val df = DecimalFormat("#.##")

data class Customer(var accountNumber:Int = 0,var pin: Int = 0, var name: String= "", var balance: Double =0.00)

object CustomerList {
    var customerList = listOf(
        Customer(12345,1010,"Brixter Porras",5000.00),
        Customer(23456,2020,"Jane Doe" ,76000.00),
        Customer(34567,3030,"John Doe" ,24000.00),
    )
}

fun List<Customer>.filterByPin(pin: Int) = this.filter { it.pin == pin } //filter and find Customer By Pin
fun List<Customer>.filterByAccountNum(accountNumber: Int) = this.filter { it.accountNumber == accountNumber } //filter and find Customer By Account Number

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
                    try {
                        toPrint()
                        val operation = Integer.valueOf(readLine())
                        performOp(operation,user.last())
                    }catch (e: Exception){
                        println("Please Select On Choices")
                    }
                }
            }
        }catch(e: Exception){
            pin = 0
            println("User Not Found")
        }
    }
}
//Choices
fun toPrint(){
    println("Choose the operation you want to perform:")
    println(" 1: Withdraw | 2: Deposit | 3: Balance | 4: Send | 5: Exit ")
}
//Operatiun to Perform
fun performOp(op: Any,user: Customer){
    when(op){
        1 -> withdraw(user)
        2 -> deposit(user)
        3 -> println("Your Balance :₱ ${checkBalance(user)}")
        4 -> sendMoney(user)
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
//Check Customer Balance
fun checkBalance(user: Customer): Any {
    return df.format(user.balance).toDouble()
}
//Customer Withdraw
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
                println("₱ $amount withdraw Successfully")
                println("Your Balance :₱ $balance")
                isAmount = true
            }else{
                println("Please Input Amount Lower Than Your Balance :")
            }
        } catch (e: Exception) {
            println("Please Input Amount :")
        }
    }
}
//Function To Deposit
fun deposit(user: Customer){
    println("Input Amount to Deposit :")
    var isAmount = false
    while (!isAmount) {
        try {
            val amount = readLine()
            val money = user.balance
            val balance = money + (amount?.toDouble()!!)
            user.balance = balance
            println("₱ $amount Deposit Successfully")
            println("Your Balance :₱ $balance")
            isAmount = true
        }catch (e: Exception){
            println("Please Input Amount :")
        }
    }
}
//Function To Send Money
fun sendMoney(user: Customer){
    var isAccountNumber = false
    var accountNumber: Int = 0
    var accountName: String = ""
    val money = user.balance
    while (!isAccountNumber) {
        try {
            if(accountNumber==0){
                println("Input Account Number of Receiver")
                accountNumber = Integer.valueOf(readLine())
            }else {
                val user2 = CustomerList.customerList.filterByAccountNum(accountNumber).last()
                if(accountName==""){
                    println("Input Account Name of Receiver")
                    accountName = readLine().toString()
                }
                else{
                    if (user2.name.equals(accountName, ignoreCase = true)){
                        try {
                            println("Input Amount to send to ${user2.name}")
                            val amount = readLine()

                            if(money >= (amount?.toDouble()!!)){
                                val balance = money - (amount.toDouble())
                                user2.balance = user2.balance + amount.toDouble()
                                user.balance = balance
                                println("₱ $amount Transfer to ${user2.name} Successfully")
                                println("Your Balance :₱ $balance")
                                isAccountNumber = true
                            }else{
                                println("Please Input Amount Lower Than Your Balance :")
                            }
                        }catch (e: Exception){
                            println("Please Input A Number")
                        }
                    }else{
                        println("Account Name of ${user2.accountNumber} is Incorrect")
                        accountName=""
                    }
                }
            }
        }catch (e: Exception){
            println("User Not Found")
            accountNumber=0
        }
    }
}




