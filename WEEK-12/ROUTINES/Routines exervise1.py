# HandleStuff_starter.py
# Messy routine for the refactoring exercise.
# Contains multiple design issues: poor cohesion, bad names,
# magic numbers, unused parameters, bugs, missing error handling.
# DO NOT fix the bugs in this file – you will create a refactored version.

# Simulated global/external data (do not modify)
corpExpense = [[0.0] * 100 for _ in range(4)]   # 4 quarters, 100 days
profit = [0.0] * 12
class ExpenseData:
    def __init__(self):
        self.type1 = [0.0] * 12
        self.type2 = [0.0] * 12
        self.type3 = [0.0] * 12
expense = ExpenseData()
SUCCESS = 1

months = 12

class InputRecord:
    def __init__(self):
        self.revenue = [0.0] * 100
        self.expense = [0.0] * 100

def UpdateCorpDatabase(empRec):
    pass
    
def EstimateRevenue(ytdRevenue,crntQtr):
    estimRevenue = ytdRevenue * 4.0 / crntQtr
    pass

def Inputrevenue(inputRec,crntQtr):
    i = 0
    for i in range(100):
        inputRec.revenue[i] = 0
        inputRec.expense[i] = corpExpense[crntQtr][i]
    pass


def CalculateProfit(inputRec,expenseType):
    if expenseType == 1:
        for i in range(months):                      
            profit[i] = inputRec.revenue[i] - expense.type1[i]   
            
    elif expenseType == 2:
        for i in range(months):   
            profit[i] = inputRec.revenue[i] - expense.type2[i]      
            
    elif expenseType == 3:
        for i in range(months):   
            profit[i] = inputRec.revenue[i] - expense.type3[i]       
    pass

if __name__ == "__main__":
    # Example (will fail with real data – for illustration only)
    rec = InputRecord()
    # HandleStuff(rec, 1, None, 0, 1000, 0, 0, 0, 0, 0, 1)
    print("Starter file loaded. Refactor the HandleStuff function.")