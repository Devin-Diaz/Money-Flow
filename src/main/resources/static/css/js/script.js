


function checkBudget(userBudget) {
    if(userBudget == null || userBudget <= 0) {
        alert("BUDGET MUST BE GREATER THAN $0 TO BEGIN RECORDING EXPENSES!")
        console.error("Budget is null or less than or equal to 0.")
        return false;
    }
    console.log('HELLO WORLD!');
    return true;
}
